package com.pr.pr.pantryraid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.pr.pr.pantryraid.RoomPersist.AppDatabase;
import com.pr.pr.pantryraid.RoomPersist.IngredientRepository;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class myPantry extends Fragment{
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";

    //declaration
    ImageButton searchButton;
    Button RecipeButton;
    EditText searchPantry;


    pantry p = new pantry(KEY);
    AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
    IngredientRepository pbI = new IngredientRepository(mdb);

    private ArrayList<ingredient> pantryList = new ArrayList<ingredient>();
    private RecyclerView rv;


    // This will make it so that when you search for ingredients it will filter the list.
    String[] items;
    ArrayAdapter<String> adapter;

    public myPantry(){

    }

    // linking the UI with jave

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        final View rootView = inflater.inflate(R.layout.my_pantry, container,false);


        searchButton = rootView.findViewById(R.id.searchButton);
//        searchPantry = rootView.findViewById(R.id.searchPantry);
        RecipeButton = rootView.findViewById(R.id.bttnRecipe);

        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);


        List<ingredient> list;
        initializeList();
//        pantryList.add();
        myPantryAdapter adapter = new myPantryAdapter(pantryList);
        rv.setAdapter(adapter);

        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        listItems.add(new ingredient(1, "ah", "fef", "", " ", 0, false, false));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ingredient> list;
                String ingredientString = searchPantry.getText().toString();
                try {
                    list = p.searchIngredient(null, true, 1, ingredientString);

                    ingredient ing = list.get(0);
                    ing.pantry = true;
                    pbI.insertIngredient(ing);

                    rootView.clearFocus();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        RecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ingredient> toSearch = new ArrayList<ingredient>();
                for(int i = 0; i < pantryList.size(); i++)
                {
                    if(pantryList.get(i).selected)
                    {
                        toSearch.add(pantryList.get(i));
                    }
                }



            }
        });

        return rootView;
        }


    private void initializeList() {

        pbI.getAllIngredients();
        ArrayList<ingredient> allIngredients = pbI.getIngredients();
        if(allIngredients != null)
        {
            for(int i = 0; i < allIngredients.size(); i++)
            {
                if(allIngredients.get(i).pantry)
                {
                    pantryList.add(allIngredients.get(i));
                }
            }
        }


    }


} // myPantry ends

