package Handlers;

import FoodModels.Food;
import Users.BasicUser;
import Users.PremiumUser;
import Users.User;
import Users.UserData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JSONHandler {

    private static Food foodConverter (JSONObject aux) throws JSONException{

        Food food = new Food();
        food.setName(aux.getString("name"));
        food.setFoodType(aux.getString("type"));
        food.setId(aux.getInt("id"));
        food.setServingSize_g(aux.getInt("servingSize_g"));
        food.setCalories(aux.getDouble("calories"));
        food.setProteins_g(aux.getDouble("proteins_g"));
        food.setCarbohydrates_g(aux.getDouble("carbohydrates_g"));
        food.setFats_g(aux.getDouble("fats_g"));
        food.setVegan(aux.getBoolean("isVegan"));
        food.setCeliac(aux.getBoolean("isCeliac"));
        food.setVegetarian(aux.getBoolean("isVegetarian"));

        JSONArray jaIngredients = aux.getJSONArray("ingredients");
        for (int j = 0; j < jaIngredients.length(); j++) {
            food.addIngredient(jaIngredients.getString(j));
        }

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
            JSONArray jsonArrayUser = jsonObjectUser.getJSONArray("users");
            JSONArray jsonArrayPremium = jsonObjectUser.getJSONArray("premium_users");

            for (int i = 0; i < jsonArrayUser.length(); i++) {
                JSONObject joFromUsersArray = jsonArrayUser.getJSONObject(i);
                User user = new BasicUser();
                user.setId(joFromUsersArray.getString("id"));
                user.setName(joFromUsersArray.getString("name"));
                user.setEmail(joFromUsersArray.getString("email"));
                user.setPassword(joFromUsersArray.getString("password"));
                UserData userData = new UserData();
                JSONObject joUserData = joFromUsersArray.getJSONObject("userData");
                userData.setAge(joUserData.getInt("age"));
                userData.setSex(joUserData.getString("sex"));
                userData.setHeight(joUserData.getInt("height"));
                userData.setWeight(joUserData.getInt("weight"));
                userData.setObjective(joUserData.getString("objective"));
                userData.setPhysicalActivity(joUserData.getString("physicalActivity"));
                ArrayList<Food> dietList = new ArrayList<>();
                JSONArray jaDietArray = joUserData.getJSONArray("diet");
                for (int j = 0; j < jaDietArray.length(); j++) {
                    JSONObject joFood = jaDietArray.getJSONObject(j);
                    dietList.add(foodConverter(joFood));
                }
                userData.setDiet(dietList);
                user.setUserData(userData);
                userList.add(user);
            }

            for (int i = 0; i < jsonArrayPremium.length(); i++) {
                JSONObject joFromUsersArray = jsonArrayPremium.getJSONObject(i);
                PremiumUser user = new PremiumUser();
                user.setId(joFromUsersArray.getString("id"));
                user.setName(joFromUsersArray.getString("name"));
                user.setEmail(joFromUsersArray.getString("email"));
                user.setPassword(joFromUsersArray.getString("password"));
                user.setNumberOfDietsGenerated(joFromUsersArray.getInt("diets_generated"));
                UserData userData = new UserData();
                JSONObject joUserData = joFromUsersArray.getJSONObject("userData");
                userData.setAge(joUserData.getInt("age"));
                userData.setSex(joUserData.getString("sex"));
                userData.setHeight(joUserData.getInt("height"));
                userData.setWeight(joUserData.getInt("weight"));
                userData.setObjective(joUserData.getString("objective"));
                userData.setPhysicalActivity(joUserData.getString("physicalActivity"));
                ArrayList<Food> dietList = new ArrayList<>();
                JSONArray jaDietArray = joUserData.getJSONArray("diet");
                for (int j = 0; j < jaDietArray.length(); j++) {
                    JSONObject joFood = jaDietArray.getJSONObject(j);
                    dietList.add(foodConverter(joFood));
                }

                userData.setDiet(dietList);
                user.setUserData(userData);
                userList.add(user);
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

