package Users;

import FoodModels.Food;
import Handlers.Intermediary;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the administrator users. It includes administrator-specific methods.
 */
public class AdminUser extends User {

    private String positionAtCompany;

    /**
     * Constructs an empty AdminUser object.
     */
    public AdminUser() {
    }

    /**
     * Constructs an AdminUser object with the specified name, password, email, user data, and position at the company.
     * @param name the name of the admin user
     * @param password the password of the admin user
     * @param email the email of the admin user
     * @param userData the user data of the admin user
     * @param positionAtCompany the position of the admin user at the company
     */
    public AdminUser(String name, String password, String email, UserData userData, String positionAtCompany) {
        super(name, password, email, userData);
        this.positionAtCompany = positionAtCompany;
    }

    /**
     * Returns a string representation of all users using the specified intermediary.
     * @param intermediary the intermediary object to retrieve the user information from
     * @return a string representation of all users
     */
    public String getAllUsers(Intermediary intermediary) {
        return intermediary.showMapUsers();
    }

    /**
     * Returns a string representation of all foods using the specified intermediary.
     * @param intermediary the intermediary object to retrieve the food information from
     * @return a string representation of all foods
     */
    public String getAllFoods(Intermediary intermediary) {
        return intermediary.showFoodMap();
    }

    /**
     * Returns the position of the admin user at the company.
     * @return the position at the company
     */
    public String getPositionAtCompany() {
        return positionAtCompany;
    }

    /**
     * Sets the position of the admin user at the company.
     * @param positionAtCompany the position at the company
     */
    public void setPositionAtCompany(String positionAtCompany) {
        this.positionAtCompany = positionAtCompany;
    }

    /**
     * Deletes a user from the system.
     * @param intermediary the intermediary object for accessing user data
     * @param user the user to be deleted
     * @throws JSONException if there is an error with JSON data
     */
    public void deleteUser(Intermediary intermediary, User user) throws JSONException {
        intermediary.deleteUser(user);
    }
    /**
     * Deletes a user from the system by email.
     * @param intermediary the intermediary object for accessing user data
     * @param email the email of the user to be deleted
     * @throws JSONException if there is an error with JSON data
     */
    public void deleteUser(Intermediary intermediary, String email) throws JSONException {
        intermediary.deleteUser(email);
    }
    /**
     * Deletes a food object from the system.
     * @param intermediary the intermediary object for accessing food data
     * @param food the food to be deleted
     * @throws JSONException if there is an error with JSON data
     */
    public void deleteFood(Intermediary intermediary, Food food) throws JSONException {
        intermediary.deleteFood(food);
    }
    /**
     * Deletes a food object from the system by ID.
     * @param intermediary the intermediary object for accessing food data
     * @param id the ID of the food to be deleted
     * @throws JSONException if there is an error with JSON data
     */
    public void deleteFood(Intermediary intermediary, int id) throws JSONException {
        intermediary.deleteFood(id);
    }
    /**
     * Deletes a user's diet from the system by food ID.
     * @param intermediary the intermediary object for accessing user data
     * @param id the ID of the food in the user's diet to be deleted
     * @throws JSONException if there is an error with JSON data
     */
    public void deleteUserDiet(Intermediary intermediary, int id) throws JSONException{
        List<User> users= intermediary.usersToList();

        for (User user:users) {
            ArrayList<Food> userDiet = user.getUserData().getDiet();

            for(Food food:userDiet){
                if(food.getId() == id){
                    user.resetDiet();
                    intermediary.updateUser(user.getEmail(), user);
                }
            }
        }
    }

    /**
     * Adds a food object to the system.
     * @param intermediary the intermediary object for accessing food data
     * @param food the food to be added
     * @throws JSONException if there is an error with JSON data
     */
    public void addFood(Intermediary intermediary,Food food) throws JSONException{
        intermediary.addFoodToFile(food);
    }

    @Override
    public void fromJSON(JSONObject jo) throws JSONException
    {
        super.fromJSON(jo);
        setPositionAtCompany(jo.getString("position"));
    }
    @Override
    public JSONObject toJSON() throws JSONException
    {
        JSONObject aux=new JSONObject();
        aux=super.toJSON();
        aux.put("position", getPositionAtCompany());
        return aux;
    }

}
