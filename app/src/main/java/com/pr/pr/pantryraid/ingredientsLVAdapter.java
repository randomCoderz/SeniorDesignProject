package com.pr.pr.pantryraid;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;


public class ingredientsLVAdapter extends BaseAdapter {

    ArrayList<ingredient> ingredientList;

    public ingredientsLVAdapter(ArrayList<ingredient> ingredientList)
    {
        this.ingredientList = ingredientList;
    }

    public int getCount()
    {
        return ingredientList.size();
    }

    public Object getItem(int position)
    {
        return ingredientList.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        View row = convertView;
        TextView ingredientName = row.findViewById(R.id.ingredientName);
        CheckBox selected = row.findViewById(R.id.checkBox);
        ingredientName.setText(ingredientList.get(position).name);
        return row;
    }
}
