package SortingHW;

public class MergeSort<T extends Comparable<T>> {
	private T[] arr; 
    private int firstIndex = 0;
    private int lastIndex = 0;
    private int SIZE = 0;
    
    public MergeSort(T[] arr){
        this.arr = arr;
        this.firstIndex = 0;
        this.lastIndex = arr.length;
        this.SIZE = arr.length; 
    }

    //method to print out the elements
    public void printArray() {
        for (T element : arr) {
            System.out.println(element + " ");
        }
        System.out.println();
    }
	
	public void mergeSortSplit(int firstIndex, int lastIndex) {
		System.out.println("Checking if they are both the same value, if they are there's only one value in the list, base case.");
		if(firstIndex < lastIndex) {
			System.out.println("Otherwise..Finding the midpoint by first splitting the left half of the list until each list is an element of 1, "
					+ "then splitting the \nright half of the list until each list is an element of 1, and finally sorting and "
					+ "merging the lists together.");
			int midPoint = (lastIndex + firstIndex) /2;
			//Finding the midpoint by first splitting the left half of the list until each list is an element of 1		
			mergeSortSplit(firstIndex, midPoint);
			//Splitting the right half of the list until each list is an element of 1
			mergeSortSplit(midPoint+1, lastIndex);
			//Sorting and merging the lists together.
			mergeTogether(firstIndex, midPoint, midPoint+1, lastIndex);
		}
	}
	
	public void mergeTogether(int leftFirst, int leftLast, int rightFirst, int rightLast) {
	    int temp = leftFirst;
	    
	    System.out.println("Created a temporary array to store the merged elements");
	    T[] tempArr = (T[]) new Comparable[rightLast + 1];
	    
	    int start = leftFirst;
	    
	    System.out.println("Iterating while there are elements in both the left and right sub-arrays");
	    while (leftFirst <= leftLast && rightFirst <= rightLast) {
	        System.out.println("Compare elements from the left and right sub-arrays, and add the smaller one to the temporary array");
	        if (this.arr[leftFirst].compareTo(this.arr[rightFirst]) <= 0) {
	            tempArr[temp++] = this.arr[leftFirst++];
	        } else {
	            tempArr[temp++] = this.arr[rightFirst++];
	        }
	    }
	    
	    System.out.println("Fill the rest of the list with whatever is left over from the left list");
	    while (leftFirst <= leftLast) {
	        tempArr[temp++] = this.arr[leftFirst++];
	    }
	    
	    System.out.println("Fill the rest of the list with whatever is left over from the right list");
	    while (rightFirst <= rightLast) {
	        tempArr[temp++] = this.arr[rightFirst++];
	    }
	    
	    System.out.println("Copy the merged elements from the temporary array back to the original array");
	    for (temp = start; temp <= rightLast; temp++) {
	        this.arr[temp] = tempArr[temp];
	    }
	}


}
