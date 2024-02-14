package BST;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<T> implements BSTInterface<T> {
    protected BSTNode<T> root; // reference to the root of this BST
    protected Comparator<T> comparator; // used for all comparisons
    protected boolean found; // used by remove
    public int counter; // counter for number of elements in BST

    // Precondition: T implements Comparable
    // Creates an empty BST object - uses the natural order of elements.
    public BinarySearchTree() {
        root = null;
        comparator = (Comparator<T>) Comparator.naturalOrder();
        counter = 0; // Initialize counter
    }

    // Creates an empty BST object - uses Comparator compare for order
    // of elements.
    public BinarySearchTree(Comparator<T> comparator) {
        root = null;
        this.comparator = comparator;
        counter = 0;
    }

    // Returns false; this link-based BST is never full.
    public boolean isFull() {
        return false;
    }

    // Returns true if this BST is empty; otherwise, returns false.
    public boolean isEmpty() {
        System.out.println("Checking if the BST is empty.");
        if (root == null) {
            return true;
        } else
            return false;
    }

    // If this BST is empty, returns null;
    // otherwise returns the smallest element of the tree.
    @Override
    public T min() {
        System.out.println("Finding the minimum element.");
        if (isEmpty()) {
            return null;
        }

        BSTNode<T> node = root;
        do {
            node = node.getLeft();
        } while (node.getLeft() != null);

        return node.getInfo();
    }


    // If this BST is empty, returns null;
    // otherwise returns the largest element of the tree.
    @Override
    public T max() {
        System.out.println("Finding the maximum element.");
        if (isEmpty()) {
            return null;
        }

        BSTNode<T> node = root;
        do {
            node = node.getRight();
        } while (node.getRight() != null);

        return node.getInfo();
    }

    // Returns the number of elements in this BST.
    public int size() {
        System.out.println("Getting the size of the BST.");
        return counter;
    }

    // Returns true if this BST contains a node with info i such that
    // comp.compare(target, i) == 0; otherwise, returns false.
    public boolean contains(T target) {
        System.out.println("Checking if the BST contains a specific element.");
        return recContains(target, root);
    }

    private boolean recContains(T target, BSTNode<T> node) {
        System.out.println("Checking if the subtree contains the target element.");

        if (node == null) {
            System.out.println("Target element not found in the subtree.");
            return false; // target is not found
        }

        int compare = comparator.compare(target, node.getInfo());
        if (compare < 0) {
            System.out.println("Target element is smaller than the current node's value. Searching in the left subtree.");
            return recContains(target, node.getLeft()); // Search left subtree
        } else if (compare > 0) {
            System.out.println("Target element is larger than the current node's value. Searching in the right subtree.");
            return recContains(target, node.getRight()); // Search right subtree
        } else {
            System.out.println("Target element found in the subtree.");
            return true; // target is found
        }
    }


    // Returns info i from node of this BST where comp.compare(target, i) == 0;
    // if no such node exists, returns null.
    public T get(T target) {
        System.out.println("Getting an element from the BST.");
        return recGet(target, root);
    }

    // Returns info i from the subtree rooted at node such that
    // comp.compare(target, i) == 0; if no such info exists, returns null.
    private T recGet(T target, BSTNode<T> node) {
        System.out.println("Searching for the target element in the subtree.");

        if (node == null) {
            System.out.println("The current node is null. Target element not found in the subtree.");
            return null; // If the node is null, the target is not found in the tree
        }

        int compare = comparator.compare(target, node.getInfo()); // Compare the target with the current node's value
        if (compare < 0) {
            System.out.println("Target element is smaller than the current node's value. Searching in the left subtree.");
            // If the target is less than the current node's value, search in the left subtree
            return recGet(target, node.getLeft());
        } else if (compare > 0) {
            System.out.println("Target element is greater than the current node's value. Searching in the right subtree.");
            // If the target is greater than the current node's value, search in the right subtree
            return recGet(target, node.getRight());
        } else {
            System.out.println("Target element found in the subtree.");
            // If the target is equal to the current node's value, return the value of the current node
            return node.getInfo();
        }
    }

    // Creates and returns an Iterator providing a traversal of a "snapshot"
    // of the current tree in the order indicated by the argument.
    // Supports Preorder, Postorder, and Inorder traversal.
    public Iterator<T> getIterator(BSTInterface.Traversal orderType) {
    	System.out.println();
        System.out.println("Creating an iterator for traversing the BST.");
        

        final LinkedQueue<T> infoQueue = new LinkedQueue<T>();

        if (orderType == BSTInterface.Traversal.Preorder) {
            System.out.println("Traversing the tree in pre-order to populate the iterator queue.");
            preOrder(root, infoQueue);
        } else if (orderType == BSTInterface.Traversal.Inorder) {
            System.out.println("Traversing the tree in in-order to populate the iterator queue.");
            inOrder(root, infoQueue);
        } else if (orderType == BSTInterface.Traversal.Postorder) {
            System.out.println("Traversing the tree in post-order to populate the iterator queue.");
            postOrder(root, infoQueue);
        }

        return new Iterator<T>() {
            // Returns true if iteration has more elements; otherwise returns false.
            public boolean hasNext() {
                //System.out.print("\nChecking if the iterator has more elements.");
                //System.out.println(); 
                return !infoQueue.isEmpty();
            }

            public T next() {
                //System.out.println("Getting the next element from the iterator queue.");
                // Returns the next element in the iteration.
                // Throws NoSuchElementException - if the iteration has no more elements
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException("Illegal invocation of next in BinarySearchTree iterator.");
                }
                return infoQueue.dequeue();
            }

            public void remove() {
                // Throws UnsupportedOperationException.
                // Not supported. Removal from snapshot iteration is meaningless.
                System.out.println("Unsupported remove attempted on BinarySearchTree iterator.");
                throw new UnsupportedOperationException("Unsupported remove attempted on BinarySearchTree iterator.");
            }
        };
    }


    private void inOrder(BSTNode<T> node, LinkedQueue<T> lq) {
        if (node == null) {
            System.out.println("Reached the end of a subtree. Returning to the parent node.");
            return;
        }

        if (node.getLeft() != null) {
            inOrder(node.getLeft(), lq); // Traverse left subtree
        }
        System.out.println("Enqueuing value of node " + node.getInfo() + " in in-order traversal.");
        lq.enqueue(node.getInfo()); // Enqueue current node's value


        if (node.getRight() != null) {
            inOrder(node.getRight(), lq); // Traverse right subtree
        }
    }


    private void preOrder(BSTNode<T> node, LinkedQueue<T> lq) {
        if (node == null) {
            System.out.println("Reached the end of a subtree. Returning to the parent node.");
            return;
        }

        System.out.println("Enqueuing value of node " + node.getInfo() + " in pre-order traversal.");
        lq.enqueue(node.getInfo()); // Enqueue current node's value

        if (node.getLeft() != null) {
            preOrder(node.getLeft(), lq); // Traverse left subtree
        }

        if (node.getRight() != null) {
            preOrder(node.getRight(), lq); // Traverse right subtree
        }
    }

    private void postOrder(BSTNode<T> node, LinkedQueue<T> lq) {
        if (node == null) {
            System.out.println("Reached the end of a subtree. Returning to the parent node.");
            return;
        }

        if (node.getLeft() != null) {
            postOrder(node.getLeft(), lq); // Traverse left subtree
        }

        if (node.getRight() != null) {
            postOrder(node.getRight(), lq); // Traverse right subtree
        }

        System.out.println("Enqueuing value of node " + node.getInfo() + " in post-order traversal.");
        lq.enqueue(node.getInfo()); // Enqueue current node's value
    }


    // Adds element to this BST. The tree retains its BST property.
    public boolean add(T element) {
        System.out.println("Adding element " + element + " to the BST.");
        root = recAdd(element, root);
        counter++;
        return true;
    }

    // Adds element to tree rooted at node; tree retains its BST property.
    private BSTNode<T> recAdd(T element, BSTNode<T> node) {
        System.out.println("Recursively adding element " + element + " to the BST.");

        if (node == null) {
            System.out.println("Creating a new node with value " + element + ".");
            return new BSTNode<T>(element);
        }

        if (comparator.compare(element, node.getInfo()) <= 0) {
            System.out.println("Going to the left subtree of node " + node.getInfo() + " to add element " + element + ".");
            node.setLeft(recAdd(element, node.getLeft())); // Add in left subtree
        } else {
            System.out.println("Going to the right subtree of node " + node.getInfo() + " to add element " + element + ".");
            node.setRight(recAdd(element, node.getRight())); // Add in right subtree
        }

        return node;
    }


	 // Removes a node with info i from tree such that comp.compare(target,i) == 0
	 // and returns true; if no such node exists, returns false.
	 public boolean remove(T target) {
	     System.out.println("Removing element " + target + " from the BST.");
	     root = recRemove(target, root);
	     counter--;
	     return found;
	 }
	
	 // Removes element with info i from tree rooted at node such that
	 // comp.compare(target, i) == 0 and returns true;
	 // if no such node exists, returns false.
	 private BSTNode<T> recRemove(T target, BSTNode<T> node) {
	     System.out.println("Recursively removing element " + target + " from the BST.");
	
	     if (node == null) {
	         found = false;
	         return null;
	     }
	
	     int compare = comparator.compare(target, node.getInfo());

	     if (compare < 0) {
	         System.out.println("Going to the left subtree of node " + node.getInfo() + " to remove element " + target + ".");
	         node.setLeft(recRemove(target, node.getLeft()));
	     } else if (compare > 0) {
	         System.out.println("Going to the right subtree of node " + node.getInfo() + " to remove element " + target + ".");
	         node.setRight(recRemove(target, node.getRight()));
	     } else {
	         System.out.println("Removing the node with element " + target + ".");
	         node = removeNode(node);
	         found = true;
	     }
	     return node;
	 }

	// Removes the information at node from the tree.
	 private BSTNode<T> removeNode(BSTNode<T> node) {
	     System.out.println("Removing node " + node.getInfo() + " from the BST.");

	     if (node.getLeft() == null && node.getRight() == null) {
	         // Node has no children
	         System.out.println("Node " + node.getInfo() + " has no children.");
	         return null;
	     } else if (node.getLeft() == null) {
	         // Node has only right child
	         System.out.println("Node " + node.getInfo() + " has only a right child.");
	         return node.getRight();
	     } else if (node.getRight() == null) {
	         // Node has only left child
	         System.out.println("Node " + node.getInfo() + " has only a left child.");
	         return node.getLeft();
	     } else {
	         // Node has both left and right children
	         System.out.println("Node " + node.getInfo() + " has both left and right children.");

	         T data = getPredecessor(node.getLeft());
	         System.out.println("Setting node's info to " + data + " after removing it from the left subtree.");
	         node.setInfo(data);

	         System.out.println("Recursively removing node with info " + data + " from the left subtree.");
	         node.setLeft(recRemove(data, node.getLeft()));
	         return node;
	     }
	 }

	// Returns the information held in the rightmost node of subtree
	 private T getPredecessor(BSTNode<T> subtree) {
	     System.out.println("Finding the predecessor of a node in the BST.");

	     BSTNode<T> current = subtree;
	     BSTNode<T> predecessor = null;

	     while (current != null) {
	         if (current.getRight() == null) {
	             predecessor = current;
	             System.out.println("Found predecessor node: " + current.getInfo());
	             current = current.getLeft();
	         } else {
	             current = current.getRight();
	         }
	     }

	     if (predecessor != null) {
	         System.out.println("Predecessor node info: " + predecessor.getInfo());
	         return predecessor.getInfo();
	     } else {
	         // If subtree is empty or has no predecessor, return null
	         System.out.println("No predecessor found.");
	         return null;
	     }
	 }

    @Override
    public Iterator<T> iterator() {
        return getIterator(BSTInterface.Traversal.Inorder);
    }

    public void breadthFirstSearch(StringBuilder result) {
        if (root != null) {
            result.append("Performing breadth-first search on the BST.\n");

            LinkedQueue<BSTNode<T>> queue = new LinkedQueue<>(); // Instantiate a queue of nodes
            queue.enqueue(root);
            while (!queue.isEmpty()) {
                BSTNode<T> node = queue.dequeue();
                // Visit node
                result.append(node.getInfo()).append(" ");
                // Enqueue the children of the node (from left to right) in the queue
                if (node.getLeft() != null) {
                    queue.enqueue(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.enqueue(node.getRight());
                }
            }
        }
    }

    public void depthFirstSearch(StringBuilder result) {
        if (root != null) {
            result.append("Performing depth-first search on the BST.\n");

            Stack<BSTNode<T>> stack = new Stack<>(); // Instantiate a stack of nodes
            stack.push(root);
            while (!stack.isEmpty()) {
                BSTNode<T> node = stack.pop();
                // Visit node
                result.append(node.getInfo()).append(" ");
                // Push the children of the node (from right to left) onto the stack
                if (node.getRight() != null) {
                    stack.push(node.getRight());
                }
                if (node.getLeft() != null) {
                    stack.push(node.getLeft());
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Binary Search Tree:\n");
        if (root != null) {
            Queue<BSTNode<T>> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                BSTNode<T> node = queue.poll();
                sb.append(node.getInfo()).append(" ");
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
            }
        }
        return sb.toString();
    }

}
