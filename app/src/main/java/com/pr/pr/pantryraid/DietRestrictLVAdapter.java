package com.pr.pr.pantryraid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class DietRestrictLVAdapter extends BaseAdapter {
    Context context;
    ArrayList<DietRestrictName> List;

    private static LayoutInflater inflater = null;

    public DietRestrictLVAdapter(Context context, ArrayList<DietRestrictName> List)
    {
        this.context = context;
        this.List = List;
    }

    public int getCount()
    {
        return List.size();
    }

    public DietRestrictName getItem(int position)
    {
        return List.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row;
        final DietRestrictHolder listViewHolder;
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.settings_restrictions_view,parent,false);
            listViewHolder = new DietRestrictHolder();
            listViewHolder.restriction = row.findViewById(R.id.restrictionName);
            listViewHolder.selected = row.findViewById(R.id.restrictionCheckBox);
            row.setTag(listViewHolder);
        }
        else
        {
            row=convertView;
            listViewHolder= (DietRestrictHolder) row.getTag();
        }
        final DietRestrictName products = getItem(position);

        listViewHolder.restriction.setText(products.name);
        listViewHolder.selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(listViewHolder.selected.isChecked()){

                }

            }
        });


        return row;
/**

 View row = convertView;
 if(row == null)
 row = inflater.inflate(R.layout.settings_restrictions_view, null);
 TextView ingredientName = row.findViewById(R.id.restrictionName);
 ingredientName.setTextSize(20);
 ingredientName.setTypeface(null, Typeface.BOLD);
 CheckBox selected = row.findViewById(R.id.restrictionCheckBox);
 ingredientName.setText(List.get(position).restriction);
 //        row.setBackgroundColor(Color.RED);
 return row;
 **/
    }
}

