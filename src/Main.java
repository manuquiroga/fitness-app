import FoodModels.Food;
import Handlers.FileHandler;
import Handlers.JSONHandler;
import UI.Profile;
import Users.Objective;
import Users.User;
import Users.UserData;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /*UserData userData = new UserData(23, 50, "LOSE_WEIGHT", 160, "female", "NONE");
        User user = new User("m", "Prueba123456", "mq@gmail.com", 10, userData);

        System.out.println(user.getCaloriesObjective());
        user.generateDiet(4, "vegan");
        System.out.println(user);*/

        UserData userData = new UserData();
        User user = new User("gabriel","gabriel1234","crisantoing@gmail.com",1,userData);
        Profile profile = new Profile(user);
    }


}