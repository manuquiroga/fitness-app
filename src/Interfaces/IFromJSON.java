package Interfaces;

import Users.UserData;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This interface is implemented by any class that needs to be instantiated through a JSON object.
 */
public interface IFromJSON {
    void IFromJSON(JSONObject jo) throws JSONException;
}
