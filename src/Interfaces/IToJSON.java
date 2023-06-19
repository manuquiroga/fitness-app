package Interfaces;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This interface is implemented by any class that needs to be converted to a JSON Object.
 */
public interface IToJSON {
    JSONObject toJSON() throws JSONException;
}
