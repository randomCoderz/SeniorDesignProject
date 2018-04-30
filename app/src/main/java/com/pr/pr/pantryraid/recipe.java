package com.pr.pr.pantryraid;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.annotation.SuppressLint;
//import android.graphics.Color;
//import android.os.Bundle;


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

    @ColumnInfo(name = "favorites")
    boolean favorites;

    @ColumnInfo(name = "mealCalendar")
    boolean mealCalendar;

    @ColumnInfo(name = "homePage")
    boolean homePage;

    @ColumnInfo(name = "day")
    int day;

    @ColumnInfo(name = "month")
    int month;

    @ColumnInfo(name = "year")
    int year;


    @SuppressLint("ValidFragment")
    public recipe(int id, String name, String url, int readyInMinutes, ArrayList<ingredient> ingredients, ArrayList<step> analyzedInstructions, String instructions, boolean favorites, boolean mealCalendar, boolean homePage, int month, int day, int year)
    {
        this.id = id;
        this.name = name;
        this.url = url;
        this.readyInMinutes = readyInMinutes;
        this.ingredients = ingredients;
        this.analyzedInstructions = analyzedInstructions;
        this.instructions = instructions;
        this.favorites = favorites;
        this.mealCalendar = mealCalendar;
        this.homePage = homePage;
        this.month = month;
        this.day = day;
        this.year = year;
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

   public boolean isFavorites() {
        return favorites;
    }

    public void setFavorites(boolean favorites) {
        this.favorites = favorites;
    }

    public boolean isMealCalendar() {
        return mealCalendar;
    }

    public void setMealCalendar(boolean mealCalendar) {
        this.mealCalendar = mealCalendar;
    }

    public boolean isHomePage() {
        return homePage;
    }

    public void setHomePage(boolean homePage) {
        this.homePage = homePage;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}


