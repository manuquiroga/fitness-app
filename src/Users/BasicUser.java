package Users;

import Interfaces.IToJSON;
import org.json.JSONException;
import org.json.JSONObject;

public class BasicUser extends User implements IToJSON {

    //Builders
    public BasicUser(String name, String password, String email, UserData userData) {
        super(name, password, email, userData);
    }

    public BasicUser() {
    }

    //equals,compare To, hashcode
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Object obj) {
        return super.compareTo(obj);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        return super.toJSON();
    }

    @Override
    public void IFromJSON (JSONObject jo) throws JSONException
    {
        super.IFromJSON(jo);
    }

    @Override
    public String toString() {
        return super.toString();
    }



}
