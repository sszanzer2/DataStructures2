/*
 *    Java Program to Implement Hash Tables Chaining with List Heads
 */ 
package HashMap;

/* Class LinkedHashEntry */
class LinkedHashEntry<K, V> {
    K key;                 // Key of the entry
    V value;               // Value associated with the key
    LinkedHashEntry<K, V> next; // Reference to the next entry in the linked list

    // Constructor to initialize key, value, and next reference
    public LinkedHashEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null; // Initially, there is no next entry
    }
}
