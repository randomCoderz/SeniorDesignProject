package com.pr.pr.pantryraid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mashape.relocation.HttpResponse;
import com.mashape.relocation.client.methods.HttpGet;
import com.mashape.relocation.impl.client.DefaultHttpClient;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import android.os.AsyncTask;

/**
 * Created by Nam on 2/22/2018.
 */

public class recipeRVAdapter extends RecyclerView.Adapter<recipeRVAdapter.recipeViewHolder>
{
    public static class recipeViewHolder extends RecyclerView.ViewHolder
    {
        CardView cv;
        TextView recipeName;
        ImageView recipeImage;

        recipeViewHolder(View itemView)
        {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            recipeName = (TextView) itemView.findViewById(R.id.cvName);
            recipeImage = (ImageView) itemView.findViewById(R.id.cvPhoto);


        }
    }

    List<recipe> recipeList;

    recipeRVAdapter(List<recipe> recipeList)
    {
        this.recipeList = recipeList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public recipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item, viewGroup, false);
        recipeViewHolder rvh = new recipeViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(recipeViewHolder holder, int i)
    {
        try {

            holder.recipeName.setText(recipeList.get(i).getName());

            Picasso.with(holder.itemView.getContext()).load(recipeList.get(i).getUrl()).into(holder.recipeImage);


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

