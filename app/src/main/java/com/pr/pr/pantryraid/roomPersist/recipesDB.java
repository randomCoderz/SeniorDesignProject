package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.pr.pr.pantryraid.ingredient;
import com.pr.pr.pantryraid.step;

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
    @TypeConverters(DataConverter.class)
    private ArrayList<ingredient> ingredients;

    @ColumnInfo(name = "instructions")
    private String instructions;

    @TypeConverters(DataConverter2.class)
    @ColumnInfo(name = "analyzedInstructions")
    private ArrayList<step> analyzedInstructions;

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
//    @Ignore
//    public recipesDB(int id, String name, String url, int readyInMinutes)
//    {
//        this.id = id;
//        this.name = name;
//        this.url = url;
//        this.readyInMinutes = readyInMinutes;
//    }
//
//    @Ignore
    public recipesDB(int id, String name, String url, int readyInMinutes, ArrayList<ingredient> ingredients, String instructions)
    {
        this.id = id;
        this.name = name;
        this.url = url;
        this.readyInMinutes = readyInMinutes;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

