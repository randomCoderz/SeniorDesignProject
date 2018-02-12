package com.pr.pr.pantryraid;

//Android Tools
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

//Unirest, Spoonacular Imports, JSON
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;
import org.json.JSONArray;

//FireBase Imports
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;

public class main extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY = "Y2arFIdXItmsh3d4HlBeB2ar1Zdzp17aqmJjsnUYGxgm2KHYG5";
    private static final String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/search?diet=vegetarian&excludeIngredients" +
            "=coconut&instructionsRequired=true&intolerances=egg%2C+gluten&limitLicense=false&number=10&offset=0&query=burger&type=main+course";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            cookBook p = new cookBook();

            p.setCommand("getRecipe");
//            String[] ingredients = {"strawbery", "appls", "oranges"};
//            p.getRecipesByIngredients(false, ingredients, false, 5, 1);
            p.getInstructions(641803, true);
            p.start();
            p.join();
            HttpResponse<JsonNode> response = p.getResponse();


//            JSONObject object = response.getBody().getObject();
            JSONArray array = response.getBody().getArray();
            JSONObject object = array.getJSONObject(0).getJSONArray("steps").getJSONObject(0);

//            System.out.println(array.toString(2));

            TextView text = (TextView) findViewById(R.id.text);
            text.setMovementMethod(new ScrollingMovementMethod());
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

    class cookBook extends Thread {
        HttpResponse<JsonNode> response_return;
        String http;
        String command;

        public cookBook()
        {
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

    class Home extends Thread
    {
        HttpResponse<JsonNode> response_return;
        String http;
        String command;

        public Home() {
            http = "";
            command = "";
        }

        /**
         * Searches for recipes (Everything but query is optional)
         * @param cuisine The cuisine(s) of the recipes (african, chinese, japanese, korean, vietnamese, thai, indian, british, irish, french, italian, mexican, spanish, middle eastern, jewish, american, cajun, southern, greek, german, nordic, eastern european, caribbean, or latin american)
         * @param diet The diet to which the recipes must be compliant. Possible values are: pescetarian, lacto vegetarian, ovo vegetarian, vegan, and vegetarian.
         * @param excludeIngredients ingredients or ingredient types that should not be contained in recipes
         * @param instructionsRequired whether recipes must have instructions
         * @param intolerances list of intolerances (dairy, egg, gluten, peanut, sesame, seafood, shellfish, soy, sulfite, tree nut, and wheat)
         * @param limitLicense Whether the recipes should have an open license that allows for displaying with proper attribution.
         * @param number The number of results to return (between 0 and 100)
         * @param offset The number of results to skip (between 0 and 900).
         * @param query The (natural language) recipe search query.
         * @param type the type of the recipes. (main course, side dish, dessert, appetizer, salad, bread, breakfast, soup, beverage, sauce, or drink)
         */
        public void searchRecipes(String[] cuisine, String diet[], String[] excludeIngredients, boolean instructionsRequired, String[] intolerances, boolean limitLicense, int number, int offset, String query, String type)
        {
            command = "search";

            http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/search?";
            if(cuisine.length > 0)
            {
                http += "cuisine=";
                for(int i = 0; i < cuisine.length; i++)
                {
                    if(i != cuisine.length - 1)
                    {
                        http += (cuisine[i] + "%2C");
                    }
                    else
                    {
                        http += cuisine[i] + "&";
                    }
                }
            }
            if(diet.length > 0)
            {
                http += "diet=";
                for(int i = 0; i < diet.length; i++)
                {
                    if(i != diet.length - 1)
                    {
                        http += (diet[i] + "%2C");
                    }
                    else
                    {
                        http += diet[i] + "&";
                    }
                }
            }
            if(excludeIngredients.length > 0)
            {
                http += "excludeIngredients=";
                for(int i = 0; i < excludeIngredients.length; i++)
                {
                    if(i != excludeIngredients.length - 1)
                    {
                        http += (excludeIngredients[i] + "%2C");
                    }
                    else
                    {
                        http += excludeIngredients[i] + "&";
                    }
                }
            }
            http += "instructionsRequired=" + instructionsRequired;
            if(intolerances.length > 0)
            {
                http += "intolerances=";
                for(int i = 0; i < intolerances.length; i++)
                {
                    if(i != intolerances.length - 1)
                    {
                        http += (intolerances[i] + "%2C");
                    }
                    else
                    {
                        http += intolerances[i] + "&";
                    }
                }
            }
            http += "limitLicense=" + limitLicense;
            if(offset > 0)
            {
                http += "offset=" + offset + "&";
            }
            http += "query=" + query;
            if(!type.equals(null))
            {
                http +="&type=" + type;
            }
        }


        /**
         * find random (popular) recipes
         * @param limitLicense Whether the recipes should have an open license that allows for displaying with proper attribution
         * @param number The number of random recipes to be returned. Must be in interval [1,100]. NOTE: Each random recipe returned counts as one request.
         * @param tags Tags that the random recipe(s) must adhere to
         */
        public void randomRecipe(boolean limitLicense, int number, String[] tags)
        {
            command = "random";

            http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?";
            http += "limitLicense=" + limitLicense;
            http += "&number=" + number;
            http += "&tags=";
            for(int i = 0; i < tags.length; i++)
            {
                if(i != tags.length - 1)
                {
                    http += (tags[i] + "%2C");
                }
                else
                {
                    http += tags[i];
                }
            }
        }

        public void run() {
            try
            {
                if(command.equals("random") || command.equals("search"))
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

        public Pantry() {
            http = "";
            command = "";
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