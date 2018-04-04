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
    ArrayList<items> products = new ArrayList<>();
    Button btnPlaceOrder;
    ArrayList<items> productOrders = new ArrayList<>();

    public shoppingCart(){

    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_shoppingcart, container, false);
        getProduct();
        //later use
        //products = (ArrayList<items>) getActivity().getIntent().getSerializableExtra("products");
        listView = (ListView) rootView.findViewById(R.id.customCartListView);
        listAdapter = new shoppingCartLVAdapter(getActivity(),products);
        listView.setAdapter(listAdapter);
        btnPlaceOrder = (Button) rootView.findViewById(R.id.btnPlaceOrder);
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
            if(listAdapter.listProducts.get(i).CartQuantity > 0)
            {
                items products = new items(
                        listAdapter.listProducts.get(i).itemName
                );
                products.CartQuantity = listAdapter.listProducts.get(i).CartQuantity;
                productOrders.add(products);
            }
        }
    }

    public void getProduct() {
        products.add(new items("one"));
        products.add(new items("two"));
        products.add(new items("three"));
        products.add(new items("four"));


//        products.add(new Product("Camera",11.0d,R.mipmap.camera_1));
//        products.add(new Product("Floppy",12.0d,R.mipmap.floppy));
//        products.add(new Product("Game Pad",13.0d,R.mipmap.game_controller));
//        products.add(new Product("Graphics Card",14.0d,R.mipmap.graphics_card));
//        products.add(new Product("HDMI Cable",16.0d,R.mipmap.hdmi));
//        products.add(new Product("Headphones",11.0d,R.mipmap.headphones));
//        products.add(new Product("I Mac",15.0d,R.mipmap.imac));
//        products.add(new Product("I Pad",17.0d,R.mipmap.ipad));
//        products.add(new Product("Keyboard",67.0d,R.mipmap.keyboard));
//        products.add(new Product("Laptop",41.0d,R.mipmap.laptop));
//        products.add(new Product("LCD",16.0d,R.mipmap.lcd));
//        products.add(new Product("Light Bulb",18.0d,R.mipmap.light_bulb));
//        products.add(new Product("Mac Mini",121.0d,R.mipmap.mac_mini));
//        products.add(new Product("Monitor",122.0d,R.mipmap.monitor));
//        products.add(new Product("Mouse",14.0d,R.mipmap.mouse));
//        products.add(new Product("Movie Camera",51.0d,R.mipmap.movie_camera));
//        products.add(new Product("Music Player",12.0d,R.mipmap.music_player));
//        products.add(new Product("PC",16.0d,R.mipmap.pc));
//        products.add(new Product("Playstation",12.0d,R.mipmap.playstation));
//        products.add(new Product("Printer",17.0d,R.mipmap.printer));
//        products.add(new Product("Remote",12.0d,R.mipmap.remote));
//        products.add(new Product("Smart Watch",18.0d,R.mipmap.smart_watch));
//        products.add(new Product("Smartphone",19.0d,R.mipmap.smartphone));
//        products.add(new Product("Tablet",21.0d,R.mipmap.tablet));
//        products.add(new Product("USB",87.0d,R.mipmap.usb));
//        products.add(new Product("Webcam",87.0d,R.mipmap.webcam));
//        products.add(new Product("Windows Phone",123.0d,R.mipmap.windows_phone));
//        products.add(new Product("Zerox",85.0d,R.mipmap.zerox));
    }
}