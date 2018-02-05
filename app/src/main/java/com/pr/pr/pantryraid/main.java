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
import android.widget.TextView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            networkConnection p = new networkConnection();
            p.start();
            p.join();

            HttpResponse<JsonNode> response = p.getResponse();
//            System.out.println(response.getBody().getObject().toString());
            JSONObject object = response.getBody().getObject();
//            System.out.println(object.toString(2));

            TextView text = (TextView) findViewById(R.id.text);
            text.setText(object.toString(2));

        }
        catch(Exception e){

        }
    }

    class networkConnection extends Thread {
        HttpResponse<JsonNode> response_return;

        public void run() {
            try{
                HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/search?diet=vegetarian&excludeIngredients=coconut&instructionsRequired=true&intolerances=egg%2C+gluten&limitLicense=false&number=10&offset=0&query=burger&type=main+course")
                        .header("X-Mashape-Key", "5EiGHCnHhQmshhJo3ecXqsynDWfip1v46Iwjsn83KwprhwkP1v")
                        .header("Accept", "application/json")
                        .asJson();
                setResponse(response);
            }
            catch (UnirestException e) {
                e.getStackTrace();
            }
        }

        public void setResponse(HttpResponse<JsonNode> response){
            response_return = response;
        }

        public HttpResponse<JsonNode> getResponse()
        {
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