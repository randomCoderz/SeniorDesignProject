package com.pr.pr.pantryraid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        getHomeRecipes();

        recipeRVAdapter adapter = new recipeRVAdapter(recipeList);
        rv.setAdapter(adapter);

        return rootView;
    }

    public void getHomeRecipes()
    {

        //Delete old home page recipes if new ones are added
        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
        RecipeRepository dbI = new RecipeRepository(mdb);
        dbI.getAllRecipes();
        List<recipe> allRecipes = dbI.getRecipes();
        List<recipe> homeRecipes = new ArrayList<>();

        if(allRecipes != null)
        {
            System.out.println("-------" + allRecipes.size());

            for(int i = 0; i < allRecipes.size(); i++)
            {
                if(allRecipes.get(i).homePage)
                {
                    homeRecipes.add(allRecipes.get(i));
                }
            }
        }

        if(homeRecipes.size() < 5)
        {
            getDailyRecipes().run();
            homeRecipes = recipeList;
            dbI.insertRecipeList(homeRecipes);
        }
        System.out.println("+++++++" + homeRecipes.size());

        recipeList = homeRecipes;
    }

    public Runnable getDailyRecipes() {
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    recipeList = new ArrayList<>();
                    recipeList = h.randomRecipe(false, 5, null);
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
