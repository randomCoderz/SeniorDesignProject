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
    private List<recipe> recipeList;
    private List<recipe> homeRecipeList;
    private RecyclerView rv;
    private home h = new home(KEY);


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {

        final View rootView = inflater.inflate(R.layout.homepage, container, false);
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(getDailyRecipes(), 0, 24, TimeUnit.HOURS);
        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
        RecipeRepository dbI = new RecipeRepository(mdb);

        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        getAllRecipesFromDB();
        if(recipeList == null)
        {
            try {
                recipeList = h.randomRecipe(false, 5, null);
                dbI.insertRecipeList(recipeList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return rootView;
    }

    public void onStart()
    {
        super.onStart();
        recipeRVAdapter adapter = new recipeRVAdapter(recipeList);
        rv.setAdapter(adapter);

    }

    //make new list for all recipes and home recipes and pull from database method
    public void getAllRecipesFromDB()
    {
        System.out.println("In get home recipes from DB");
        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
        RecipeRepository dbI = new RecipeRepository(mdb);
        dbI.getAllRecipes();
        List<recipe> allRecipes = dbI.getRecipes();
        if(allRecipes != null)
        {
            homeRecipeList = new ArrayList<>();
            for(int i = 0; i < allRecipes.size(); i++)
            {
                if(allRecipes.get(i).homePage)
                {
                    homeRecipeList.add(allRecipes.get(i));
                }
                System.out.println(allRecipes.get(i).name + " " + allRecipes.get(i).homePage);
            }
            recipeList = homeRecipeList;
        }
//        System.out.println("recipes size:" + recipeList.size());
    }

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

//    public Runnable getDailyRecipes() {
//        final Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    recipeList = h.randomRecipe(false, 5, null);
//                    System.out.println("in getDailyRecipes, recipeslist size: " + recipeList.size());
//                    //gets correct recipe, need to store it
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            };
//        };
//        return task;
//    }


}
