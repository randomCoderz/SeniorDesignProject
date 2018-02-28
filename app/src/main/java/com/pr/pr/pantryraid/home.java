package com.pr.pr.pantryraid;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by Kan on 2/25/18.
 */
class home extends Thread
{
    HttpResponse<JsonNode> response_return;
    String http;
    String command;
    private String KEY;

    public home(String key) {
        http = "";
        command = "";
        KEY = key;
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
        http += "instructionsRequired=" + instructionsRequired;
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

}
