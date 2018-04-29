package com.pr.pr.pantryraid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class myPantry extends Fragment
{
    //declaration
    ImageButton searchButton;
    Button RecipeButton;
    EditText searchPantry;
    //ArrayList<String> listItems;
    private ArrayList<ingredient> pantryList = new ArrayList<ingredient>();
    private RecyclerView rv;

    // This will make it so that when you search for ingredients it will filter the list.
    String[] items;
    ArrayAdapter<String> adapter;

    public myPantry(){

    }

    // linking the UI with jave
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.my_pantry, container, false);

        searchButton = rootView.findViewById(R.id.searchButton);
        searchPantry = rootView.findViewById(R.id.searchPantry);
        RecipeButton = rootView.findViewById(R.id.bttnRecipe);

        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        getList();

        myPantryAdapter adapter = new myPantryAdapter(pantryList);
        rv.setAdapter(adapter);

        return rootView;
    }

    public void getList()
    {
        pantryList.add(new ingredient(0, "test", "fef", "", " ", 0, false, false));
        pantryList.add(new ingredient(1, "test1", "fef", "", " ", 0, false, false));
        pantryList.add(new ingredient(2, "test2", "fef", "", " ", 0, false, false));
    }

//    private void initializeList() {
//        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
//        IngredientRepository pbI = new IngredientRepository(mdb);
//
//
//    }

} // myPantry ends

