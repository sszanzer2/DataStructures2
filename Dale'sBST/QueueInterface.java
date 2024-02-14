package BST;

public interface QueueInterface<T> {
    //Adds an element to the end of the queue.
    void enqueue(T element);
    //Removes and returns the element at the front of the queue.
    T dequeue();
    //Checks if the queue is empty.
    boolean isEmpty();
    //Returns the number of elements in the queue.
    int size();
}
