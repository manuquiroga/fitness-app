package Users;

import FoodModels.Food;
import org.json.JSONException;
import org.json.JSONObject;

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

    public void setNumberOfDietsGenerated(int numberOfDietsGenerated) {
        this.numberOfDietsGenerated = numberOfDietsGenerated;
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

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put("diets_generated",numberOfDietsGenerated);
        return jsonObject;
    }

    //Methods:


}
