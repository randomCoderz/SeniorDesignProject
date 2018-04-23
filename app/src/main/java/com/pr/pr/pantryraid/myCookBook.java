package com.pr.pr.pantryraid;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONException;

import java.util.List;

public class myCookBook extends Fragment {
    //GUI
    Button favoriteRecipes;
    Button viewIngredients;
    Button shoppingCart;
    Button searchRicipe;

    //Cards
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";
    private List<recipe> recipeList;
    private RecyclerView rv;
    private home h = new home(KEY);

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.cookbook, container, false);

        rv = rootView.findViewById(R.id.rv  );

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        try {
            recipeList = h.randomRecipe(false, 2, null);
            recipeRVAdapter adapter = new recipeRVAdapter(recipeList);

            rv.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // linking buttons
        favoriteRecipes = (Button)rootView.findViewById(R.id.bttnFav);
        viewIngredients = (Button)rootView.findViewById(R.id.bttnIngredients);
        shoppingCart = (Button)rootView.findViewById(R.id.bttnShoppingCart);
        searchRicipe = (Button)rootView.findViewById(R.id.bttnSearchRecipe);


        //on click buttons redirect to other pages favoriteRecipes
        favoriteRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //might need to extends Activity in this class!!!
                // AND IMPORT    import android.app.Activity;

                /*Intent intent = new Intent(cookBook.this, pantry.class);
                Intent intent = new Intent(this, pantry.class);
                Intent intent = new Intent(this, pantry.class);
                startActivity(intent);*/
            }
        });

        //on click buttons redirect to other pages viewIngredients
        viewIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //on click buttons redirect to other pages shoppingCart
        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootView;
    }
}
