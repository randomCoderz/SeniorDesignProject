package com.pr.pr.pantryraid;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by Kan on 2/26/18.
 */
class pantry extends Thread
{
    HttpResponse<JsonNode> response_return;
    String http;
    private String KEY;

    public pantry(String key) {
        http = "";
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

            HttpResponse<JsonNode> response = Unirest.get(http)
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
