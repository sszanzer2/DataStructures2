package SortingHW;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TestingSorts {

	@Test
	public void testSwapWorks() {
	    QuickSort<Student> qs = new QuickSort<>();
	    Student[] students = {
	        new Student("Shana", 85),
	        new Student("Chaya", 92)
	    };
	    int i = 0;
	    int j = 1;

	    qs.swap(students, i, j);

	    // Verify that the elements at indices i and j have been swapped
	    assertEquals("Chaya", students[i].getName());
	    assertEquals(92, students[i].getGrade());
	    
	    assertEquals("Shana", students[j].getName());
	    assertEquals(85, students[j].getGrade());
	}

	@Test
	public void testQuickSort() {
	    Student[] originalArray = {
	        new Student("Shana", 85),
	        new Student("Chaya", 92),
	    };
	    Student[] copyArray = originalArray.clone();

	    QuickSort<Student> qs = new QuickSort<>();
	    qs.quickSort(originalArray, 0, originalArray.length - 1);

	    // Verify that the array is sorted
	    Arrays.sort(copyArray);
	    assertArrayEquals(copyArray, originalArray);
	    
	    // Testing Already Sorted Array
        Student[] alreadySortedArray = {
            new Student("Alicia", 80),
            new Student("Mary", 90),
            new Student("Joe", 95)
        };
        Student[] copyAlreadySortedArray = alreadySortedArray.clone();
        QuickSort<Student> qs3 = new QuickSort<>();
        qs3.quickSort(alreadySortedArray, 0, alreadySortedArray.length - 1);
        assertArrayEquals(copyAlreadySortedArray, alreadySortedArray);

        // Testing Reverse Sorted Array
        Student[] reverseSortedArray = {
            new Student("Sienna", 95),
            new Student("Eva", 90),
            new Student("Kathy", 85)
        };
        Student[] copyReverseSortedArray = reverseSortedArray.clone();
        QuickSort<Student> qs4 = new QuickSort<>();
        qs4.quickSort(reverseSortedArray, 0, reverseSortedArray.length - 1);
        Arrays.sort(copyReverseSortedArray);
        assertArrayEquals(copyReverseSortedArray, reverseSortedArray);

        // Testing Array with Duplicate Grades
        Student[] duplicateGradesArray = {
            new Student("Freddy", 88),
            new Student("Peter", 92),
            new Student("Jones", 88),
            new Student("Caleb", 92)
        };
        Student[] copyDuplicateGradesArray = duplicateGradesArray.clone();
        QuickSort<Student> qs5 = new QuickSort<>();
        qs5.quickSort(duplicateGradesArray, 0, duplicateGradesArray.length - 1);
        Arrays.sort(copyDuplicateGradesArray);
        assertArrayEquals(copyDuplicateGradesArray, duplicateGradesArray);
    }
	

	@Test
	public void testMergeSort() {
	    Student[] originalArray = {
	        new Student("Shana", 85),
	        new Student("Chaya", 92),
	    };
	    Student[] copyArray = originalArray.clone();

	    MergeSort<Student> ms = new MergeSort<>(originalArray);
	    ms.mergeSortSplit(0, originalArray.length - 1);

	    // Verify that the array is sorted
	    Arrays.sort(copyArray);
	    assertArrayEquals(copyArray, originalArray);
	    
	    // Testing Already Sorted Array
        Student[] alreadySortedArray = {
            new Student("Alice", 80),
            new Student("Bailey", 90),
            new Student("Lainey", 95)
        };
        Student[] copyAlreadySortedArray = alreadySortedArray.clone();
        MergeSort<Student> ms3 = new MergeSort<>(alreadySortedArray);
        ms3.mergeSortSplit(0, alreadySortedArray.length - 1);
        assertArrayEquals(copyAlreadySortedArray, alreadySortedArray);

        // Testing Reverse Sorted Array
        Student[] reverseSortedArray = {
            new Student("David", 95),
            new Student("Eva", 90),
            new Student("Cassie", 85)
        };
        Student[] copyReverseSortedArray = reverseSortedArray.clone();
        MergeSort<Student> ms4 = new MergeSort<>(reverseSortedArray);
        ms4.mergeSortSplit(0, reverseSortedArray.length - 1);
        Arrays.sort(copyReverseSortedArray);
        assertArrayEquals(copyReverseSortedArray, reverseSortedArray);

        // Testing Array with Duplicate Grades
        Student[] duplicateGradesArray = {
            new Student("Grace", 88),
            new Student("Harry", 92),
            new Student("Mindy", 88),
            new Student("Jacob", 92)
        };
        Student[] copyDuplicateGradesArray = duplicateGradesArray.clone();
        MergeSort<Student> ms5 = new MergeSort<>(duplicateGradesArray);
        ms5.mergeSortSplit(0, duplicateGradesArray.length - 1);
        Arrays.sort(copyDuplicateGradesArray);
        assertArrayEquals(copyDuplicateGradesArray, duplicateGradesArray);
	}


	@Test
    public void testStudentComparable() {
        // Testing Compare two students with different grades
        Student student1 = new Student("Michal", 85);
        Student student2 = new Student("Jenny", 92);
        assertTrue(student1.compareTo(student2) < 0);

        // Testing Compare two students with the same grade
        Student student3 = new Student("Tom", 88);
        Student student4 = new Student("Katie", 88);
        assertTrue(student3.compareTo(student4) == 0);
	}
}
