package BST;

public interface StackInterface<T> {
    // Method to check if the stack is empty
    boolean isEmpty();

    // Method to push an element onto the stack
    void push(T data);

    // Method to pop an element from the stack
    T pop();

    // Method to peek at the top element of the stack
    T peek();
}

