package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pr.pr.pantryraid.ingredient;
import com.pr.pr.pantryraid.step;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataConverter{


    @TypeConverter
    public static String getIngredients(ArrayList<ingredient> i){
        if(i == null){
            return null;
        }
        Gson gson = new Gson();
        String json = gson.toJson(i);
        return json;
    }

    @TypeConverter
    public static ArrayList<ingredient> toIngredient(String s){
        if(s == null){
            return null;
        }
        Type listType = new TypeToken<ArrayList<ingredient>>(){}.getType();
        return new Gson().fromJson(s, listType);
    }

    @TypeConverter
    public static String aInstructions(ArrayList <step> i){
        if(i == null){
            return null;
        }
        Gson gson = new Gson();
        String json = gson.toJson(i);
        return json;
    }

    @TypeConverter
    public static ArrayList<step> toAInstructions(String s){
        if(s == null){
            return null;
        }
        Type listType = new TypeToken<ArrayList<step>>(){}.getType();
        return new Gson().fromJson(s, listType);
    }

}