package Handlers;

import FoodModels.Food;
import Users.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * This class is for handling JSON files and converting their information to JSON format.
 */
public class JSONHandler {

    /**
     * Instantiates a food object from a JSON object
     * @param aux a JSON object with the information of the food.
     * @return a Food object with the information from the JSON object.
     * @throws JSONException if the key is not found or if the value is not of the expected type.
     */
    public static Food foodConverter (JSONObject aux) throws JSONException{
        Food food=new Food();
        food.IFromJSON(aux);
        return food;
    }

    /**
     * Reads from the food file and inserts them into an ArrayList
     * @return an ArrayList containing the foods from a file.
     */
    public static ArrayList<Food> readFoodFile() {
        String jsonResponse = FileHandler.read("foods");

        ArrayList<Food> foodList = new ArrayList<>();

        try {
            JSONObject jsonObjectFood = new JSONObject(jsonResponse);
            JSONArray jsonArrayFood = jsonObjectFood.getJSONArray("foods");

            for (int i = 0; i < jsonArrayFood.length(); i++) {
                JSONObject joFromFoodsArray = jsonArrayFood.getJSONObject(i);
                foodList.add(foodConverter(joFromFoodsArray));
            }

        } catch (JSONException e) {
            System.err.println("Wrong formulated JSON"+e.getCause());
        }
        return foodList;
    }

    /**
     * Reads from the user file and inserts them into an ArrayList
     * @return an ArrayList containing the users from a file.
     */
    public static ArrayList<User> readUserFile() {
        String jsonResponse = FileHandler.read("user");

        ArrayList<User> userList = new ArrayList<>();

        try {
            JSONObject jsonObjectUser = new JSONObject(jsonResponse);
            JSONArray jsonArrayAdmins = jsonObjectUser.getJSONArray("admin_users");
            JSONArray jsonArrayBasicUsers = jsonObjectUser.getJSONArray("basic_users");
            JSONArray jsonArrayPremium = jsonObjectUser.getJSONArray("premium_users");

            for (int i = 0; i < jsonArrayAdmins.length(); i++) {
                AdminUser adminUser = new AdminUser();
                adminUser.IFromJSON(jsonArrayAdmins.getJSONObject(i));
                userList.add(adminUser);
            }

            for (int i = 0; i < jsonArrayPremium.length(); i++) {
                PremiumUser premiumUser = new PremiumUser();
                premiumUser.IFromJSON(jsonArrayPremium.getJSONObject(i));
                userList.add(premiumUser);
            }

            for (int i = 0; i < jsonArrayBasicUsers.length(); i++) {
                BasicUser basicUser = new BasicUser();
                basicUser.IFromJSON(jsonArrayBasicUsers.getJSONObject(i));
                userList.add(basicUser);
            }

        } catch (JSONException e) {
            System.err.println("Wrong formulated JSON"+e.getCause());
        }
        return userList;
    }

    /**
     * Transforms a user object into a JSON object and saves it in a file.
     * @param user a user object that we want to save in a file.
     * @throws JSONException if the value is non-finite number or if the key is null.
     */
    public static void userToFile(User user)throws JSONException
    {
        JSONObject jsonObject=user.toJSON();
        FileHandler.saveInFile(jsonObject, "user");
    }

}

