package Collections;

import Users.User;

import java.util.Map;
import java.util.TreeMap;

public class GenericTreeMap<K, V extends User> {
    private TreeMap<K, V> genericTreeMap;

    public GenericTreeMap() {
        genericTreeMap = new TreeMap<>();
    }

    public void put(K k, V v){
        if(!genericTreeMap.containsKey(k))
        {
            genericTreeMap.put(k,v);
        }

    }

    public V removeByKey(K k){
        return genericTreeMap.remove(k);
    }

    public V searchByKey(K k){
        return genericTreeMap.get(k);
    }

    public boolean containsValue(V v) {
        return genericTreeMap.containsValue(v);
    }

    public String toString(){
        String s = "";
        for (Map.Entry<K, V> entry : genericTreeMap.entrySet()){
            s += "[K=" + entry.getKey() + ", V=" + entry.getValue() + "]\n";
        }
         return s;
    }
}
