package com.pr.pr.pantryraid;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kan on 2/25/18.
 */
class home extends Thread
{
    HttpResponse<JsonNode> response_return;
    String http;
    private String KEY;

    public home(String key) {
        http = "";
        KEY = key;
    }

    /**
     * Searches for recipesDB (Everything but query is optional)
     * @param cuisine The cuisine(s) of the recipesDB (african, chinese, japanese, korean, vietnamese, thai, indian, british, irish, french, italian, mexican, spanish, middle eastern, jewish, american, cajun, southern, greek, german, nordic, eastern european, caribbean, or latin american)
     * @param diet The diet to which the recipesDB must be compliant. Possible values are: pescetarian, lacto vegetarian, ovo vegetarian, vegan, and vegetarian.
     * @param excludeIngredients ingredients or ingredient types that should not be contained in recipesDB
     * @param instructionsRequired whether recipesDB must have instructions
     * @param intolerances list of intolerances (dairy, egg, gluten, peanut, sesame, seafood, shellfish, soy, sulfite, tree nut, and wheat)
     * @param limitLicense Whether the recipesDB should have an open license that allows for displaying with proper attribution.
     * @param number The number of results to return (between 0 and 100)
     * @param offset The number of results to skip (between 0 and 900).
     * @param query The (natural language) recipe search query.
     * @param type the type of the recipesDB. (main course, side dish, dessert, appetizer, salad, bread, breakfast, soup, beverage, sauce, or drink)
     */

    public ArrayList<recipe> searchRecipes(String[] cuisine, String diet[], String[] excludeIngredients, boolean instructionsRequired, String[] intolerances, boolean limitLicense, int number, int offset, String query, String type) throws InterruptedException, JSONException {

        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipesDB/search?";
        if(cuisine != null)
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
        if(diet != null)
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
        if(excludeIngredients != null)
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
        if(instructionsRequired == true)
            http += "instructionsRequired=" + instructionsRequired + "&";
        if(intolerances != null)
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
        if(limitLicense == true)
            http += "limitLicense=" + limitLicense + "&";
        if(number > 0)
            http += "number=" + number;
        if(offset > 0)
        {
            http += "offset=" + offset;
        }
        http += "&query=" + query;
        if(type != null)
        {
            http +="&type=" + type;
        }

        ArrayList<recipe> recipeList = new ArrayList<>();

        start();
        join();
        HttpResponse<JsonNode> response = response_return;

        JSONObject object = response.getBody().getObject();
        JSONArray array = object.getJSONArray("results");
        for(int i = 0; i < array.length(); i++)
        {
            JSONObject recipe = array.getJSONObject(i);
            int id = recipe.getInt("id");
            String title = recipe.getString("title");

            String image = "https://spoonacular.com/recipeImages/";
            image += recipe.getString("image");


            int readyInMinutes = recipe.getInt("readyInMinutes");
            recipeList.add(new recipe(id, title, image, readyInMinutes));
        }
        return recipeList;
    }

    /**
     * Searches for recipesDB (Everything but query is optional)
     * @param instructionsRequired whether recipesDB must have instructions
     * @param number The number of results to return (between 0 and 100)
     * @param query The (natural language) recipe search query.
     */
    public ArrayList<recipe> searchRecipes(boolean instructionsRequired, int number, String query) throws InterruptedException, JSONException {

        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipesDB/search?";

        http += "instructionsRequired=" + instructionsRequired + "&";
        http += "number=" + number + "&";
        http += "query=" + query;

        ArrayList<recipe> recipeList = new ArrayList<>();

        start();
        join();
        HttpResponse<JsonNode> response = response_return;

        JSONObject object = response.getBody().getObject();
        JSONArray array = object.getJSONArray("results");
        for(int i = 0; i < array.length(); i++)
        {
            JSONObject recipe = array.getJSONObject(i);
            int id = recipe.getInt("id");
            String title = recipe.getString("title");

            String image = "https://spoonacular.com/recipeImages/";
            image += recipe.getString("image");


            int readyInMinutes = recipe.getInt("readyInMinutes");
            recipeList.add(new recipe(id, title, image, readyInMinutes));
        }
        return recipeList;
    }


    /**
     * find random (popular) recipesDB
     * @param limitLicense Whether the recipesDB should have an open license that allows for displaying with proper attribution
     * @param number The number of random recipesDB to be returned. Must be in interval [1,100]. NOTE: Each random recipe returned counts as one request.
     * @param tags Tags that the random recipe(s) must adhere to
     */
    public ArrayList<recipe> randomRecipe(boolean limitLicense, int number, String[] tags) throws InterruptedException, JSONException {

        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipesDB/random?";
        http += "limitLicense=" + limitLicense;
        http += "&number=" + number;
        if(tags != null)
        {
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

        ArrayList<recipe> recipeList = new ArrayList<>();

        start();
        join();
        HttpResponse<JsonNode> response = response_return;

        JSONArray array = response.getBody().getObject().getJSONArray("recipesDB");
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
        return recipeList;
    }

    public void run() {
        try
        {
                HttpResponse<JsonNode> response = Unirest.get(http)
                        .header("X-Mashape-Key", KEY)
                        .header("Accept", "application/json")
                        .asJson();
                setResponse(response);
        }
        catch (UnirestException e)
        {
            e.getStackTrace();
        }
    }

    public void setResponse(HttpResponse<JsonNode> response) {
        response_return = response;
    }


}
