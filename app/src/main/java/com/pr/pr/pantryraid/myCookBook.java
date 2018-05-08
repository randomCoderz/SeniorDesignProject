package com.pr.pr.pantryraid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.json.JSONException;

import java.util.List;

public class myCookBook extends Fragment {
    //GUI
    Button favoriteRecipes;
    Button viewIngredients;
    ImageButton shoppingCart;
    ImageButton searchRecipe;
    EditText recipeName;
    String stringRecipe;

    //Cards
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";
    private List<recipe> recipeList;
    private RecyclerView rv;
    private home h = new home(KEY);
    recipeRVAdapter adapter;
    recipeRVAdapter adapter2;

    private cookBook c = new cookBook(KEY);

    public myCookBook()
    {

    }
    @SuppressLint("ValidFragment")
    public myCookBook(List<recipe> recipeList)
    {
        this.recipeList = recipeList;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.cookbook, container, false);

        rv = rootView.findViewById(R.id.rv  );

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
/////////////////////////////////////
        if(recipeList != null)
            System.out.println(recipeList.size());
        adapter = new recipeRVAdapter(recipeList);
        rv.setAdapter(adapter);

        //this is for the keyboard to hide later.
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        searchRecipe = (ImageButton)rootView.findViewById(R.id.bttnSearchRecipe);
        recipeName = (EditText) rootView.findViewById(R.id.editText);


        searchRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<recipe> updatedList;
                stringRecipe = recipeName.getText().toString();
                try {
                    updatedList = c.searchRecipes(null,null,null,true,null,false,5,0,stringRecipe,null);

                    // adapter.recipeRVAdapterR(updatedList);
                    rootView.clearFocus();

                    //this is to hide the keyboard
                    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);


                    refresh(updatedList);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



        return rootView;
    }


    public void refresh(List<recipe> newlist){

        adapter.recipeRVAdapterRefresh(newlist);

        rv.setAdapter(adapter);

    }

}

