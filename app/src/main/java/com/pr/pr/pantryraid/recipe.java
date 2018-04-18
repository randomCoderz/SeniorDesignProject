package com.pr.pr.pantryraid;

import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.squareup.picasso.Picasso;
import android.content.Intent;



/**
 * Created by Kan on 2/22/18.
 */

public class recipe extends Fragment
{
    int id;
    String name;
    String url;
    int readyInMinutes;
    private ingredientsLVAdapter listAdapter;
    ArrayList<ingredient> ingredients = new ArrayList<>();
    String instructions;
    ArrayList<step> analyzedInstructions;
    private ListView listView;

    public recipe() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.recipe_info, container, false);

        listView = rootView.findViewById(R.id.ingredientList);
        listView.setAdapter(new ingredientsLVAdapter(getActivity(), ingredients));
        TextView recipeName = rootView.findViewById(R.id.recipeName);
        recipeName.setText(name);
        ImageView img = rootView.findViewById(R.id.recipeImage);
        img.getLayoutParams().width = 700;
        img.getLayoutParams().height = 700;
        Picasso.with(rootView.getContext()).load(url).into(img);
        TextView readyInMin = rootView.findViewById(R.id.readyInMin);
        readyInMin.setText("Ready in: " + readyInMinutes + " minutes");
        final Button instructions = rootView.findViewById(R.id.instructions);
        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String instr = "";
                for(int i = 0; i < analyzedInstructions.size(); i++)
                {

                    step x = analyzedInstructions.get(i);
                    instr += x.number + ". " + x.step_description + "\n";
                }
                Fragment fragment =  new instructions(instr);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, fragment).addToBackStack(null).commit();
            }
        });



        return rootView;
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
            ingredient > ingredients, ArrayList<step> analyzedInstructions, String instructions)
    {
        this.id = id;
        this.name = name;
        this.url = url;
        this.readyInMinutes = readyInMinutes;
        this.ingredients = ingredients;
        this.analyzedInstructions = analyzedInstructions;
        this.instructions = instructions;
    }

}
