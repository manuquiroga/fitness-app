import FoodModels.Food;
import Handlers.FileHandler;
import Handlers.Intermediary;
import Handlers.JSONHandler;
import UI.Home;
import UI.Profile;
import Users.AdminUser;
import Users.Objective;
import Users.User;
import Users.UserData;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AdminUser adminUser = new AdminUser();
        Intermediary intermediary = new Intermediary();
        ArrayList<User> users= JSONHandler.readUserFile();
        System.out.println(users);

        for (int i = 0; i<users.size() ; i++){
            intermediary.addUserToMap(users.get(i));
        }
    }
}