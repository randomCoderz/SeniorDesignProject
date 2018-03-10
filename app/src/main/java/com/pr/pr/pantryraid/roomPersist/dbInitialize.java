package com.pr.pr.pantryraid.roomPersist;

import android.os.AsyncTask;

public class dbInitialize{

    public static void populateRecipes(AppDatabase db){
            populateRecipesAsync task = new populateRecipesAsync(db);
            task.execute();
        }


//    public static void fetch(AppDatabase db){
//        for(int i = 0; i < 100; i++) {
//            System.out.println("IM IN THE FETCH");
//        }
//        System.out.println(db.recipeDao().getRecipes());
//    }


    private static class populateRecipesAsync extends AsyncTask<Void, Void, Void>{
        private final AppDatabase mdb;
        populateRecipesAsync(AppDatabase db){mdb = db;}
        protected Void doInBackground(final Void... params){
            recipes r = new recipes("Hello",10,20);
            mdb.recipeDao().testPass(r);
           // System.out.println(mdb.recipeDao().getRecipes());
            return null;
        }
    }
}