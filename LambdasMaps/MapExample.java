package HashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Function;

public class MapExample {
    public static void main(String[] args) {
        String filePath = "prideandprejudice.txt";
        Scanner scanner = new Scanner(System.in);

        // Define hash functions as lambdas
        Function<String, Integer> naiveHash = key -> {
            int hashValue = 1;
            for (int i = 0; i < key.length(); i++) {
                hashValue += key.charAt(i);
            }
            return hashValue % 1600;
        };

        Function<String, Integer> sophisticatedHash = key -> {
            int sum = 0;
            for (int i = 0; i < key.length(); i++) {
                sum += (int) key.charAt(i);
            }
            return sum % 1600;
        };

        // Store hash functions in a map
        Map<Integer, Function<String, Integer>> hashFunctionMap = new HashMap<>();
        hashFunctionMap.put(1, naiveHash);
        hashFunctionMap.put(2, sophisticatedHash);

        // Let the user choose a hash function
        System.out.println("Choose a hash function:");
        System.out.println("1. Naive");
        System.out.println("2. Sophisticated");
        System.out.println("Please enter your choice: ");
        int answer = scanner.nextInt();

        // Retrieve the selected hash function
        Function<String, Integer> selectedHashFunction = hashFunctionMap.get(answer);
        if (selectedHashFunction == null) {
            System.out.println("Invalid choice.");
            return;
        }

        // Create the hash table with the selected hash function
        HashTable<String, Integer> hashTable = new HashTable<>(1600, selectedHashFunction);

        // Count word occurrences
        countWordOccurrences(filePath, hashTable);

        // Menu loop
        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View word count for a particular word");
            System.out.println("2. View words in descending order of occurrence");
            System.out.println("3. View report on internal structure of the hash table");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the word: ");
                    String word = scanner.nextLine().toLowerCase();
                    int count = hashTable.get(word);
                    if (count != -1) {
                        System.out.println("Word count for '" + word + "': " + count);
                    } else {
                        System.out.println("Word '" + word + "' not found.");
                    }
                    break;
                case 2:
                    viewWordsDescendingOrder(hashTable, selectedHashFunction);
                    break;
                case 3:
                    hashTable.generateReport();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
        scanner.close();
    }

    private static void countWordOccurrences(String filePath, HashTable<String, Integer> hashTable) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String word = scanner.next().replaceAll("[^a-zA-Z]", "").toLowerCase();
                if (!word.isEmpty()) {
                    hashTable.insert(word, hashTable.get(word) != null ? hashTable.get(word) + 1 : 1);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        }
    }

    private static void viewWordsDescendingOrder(HashTable<String, Integer> hashTable,  Function<String, Integer> hashFunction) {
        TreeMap<Integer, String> sortedWords = new TreeMap<>();
        for (LinkedHashEntry<String, Integer> entry : hashTable) {
            String word = entry.key;
            int count = entry.value;
            sortedWords.put(count, word);
        }
        System.out.println("\nWords in descending order of occurrence:");
        for (int count : sortedWords.descendingKeySet()) {
            String word = sortedWords.get(count);
            int listLength = getLinkedListLength(hashTable, word, hashFunction); // Get the length of the linked list
            System.out.println(word + ": " + count + " (List Length: " + listLength + ")");

        }
    }
        
        // Helper method to get the length of the linked list for a particular word
        private static <K, V> int getLinkedListLength(HashTable<K, V> hashTable, K key, Function<String, Integer> selectedHashFunction) {
            int hash = selectedHashFunction.apply(key.toString()); // Use the selected hash function
            int length = 0;
            int position = 0;
            // Iterate over all entries in the bucket corresponding to the hash value
            for (LinkedHashEntry<K, V> entry = hashTable.getEntry(hash); entry != null; entry = entry.next) {
                position++;
                if (entry.key.equals(key)) {
                    length = position;
                    break; // Exit the loop once the word is found
                }
            }
            return length;
        
    }
}
