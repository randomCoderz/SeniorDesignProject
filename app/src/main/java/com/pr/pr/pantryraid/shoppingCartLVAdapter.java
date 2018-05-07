package com.pr.pr.pantryraid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Benjamin on 3/22/2018.
 */

public class shoppingCartLVAdapter extends BaseAdapter{
    public ArrayList<ingredient> listProducts;
    private Context context;

    public shoppingCartLVAdapter(Context context, ArrayList<ingredient> listProducts){
        this.context = context;
        this.listProducts = listProducts;
    }
    @Override
    public int getCount() {
        if(listProducts == null)
        {
            return 0;
        }
        return listProducts.size();
    }

    @Override
    public ingredient getItem(int position) {
        return listProducts.get(position);
    }

    public void refreshShoppingCartAdapter(ArrayList<ingredient> listProducts)
    {
        this.listProducts = listProducts;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        final shoppingCartLVHolder listViewHolder;
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.shoppingcart_listview,parent,false);
            listViewHolder = new shoppingCartLVHolder();
            listViewHolder.itemName = row.findViewById(R.id.item);
            listViewHolder.itemQty = row.findViewById(R.id.quantityNumber);
            listViewHolder.btnMinus = row.findViewById(R.id.minusButton);
            listViewHolder.btnPlus = row.findViewById(R.id.plusButton);
            row.setTag(listViewHolder);
        }
        else
        {
            row=convertView;
            listViewHolder= (shoppingCartLVHolder) row.getTag();
        }
        final ingredient products = getItem(position);

        listViewHolder.itemName.setText(products.name);

        listViewHolder.itemQty.setText((int)products.quantity+"");

        listViewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                updateQuantity(position,listViewHolder.itemQty,1);
            }
        });
        //listViewHolder.edTextQuantity.setText("0");
        listViewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(position,listViewHolder.itemQty,-1);

            }
        });



        return row;
    }

    private void updateQuantity(int position, EditText itemQty, int value) {

        ingredient products = getItem(position);
        if(value > 0)
        {
            products.quantity = products.quantity + 1;
        }
        else
        {
            if(products.quantity > 0)
            {
                products.quantity = products.quantity - 1;
            }

        }

        itemQty.setText((int)products.quantity+"");

    }
}