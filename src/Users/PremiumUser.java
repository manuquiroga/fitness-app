package Users;

import FoodModels.Food;

import java.util.ArrayList;

public class PremiumUser extends User{

    private int numberOfDietsGenerated;

    //constructors:
    public PremiumUser(String name, String password, String email, int id, UserData userData) {
        super(name, password, email, id, userData);
        this.numberOfDietsGenerated = 0;
    }

    public PremiumUser() {
    }

    public int getNumberOfDietsGenerated() {
        return numberOfDietsGenerated;
    }

    public void addMaxDiet(){
        numberOfDietsGenerated++;
    }

    //equals, hashCode, compareTo, toString

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Object obj) {
        return super.compareTo(obj);
    }

    @Override
    public String toString() {
        return "PremiumUser{" +
                "numberOfDietsGenerated=" + numberOfDietsGenerated +
                '}';
    }

    //Methods:


}
