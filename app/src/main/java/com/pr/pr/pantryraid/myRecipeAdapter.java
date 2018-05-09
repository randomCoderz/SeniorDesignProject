package com.pr.pr.pantryraid;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Nam on 4/28/2018.
 */

public class myRecipeAdapter extends RecyclerView.Adapter<myRecipeAdapter.recipeViewHolder>
{
    public static class recipeViewHolder extends RecyclerView.ViewHolder
    {
        int index;
        boolean missing;
        CardView cv;
        TextView ingredientName;
        CheckBox checkBox;

        recipeViewHolder(View itemView)
        {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            ingredientName = itemView.findViewById(R.id.cvName);
            checkBox = itemView.findViewById(R.id.checkBox);

            cv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(checkBox.isChecked() == false)
                    {
                        ingredientList.get(index).selected = true;
                        checkBox.setChecked(true);
                    }
                    else
                    {
                        ingredientList.get(index).selected = false;
                        checkBox.setChecked(false);
                    }
                }
            });

        }
    }

    private static List<ingredient> ingredientList;

    public myRecipeAdapter(List<ingredient> ingredientList) { this.ingredientList = ingredientList; }

    public void myPantryAdapterRefresh(List<ingredient> ingredientList)
    {
        this.ingredientList = ingredientList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {

        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public myRecipeAdapter.recipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_my_pantry_ingredient, viewGroup, false);

        recipeViewHolder rvh = new recipeViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(myRecipeAdapter.recipeViewHolder holder, int i)
    {
        try {
            if(ingredientList.get(i).missing)
            {
                holder.ingredientName.setTextColor(Color.RED);
            }
            holder.ingredientName.setText(new DecimalFormat("#.##").format(ingredientList.get(i).amount) + " " + ingredientList.get(i).unit + " " + ingredientList.get(i).name);
            holder.index = i;
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount()
    {
        if(ingredientList == null)
        {
            return 0;
        }
        return ingredientList.size();
    }

}
