package com.pr.pr.pantryraid.roomPersist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.TypeConverter;
import android.os.AsyncTask;

import java.util.List;

public class dbInitialize{

    public static void populateRecipes(AppDatabase db){
        populateDBAsync task = new populateDBAsync(db);
        task.execute();
    }

    public static void fetchById(AppDatabase mdb, int id){
        mdb.recipesdao().getRecipeFromID(id);
    }

    public static void populateWithData(AppDatabase db){
        db.recipesdao().insertRecipes(new recipesDB(20, "name"));
        db.recipesdao().insertRecipes(new recipesDB(30, "names"));
    }

    private static class populateDBAsync extends AsyncTask<Void, Void, Void>{
        private final AppDatabase mdb;

        populateDBAsync(AppDatabase db){mdb = db;}

        protected Void doInBackground(final Void... params){
            populateWithData(mdb);
            return null;
        }
    }
}