package com.pr.pr.pantryraid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.pr.pr.pantryraid.RoomPersist.AppDatabase;
import com.pr.pr.pantryraid.RoomPersist.IngredientRepository;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class myPantry extends Fragment{
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";
    private MaterialSearchView searchView;

    //declaration
//    ImageButton searchButton;
//    Button RecipeButton;
//    Button deleteButton;
    EditText searchPantry;

    //ArrayList<String> listItems;


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
        setHasOptionsMenu(true);

//        Buttons
//        searchButton = rootView.findViewById(R.id.searchButton);
//        RecipeButton = rootView.findViewById(R.id.bttnRecipe);
//        deleteButton = rootView.findViewById(R.id.bttnDelete);

        //searchPantry = rootView.findViewById(R.id.searchPantry);

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

        ////////////////////////////////////////
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

        ////////////////////////////////////////

        //This button will search for an ingredient
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                List<ingredient> list;
//                String ingredientString = searchPantry.getText().toString();
//                try {
//                    list = p.searchIngredient(null, true, 1, ingredientString);
//
//                    ingredient ing = list.get(0);
//                    ing.pantry = true;
//                    pbI.insertIngredient(ing);
//
//                    rootView.clearFocus();
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        });
//

        //This button will search a recipe with selected ingredients
//        RecipeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ArrayList<ingredient> toSearch = new ArrayList<ingredient>();
//                for(int i = 0; i < pantryList.size(); i++)
//                {
//                    if(pantryList.get(i).selected)
//                    {
//                        toSearch.add(pantryList.get(i));
//                    }
//                }
//
//
//            }
//        });

        //This button will delete selected ingredients from my pantry
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//            }
//        });



        return rootView;
        }

//        @Override
//        public void onCreate(Bundle savedInstanceState){
//            super.onCreate(savedInstanceState);
//            setHasOptionsMenu(true);
//        }
//
        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
            super.onCreateOptionsMenu(menu, inflater);
            MenuItem item = menu.findItem(R.id.action_search);
            item.setVisible(true);
            searchView.setMenuItem(item);
        }


    private void initializeList() {

        pbI.getAllIngredients();
        ArrayList<ingredient> allIngredients = pbI.getIngredients();
        for(int i = 0; i < allIngredients.size(); i++)
        {
            if(allIngredients.get(i).pantry)
            {
                pantryList.add(allIngredients.get(i));
            }
        }

    }


} // myPantry ends

