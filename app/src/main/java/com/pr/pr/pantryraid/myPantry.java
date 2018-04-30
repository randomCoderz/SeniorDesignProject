package com.pr.pr.pantryraid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.pr.pr.pantryraid.RoomPersist.AppDatabase;
import com.pr.pr.pantryraid.RoomPersist.IngredientRepository;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class myPantry extends Fragment{
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";

    //declaration
    ImageButton searchButton;
    Button RecipeButton;
    EditText searchPantry;
    CheckBox checkBox;
    //ArrayList<String> listItems;

    ArrayList<ingredient> listItems = new ArrayList<>();
    private shoppingCartLVAdapter listAdapter;

    ////////////////
    int id;

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
        searchPantry = rootView.findViewById(R.id.searchPantry);
        RecipeButton = rootView.findViewById(R.id.bttnDelete);
        checkBox = rootView.findViewById(R.id.checkBox);

        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);


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







        getList();

        myPantryAdapter adapter = new myPantryAdapter(pantryList);
        rv.setAdapter(adapter);

        return rootView;
        }


    private void initializeList() {

        pbI.getIngredientByID(id);
        listItems.add(pbI.getIngredient());

    }

    public void getList()
    {
        pantryList.add(new ingredient(0, "test", "fef", "", " ", 0, false, false));
        pantryList.add(new ingredient(1, "test1", "fef", "", " ", 0, false, false));
        pantryList.add(new ingredient(2, "test2", "fef", "", " ", 0, false, false));
    }


} // myPantry ends

