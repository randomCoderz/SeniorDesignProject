package com.pr.pr.pantryraid;

//Android Tools
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;

//Unirest, Spoonacular Imports, JSON
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


import java.util.ArrayList;
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

            setContentView(R.layout.activity_main);

            rv = findViewById(R.id.rv);

            LinearLayoutManager llm = new LinearLayoutManager(this);

            rv.setHasFixedSize(true);
            rv.setLayoutManager(llm);



        try {
            //make recipe list
            getRecipeInformation();
            //intialize adapter
            recipeRVAdapter adapter = new recipeRVAdapter(recipeList);

            rv.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }


    }


    private void initializeAdapter()
    {
        recyclerViewAdapter adapter = new recyclerViewAdapter(ingredientList);

        rv.setAdapter(adapter);
    }

    private void getRecipeInformation() throws JSONException, InterruptedException {

        recipeList = new ArrayList<>();
        home h = new home(KEY);

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
                String ingredient_name = ingredient.getString("name");
                String amount = ingredient.getString("originalString");
                String ingredient_image = ingredient.getString("image");
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
        // automatically handle clicks on the home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


