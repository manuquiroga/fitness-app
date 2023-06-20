package Users;

import Interfaces.IToJSON;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * This class represents a basic user.
 */
public class BasicUser extends User implements IToJSON {

    /**
     * Constructs a BasicUser object with the specified name, password, email, and user data.
     * @param name the name of the basic user
     * @param password the password of the basic user
     * @param email the email of the basic user
     * @param userData the user data of the basic user
     */
    public BasicUser(String name, String password, String email, UserData userData) {
        super(name, password, email, userData);
    }

    /**
     * Constructs an empty BasicUser object.
     */
    public BasicUser() {
    }

    /**
     * Checks if this BasicUser is equal to the given object.
     * @param obj the object to compare
     * @return true if the BasicUser is equal to the object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Compares this BasicUser to the given object.
     * @param obj the object to compare
     * @return a negative integer, zero, or a positive integer as this BasicUser is less than, equal to, or greater than the object
     */
    @Override
    public int compareTo(Object obj) {
        return super.compareTo(obj);
    }

    /**
     * Returns the hash code value for this BasicUser.
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Returns a string representation of this BasicUser.
     * @return a string representation of the BasicUser
     */
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        return super.toJSON();
    }

    @Override
    public void fromJSON (JSONObject jo) throws JSONException
    {
        super.fromJSON(jo);
    }


}
