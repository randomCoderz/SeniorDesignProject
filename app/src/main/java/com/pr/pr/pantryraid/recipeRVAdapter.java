package com.pr.pr.pantryraid;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Nam on 2/22/2018.
 */

public class recipeRVAdapter extends RecyclerView.Adapter<recipeRVAdapter.recipeViewHolder> {
    public static class recipeViewHolder extends RecyclerView.ViewHolder {
        int index;
        CardView cv;
        TextView recipeName;
        ImageView recipeImage;

        recipeViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            recipeName = itemView.findViewById(R.id.cvName);
            recipeImage = itemView.findViewById(R.id.cvPhoto);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    recipeFragment recipe = new recipeFragment();
                    if (recipeList != null) {
                        recipe = new recipeFragment(recipeList.get(index));

                    }
                    Fragment myFragment = recipe;
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, myFragment).addToBackStack(null).commit();

                }
            });

        }
    }

    private static List<recipe> recipeList;

    public recipeRVAdapter(List<recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public void recipeRVAdapterRefresh(List<recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public recipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item, viewGroup, false);
        recipeViewHolder rvh = new recipeViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(recipeViewHolder holder, int i) {
        try {

            holder.recipeName.setText(recipeList.get(i).name);
            holder.index = i;

            Picasso.with(holder.itemView.getContext()).load(recipeList.get(i).url).resize(2300, 1300).into(holder.recipeImage);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        if (recipeList == null)
            return 0;
        return recipeList.size();
    }

}