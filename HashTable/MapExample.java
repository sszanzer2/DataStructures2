package HashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

//I was told I did not need to have prints statements dictating the events that are occuring

public class MapExample {
    public static void main(String[] args) {
        // Specify the path to the text file
        String filePath = "prideandprejudice.txt";

        Scanner scanner = new Scanner(System.in);
       
        int answer;

        do {
            System.out.println("Choose a hash function:");
            System.out.println("1. Naive");
            System.out.println("2. Sophisticated");
            System.out.println("Please enter your choice: ");
            
            // Check if the input is an integer
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.next(); // Consume the invalid input
            }
            
            answer = scanner.nextInt();
            
            // Check if the input is within the valid range (1 or 2)
            if (answer != 1 && answer != 2) {
                System.out.println("Invalid choice. Please enter 1 for Naive or 2 for Sophisticated.");
            }
        } while (answer != 1 && answer != 2);

        HashFunction hashFunction;

        if (answer == 1) {
            hashFunction = HashFunction.NAIVE;
        } else {
            hashFunction = HashFunction.SOPHISTICATED;
        }

        // Initialize your hash table
        HashTable<String, Integer> hashTable = new HashTable<>(1600 , hashFunction);

        // Read the text file and count word occurrences
        countWordOccurrences(filePath, hashTable);
        
        // User interactions
        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View word count for a particular word");
            System.out.println("2. View words in descending order of occurrence");
            System.out.println("3. View report on internal structure of the hash table");
            System.out.println("4. Exit");

            // Read user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // View word count for a particular word
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
                    // View words in descending order of occurrence
                    viewWordsDescendingOrder(hashTable, hashFunction);
                    break;
                case 3:
                    // View report on internal structure of the hash table
                	// Generate and print the report
                    hashTable.generateReport();

                    break;
                case 4:
                    // Exit the program
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }

        // Close the scanner
        scanner.close();
    }

    private static void countWordOccurrences(String filePath, HashTable<String, Integer> hashTable) {
        try {
            // Create a File object representing the text file
            File file = new File(filePath);

            // Create a Scanner to read from the file
            Scanner scanner = new Scanner(file);

            // Tokenize the text into words and process each word
            while (scanner.hasNext()) {
                // Read the next word from the file
                String word = scanner.next();

                // Preprocess the word (remove punctuation and convert to lowercase)
                word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

                // Insert the preprocessed word into the hash table
                if (!word.isEmpty()) {
                    if (hashTable.get(word) != null) {
                        // If the word already exists in the hash table, increment its count
                        hashTable.insert(word, hashTable.get(word) + 1);
                    } else {
                        // Otherwise, insert the word with count 1
                        hashTable.insert(word, 1);
                    }
                }
            }

            // Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        }
    }

    // View words in descending order of occurrence
    private static void viewWordsDescendingOrder(HashTable<String, Integer> hashTable, HashFunction hashFunction) {
        // Create a TreeMap to sort words by their counts
        TreeMap<Integer, String> sortedWords = new TreeMap<>();

        // Iterate through the hash table and populate the TreeMap
        for (LinkedHashEntry<String, Integer> entry : hashTable) {
            String word = entry.key;
            int count = entry.value;
            sortedWords.put(count, word);
        }

        // Print words in descending order of occurrence
        System.out.println("\nWords in descending order of occurrence:");
        for (int count : sortedWords.descendingKeySet()) {
            String word = sortedWords.get(count);
            int listLength = getLinkedListLength(hashTable, word, hashFunction); // Get the length of the linked list
            System.out.println(word + ": " + count + " (List Length: " + listLength + ")");
        }
    }

    // Helper method to get the length of the linked list for a particular word
    private static <K, V> int getLinkedListLength(HashTable<K, V> hashTable, K key, HashFunction hashFunction) {
        int hash;
        if (hashFunction == HashFunction.NAIVE) {
            hash = hashTable.naiveHash(key.toString());
        } else {
            hash = hashTable.sophisticatedHash(key.toString());
        }
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
