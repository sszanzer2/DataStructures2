package HashMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;


public class HashTableTest {
    private HashTable<String, Integer> hashTable;

    @BeforeEach
    public void setUp() {
        hashTable = new HashTable<>(100, HashFunction.NAIVE); // Creating a hash table with a size of 10
    }

    @Test
    public void testInsertAndGet() {
        hashTable.insert("hello", 1);
        hashTable.insert("to", 2);
        
        assertEquals(1, (int)hashTable.get("hello"));
        assertEquals(2, (int)hashTable.get("to"));
    }

    @Test
    public void testInsertAndUpdate() {
        hashTable.insert("hello", 1);
        hashTable.insert("hello", 2); // Update count for "hello"
        
        assertEquals(2, (int)hashTable.get("hello"));
    }

    @Test
    public void testRemove() {
        hashTable.insert("hello", 1);
        hashTable.remove("hello");
        
        assertNull(hashTable.get("hello"));
    }

    @Test
    public void testRetrieval() {
        hashTable.insert("key1", 1);
        hashTable.insert("key2", 2);
        assertEquals(1, (int) hashTable.get("key1"));
        assertNull(hashTable.get("key3"));
    }
    
    @Test
    public void testSize() {
        hashTable.insert("hello", 1);
        hashTable.insert("to", 2);
        
        assertEquals(2, hashTable.getSize());
    }

    @Test
    public void testEmptyTable() {       
        assertTrue(hashTable.isEmpty());
    }

    @Test
    public void TestMakeEmpty() {
    	 hashTable.insert("Hello", 1);
    	 hashTable.insert("to", 1);
    	 hashTable.makeEmpty(); 
    	 assertTrue(hashTable.isEmpty());
    }
    @Test
    public void testNotEmptyTable() {
        hashTable.insert("hello", 1);
        
        assertFalse(hashTable.isEmpty());
    }

    @Test
    public void testIterator() {
        hashTable.insert("hello", 1);
        hashTable.insert("to", 2); 
     
        int count = 0;
        for (LinkedHashEntry<String, Integer> entry : hashTable) {
            count++;
        }
        assertEquals(2, count);
    } 
}


