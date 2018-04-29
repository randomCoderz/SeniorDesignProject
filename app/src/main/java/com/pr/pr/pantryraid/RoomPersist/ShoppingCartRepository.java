package com.pr.pr.pantryraid.RoomPersist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pr.pr.pantryraid.ingredient;
import com.pr.pr.pantryraid.recipe;

import java.util.List;

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

    public void insertShoppingCartItemList(List<shoppingCartItems> r){
        new insertListAsync(gdb).execute(r);
    }

    private static class insertListAsync extends AsyncTask<List<shoppingCartItems>, Void, Void>{
        private final AppDatabase mdb;
        insertListAsync(AppDatabase db){mdb = db;}

        @Override
        protected Void doInBackground(final List<shoppingCartItems>... params){
            mdb.shoppingcartDAO().insertShoppingCartItemList(params[0]);
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

    public void getAllCartItems(){
        new scAllItemListAsync(gdb).execute();
    }

    private static class scAllItemListAsync extends AsyncTask<Void, Void, List<shoppingCartItems>>{
        private final AppDatabase mdb;
        scAllItemListAsync(AppDatabase db){mdb = db;}

        @Override
        protected List<shoppingCartItems> doInBackground(final Void... params){
            return mdb.shoppingcartDAO().getAllCartItems();
        }

        @Override
        protected void onPostExecute(List<shoppingCartItems> r){
            for (int i = 0; i < r.size(); i++) {
                System.out.println(r.get(i).getName());
            }
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