import FoodModels.Food;
import FoodModels.FoodType;
import Users.PhysicalActivity;
import Users.User;
import Users.UserData;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Food food = new Food(1, "asd", 3, 4, 5, 6, 8, FoodType.BREAKFAST, false, false, false);
        UserData userData = new UserData(32, 75, 80, 175, "male", PhysicalActivity.MODERATE);
        userData.addFood(food);
        User user = new User("name", "password", "email", 1, userData);
    }


}