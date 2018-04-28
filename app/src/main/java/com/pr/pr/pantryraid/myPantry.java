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


        listAdapter = new shoppingCartLVAdapter(getActivity(),listItems);
        pantryList.setAdapter(listAdapter);




//        initList();
//        searchPantry.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.toString().equals("")){
//                    // reset listview
//                    initList();
//                }
//                else{
//                    // perform search
//                    searchItem(s.toString());
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }



            // not sure if i need to make it final
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
//
//    private void initList() {
//        items=new String[]{"apple","banana","milk","cherry","avocado"};
//        listItems=new ArrayList<>(Arrays.asList(items));
//        //might need to extends ActionBarActivity in this class!!!
//        adapter=new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, listItems);
//        myPantry.setAdapter(adapter);
//    }

    private void initializeList() {
        AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
        IngredientRepository pbI = new IngredientRepository(mdb);


    }


} // myPantry ends




