package com.pr.pr.pantryraid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pr.pr.pantryraid.RoomPersist.AppDatabase;
import com.pr.pr.pantryraid.RoomPersist.RecipeRepository;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nam on 4/30/2018.
 */

public class homePage extends Fragment
{
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";
    private List<recipe> recipeList = new ArrayList<>();
    private List<recipe> homeRecipeList = new ArrayList<>();
    private RecyclerView rv;
    private home h = new home(KEY);


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {

        final View rootView = inflater.inflate(R.layout.homepage, container, false);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(getDailyRecipes(), 0, 24, TimeUnit.HOURS);


        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);



//        getAllRecipesFromDB();
//        System.out.println("In on create recipelist size: " + recipeList.size());
//        try {
//            if(recipeList.size() < 0)
//            {
//                getHomeRecipes();
//            }
//            else
//            {
//                homeRecipeList = recipeList;
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        System.out.println(recipeList.size());

        return rootView;
    }

    public void onStart()
    {
        super.onStart();
        recipeRVAdapter adapter = new recipeRVAdapter(homeRecipeList);
        rv.setAdapter(adapter);

    }

//    public void getHomeRecipes() throws InterruptedException, JSONException {
//        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
//        RecipeRepository dbI = new RecipeRepository(mdb);
//        //Delete old home page recipes if new ones are added
//        System.out.println("In get home recipes");
//
//
//        System.out.println("recipe list size = " + recipeList.size());
//        recipeList = h.randomRecipe(false, 5, null);
//        while(recipeList.size() == 0)
//        {
//            Thread.sleep(5000);
//        }
//        System.out.println("After sleeping recipelist : " + recipeList.size());
//        dbI.insertRecipeList(recipeList);
//        homeRecipeList = recipeList;
//
////        if(homeRecipes.size() == 0)
////        {
////            System.out.println("homerecipes size = 0");
////            getDailyRecipes().run();
////            dbI.insertRecipeList(recipeList);
////        }
////        else
////        {
////            System.out.println("homerecipes size !!!!!!= 0");
////            recipeList = homeRecipes;
////        }
//    }

    //make new list for all recipes and home recipes and pull from database method
//    public void getAllRecipesFromDB()
//    {
//        System.out.println("In get home recipes from DB");
//        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
//        RecipeRepository dbI = new RecipeRepository(mdb);
//        dbI.getAllRecipes();
//        List<recipe> allRecipes = dbI.getRecipes();
//        if(allRecipes != null)
//        {
//           recipeList = allRecipes;
//        }
//        System.out.println("recipes size:" + recipeList.size());
//    }

    public void removeOldHomeRecipes()
    {
        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
        RecipeRepository dbI = new RecipeRepository(mdb);
        dbI.getAllRecipes();
        List<recipe> allRecipes = dbI.getRecipes();
        if(allRecipes != null)
        {
            for(int i = 0; i < allRecipes.size(); i++)
            {
                if(allRecipes.get(i).homePage)
                {
                    dbI.removeRecipe(allRecipes.get(i));
                }
            }
        }

    }

    public Runnable getDailyRecipes() {
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                try {

                    recipeList = h.randomRecipe(false, 5, null);
                    System.out.println("in getDailyRecipes, recipeslist size: " + recipeList.size());
                    //gets correct recipe, need to store it
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        };
        return task;
    }


}
