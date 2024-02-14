package BST;

public class Stack<T> implements StackInterface<T>{
    private Node<T> top; // Reference to the top of the stack

    // Constructor to initialize an empty stack
    public Stack() {
        this.top = null;
    }

    @Override
    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    // Method to push an element onto the stack
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
    }

    @Override
    // Method to pop an element from the stack
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    @Override
    // Method to peek at the top element of the stack
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    // Node class for the linked list implementation of the stack
    private static class Node<T> {
        private T data;
        private Node<T> next;

        // Constructor to initialize a node with data
        private Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
