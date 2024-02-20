package HashMap;

public class MapEntry<K, V> {
    // Instance variables to store key and value
    protected K key;
    protected V value;

    // Constructor to initialize key and value
    MapEntry(K k, V v) {
        key = k;
        value = v;
    }

    // Getter method to retrieve the key
    public K getKey() {
        return key;
    }

    // Getter method to retrieve the value
    public V getValue() {
        return value;
    }

    // Setter method to set the value
    public void setValue(V v) {
        value = v;
    }

    // Override toString method to provide a string representation of MapEntry
    @Override
    public String toString() {
        return "Key: " + key + "\nValue: " + value;
    }
}
