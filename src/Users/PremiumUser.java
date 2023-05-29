package Users;

import FoodModels.Food;

import java.util.ArrayList;

public class PremiumUser extends User{


    //Builders:
    public PremiumUser(String name, String password, String email, int id, UserData userData) {
        super(name, password, email, id, userData);
    }

    public PremiumUser() {
    }

    //equals, hashCode, compareTo, toString

    //Methods:

    public ArrayList<Food> modifyDiet()
    {
        return diet;
    }

    public ArrayList<Food> notIncludeInDiet(StringBuilder notInclude)
    {
        return diet;
    }
}
