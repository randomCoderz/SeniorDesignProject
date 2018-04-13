package com.pr.pr.pantryraid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Kan on 2/22/18.
 */

public class recipe extends Fragment {
    int id;
    String name;
    String url;
    int readyInMinutes;
    private ingredientsLVAdapter listAdapter;
    ArrayList<ingredient> ingredients = new ArrayList<>();
    String instructions;
    ArrayList<step> analyzedInstructions;
    private ListView listView;
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";
    private home h = new home(KEY);

    public recipe() {

    }

    @SuppressLint("ValidFragment")
    public recipe(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @SuppressLint("ValidFragment")
    public recipe(int id, String name, String url)
    {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    @SuppressLint("ValidFragment")
    public recipe(int id, String name, String url, int readyInMinutes)
    {
        this.id = id;
        this.name = name;
        this.url = url;
        this.readyInMinutes = readyInMinutes;
    }


    @SuppressLint("ValidFragment")
    public recipe(int id, String name, String url, int readyInMinutes, ArrayList<
            ingredient > ingredients, String instructions)
    {
        this.id = id;
        this.name = name;
        this.url = url;
        this.readyInMinutes = readyInMinutes;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        {
            View rootView = inflater.inflate(R.layout.recipe_info, container, false);

//            List<recipe> list = null;
//            try {
//                list = h.randomRecipe(true, 1, null);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            recipe r = list.get(0);
//            ingredients = r.ingredients;


            listView = rootView.findViewById(R.id.ingredientList);
            listAdapter = new ingredientsLVAdapter(getActivity(),ingredients);
            listView.setAdapter(listAdapter);
            return rootView;
        }





    }
}
