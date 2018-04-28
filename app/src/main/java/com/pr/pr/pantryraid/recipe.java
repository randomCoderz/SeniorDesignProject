package com.pr.pr.pantryraid;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.annotation.SuppressLint;
//import android.graphics.Color;
//import android.os.Bundle;


//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.*;
//import com.squareup.picasso.Picasso;

import java.util.ArrayList;

@Entity(tableName = "Recipes")
public class recipe
{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "recipeID")
    private int id;

    //Foreign Key to be used from Ingredients
    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "readyInMinutes")
    private int readyInMinutes;

    @ColumnInfo(name = "ingredients")
    private ArrayList<ingredient> ingredients;

    @ColumnInfo(name = "instructions")
    private String instructions;

    @ColumnInfo(name = "analyzedInstructions")
    private ArrayList<step> analyzedInstructions;

    @SuppressLint("ValidFragment")
    public recipe(int id, String name, String url, int readyInMinutes, ArrayList<ingredient> ingredients, ArrayList<step> analyzedInstructions, String instructions)
    {
        this.id = id;
        this.name = name;
        this.url = url;
        this.readyInMinutes = readyInMinutes;
        this.ingredients = ingredients;
        this.analyzedInstructions = analyzedInstructions;
        this.instructions = instructions;
    }

    //Getters and Setters
    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public ArrayList<ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<ingredient> ingredients) {
        this.ingredients = ingredients;

    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public ArrayList<step> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(ArrayList<step> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }

}

