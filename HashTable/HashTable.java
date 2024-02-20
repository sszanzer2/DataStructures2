package HashMap;

import java.util.Iterator;

public class HashTable<K, V> implements Iterable<LinkedHashEntry<K, V>> {
    private int TABLE_SIZE; // The size of the hash table
    private int size; // The number of key-value pairs stored in the hash table
    private LinkedHashEntry<K, V>[] table; // Array to hold linked lists of key-value pairs
    private HashFunction hashFunction; // Field for storing the chosen hash function

    /* Constructor */
    public HashTable(int ts, HashFunction hashFunction) {
        size = 0;
        TABLE_SIZE = ts;
        table = new LinkedHashEntry[TABLE_SIZE]; // Initialize the array with the specified size
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null; // Initialize each element of the array to null
        this.hashFunction = hashFunction; // Store the chosen hash function
    }

    /* Function to get number of key-value pairs */
    public int getSize() {
        return size;
    }
    
    // Public getter method for accessing TABLE_SIZE
    public int getTableSize() {
        return TABLE_SIZE;
    }
    
    /* Function to check if the hash table is empty */
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Public getter method for accessing the table array
    public LinkedHashEntry<K, V> getEntry(int index) {
        return table[index];
    }
    
    /* Function to clear hash table */
    public void makeEmpty() {
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null; // Set each element of the array to null
        size = 0; // Reset size
    }
    
    /* Function to get value of a key */
    public V get(K key) {
    	int hash;
    	if(hashFunction == hashFunction.NAIVE) {
            // Calculate hash using naive hash function
    		hash = naiveHash(key.toString());
    	} else {
            // Calculate hash using sophisticated hash function
    		hash = sophisticatedHash(key.toString());
    	}

        if (table[hash] == null)
            return null;
        else {
            // Traverse the linked list at the computed hash index to find the entry with the given key
            LinkedHashEntry<K, V> entry = table[hash];
            while (entry != null && !entry.key.equals(key))
                entry = entry.next;
            if (entry == null)
                return null;
            else
                return entry.value; // Return the value associated with the key
        }
    }

    /* Function to insert a key-value pair */
    public void insert(K key, V value) {
    	int hash;
    	if(hashFunction == hashFunction.NAIVE) {
            // Calculate hash using naive hash function
    		hash = naiveHash(key.toString());
    	} else {
            // Calculate hash using sophisticated hash function
    		hash = sophisticatedHash(key.toString());
    	}
        if (table[hash] == null)
            table[hash] = new LinkedHashEntry<K, V>(key, value); // Create a new entry at the computed hash index
        else {
            // Traverse the linked list at the computed hash index to find the entry with the given key
            LinkedHashEntry<K, V> entry = table[hash];
            while (entry.next != null && !entry.key.equals(key))
                entry = entry.next;
            if (entry.key.equals(key))
                entry.value = value; // Update the value if the key already exists
            else
                entry.next = new LinkedHashEntry<K, V>(key, value); // Append a new entry to the linked list
        }
        size++; // Increment the size of the hash table
    }
 
    public void remove(K key) {
    	int hash;
    	if(hashFunction == hashFunction.NAIVE) {
            // Calculate hash using naive hash function
    		hash = naiveHash(key.toString());
    	} else {
            // Calculate hash using sophisticated hash function
    		hash = sophisticatedHash(key.toString());
    	}
        if (table[hash] != null) {
            LinkedHashEntry<K, V> prevEntry = null;
            LinkedHashEntry<K, V> entry = table[hash];
            while (entry.next != null && !entry.key.equals(key)) {
                prevEntry = entry;
                entry = entry.next;
            }
            if (entry.key.equals(key)) {
                if (prevEntry == null)
                    table[hash] = entry.next; // Remove the entry at the computed hash index
                else
                    prevEntry.next = entry.next; // Remove the entry from the linked list
                size--; // Decrement the size of the hash table
            }
        }
    }

    /* Function to print hash table */
    public void printHashTable() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.print("\nBucket "+ (i + 1) +" : ");
            LinkedHashEntry<K, V> entry = table[i];
            while (entry != null) {
                System.out.print(entry.key + ": " + entry.value + " "); // Print key-value pairs in the linked list
                entry = entry.next;
            }            
        }
    }

    // Implementing Iterable interface
    @Override
    public Iterator<LinkedHashEntry<K, V>> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<LinkedHashEntry<K, V>> {
        private int currentIndex = 0; // Index used for iterating over the hash table
        private LinkedHashEntry<K, V> currentEntry = null; // Current entry in the iteration

        // Method to check if there are more elements in the iteration
        @Override
        public boolean hasNext() {
            // Check if there are more elements in the current linked list
            if (currentEntry != null && currentEntry.next != null)
                return true;
            
            // Iterate through the remaining buckets in the hash table
            for (int i = currentIndex; i < TABLE_SIZE; i++) {
                if (table[i] != null)
                    return true; // If a non-null entry is found, there are more elements
            }
            return false; // No more elements found
        }

        // Method to retrieve the next element in the iteration
        @Override
        public LinkedHashEntry<K, V> next() {
            // If there are more elements in the current linked list, move to the next one
            if (currentEntry != null && currentEntry.next != null) {
                currentEntry = currentEntry.next;
                return currentEntry;
            }

            // Iterate through the remaining buckets in the hash table
            while (currentIndex < TABLE_SIZE) {
                if (table[currentIndex] != null) {
                    currentEntry = table[currentIndex++]; // Move to the next non-null entry
                    return currentEntry;
                }
                currentIndex++; // Move to the next bucket
            }
            return null; // No more elements found
        }
    }


    // Naive hash function
    private int naiveHash(String key) {
        int hash = 1;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
        }
        return hash % TABLE_SIZE; // Return the computed hash value
    }

    // Sophisticated hash function
    private int sophisticatedHash(String key) { 
        // Ensure the key is not null
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        // Calculate the sum of ASCII values of each character in the key
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += (int) key.charAt(i); // Add the ASCII value of each character
        }

        // Compress the sum using modulo operation
        int compressedHash = sum % TABLE_SIZE;

        return compressedHash; // Return the computed hash value
    }

    // Method to generate a report on the internal structure of the hash table
    public void generateReport() {
        System.out.println("Report on the internal structure of the hash table:");
        System.out.println("Number of words: " + size); // Print the number of key-value pairs
        System.out.println("Array size: " + TABLE_SIZE); // Print the size of the hash table
        
        // Print the length of the linked list for each hash code
        System.out.println("\nLength of the linked list for each hash code:");
        for (int i = 0; i < TABLE_SIZE; i++) {
            int length = 0;
            LinkedHashEntry<K, V> entry = table[i];
            while (entry != null) {
                length++;
                entry = entry.next;
            }
            System.out.println("Hash code " + i + ": " + length); // Print the length of the linked list
        }
        
        // Calculate the number of unused array slots
        int unusedSlots = 0;
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] == null) {
                unusedSlots++;
            }
        }
        System.out.println("\nNumber of unused array slots: " + unusedSlots); // Print the number of unused array slots

        // Print the printout of each slot
        System.out.println("\nPrintout of each slot:");
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.print("Bucket " + (i + 1) + " : ");
            LinkedHashEntry<K, V> entry = table[i];
            while (entry != null) {
            	 System.out.print(entry.key + ": " + entry.value + " "); // Print key-value pairs in the linked list
                entry = entry.next;
            }
            System.out.println();
        }
    }
}
