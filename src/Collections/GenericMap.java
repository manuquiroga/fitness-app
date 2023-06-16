package Collections;

import Handlers.JSONHandler;
import Interfaces.IToJSON;
import Users.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class GenericMap<K, V> {
    private Map<K, V> genericMap;

    public GenericMap() {
        genericMap = new HashMap<>();
    }

    public void put(K k, V v){
        if(!genericMap.containsKey(k))
        {
            genericMap.put(k,v);
        }
    }
    public V removeByKey(K k){
        return genericMap.remove(k);
    }

    public V searchByKey(K k){
        return genericMap.get(k);
    }

    public boolean containsValue(V v) {
        return genericMap.containsValue(v);
    }

    public boolean containsKey(K k){ return genericMap.containsKey(k);}

    public String toString(){
        String s = "";
        for (Map.Entry<K, V> entry : genericMap.entrySet()){
            s += "[K=" + entry.getKey() + ", V=" + entry.getValue() + "]\n";
        }
         return s;
    }

    public List<V> toList()
    {
        return genericMap.values().stream().toList();
    }



}
