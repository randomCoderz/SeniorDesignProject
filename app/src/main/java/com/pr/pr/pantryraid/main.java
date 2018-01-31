package com.pr.pr.pantryraid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //////////////////////////////////////////////////////
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //networkConnection();
    }

//    public void networkConnection(){
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    HttpResponse<JsonNode> response = Unirest.post("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/products/classify")
//                            .header("X-Mashape-Key", "OkS1xENCS8mshJ1RJzjn5X9y1Ij5p11nRF2jsnMlSkI0S2WUan")
//                            .header("Content-Type", "application/json")
//                            .header("Accept", "application/json")
//                            .body("{\"title\":\"Kroger Vitamin A & D Reduced Fat 2% Milk\",\"upc\":\"\",\"plu_code\":\"\"}")
//                            .asJson();
//                } catch (UnirestException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
