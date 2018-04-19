package com.pr.pr.pantryraid.RoomPersist;

import android.os.AsyncTask;

import java.util.ArrayList;

import com.pr.pr.pantryraid.ingredient;
import com.pr.pr.pantryraid.recipe;
public class dbInitialize{

    public static void populateRecipes(AppDatabase db){
        DBAsync task = new DBAsync(db);
        task.execute();
    }

    public static void fetch(AppDatabase mdb){
        System.out.println(mdb.recipesdao().allRecipes().getName());
    }

    public static void populateWithData(AppDatabase db){
        ArrayList<ingredient> test = new ArrayList<>();
        test.add(new ingredient(1, "hel", "7"));
        db.recipesdao().insertRecipes(new recipe(20, "Tester", "lolol", 5, test, "Hello"));
        db.recipesdao().insertRecipes(new recipe(100, "test2","llll",10,test,"testinggg"));
    }

    private static class DBAsync extends AsyncTask<Void, Void, Void>{
        private final AppDatabase mdb;

        DBAsync(AppDatabase db){mdb = db;}

        protected Void doInBackground(final Void... params){
            populateWithData(mdb);
            fetch(mdb);
            return null;
        }
    }
}