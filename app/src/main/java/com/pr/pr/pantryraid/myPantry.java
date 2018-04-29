package com.pr.pr.pantryraid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    ListView pantryList;
    //ArrayList<String> listItems;
    ArrayList<ingredient> listItems = new ArrayList<>();
    private shoppingCartLVAdapter listAdapter;

    pantry p = new pantry(KEY);
    AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
    IngredientRepository pbI = new IngredientRepository(mdb);


    // This will make it so that when you search for ingredients it will filter the list.
    String[] items;

    ArrayAdapter<String> adapter;

    public myPantry(){

    }

    // linking the UI with jave
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        final View rootView = inflater.inflate(R.layout.my_pantry, container,false);

        searchButton = (ImageButton)rootView.findViewById(R.id.searchButton);
        searchPantry = (EditText)rootView.findViewById(R.id.searchPantry);
        pantryList = (ListView)rootView.findViewById(R.id.myPantry);
        RecipeButton = (Button)rootView.findViewById(R.id.bttnRecipe);


        pantryList = rootView.findViewById(R.id.myPantry);
        listAdapter = new shoppingCartLVAdapter(getActivity(),listItems);

        pantryList.setAdapter(listAdapter);



//        listItems.add(new ingredient(1, "ah", "fef", "", " ", 0, false, false));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ingredient> updatedList;
                String ingredientString = searchPantry.getText().toString();



            }
        });

        return rootView;
        }


    private void initializeList() {



    }



} // myPantry ends

