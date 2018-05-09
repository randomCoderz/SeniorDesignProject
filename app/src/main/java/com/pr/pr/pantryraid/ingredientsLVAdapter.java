package com.pr.pr.pantryraid;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ingredientsLVAdapter extends BaseAdapter {
    Context context;
    ArrayList<ingredient> ingredientList;

    private static LayoutInflater inflater = null;

    public ingredientsLVAdapter(Context context, ArrayList<ingredient> ingredientList)
    {
        this.context = context;
        this.ingredientList = ingredientList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    public View getView(final int position, View convertView, ViewGroup parent)
    {

        View row = convertView;
        if(row == null)
            row = inflater.inflate(R.layout.ingredient_item, null);
        final TextView ingredientName = row.findViewById(R.id.ingredientName);
        ingredientName.setTextSize(20);
        ingredientName.setTypeface(null, Typeface.BOLD);
        CheckBox selected = row.findViewById(R.id.checkBox);
        double a = Double.parseDouble(ingredientList.get(position).amount);
        ingredientName.setText(new DecimalFormat("#.##").format(a) + " " + ingredientList.get(position).unit + " " + ingredientList.get(position).name);

//        row.setBackgroundColor(Color.RED);
        selected.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(ingredientList.get(position).selected)
                ingredientList.get(position).selected = false;
            else
                ingredientList.get(position).selected = true;

        }
    });


        return row;
    }
}
