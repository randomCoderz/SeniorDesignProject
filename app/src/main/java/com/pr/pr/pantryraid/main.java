package com.pr.pr.pantryraid;

//Android Tools
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

//Unirest, Spoonacular Imports, JSON
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;

//FireBase Imports
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;

public class main extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5"; //Place Your Key Here!
    private static final String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/search?diet=vegetarian&excludeIngredients" +
            "=coconut&instructionsRequired=true&intolerances=egg%2C+gluten&limitLicense=false&number=10&offset=0&query=burger&type=main+course";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            networkConnection p = new networkConnection();
            p.start();
            p.join();
            HttpResponse<JsonNode> response = p.getResponse();
            JSONObject object = response.getBody().getObject();
            TextView text = (TextView) findViewById(R.id.text);
            text.setText(object.toString(2));
        } catch (Exception e) {
            System.err.println("Network Connection Error.");
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    class networkConnection extends Thread {
        HttpResponse<JsonNode> response_return;

        public void run() {
            try {
                HttpResponse<JsonNode> response = Unirest.get(URL)
                        .header("X-Mashape-Key", KEY)
                        .header("Accept", "application/json")
                        .asJson();
                setResponse(response);
            } catch (UnirestException e) {
                e.getStackTrace();
            }
        }

        public void setResponse(HttpResponse<JsonNode> response) {
            response_return = response;
        }

        public HttpResponse<JsonNode> getResponse() {
            return response_return;
        }

    }

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
