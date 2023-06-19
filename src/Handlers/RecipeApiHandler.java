package Handlers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * "This class is responsible for handling the recipe API."
 */
public class RecipeApiHandler {
    private static final String API_URL = "https://api.edamam.com/api/recipes/v2";
    private static final String APP_ID = "5195c7e1";
    private static final String APP_KEY = "89c6979c6c97d1c5c9b2f70bd4919894";

    /**
     * retrieves the response from the API.
     * @param query a string with the name of the food to search for
     * @return a string with the JSON response
     */
    public static String searchRecipe(String query) {

        try {
            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            String url = API_URL + "?type=public&q=" + encodedQuery + "&app_id=" + APP_ID + "&app_key=" + APP_KEY;

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            return response.toString();

        } catch (IOException e) {
            System.err.println("Search recipe error: "+e.getMessage());
        }
        return "";
    }

    /**
     * Provides the first URL of the recipe.
     * @param jsonResponse a string with the JSON response from the recipe API
     * @return a string with the first URL of the recipe for the food
     */
    public static String getRecipeUrl(String jsonResponse){
        String url = "";
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray hits = jsonObject.getJSONArray("hits");
            JSONObject firstRecipe = (JSONObject) hits.get(0);
            JSONObject firstRecipeFinal = firstRecipe.getJSONObject("recipe");
            url = firstRecipeFinal.getString("url");

        } catch (JSONException e) {
            System.err.println("Get recipe url error: "+e.getMessage());
        }
        return url;
    }

    /**
     * opens the URL in your browser.
     * @param url a string with the url
     * @author Manuel Quiroga
     */
    public static void openBrowser(String url) throws IOException, URISyntaxException {

        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            URI uri = new URI(url);
            desktop.browse(uri);
        }

    }
}
