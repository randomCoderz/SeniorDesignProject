package com.pr.pr.pantryraid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.pr.pr.pantryraid.RoomPersist.AppDatabase;
import com.pr.pr.pantryraid.RoomPersist.IngredientRepository;

import java.util.ArrayList;

public class shoppingCart extends Fragment{

    AppDatabase mdb = AppDatabase.getInMemoryDatabase(this.getContext());
    IngredientRepository pbI = new IngredientRepository(mdb);


    private ListView listView;
    private shoppingCartLVAdapter listAdapter;
    ArrayList<ingredient> products = new ArrayList<>();
    Button btnPlaceOrder;

    public shoppingCart(){

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_shoppingcart, container, false);

        //later use
        //products = (ArrayList<items>) getActivity().getIntent().getSerializableExtra("products");
        listView = rootView.findViewById(R.id.customCartListView);
        initalizeData();
        listAdapter = new shoppingCartLVAdapter(getActivity(),products);
        listView.setAdapter(listAdapter);
        btnPlaceOrder = rootView.findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    placeOrder();
            }
        });
        return rootView;
    }

    public void initalizeData()
    {

        pbI.getAllIngredients();
        ArrayList<ingredient> allIngredients = pbI.getIngredients();
        for(int i = 0; i < allIngredients.size(); i++)
        {
            if(allIngredients.get(i).shoppingCart)
            {
                products.add(allIngredients.get(i));
            }
        }

    }

}