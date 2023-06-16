package Users;

import FoodModels.Food;

import java.util.ArrayList;

public class PremiumUser extends User{

    private int numberOfDietsGenerated;

    //constructors:
    public PremiumUser(String name, String password, String email, int id, UserData userData, int numberOfDietsGenerated) {
        super(name, password, email, id, userData);
        this.numberOfDietsGenerated=numberOfDietsGenerated;
    }

    public PremiumUser() {
    }

    //equals, hashCode, compareTo, toString

    //Methods:


}
