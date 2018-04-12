package com.pr.pr.pantryraid;

import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ingredientsAdapter extends ArrayAdapter<ingredient> {
    private final Context context;
    private final ingredient[] values;

    public ingredientsAdapter(Context context, ingredient[] values)
    {
        super(context, R.layout.ingredient_item, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.ingredient_item, parent, false);
        TextView ingredientName = (TextView) row.findViewById(R.id.ingredientName);
        ingredientName.setText(values[position].name);

        return row;
    }
}
