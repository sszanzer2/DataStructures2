package BST;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class BSTTest {
	
		
		private BinarySearchTree<Integer> bst;
	
	    @BeforeEach
	    public void setup() {
	        bst = new BinarySearchTree<>();
	    }
		

	    @Test
	    public void testAddAndContains() {
	        assertTrue(bst.add(5));
	        assertTrue(bst.add(3));
	        assertTrue(bst.add(7));
	        assertTrue(bst.contains(5));
	        assertTrue(bst.contains(3));
	        assertTrue(bst.contains(7));
	        assertFalse(bst.contains(2));
	    }

	    @Test
	    public void testMinAndMax() {
	        assertNull(bst.min());
	        assertNull(bst.max());

	        bst.add(5);
	        bst.add(3);
	        bst.add(7);
	        assertEquals(3, bst.min());
	        assertEquals(7, bst.max());
	    }

	    @Test
	    public void testRemoveLeafNode() {
	        bst.add(5);
	        assertTrue(bst.remove(5));
	        assertFalse(bst.contains(5));
	    }

	    @Test
	    public void testRemoveNodeWithOneChild() {
	        bst.add(5);
	        bst.add(3);
	        assertTrue(bst.remove(5));
	        assertFalse(bst.contains(5));
	        assertTrue(bst.contains(3));
	    }

	    @Test
	    public void testRemoveNodeWithTwoChildren() {
	        bst.add(5);
	        bst.add(3);
	        bst.add(7);
	        assertTrue(bst.remove(5));
	        assertFalse(bst.contains(5));
	        assertTrue(bst.contains(3));
	        assertTrue(bst.contains(7));
	    }

	    @Test
	    public void testRemoveRootNode() {
	        bst.add(5);
	        bst.add(3);
	        bst.add(7);
	        assertTrue(bst.remove(5));
	        assertFalse(bst.contains(5));
	        assertTrue(bst.contains(3));
	        assertTrue(bst.contains(7));
	    }

	    @Test
	    public void testInOrderTraversal() {
	        bst.add(5);
	        bst.add(3);
	        bst.add(7);
	        StringBuilder sb = new StringBuilder();
	        bst.getIterator(BSTInterface.Traversal.Inorder)
	                .forEachRemaining(node -> sb.append(node).append(" "));
	        assertEquals("3 5 7 ", sb.toString());
	    }

	    @Test
	    public void testPreOrderTraversal() {
	        bst.add(5);
	        bst.add(3);
	        bst.add(7);
	        StringBuilder sb = new StringBuilder();
	        bst.getIterator(BSTInterface.Traversal.Preorder)
	                .forEachRemaining(node -> sb.append(node).append(" "));
	        assertEquals("5 3 7 ", sb.toString());
	    }

	    @Test
	    public void testPostOrderTraversal() {
	        bst.add(5);
	        bst.add(3);
	        bst.add(7);
	        StringBuilder sb = new StringBuilder();
	        bst.getIterator(BSTInterface.Traversal.Postorder)
	                .forEachRemaining(node -> sb.append(node).append(" "));
	        assertEquals("3 7 5 ", sb.toString());
	    }
	    
	    @Test
	    public void testSize() {
	        assertEquals(0, bst.size());
	        bst.add(5);
	        assertEquals(1, bst.size());
	        bst.add(3);
	        assertEquals(2, bst.size());
	        bst.remove(3);
	        assertEquals(1, bst.size());
	    }
	    
	    @Test
	    public void testBreadthFirstSearch() {
	        bst.add(5);
	        bst.add(3);
	        bst.add(8);
	        bst.add(2);
	        bst.add(7);

	        // Create a StringBuilder to capture the output
	        StringBuilder result = new StringBuilder();
	        
	        // Call the breadthFirstSearch method with the StringBuilder
	        bst.breadthFirstSearch(result);

	        // Construct the expected output
	        String expectedOutput = "Performing breadth-first search on the BST.\n5 3 8 2 7";

	        // Assert the output
	        assertEquals(expectedOutput, result.toString().trim());
	    }


	    @Test
	    public void testDepthFirstSearch() {
	        bst.add(5);
	        bst.add(3);
	        bst.add(8);
	        bst.add(2);
	        bst.add(7);

	        // Create a StringBuilder to capture the output
	        StringBuilder result = new StringBuilder();
	        
	        // Call the depthFirstSearch method with the StringBuilder
	        bst.depthFirstSearch(result);

	        // Construct the expected output
	        String expectedOutput = "Performing depth-first search on the BST.\n5 3 2 8 7";

	        // Assert the output
	        assertEquals(expectedOutput, result.toString().trim());
	    }

}


