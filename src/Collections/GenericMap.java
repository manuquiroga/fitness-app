package Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 This class represents a generic map that stores key-value pairs.
 @param <K> the type of the keys
 @param <V> the type of the values
 */
public class GenericMap<K, V> {
    private Map<K, V> genericMap;

    /**
     Constructs a new instance of GenericMap.
     */
    public GenericMap() {
        genericMap = new HashMap<>();
    }
    /**

     Adds a key-value pair to the map if the key doesn't already exist.
     @param k the key
     @param v the value
     */
    public void put(K k, V v){
        if(!genericMap.containsKey(k))
        {
            genericMap.put(k,v);
        }
    }
    /**

     Removes the value associated with the specified key from the map.
     @param k the key to be removed
     @return the removed value, or null if the key was not found
     */
    public V removeByKey(K k){
        return genericMap.remove(k);
    }
    /**

     Retrieves the value associated with the specified key from the map.
     @param k the key to search for
     @return the value associated with the key, or null if the key was not found
     */
    public V searchByKey(K k){
        return genericMap.get(k);
    }
    /**

     Checks if the map contains the specified value.
     @param v the value to check for
     @return true if the value is found, false otherwise
     */
    public boolean containsValue(V v) {
        return genericMap.containsValue(v);
    }
    /**
     Checks if the map contains the specified key.
     @param k the key to check for
     @return true if the key is found, false otherwise
     */
    public boolean containsKey(K k){ return genericMap.containsKey(k);}

    /**
     Returns a string representation of the map.
     @return a string representation of the map
     */
    public String toString(){
        String s = "";
        for (Map.Entry<K, V> entry : genericMap.entrySet()){
            s += "\n------------------------------\n"+
                    "KEY:" + entry.getKey()+"\n"+"VALUE" + entry.getValue() + "\n------------------------------\n";
        }
        return s;
    }
    /**
     Converts the values of the map into a list.
     @return a list of values in the map
     */
    public List<V> toList()
    {
        return genericMap.values().stream().toList();
    }
}