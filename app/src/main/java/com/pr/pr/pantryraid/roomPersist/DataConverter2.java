package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pr.pr.pantryraid.step;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataConverter2 {
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
    public static ArrayList<String> toAInstructions(String s){
        if(s == null){
            return null;
        }
        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        return new Gson().fromJson(s, listType);
    }
}
