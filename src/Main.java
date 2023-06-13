import FoodModels.Food;
import Handlers.FileHandler;
import Handlers.JSONHandler;
import Users.Objective;
import Users.User;
import Users.UserData;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        UserData userData = new UserData(23, 80, "MAINTAIN_WEIGHT", 180, "female", "NONE");
        User user = new User("m", "Prueba123456", "mq@gmail.com", 10, userData);

        System.out.println(user.getCaloriesObjective());
        //user.generateDiet(5, "vegan");
        System.out.println(user);
    }


}