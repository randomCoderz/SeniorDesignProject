package com.pr.pr.pantryraid.RoomPersist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pr.pr.pantryraid.ingredient;
import com.pr.pr.pantryraid.recipe;

import java.util.ArrayList;
import java.util.List;

public class IngredientRepository {
    private AppDatabase gdb;

    public IngredientRepository(AppDatabase data){
        gdb = data;
    }

    //Insert Recipe into Database
    public void insertIngredient(ingredient r){
        new insertAsync(gdb).execute(r);
    }

    private static class insertAsync extends AsyncTask<ingredient, Void, Void>{
        private final AppDatabase mdb;
        insertAsync(AppDatabase db){mdb = db;}

        @Override
        protected Void doInBackground(final ingredient... params){
            mdb.ingredientDAO().insertIngredient(params[0]);
            return null;
        }
    }

    public void insertIngredientList(List<ingredient> r){
        new insertListAsync(gdb).execute(r);
    }

    private static class insertListAsync extends AsyncTask<List<ingredient>, Void, Void>{
        private final AppDatabase mdb;
        insertListAsync(AppDatabase db){mdb = db;}

        @Override
        protected Void doInBackground(final List<ingredient>... params){
            mdb.ingredientDAO().insertIngredientList(params[0]);
            return null;
        }
    }

    //Get Recipe from by ID from Database
    public void getIngredientByID(int id){
        new ingredientIDAsync(gdb).execute(id);
    }

    private static class ingredientIDAsync extends AsyncTask<Integer, Void, ingredient>{
        private final AppDatabase mdb;
        ingredientIDAsync(AppDatabase db){mdb = db;}

        @Override
        protected ingredient doInBackground(final Integer... params){
            return mdb.ingredientDAO().getIngredientFromID(params[0]);
        }

        @Override
        protected void onPostExecute(ingredient r){
            ing.add(r);
        }

    }

    public void getAllIngredients(){
        new ingredientAllAsync(gdb).execute();
    }

    private static class ingredientAllAsync extends AsyncTask<Void, Void, List<ingredient>> {
        private final AppDatabase mdb;

        ingredientAllAsync(AppDatabase db) {
            mdb = db;
        }

        @Override
        protected List<ingredient> doInBackground(final Void... params) {
            return mdb.ingredientDAO().getAllIngredients();
        }

        @Override
        protected void onPostExecute(List<ingredient> r) {
            setIngredient((ArrayList<ingredient>) r);
        }
    }


        //Remove Recipe from Database
    public void removeIngredient(ingredient r){
        new removeAsync(gdb).execute(r);
    }

    private static class removeAsync extends AsyncTask<ingredient, Void, Void>{
        private final AppDatabase mdb;
        removeAsync(AppDatabase db){mdb = db;}

        @Override
        protected Void doInBackground(final ingredient... params){
            mdb.ingredientDAO().deleteAnIngredient(params[0]);
            return null;
        }
    }

    static ArrayList<ingredient> ing;

    public static void setIngredient(ArrayList<ingredient> i)
    {
        ing = i;
    }

    public ArrayList<ingredient> getIngredients()
    {
        return ing;
    }
}