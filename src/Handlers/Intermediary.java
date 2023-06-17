
package Handlers;

import Collections.GenericMap;
import FoodModels.Food;
import Interfaces.IToJSON;
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
        JSONObject joAux = new JSONObject();
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

    public void deleteUser(String email, User user) throws JSONException {
        User aux=null;
        JSONObject joAux=new JSONObject();
        if((!userMap.containsKey(user.getEmail())) && userMap.containsKey(email))
        {
            aux=userMap.removeByKey(email);
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

    public void deleteFood(int id, Food food) throws JSONException {
        Food aux=null;
        JSONObject joAux=new JSONObject();
        if(foodMap.containsKey(food.getId()))
        {
            aux=foodMap.removeByKey(id);
            joAux=userToJSON();
            FileHandler.rewriteFile(joAux, "food");
        }
    }

    public String showTreeFood (){return foodMap.toString();}

    public JSONObject userToJSON() throws JSONException {
        List<User> userList=userMap.toList();
        JSONArray userArray=new JSONArray();
        JSONObject jo = new JSONObject();
        for (int i = 0; i < userList.size(); i++) {
            userArray.put(userList.get(i).toJSON());
        }
        jo.put("data", userArray);
        return jo;
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


}
