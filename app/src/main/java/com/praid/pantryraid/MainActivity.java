package com.praid.pantryraid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    void test() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/products/classify")
                .header("X-Mashape-Key", "OkS1xENCS8mshJ1RJzjn5X9y1Ij5p11nRF2jsnMlSkI0S2WUan")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body("{\"title\":\"Kroger Vitamin A & D Reduced Fat 2% Milk\",\"upc\":\"\",\"plu_code\":\"\"}")
                .asJson();
    }
}
