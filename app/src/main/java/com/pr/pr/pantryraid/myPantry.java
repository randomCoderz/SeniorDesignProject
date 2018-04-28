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

import java.util.ArrayList;
import java.util.Arrays;

public class myPantry extends Fragment{

    //declaration
    ImageButton searchButton;
    EditText searchPantry;
    ListView pantryList;
    //ArrayList<String> listItems;
    ArrayList<ingredient> listItems = new ArrayList<>();
    private shoppingCartLVAdapter listAdapter;



    // This will make it so that when you search for ingredients it will filter the list.
    String[] items;

    ArrayAdapter<String> adapter;

    public myPantry(){

    }

    // linking the UI with jave
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.my_pantry, container,false);

        searchButton = (ImageButton)rootView.findViewById(R.id.searchButton);
        searchPantry = (EditText)rootView.findViewById(R.id.searchPantry);
        pantryList = (ListView)rootView.findViewById(R.id.myPantry);


        pantryList = rootView.findViewById(R.id.myPantry);
        listAdapter = new shoppingCartLVAdapter(getActivity(),listItems);
        pantryList.setAdapter(listAdapter);

        listItems.add(new ingredient(1, "ah", "fef", "", " ", 0, false, false));

        return rootView;
        }



    public void searchItem(String textToSearch){

        for(String item:items){

            if(!item.contains(textToSearch)){

                listItems.remove(item);

            }

        }

        adapter.notifyDataSetChanged();

    }

} // myPantry ends

