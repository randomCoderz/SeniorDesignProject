package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pr.pr.pantryraid.ingredient;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataConverter{


    @TypeConverter
    public static String getIngredients(ArrayList<Ingredient> i){
        if(i == null){
            return null;
        }
        Gson gson = new Gson();
        String json = gson.toJson(i);
        return json;
    }

    @TypeConverter
    public static ArrayList<Ingredient> toIngredient(String s){
        if(s == null){
            return null;
        }
        Type listType = new TypeToken<ArrayList<Ingredient>>(){}.getType();
        return new Gson().fromJson(s, listType);
    }

    @TypeConverter
    public static String aInstructions(ArrayList <Step> i){
        if(i == null){
            return null;
        }
        Gson gson = new Gson();
        String json = gson.toJson(i);
        return json;
    }

    @TypeConverter
    public static ArrayList<Step> toAInstructions(String s){
        if(s == null){
            return null;
        }
        Type listType = new TypeToken<ArrayList<Step>>(){}.getType();
        return new Gson().fromJson(s, listType);
    }

}