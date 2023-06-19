
package Handlers;

import Collections.GenericMap;
import Exceptions.FoodNotInMapException;
import Exceptions.UserNotInMapException;
import FoodModels.Food;
import Users.BasicUser;
import Users.PremiumUser;
import Users.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * This class works as a connector between all the classes and is used to avoid working directly with files.
 */
public class Intermediary {
    private GenericMap<String,User> userMap;
    private GenericMap<Integer, Food> foodMap;

    /**
     * Constructs a new instance of Intermediary.
     */
    public Intermediary(){
        userMap = new GenericMap<String,User>();
        foodMap = new GenericMap<Integer, Food>();
    }

    /**
     * Adds an object of the class User to the map of users.
     * @param user an object of the User class to be added
     */
    public void addUserToMap (User user){
        if(!userMap.containsKey(user.getEmail()) && !userMap.containsValue(user)){
            userMap.put(user.getEmail(),user);
        }
    }

    /**
     * Updates a user in the map of users, with a security check if the user's email changes (email is used as the key).
     * @param email the email of the user
     * @param user an object of the User class to be modified
     * @throws JSONException if the value is non-finite number or if the key is null
     */
    public void updateUser (String email, User user) throws JSONException {
        JSONObject joAux=new JSONObject();
        if(!email.equals(user.getEmail())) {
            if (!userMap.containsKey(user.getEmail()) && userMap.containsKey(email)) {
                userMap.removeByKey(email);
                userMap.put(user.getEmail(), user);
                joAux = userToJSON();
                FileHandler.rewriteFile(joAux, "user");
            }
        }else if(userMap.containsKey(email)){
            userMap.removeByKey(email);
            userMap.put(user.getEmail(), user);
            joAux = userToJSON();
            FileHandler.rewriteFile(joAux, "user");
        }
    }

    /**
     * deletes a user in the map of users and rewrites the user file.
     * @param user an object of the User class to be deleted.
     * @throws JSONException if the value is non-finite number or if the key is null
     */
    public void deleteUser(User user) throws JSONException {
        JSONObject joAux=new JSONObject();
        if(userMap.containsKey(user.getEmail()))
        {
            userMap.removeByKey(user.getEmail());
            joAux=userToJSON();
            FileHandler.rewriteFile(joAux, "user");
        }
    }
    /**
     * deletes a user in the map of users and rewrites the user file.
     * @param email a string with the email of the user to be deleted.
     * @throws JSONException if the value is non-finite number or if the key is null
     */
    public void deleteUser(String email) throws JSONException {
        JSONObject joAux=new JSONObject();
        if(userMap.containsKey(email))
        {
            userMap.removeByKey(email);
            joAux=userToJSON();
            FileHandler.rewriteFile(joAux, "user");
        }
    }

    public String showMapUsers(){return userMap.toString();}

    /**
     * Adds a Food object to the map of foods.
     * @param food a Food object to be added
     */
    public void addFoodToMap (Food food){
        if (!foodMap.containsKey(food.getId()) && !foodMap.containsValue(food)){
            foodMap.put(food.getId(),food);
        }
    }

    /**
     * deletes a food object in the map of foods and rewrites the foods file.
     * @param food a food object to be deleted.
     * @throws JSONException if the value is non-finite number or if the key is null
     */
    public void deleteFood(Food food) throws JSONException {
        JSONObject joAux;
        if(foodMap.containsKey(food.getId()))
        {
            foodMap.removeByKey(food.getId());
            refactorFoodIDs();
            joAux=foodToJSON();
            FileHandler.rewriteFile(joAux, "food");
        }
    }

    /**
     * deletes a food object in the map of foods and rewrites the foods file.
     * @param id the if of the food object to be deleted.
     * @throws JSONException if the value is non-finite number or if the key is null
     */
    public void deleteFood(int id) throws JSONException {
        JSONObject joAux;
        if(foodMap.containsKey(id));
        {
            foodMap.removeByKey(id);
            refactorFoodIDs();
            joAux=foodToJSON();
            FileHandler.rewriteFile(joAux, "food");
        }
    }

    /**
     * Converts the user map to a list.
     * @return The list of users.
     */
    public List<User> usersToList(){
        return userMap.toList();
    }
    /**
     * Converts the food map to a list.
     * @return The list of foods.
     */
    public List<Food> foodToList(){
        return foodMap.toList();
    }

    /**
     * Moves all the meals from the map to a list, reassigns the IDs, and recreates the meal map.
     */
    public void refactorFoodIDs()
    {
        List<Food> foodList =foodMap.toList();
        for (int i = 0; i < foodList.size(); i++) {
            foodList.get(i).setId(i+1);
        }
        listToMap(foodList);
    }
    /**
     * Converts the food list to a map and updates the food map.
     * @param foodList The list of foods.
     */
    public void listToMap(List<Food> foodList)
    {
        foodMap=new GenericMap<Integer, Food>();
        for (Food food : foodList)
        {
          addFoodToMap(food);
        }
    }

    /**
     * Returns a string representation of the food map.
     * @return The string representation of the food map.
     */
    public String showFoodMap (){
        return foodMap.toString();
    }

    /**
     * translates the map of users to a JSONObject.
     * @return a JSONObject with all the users.
     * @throws JSONException if the value is non-finite number or if the key is null
     */
    public JSONObject userToJSON() throws JSONException {
        List<User> userList=userMap.toList();
        JSONArray basicArray = new JSONArray();
        JSONArray premiumArray = new JSONArray();
        JSONArray adminArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i) instanceof PremiumUser){
                PremiumUser aux = (PremiumUser) userList.get(i);
                premiumArray.put(aux.toJSON());
            }else if(userList.get(i) instanceof BasicUser){
                basicArray.put(userList.get(i).toJSON());
            }else{
                adminArray.put(userList.get(i).toJSON());
            }
        }
        jsonObject.put("premium_users",premiumArray);
        jsonObject.put("basic_users",basicArray);
        jsonObject.put("admin_users",adminArray);
        return jsonObject;
    }
    /**
     * translates the map of foods to a JSONObject.
     * @return a JSONObject with all the foods.
     * @throws JSONException if the value is non-finite number or if the key is null
     */
    public JSONObject foodToJSON() throws JSONException {
        List<Food> foodList=foodMap.toList();
        JSONArray foodArray=new JSONArray();
        JSONObject jo = new JSONObject();
        for (int i = 0; i < foodList.size(); i++) {
            foodArray.put(foodList.get(i).toJSON());
        }
        jo.put("foods", foodArray);
        return jo;
    }

    /**
     * searches for a food object in the food map.
     * @param foodID the ID of the food to search for
     * @return An instance of the Food class with the information of the found meal.
     * @throws FoodNotInMapException If the meal does not exist in the map.
     */
    public Food searchFood (int foodID) throws FoodNotInMapException {
        Food foodFound;
        if(foodMap.containsKey(foodID))
        {
            foodFound=foodMap.searchByKey(foodID);
        }else {
            throw new FoodNotInMapException();
        }
        return foodFound;
    }

    /**
     * searches for a user object in the user map.
     * @param email the email of the user to search for
     * @return An instance of the User class with the information of the found user.
     * @throws UserNotInMapException If the user does not exist in the map.
     */
    public User searchUser (String email) throws UserNotInMapException {
        User userFound;
        if(userMap.containsKey(email))
        {
            userFound=userMap.searchByKey(email);
        }else{
            throw new UserNotInMapException();
        }
        return userFound;
    }

    /**
     * adds a food object to the foods file.
     * @param food the food object to be added
     * @throws JSONException if the value is non-finite number or if the key is null
     */
    public void addFoodToFile(Food food) throws JSONException {
        JSONObject joAux;
        List<Food> foodList=foodMap.toList();
        food.setId(foodList.size()+1);
        if(!foodMap.containsKey(food.getId()))
        {
            joAux=foodToJSON();
            FileHandler.rewriteFile(joAux,"foods");
        }
    }
}
