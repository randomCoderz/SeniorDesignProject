package com.pr.pr.pantryraid;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;


import com.github.clans.fab.FloatingActionButton;
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

    //
    cookBook c = new cookBook(KEY);
    pantry p = new pantry(KEY);
    AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
    IngredientRepository pbI = new IngredientRepository(mdb);

    private ArrayList<ingredient> pantryList = new ArrayList<ingredient>();

    ArrayAdapter<String> adapter;

    RecyclerView rv;
    myPantryAdapter adapter2;


    public myPantry(){

    }

    // linking the UI with jave

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        final View rootView = inflater.inflate(R.layout.my_pantry, container,false);
        setHasOptionsMenu(true);

        final FloatingActionButton searchSelected = rootView.findViewById(R.id.searchRecipe);

//        searchSelected.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ArrayList<ingredient> selected = new ArrayList<>();
//                for (int i = 0; i < pantryList.size(); i++)
//                {
//                    if(pantryList.get(i).selected && pantryList.get(i) != null)
//                    {
//                        pantryList.get(i).shoppingCart = true;
//                        selected.add(pantryList.get(i));
//                        System.out.println(pantryList.get(i).name);
//                    }
//                }
//
//                try {
//                    ArrayList<recipe> searched = c.getRecipesByIngredients(false, selected, false, 5, 5);
//                    Fragment frag = new myCookBook(searched);
//                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, frag).addToBackStack(null).commit();
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        initializeList();

        rv = rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        adapter2 = new myPantryAdapter(pantryList);
        rv.setAdapter(adapter2);

        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                try {
                    List<ingredient> list = p.searchIngredient(null, true, 1, query);

                    ingredient ing = list.get(0);
                    pantryList.add(ing);
                    ing.pantry = true;
                    pbI.insertIngredient(ing);

                    rootView.clearFocus();

                    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                    adapter2.myPantryAdapterRefresh(pantryList);

                    rv.setAdapter(adapter2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

            return rootView;
        }


        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
            super.onCreateOptionsMenu(menu, inflater);
            final MaterialSearchView searchView = getActivity().findViewById(R.id.search_view);
            MenuItem item = menu.findItem(R.id.action_search);
            searchView.setMenuItem(item);
            menu.findItem(R.id.action_search).setVisible(true);
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


}

