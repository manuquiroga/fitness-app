package Interfaces;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This interface is implemented by any class that needs to be converted to a JSON Object.
 */
public interface IToJSON {
    /**
     * Converts the object to a JSON object.
     * @return the JSON object representing the object
     * @throws JSONException if there is an error creating the JSON object
    */
    JSONObject toJSON() throws JSONException;

    void fromJSON(JSONObject jo) throws JSONException;
}
