package Handlers;

import FoodModels.Food;
import Users.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JSONHandler {

    public static Food foodConverter (JSONObject aux) throws JSONException{

        Food food=new Food();
        food.IFromJSON(aux);
        return food;
    }


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

    public static void userToFile(User user)throws JSONException
    {
        JSONObject jsonObject=user.toJSON();
        FileHandler.saveInFile(jsonObject, "user");
    }
    public static void rewriteUserFile(User user)throws JSONException
    {
        JSONObject jsonObject=user.toJSON();
        FileHandler.rewriteFile(jsonObject, "user");
    }

    public static void foodToFile(Food food)throws JSONException
    {
        JSONObject jsonObject =food.toJSON();

        FileHandler.saveInFile(jsonObject, "food");
    }
    public static void rewriteFoodFile(Food food)throws JSONException
    {
        JSONObject jsonObject=food.toJSON();
        FileHandler.rewriteFile(jsonObject, "food");
    }

    public static int countItemsInUserJSON() throws FileNotFoundException {

        ArrayList<User> userList = readUserFile();
        return userList.size();
    }

}

