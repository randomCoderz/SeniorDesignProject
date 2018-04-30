package com.pr.pr.pantryraid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.List;

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

        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        try {
            recipeList = h.randomRecipe(false, 5, null);
            recipeRVAdapter adapter = new recipeRVAdapter(recipeList);
            rv.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rootView;
    }
}
