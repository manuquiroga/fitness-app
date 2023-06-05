import FoodModels.Food;
import Handlers.JSONHandler;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<Food> foods = JSONHandler.readFoodFile();

        System.out.println(foods);
    }


}