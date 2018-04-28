package com.pr.pr.pantryraid.RoomPersist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pr.pr.pantryraid.ingredient;
import com.pr.pr.pantryraid.recipe;

public class ShoppingCartRepository {
    private AppDatabase gdb;

    public ShoppingCartRepository(AppDatabase data){
        gdb = data;
    }

    //Insert Recipe into Database
    public void insertShoppingCartItem(shoppingCartItems r){
        new insertAsync(gdb).execute(r);
    }

    private static class insertAsync extends AsyncTask<shoppingCartItems, Void, Void>{
        private final AppDatabase mdb;
        insertAsync(AppDatabase db){mdb = db;}

        @Override
        protected Void doInBackground(final shoppingCartItems... params){
            mdb.shoppingcartDAO().insertShoppingCartItem(params[0]);
            return null;
        }
    }

    //Get Recipe from by ID from Database
    public void getShoppingCartItemFromID(int id){
        new scItemAsync(gdb).execute(id);
    }

    private static class scItemAsync extends AsyncTask<Integer, Void, shoppingCartItems>{
        private final AppDatabase mdb;
        scItemAsync(AppDatabase db){mdb = db;}

        @Override
        protected shoppingCartItems doInBackground(final Integer... params){
            return mdb.shoppingcartDAO().getCartItemsFromID(params[0]);
        }

        @Override
        protected void onPostExecute(shoppingCartItems r){
            System.out.println(r.getName());
        }

    }


    //Remove Recipe from Database
    public void removeCartItem(shoppingCartItems r){
        new removeAsync(gdb).execute(r);
    }

    private static class removeAsync extends AsyncTask<shoppingCartItems, Void, Void>{
        private final AppDatabase mdb;
        removeAsync(AppDatabase db){mdb = db;}

        @Override
        protected Void doInBackground(final shoppingCartItems... params){
            mdb.shoppingcartDAO().deleteACartItem(params[0]);
            return null;
        }
    }
}