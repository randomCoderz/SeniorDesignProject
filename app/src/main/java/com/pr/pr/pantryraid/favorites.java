package com.pr.pr.pantryraid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Nam on 4/27/2018.
 */

public class favorites extends Fragment
{
    private boolean favorite;
    private CheckBox checkBox;

    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";

    private List<recipe> recipeList;
    private RecyclerView rv;
    private home h = new home(KEY);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.favorite, container, false);

        //RVAdapter for recipe cards
        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        try {
            recipeList = h.randomRecipe(false, 5, null);
            recipeFavoriteRVAdapter adapter = new recipeFavoriteRVAdapter(recipeList);
            rv.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rootView;
    }
}
