package Interfaces;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This interface is implemented by any class that needs to be instantiated through a JSON object.
 */
public interface IFromJSON {
    /**
     * Initializes the object using the data from a JSON object.
     * @param jo the JSON object containing the data
     * @throws JSONException if there is an error parsing the JSON object
    */
    void fromJSON(JSONObject jo) throws JSONException;
}
