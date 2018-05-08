package com.pr.pr.pantryraid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

public class DietRestrictLVAdapter extends ArrayAdapter<DietRestrictName> implements CompoundButton.OnCheckedChangeListener {
    Context context;

    SharedPreferences.Editor editor;
    savedSettings s = new savedSettings();


    SparseBooleanArray List;
    int layoutResourceId;
    DietRestrictName  data[];


    private static LayoutInflater inflater = null;

    public DietRestrictLVAdapter(Context context,int layoutResourceId, DietRestrictName[] data)
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        List = new SparseBooleanArray(data.length);

    }



    public boolean isChecked(int position) {
        return List.get(position, false);
    }

    public void setChecked(int position, boolean isChecked) {
        List.put(position, isChecked);

    }

    public void toggle(int position) {
        setChecked(position, !isChecked(position));

    }



    public int getCount()
    {
        return data.length;
    }


    public long getItemId(int position)
    {
        return position;
    }

    public DietRestrictName getItem(int position)
    {
        return data[position];
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView,
                                 boolean isChecked) {

        List.put((Integer) buttonView.getTag(), isChecked);

    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        SharedPreferences sharedPrefs = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
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
        editor = sharedPrefs.edit();
        listViewHolder.selected.setChecked(sharedPrefs.getBoolean("CheckValue" +position,false));
        DietRestrictName products = data[position];
        listViewHolder.restriction.setText(products.name);
        listViewHolder.selected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    editor.putBoolean("CheckValue"+position,isChecked);
                    editor.commit();


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

