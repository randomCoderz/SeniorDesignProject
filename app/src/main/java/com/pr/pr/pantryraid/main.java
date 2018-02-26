package com.pr.pr.pantryraid;

//Android Tools
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

//Unirest, Spoonacular Imports, JSON
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class main extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";
    private static final String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/search?diet=vegetarian&excludeIngredients" +
            "=coconut&instructionsRequired=true&intolerances=egg%2C+gluten&limitLicense=false&number=10&offset=0&query=burger&type=main+course";


    private List<ingredient> ingredientList;
    private List<recipe> recipeList;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
//

//            super.onCreate(savedInstanceState);

//
            setContentView(R.layout.activity_main);

            rv = findViewById(R.id.rv);

            LinearLayoutManager llm = new LinearLayoutManager(this);

            rv.setHasFixedSize(true);
            rv.setLayoutManager(llm);

//            initializeData();
            initializeAdapter();

        try {
            getRecipeInformation();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }


    }

//    private void initializeData()
//    {
//        ingredientList = new ArrayList<>();
//        ingredientList.add(new ingredient("chicken", "chicken description", R.drawable.chicken));
//        ingredientList.add(new ingredient("pork", "pork description"));
//        ingredientList.add(new ingredient("beef", "beef description"));
//        ingredientList.add(new ingredient("chicken", "chicken description", R.drawable.chicken));
//    }

    private void initializeAdapter()
    {
        recyclerViewAdapter adapter = new recyclerViewAdapter(ingredientList);
        rv.setAdapter(adapter);
    }

    private void getRecipeInformation() throws JSONException, InterruptedException {

        recipeList = new ArrayList<>();
        Home h = new Home(KEY);

        h.randomRecipe(false, 5, null);
        h.start();
        h.join();
        HttpResponse<JsonNode> response = h.getResponse();


//        JSONObject object = response.getBody().getObject();
        JSONArray array = response.getBody().getObject().getJSONArray("recipes");
        for(int i = 0; i < array.length(); i++)
        {
            JSONObject object = array.getJSONObject(i);
            int id = object.getInt("id");
            String title = object.getString("title");
            String image = object.getString("image");
            int readyInMinutes = object.getInt("readyInMinutes");
            String instructions = object.getString("instructions");
            ArrayList<ingredient> ingredients = new ArrayList<>();
            JSONArray ingredient_array = object.getJSONArray("extendedIngredients");
            for(int j = 0; j < ingredient_array.length(); j++)
            {
                JSONObject ingredient = ingredient_array.getJSONObject(j);
                int ingredient_id = ingredient.getInt("id");
                String ingredient_name = object.getString("name");
                String amount = object.getString("originalString");
                String ingredient_image = object.getString("image");
                ingredients.add(new ingredient(ingredient_id, ingredient_name, amount, ingredient_image));
            }
            recipeList.add(new recipe(id, title, image, readyInMinutes, ingredients, instructions));

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

class cookBook extends Thread
{
    HttpResponse<JsonNode> response_return;
    String http;
    String command;
    private String KEY = "";

    public cookBook(String key)
    {
        KEY = key;
        http = "";
        command = "";
    }

    /**
     * Searching for recipes by ingredients, ***doesnt work if ingredients are mispelled*** possible solution popup exception
     * @param fillIngredients Add information about the used and missing ingredients in each recipe.
     * @param ingredients list of ingredients that the recipes should contain.
     * @param limitLicense Whether to only show recipes with an attribution license.
     * @param number The maximal number of recipes to return
     * @param ranking Whether to maximize used ingredients (1) or minimize missing ingredients (2) first
     */
    public void getRecipesByIngredients(boolean fillIngredients, String[] ingredients, boolean limitLicense, int number, int ranking)
    {
        command = "getRecipe";

        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?";
        http += "fillIngredients=" + fillIngredients;
        http += "&ingredients=";
        for(int i = 0; i < ingredients.length; i++)
        {
            if(i != ingredients.length - 1)
            {
                http += (ingredients[i] + "%2C");
            }
            else
            {
                http += ingredients[i];
            }
        }
        http += "&limitLicense=" + limitLicense;
        http += "&number=" + number;
        http += "&ranking=" + ranking;


    }

    public void getInstructions(int recipeID, boolean stepBreakdown)
    {

        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" +
                recipeID + "/analyzedInstructions?";
        http += "stepBreakdown=" + stepBreakdown;

    }

//        public String[] getIngredients

    public void run() {
        try {
            if(command.equals("getRecipe"))
            {
                HttpResponse<JsonNode> response = Unirest.get(http)
                        .header("X-Mashape-Key", KEY)
                        .header("Accept", "application/json")
                        .asJson();
                setResponse(response);
            }

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

    public void setCommand(String command_)
    {
        command = command_;
    }

}



class Pantry extends Thread
{
    HttpResponse<JsonNode> response_return;
    String http;
    String command;
    private String KEY;

    public Pantry(String key) {
        http = "";
        command = "";
        KEY = key;
    }

    public void searchIngredient(String intolerances, boolean metaInformation, int number, String query)
    {
        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/ingredients/autocomplete?";
        if(!intolerances.equals(null))
        {
            http += "intolerances=" + intolerances + "&";
        }
        http += "metaInformation=" + metaInformation + "&";
        if(number > 0)
        {
            http += "number=" + "&";
        }
        http += "query=" + query;
    }

    public void run() {
        try
        {
            if(command.equals("searchItem"))
            {
                HttpResponse<JsonNode> response = Unirest.get(http)
                        .header("X-Mashape-Key", KEY)
                        .header("Accept", "application/json")
                        .asJson();
                setResponse(response);
            }

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