package SortingHW;

import java.util.Scanner;

public class Main {
	public static <T> void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int answer;
		 while (true) {
	            System.out.println("Would you like to see the results automatically or input your own names?"
	                    + "\nFor automatic enter 1, For entering your own names enter 2: ");
	            
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
	            automaticResults();
	        } else if (answer == 2) {
	            yourResults(scanner);
	        }
	}
	
	public static void automaticResults() {
	    Student[] students = {
	        new Student("Shana", 85),
	        new Student("Chaya", 92),
	        new Student("Rena", 78),
	        new Student("Mindy", 89),
	        new Student("Kayla", 95),
	        new Student("Fraidy", 88)
	    };

	    int high = students.length;

	    QuickSort<Student> qs = new QuickSort<>();
	    qs.quickSort(students, 0, high - 1);
	    System.out.println("Quick sort: ");
	    for (int i = 0; i < high; i++)
	        System.out.println(students[i]);

	    MergeSort<Student> ms = new MergeSort<>(students);

	    System.out.println();
	    ms.mergeSortSplit(0, high - 1);
	    System.out.println("Merge sort: ");
	    ms.printArray();
	}

	
	public static void yourResults(Scanner scanner) {
		System.out.println("How many student names would you like to enter? ");
		int nameAmount = 0;

		// Validate input until a valid number is provided
		while (true) {
		    if (scanner.hasNextInt()) {
		        nameAmount = scanner.nextInt();

		        if (nameAmount > 0) {
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

		Student[] students = new Student[nameAmount];
		int high = students.length;

		for (int i = 0; i < nameAmount; i++) {
		    System.out.println("Enter the name of student " + (i + 1) + ": ");
		    String name = scanner.nextLine();

		    int grade = 0;

		    // Validate input until a valid grade is provided
		    while (true) {
		        System.out.println("Enter the grade of student " + (i + 1) + ": ");
		        
		        if (scanner.hasNextInt()) {
		            grade = scanner.nextInt();

		            if (grade >= 0 && grade <= 100) {
		                break; // Valid input, break out of the loop
		            } else {
		                System.out.println("Invalid input. Please enter a grade between 0 and 100.");
		            }
		        } else {
		            System.out.println("Invalid input. Please enter a number.");
		            scanner.next(); // Consume the invalid input
		        }
		    }

		    scanner.nextLine(); // Consume the newline character

		    students[i] = new Student(name, grade);
		}


	    QuickSort<Student> qs = new QuickSort<>();
	    MergeSort<Student> ms = new MergeSort<>(students);

	    qs.quickSort(students, 0, high - 1);
	    System.out.println("Quick sort: ");
	    for (int i = 0; i < high; i++)
	        System.out.println(students[i]);

	    System.out.println();
	    ms.mergeSortSplit(0, high - 1);
	    System.out.println("Merge sort: ");
	    ms.printArray();
	}

}
