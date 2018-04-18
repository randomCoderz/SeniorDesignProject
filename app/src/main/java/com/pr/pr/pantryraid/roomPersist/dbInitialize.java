package com.pr.pr.pantryraid.roomPersist;

import android.os.AsyncTask;

import java.util.ArrayList;

public class dbInitialize{

    public static void populateRecipes(AppDatabase db){
        populateDBAsync task = new populateDBAsync(db);
        task.execute();
    }

//    public static void fetchAll(AppDatabase mdb){
//        System.out.println("!!!!!!!!!!DARUDE SANDSTORM!!!!!!!!!!!!");
//        fetchThing task = new fetchThing(mdb);
//        task.execute();
//        //System.out.println(mdb.recipesdao().allRecipes().getValue());
//    }

    public static void fetch(AppDatabase mdb){
        System.out.println(mdb.recipesdao().allRecipes().getValue());
    }

    public static void populateWithData(AppDatabase db){
        ArrayList<Ingredient> test = new ArrayList<>();
        test.add(new Ingredient(1, "hel", "7"));
        test.add(new Ingredient(2, "hell", "7"));
        test.add(new Ingredient(3, "hello", "7"));

        db.recipesdao().insertRecipes(new recipesDB(20, "Tester", "lololol", 5, test, "Hello"));
    }

    private static class populateDBAsync extends AsyncTask<Void, Void, Void>{
        private final AppDatabase mdb;

        populateDBAsync(AppDatabase db){mdb = db;}

        protected Void doInBackground(final Void... params){
            populateWithData(mdb);
            fetch(mdb);
            return null;
        }
    }

//
//    //Second Async
//    private static class fetchThing extends AsyncTask<Void, Void, Void>{
//        private final AppDatabase mdb;
//
//        fetchThing(AppDatabase db){mdb = db;}
//
//        protected Void doInBackground(final Void... params){
//            fetch(mdb);
//            return null;
//        }
//    }




}