package com.pr.pr.pantryraid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class shoppingCart extends Fragment{

    private ListView listView;
    private shoppingCartLVAdapter listAdapter;
    ArrayList<ingredient> products = new ArrayList<>();
    Button btnPlaceOrder;
    ArrayList<ingredient> productOrders = new ArrayList<>();

    public shoppingCart(){

    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_shoppingcart, container, false);
        getProduct();
        //later use
        //products = (ArrayList<items>) getActivity().getIntent().getSerializableExtra("products");
        listView = rootView.findViewById(R.id.customCartListView);
        listAdapter = new shoppingCartLVAdapter(getActivity(),products);
        listView.setAdapter(listAdapter);
        btnPlaceOrder = rootView.findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
        return rootView;
    }

    private void placeOrder()
    {
        //sending
        //Intent i = new Intent(getActivity(), HomePage.class);
       // i.putExtra("products", products);
       // startActivity(i);

        productOrders.clear();
        for(int i=0;i<listAdapter.listProducts.size();i++)
        {
            if(listAdapter.listProducts.get(i).quantity > 0)
            {
//                ingredient products = new ingredient(
//                        listAdapter.listProducts.get(i).name
//                );
//                products.quantity = listAdapter.listProducts.get(i).quantity;
//                productOrders.add(products);
            }
        }
    }

    public void getProduct() {
//        products.add(new items("one"));
//        products.add(new items("two"));
//        products.add(new items("three"));
//        products.add(new items("four"));
          products.add(new ingredient(1, "ah", "fef", "", " ", 0, false, false));

    }
}