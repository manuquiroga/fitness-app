
package Handlers;

import Collections.GenericMap;
import Exceptions.FoodNotInMapException;
import Exceptions.UserNotInMapException;
import FoodModels.Food;
import Interfaces.IToJSON;
import Users.BasicUser;
import Users.PremiumUser;
import Users.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Intermediary {


    private GenericMap<String,User> userMap;
    private GenericMap<Integer, Food> foodMap;

    public Intermediary(){
        userMap = new GenericMap<String,User>();
        foodMap = new GenericMap<Integer, Food>();
    }


    //User
    public void addUserToMap (User user){
        if(!userMap.containsKey(user.getEmail()) && !userMap.containsValue(user)){
            userMap.put(user.getEmail(),user);
        }
    }

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

    public void deleteUser(User user) throws JSONException {
        JSONObject joAux=new JSONObject();
        if(userMap.containsKey(user.getEmail()))
        {
            userMap.removeByKey(user.getEmail());
            joAux=userToJSON();
            FileHandler.rewriteFile(joAux, "user");
        }
    }
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

    //Food

    public void addFoodToMap (Food food){
        if (!foodMap.containsKey(food.getId()) && !foodMap.containsValue(food)){
            foodMap.put(food.getId(),food);
        }
    }

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

    public List<User> usersToList(){
        return userMap.toList();
    }

    public List<Food> foodToList(){
        return foodMap.toList();
    }

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


    public void refactorFoodIDs()
    {
        List<Food> foodList =foodMap.toList();
        for (int i = 0; i < foodList.size(); i++) {
            foodList.get(i).setId(i+1);
        }
        listToMap(foodList);
    }

    public void listToMap(List<Food> foodList)
    {
        foodMap=new GenericMap<Integer, Food>();
        for (Food food : foodList)
        {
          addFoodToMap(food);
        }
    }
    public String showFoodMap (){return foodMap.toString();}

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

    public void addFoodToFile(Food food) throws JSONException {
        JSONObject joAux;
        List<Food> foodList=foodMap.toList();
        food.setId(foodList.size()+1);
        if(!foodMap.containsKey(food.getId()))
        {
            addFoodToMap(food);
            joAux=foodToJSON();
            FileHandler.saveInFile(joAux, "food");
        }
    }





}
