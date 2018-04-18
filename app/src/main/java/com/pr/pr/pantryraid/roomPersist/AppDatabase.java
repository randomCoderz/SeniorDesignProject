package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@TypeConverters({DataConverter.class})
@Database(entities = {recipesDB.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract recipesDAO recipesdao();
    //public abstract pantryDAO pantrydao();
    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).build();
        }
        return INSTANCE;
    }

//    public static AppDatabase getDatabase(Context context) {
//        if (INSTANCE == null) {
//            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "RECIPES").build();
//        }
//        return INSTANCE;
//    }
    public static void destroyInstance(){INSTANCE = null;}
}