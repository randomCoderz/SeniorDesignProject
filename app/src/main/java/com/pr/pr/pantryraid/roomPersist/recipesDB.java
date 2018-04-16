package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity(tableName = "Recipes")
public class recipesDB {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "recipeID")
    private int id;

    //Foreign Key to be used from Ingredients
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "readyInMinutes")
    private int readyInMinutes;

    @ColumnInfo(name = "ingredients")
    private ArrayList<Ingredient> ingredients;

//    public void setIngredients(ArrayList<String> ingredients) {
//        this.ingredients = ingredients;
//    }

    @ColumnInfo(name = "instructions")
    private String instructions;

    @ColumnInfo(name = "analyzedInstructions")
    private ArrayList<Step> analyzedInstructions;

//@ColumnInfo(name = "analyzedInstructions")
    //private ArrayList<step> analyzedInstructions;

//    //Constructor for roomPersist
//    public recipesDB(int id, String name)
//    {
//        this.id = id;
//        this.name = name;
//    }
//
//    @Ignore
//    public recipesDB(int id, String name, String url)
//    {
//        this.id = id;
//        this.name = name;
//        this.url = url;
//    }
//

//    public recipesDB(int id, String name, String url, int readyInMinutes)
//    {
//        this.id = id;
//        this.name = name;
//        this.url = url;
//        this.readyInMinutes = readyInMinutes;
//    }

    public recipesDB(int id, String name, String url, int readyInMinutes, ArrayList<Ingredient> ingredients, String instructions)
    {
        this.id = id;
        this.name = name;
        this.url = url;
        this.readyInMinutes = readyInMinutes;
        this.ingredients = ingredients;
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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public ArrayList<Step> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(ArrayList<Step> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }
}

