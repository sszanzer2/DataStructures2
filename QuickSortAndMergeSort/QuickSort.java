package SortingHW;

public class QuickSort<T extends Comparable<T>> {
	public void swap(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i]=arr[j];
		arr[j]=temp;	
	}
	
	public int partition(T[] arr, int low, int high) {
		System.out.println("\nUsing the last element in the array as the pivot to partition the array");
		T pivot = arr[high];
		System.out.println("Declaring i which is a pointer that starts one position before the low index,"
				+ " \nwe use it to keep track of the last position where we found an element less than the pivot");
		int i = low-1;
		System.out.println("J is a pointer that traverses the array from the low index to one position before the high index, "
				+ "\nit is used to compare each element with the pivot");

		for(int j=low; j< high;j++) {
			System.out.println("Checking if the current element is less than the pivot");
			if(arr[j].compareTo(pivot)<=0) {
				System.out.println("It is, so we increment i and swap the elements at positions i and j, "
						+ "this ensures that all elements less than the pivot are on the left side");

				i++;
				swap(arr, i, j);
			}else {
				System.out.println("Current element is not less than pivot, no swap");
			}
		}
		System.out.println("After the loop, swap the pivot with the element at position i + 1, this places the pivot at its correct "
				+ "position in the sorted array");

		swap(arr, i+1, high);
		System.out.println("Then return the index of the pivot after the swap, this index will be used to partition the array "
				+ "in the next recursive calls");

		return (i+1);
	}
	
	public void quickSort(T[] arr, int low, int high) {
		if(low < high) {
			int pivot= partition(arr, low, high);
			quickSort(arr, low, pivot-1);
			quickSort(arr, pivot+1, high);
		}
	}

}
