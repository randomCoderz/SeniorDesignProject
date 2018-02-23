package com.pr.pr.pantryraid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mashape.relocation.HttpResponse;
import com.mashape.relocation.client.methods.HttpGet;
import com.mashape.relocation.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

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
            holder.recipeName.setText(recipeList.get(i).name);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(recipeList.get(i).url).getContent());
            holder.recipeImage.setImageBitmap(bitmap);

            //InputStream is = (InputStream) new URL(recipeList.get(i).url).getContent();
            //InputStream is = (InputStream) new URL("https://images.spoonacular.com/file/wximages/228270-312x231.png").getContent();
            //Drawable d = Drawable.createFromStream(is, "src name");
            //holder.recipeImage.setImageDrawable(d);
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