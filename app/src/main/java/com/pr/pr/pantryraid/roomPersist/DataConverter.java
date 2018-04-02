package com.pr.pr.pantryraid.roomPersist;
import android.arch.persistence.room.TypeConverter;

import com.pr.pr.pantryraid.ingredient;

public class DataConverter {
    @TypeConverter
    public static String fromIngredient(ingredient i){
        if(i == null){
            return null;
        }
        return(i.toString());
    }

    public static ingredient toIngredient(String s){
        if(s == null){
            return null;
        }

        return(new ingredient(s));
    }
}
