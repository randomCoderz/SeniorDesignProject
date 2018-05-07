package com.pr.pr.pantryraid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pr.pr.pantryraid.RoomPersist.AppDatabase;
import com.pr.pr.pantryraid.RoomPersist.IngredientRepository;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class shoppingCart extends Fragment{
    SharedPreferences mpref;


    AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
    IngredientRepository pbI = new IngredientRepository(mdb);


    private ListView listView;
    private shoppingCartLVAdapter listAdapter;
    ArrayList<ingredient> products = new ArrayList<>();
    Button btnPurchase;

    public shoppingCart(){

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_shoppingcart, container, false);

        Gson gson = new Gson();
        mpref = getActivity().getPreferences(MODE_PRIVATE);
        String json = mpref.getString("settings", "");

        //later use
        //products = (ArrayList<items>) getActivity().getIntent().getSerializableExtra("products");
        listView = rootView.findViewById(R.id.customCartListView);
        initalizeData();

        btnPurchase = rootView.findViewById(R.id.btnPurchased);
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            for(int i = 0; i < products.size(); i++)
            {
                products.get(i).shoppingCart = false;
                products.get(i).pantry = true;
                products.get(i).missing = false;
            }
            pbI.insertIngredientList(products);
            products = new ArrayList<>();
            listAdapter.refreshShoppingCartAdapter(products);
            listView.setAdapter(listAdapter);

            }

            SharedPreferences pref = getActivity().getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = pref.edit();
            Gson gson = new Gson();
//                String json = gson.toJson(s);
//                prefsEditor.putString("settings", json);
//            prefsEditor.commit();
//            Toast.makeText(getActivity(), "Your Settings Have Been Saved", Toast.LENGTH_LONG).show();

        });

        return rootView;
    }

    public void getList(){

    }
    public void onStart() {

        super.onStart();
        listAdapter = new shoppingCartLVAdapter(getActivity(),products);
        listView.setAdapter(listAdapter);
    }

    public void initalizeData()
    {
        pbI.getAllIngredients();
        ArrayList<ingredient> allIngredients = pbI.getIngredients();
        if(allIngredients != null)
        {
            for(int i = 0; i < allIngredients.size(); i++)
            {
                if(allIngredients.get(i).shoppingCart)
                {
                    products.add(allIngredients.get(i));
                }
            }
        }


    }

}