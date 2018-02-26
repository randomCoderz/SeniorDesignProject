package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Room;
import android.content.Context;

@Database(entities = {recipes.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract DAO recipeDao();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).build();
        }
        return INSTANCE;
    }
    public static void destroyInstance(){INSTANCE = null;}
}