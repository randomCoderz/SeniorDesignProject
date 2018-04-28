package com.pr.pr.pantryraid.RoomPersist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pr.pr.pantryraid.ingredient;
import com.pr.pr.pantryraid.recipe;

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
            System.out.println(r.getName());
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
}