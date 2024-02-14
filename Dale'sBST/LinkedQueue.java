package BST;

public class LinkedQueue<T> implements QueueInterface<T>{
	private Node<T> front; // Reference to the front of the queue
    private Node<T> rear;  // Reference to the rear of the queue
    private int size;      // Number of elements in the queue
    
    // Inner class representing a node in the queue
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
   

    public LinkedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    @Override
    // Method to check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    // Method to get the size of the queue
    public int size() {
        return size;
    }

    @Override
    // Method to add an element to the rear of the queue
    public void enqueue(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    @Override
    // Method to remove and return the element at the front of the queue
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T removedElement = front.data;
        front = front.next;
        size--;
        if (isEmpty()) {
            rear = null;
        }
        return removedElement;
    }

    // Method to return (but not remove) the element at the front of the queue
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }


}
