import FoodModels.Food;
import Handlers.FileHandler;
import Handlers.JSONHandler;
import Users.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        //ArrayList<Food> foods = JSONHandler.readFoodFile();
        //System.out.println(foods);

        ArrayList<User> users = JSONHandler.readUserFile();
        System.out.println(users.get(0).getUserData().calculateBMR());
        System.out.println(users.get(0));
    }


}