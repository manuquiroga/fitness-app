package Collections;

import java.util.Map;
import java.util.TreeMap;

public class GenericTreeMap<K, V> {
    private Map<K, V> treeMap;

    public GenericTreeMap() {
        treeMap = new TreeMap<>();
    }

    public void put(K k, V v){
        treeMap.put(k,v);
    }

    public V removeByKey(K k){
        return treeMap.remove(k);
    }

    public V searchByKey(K k){
        return treeMap.get(k);
    }

    public boolean containsValue(V v) {
        return treeMap.containsValue(v);
    }

    public String toString(){
        String s = "";
        for (Map.Entry<K, V> entry : treeMap.entrySet()){
            s += "[K=" + entry.getKey() + ", V=" + entry.getValue() + "]\n";
        }
         return s;
    }
}
