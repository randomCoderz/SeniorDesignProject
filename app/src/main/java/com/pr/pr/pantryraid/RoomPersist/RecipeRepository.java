package com.pr.pr.pantryraid.RoomPersist;

import android.os.AsyncTask;

import com.pr.pr.pantryraid.recipe;

import java.util.List;

public class RecipeRepository {
    private AppDatabase gdb;

    private static List<recipe> rec;

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

    public void insertRecipeList(List<recipe> r){
        new insertListAsync(gdb).execute(r);
    }

    private static class insertListAsync extends AsyncTask<List<recipe>, Void, Void>{
        private final AppDatabase mdb;
        insertListAsync(AppDatabase db){mdb = db;}

        @Override
        protected Void doInBackground(final List<recipe>... params){
            mdb.recipesdao().insertRecipesList(params[0]);
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
            System.out.println(r.name);

        }

    }

    public void getFavorites(){
        new recipeFavAsync(gdb).execute();
    }

    private static class recipeFavAsync extends AsyncTask<Void, Void, List<recipe>>{
        private final AppDatabase mdb;
        recipeFavAsync(AppDatabase db){mdb = db;}

        @Override
        protected List<recipe> doInBackground(final Void... params){
            return mdb.recipesdao().getFavorites();
        }

        @Override
        protected void onPostExecute(List<recipe> r){
            setRecipe(r);
        }

    }

    public void getAllRecipes(){
        new recipeAllAsync(gdb).execute();
    }

    private static class recipeAllAsync extends AsyncTask<Void, Void, List<recipe>>{
        private final AppDatabase mdb;
        recipeAllAsync(AppDatabase db){mdb = db;}

        @Override
        protected List<recipe> doInBackground(final Void... params){
            return mdb.recipesdao().allRecipes();
        }

        @Override
        protected void onPostExecute(List<recipe> r){
            setRecipe(r);
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

    public static void setRecipe(List<recipe> i)
    {
        rec = i;
    }

    public List<recipe> getRecipes()
    {
        return rec;
    }
}

