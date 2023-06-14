
package Handlers;

import Collections.GenericMap;
import FoodModels.Food;
import Users.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Intermediary {


    private GenericMap<String,User> userMap;
    private GenericMap<Integer, Food> foodMap;


    //User
    public void addUserToMap (User user){
        if(!userMap.containsKey(user.getEmail()) && !userMap.containsValue(user)){
            userMap.put(user.getEmail(),user);
        }
    }

    public void updateUser (String email, User user){
        User aux=null;
        if(userMap.containsKey(user.getEmail()))
        {
            aux=userMap.removeByKey(email);
            userMap.put(user.getEmail(), user);
        }
    }

    //public deactivateUser()

    public String showMapUsers(){return userMap.toString();}

    //Food

    public void addFoodToMap (Food food){
        if (!foodMap.containsKey(food.getId()) && !foodMap.containsValue(food)){
            foodMap.put(food.getId(),food);
        }
    }

    public String showTreeFood (){return foodMap.toString();}

}
