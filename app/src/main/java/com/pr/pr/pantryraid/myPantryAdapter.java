package com.pr.pr.pantryraid;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nam on 4/28/2018.
 */

public class myPantryAdapter extends RecyclerView.Adapter<myPantryAdapter.recipeViewHolder>
{
    public static class recipeViewHolder extends RecyclerView.ViewHolder
    {
        int index;
        CardView cv;
        TextView ingredientName;
        CheckBox checkBox;

        recipeViewHolder(View itemView)
        {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            ingredientName = itemView.findViewById(R.id.cvName);
            checkBox = itemView.findViewById(R.id.checkBox);
            
            for(int i = 0; i < ingredientList.size(); i++)
            {
                if(ingredientList.get(i).missing)
                {
                    cv.setCardBackgroundColor(Color.RED);
                }
            }
            
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

    public myPantryAdapter(List<ingredient> ingredientList) { this.ingredientList = ingredientList; }

    public void myPantryAdapter(List<ingredient> ingredientList)
    {
        this.ingredientList = ingredientList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public myPantryAdapter.recipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_my_pantry_ingredient, viewGroup, false);
        recipeViewHolder rvh = new recipeViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(myPantryAdapter.recipeViewHolder holder, int i)
    {
        try {
            holder.ingredientName.setText(ingredientList.get(i).name);
            holder.index = i;
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount()
    {
        return ingredientList.size();
    }

}
