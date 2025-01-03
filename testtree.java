/**
 * This will test insertions, removals, traversals, searching, and node counting.
 * 
 * @author (Mikey Dimucci)
 * @version (2023)
 */
public class testtree {

    public static void main(String[] args) {
        // Test BinaryTree
        System.out.println("Testing BinaryTree:");
        BinaryTree binaryTree = new BinaryTree();
        testTree(binaryTree);

        // Test AVLTree
        System.out.println("\nTesting AVLTree:");
        AVLTree avlTree = new AVLTree();
        testTree(avlTree);

        // Test RedBlackTree
        System.out.println("\nTesting RedBlackTree:");
        RedBlackTree redBlackTree = new RedBlackTree();
        testTree(redBlackTree);
    }

    public static void testTree(BinaryTree tree) {
        // Test if the tree is initially empty
        System.out.println("Is the tree empty? " + tree.isEmpty());  // Expected: true

        // Insert some elements into the tree
        System.out.println("\nInserting elements into the tree...");
        tree.insert("M");
        tree.insert("C");
        tree.insert("R");
        tree.insert("A");
        tree.insert("E");
        tree.insert("S");
        tree.insert("Z");

        // Test if the tree is empty after insertions
        System.out.println("Is the tree empty? " + tree.isEmpty());  // Expected: false

        // Test searching for elements
        System.out.println("\nSearching for elements in the tree...");
        System.out.println("Searching for 'M': " + tree.search("M"));  // Expected: true
        System.out.println("Searching for 'Z': " + tree.search("Z"));  // Expected: true
        System.out.println("Searching for 'X': " + tree.search("X"));  // Expected: false

        // Test traversals
        System.out.println("\nIn-order traversal of the tree:");
        tree.inorder();

        // Test node counting
        System.out.println("\nNumber of nodes in the tree: " + tree.countNodes());  // Expected: 7

        // Test removals
        System.out.println("\nRemoving elements from the tree...");
        tree.remove("M");
        tree.remove("C");
        tree.remove("R");

        // Test if the tree is empty after removals
        System.out.println("Is the tree empty? " + tree.isEmpty());  // Expected: false

        // Test node counting after removals
        System.out.println("Number of nodes in the tree after removals: " + tree.countNodes());  // Expected: 4
    }
}