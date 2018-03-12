package com.pr.pr.pantryraid;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nam on 2/17/2018.
 */

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ingredientViewHolder>
{
    public static class ingredientViewHolder extends RecyclerView.ViewHolder
    {
        CardView cv;
        TextView ingredientName;
        TextView ingredientDescription;
        ImageView ingredientImage;

        ingredientViewHolder(View itemView)
        {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            ingredientName = (TextView) itemView.findViewById(R.id.cvName);
            ingredientDescription = (TextView) itemView.findViewById(R.id.cvDescription);
            ingredientImage = (ImageView) itemView.findViewById(R.id.cvPhoto);
        }
    }

    List<ingredient> ingredientList;

    recyclerViewAdapter(List<ingredient> ingredientList)
    {
        this.ingredientList = ingredientList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ingredientViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item, viewGroup, false);
        ingredientViewHolder ivh = new ingredientViewHolder(view);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ingredientViewHolder holder, int i)
    {
        holder.ingredientName.setText(ingredientList.get(i).name);
//        holder.ingredientDescription.setText(ingredientList.get(i).description);
        holder.ingredientImage.setImageResource(ingredientList.get(i).photoID);
    }

    @Override
    public int getItemCount()
    {
        return ingredientList.size();
    }

}
