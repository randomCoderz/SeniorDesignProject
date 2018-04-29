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
        TextView recipeName;
        CheckBox checkBox;

        recipeViewHolder(View itemView)
        {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            recipeName = itemView.findViewById(R.id.cvName);
            checkBox = itemView.findViewById(R.id.checkBox);

            cv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(checkBox.isChecked() == false)
                    {
                        cv.setCardBackgroundColor(Color.GREEN);
                        checkBox.setChecked(true);
                    }
                    else
                    {
                        cv.setCardBackgroundColor(0x0106000f);
                        checkBox.setChecked(false);
                    }
                }
            });

        }
    }

    private static List<ingredient> recipeList;

    public myPantryAdapter(List<ingredient> recipeList) { this.recipeList = recipeList; }

    public void myPantryAdapter(List<ingredient> recipeList)
    {
        this.recipeList = recipeList;
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
            holder.recipeName.setText(recipeList.get(i).name);
            holder.index = i;
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount()
    {
        return recipeList.size();
    }
}
