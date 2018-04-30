package com.pr.pr.pantryraid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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


    cookBook c = new cookBook(KEY);
    //declaration
    ImageButton searchButton;
    Button RecipeButton;
    Button deleteButton;
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


        searchButton = rootView.findViewById(R.id.searchButton);
//        searchPantry = rootView.findViewById(R.id.searchPantry);
        RecipeButton = rootView.findViewById(R.id.bttnRecipe);
        deleteButton = rootView.findViewById(R.id.bttnDelete);

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

        //buttons click lister:

        //This button will search for an ingredient
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


        //This button will search a recipe with selected ingredients
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
                ArrayList<recipe> results = new ArrayList<>();
                try {
                    results = c.getRecipesByIngredients(false, toSearch, true, 5, 1);
                    if(results != null)
                        System.out.println(results.size());

                    Fragment fragment =  new myCookBook(results);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, fragment).addToBackStack(null).commit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        });

        //This button will delete selected ingredients from my pantry
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ingredient> toDelete = new ArrayList<>();
                for(int i = 0; i < pantryList.size(); i++)
                {
                    if(pantryList.get(i).selected)
                    {
                        toDelete.add(pantryList.get(i));
                    }
                }
                for(int j = 0; j < toDelete.size(); j++)
                {
                    pbI.removeIngredient(toDelete.get(j));
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

