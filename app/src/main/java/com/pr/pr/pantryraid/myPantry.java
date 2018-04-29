package com.pr.pr.pantryraid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    ListView pantryList;
    //ArrayList<String> listItems;
    ArrayList<ingredient> listItems = new ArrayList<>();
    private shoppingCartLVAdapter listAdapter;

    ////////////////
    int id;

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

        List<ingredient> list;
        try {
            list = p.searchIngredient(null, true, 1, "appe");
            pbI.insertIngredient(list.get(0));
            id = list.get(0).id;
            rootView.clearFocus();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initializeList();
        listAdapter = new shoppingCartLVAdapter(getActivity(),listItems);

        pantryList.setAdapter(listAdapter);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        listItems.add(new ingredient(1, "ah", "fef", "", " ", 0, false, false));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ingredient> list;
                String ingredientString = searchPantry.getText().toString();
                try {
                    list = p.searchIngredient(null, true, 1, ingredientString);
                    pbI.insertIngredient(list.get(0));

                    rootView.clearFocus();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        return rootView;
        }


    private void initializeList() {

        pbI.getIngredientByID(id);
        listItems.add(pbI.getIngredient());

    }



} // myPantry ends

