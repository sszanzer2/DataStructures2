package BST;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BSTPractice<Character> bst = new BSTPractice<Character>();
        Iterator<Character> iterator = null; // Initialize iterator

        int answer;
        while (true) {
            System.out.println("Would you like to see the results automatically or input your own?"
                    + "\nFor automatic enter 1, For entering your own enter 2: ");

            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();

                if (answer == 1 || answer == 2) {
                    break; // Valid input, break out of the loop
                } else {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
        }

        if (answer == 1) {
            automaticResults(bst, iterator);
        } else if (answer == 2) {
            yourResults(bst, iterator, scanner);
        }
    }

    public static void automaticResults(BSTPractice<Character> bst, Iterator<Character> iterator) {
        bst.add('A');
        bst.add('D');
        bst.add('J');
        bst.add('C');
        bst.add('H');
        bst.add('B');
        bst.add('I');
        bst.add('G');
        bst.add('E');
        bst.add('K');
        bst.add('F');

        printTraversal("Inorder: ", bst.getIterator(BSTInterface.Traversal.Inorder));
        printTraversal("\nPreorder: ", bst.getIterator(BSTInterface.Traversal.Preorder));
        printTraversal("\nPostorder: ", bst.getIterator(BSTInterface.Traversal.Postorder));
    }

    public static void yourResults(BSTPractice<Character> bst, Iterator<Character> iterator, Scanner scanner) {
        System.out.println("How many characters would you like to enter? ");
        int charAmount = 0;

        while (true) {
            if (scanner.hasNextInt()) {
                charAmount = scanner.nextInt();

                if (charAmount > 0) {
                    break; // Valid input, break out of the loop
                } else {
                    System.out.println("Invalid input. Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
        }

        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < charAmount; i++) {
            System.out.println("Enter character " + (i + 1) + ": ");
            char c = scanner.next().toUpperCase().charAt(0); // Read the first character of the input string
            bst.add(c);
        }
        scanner.nextLine(); // Consume the newline character
        printTraversal("Inorder: ", bst.getIterator(BSTInterface.Traversal.Inorder));
        printTraversal("Preorder: ", bst.getIterator(BSTInterface.Traversal.Preorder));
        printTraversal("Postorder: ", bst.getIterator(BSTInterface.Traversal.Postorder));
    }

    private static void printTraversal(String label, Iterator<Character> iterator) {
        System.out.print(label);
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();
    }
}
