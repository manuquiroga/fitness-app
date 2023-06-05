
package Handlers;

import FoodModels.Food;
import Users.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Intermediary {

    /*
    private TreeMap<String,User> userTreeMap;
    private ArrayList<Food> foodTreeMap;


    //User
    public void addUserToTreeMap (User user){
        if(!userTreeMap.containsKey(user.getEmail())){
            userTreeMap.put(user.getEmail(),user);
        }
    }

    //public void updateUser (User user)

    //public deactivateUser()

    public String showTreeUsers(){
        ArrayList<Map.Entry<String,User>> usersArray = new ArrayList<>(userTreeMap.entrySet());
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String,User> entry:usersArray) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(", ");
            stringBuilder.append(entry.getValue());
        }
        return stringBuilder.toString();
    }

    //Food

    public void addFoodToTreeMap (Food food){
        if (!foodTreeMap.containsKey(food)){
            foodTreeMap.put(food.getId(),food);
        }
    }

    public String showTreeFood (){
        ArrayList<Map.Entry<Integer,Food>> foodArray = new ArrayList<>(foodTreeMap.entrySet());
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer,Food> entry:foodArray) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(", ");
            stringBuilder.append(entry.getValue());
        }
        return stringBuilder.toString();
    }
    */
}
