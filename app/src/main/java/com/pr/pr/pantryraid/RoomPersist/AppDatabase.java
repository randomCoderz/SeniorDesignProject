package com.pr.pr.pantryraid.RoomPersist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.pr.pr.pantryraid.ingredient;
import com.pr.pr.pantryraid.recipe;


@TypeConverters({DataConverter.class})
@Database(entities = {recipe.class, ingredient.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract RecipesDAO recipesdao();
    public abstract IngredientDAO ingredientDAO();


    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){INSTANCE = null;}
}