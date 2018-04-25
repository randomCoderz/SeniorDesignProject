package com.pr.pr.pantryraid.RoomPersist;

import android.os.AsyncTask;

public class PantryRepository {

    private AppDatabase mdb;

    public PantryRepository(AppDatabase db){ mdb = db; }

    //Insert Recipe into Database
    public void insertPantry(pantryDB p){
        new insertPantryAsync(mdb).execute(p);
    }

    private static class insertPantryAsync extends AsyncTask<pantryDB, Void, Void> {
        private final AppDatabase mdb;
        insertPantryAsync(AppDatabase db){mdb = db;}

        @Override
        protected Void doInBackground(final pantryDB... params){
            mdb.pantrydao().insertPantry(params[0]);
            return null;
        }
    }
}
