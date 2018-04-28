package com.pr.pr.pantryraid.RoomPersist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pr.pr.pantryraid.recipe;

public class RecipeRepository {
    private AppDatabase gdb;

    public RecipeRepository(AppDatabase data){
        gdb = data;
    }

//    recipe getRecipeByID(int id){
//        return gdb.recipesdao().getRecipeFromID(id);
//    }

    //Insert Recipe into Database
    public void insertRecipe(recipe r){
        new insertAsync(gdb).execute(r);
    }

    private static class insertAsync extends AsyncTask<recipe, Void, Void>{
        private final AppDatabase mdb;
        insertAsync(AppDatabase db){mdb = db;}

        @Override
        protected Void doInBackground(final recipe... params){
            mdb.recipesdao().insertRecipes(params[0]);
            return null;
        }
    }

    //Get Recipe from by ID from Database

    public void getRecipeByID(int id){
        new recipeIDAsync(gdb).execute(id);
    }

    private static class recipeIDAsync extends AsyncTask<Integer, Void, recipe>{
        private final AppDatabase mdb;
        recipeIDAsync(AppDatabase db){mdb = db;}

        @Override
        protected recipe doInBackground(final Integer... params){
            return mdb.recipesdao().getRecipeFromID(params[0]);
        }

        @Override
        protected void onPostExecute(recipe r){
<<<<<<< HEAD

            System.out.println(r.name);

        }

    }

    public void getFavorites(){
        new recipeFavAsync(gdb).execute();
    }

    private static class recipeFavAsync extends AsyncTask<Void, Void, recipe>{
        private final AppDatabase mdb;
        recipeFavAsync(AppDatabase db){mdb = db;}

        @Override
        protected recipe doInBackground(final Void... params){
            return mdb.recipesdao().getFavorites();
        }

        @Override
        protected void onPostExecute(recipe r){
            System.out.println(r.name);

=======
            System.out.println(r.name);
>>>>>>> parent of 01a5877... Revert "Merge branch 'gui' of https://github.com/randomCoderz/SeniorDesignProject into gui"
        }

    }


    //Remove Recipe from Database
    public void removeRecipe(recipe r){
        new removeAsync(gdb).execute(r);
    }

    private static class removeAsync extends AsyncTask<recipe, Void, Void>{
        private final AppDatabase mdb;
        removeAsync(AppDatabase db){mdb = db;}

        @Override
        protected Void doInBackground(final recipe... params){
            mdb.recipesdao().deleteARecipe(params[0]);
            return null;
        }
    }
}