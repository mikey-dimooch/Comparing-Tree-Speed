/* Mikey Dimucci
 * Main program
 * Program 2
 * 11/12/2024
*/

/* Conclusion
 * For insertion operations in the unsorted library, the Binary Tree is by far the slowest. The high insertion time is because of the Binary Tree’s 
 * lack of balancing, which can have unstructured form and slower insertion speed. In contrast, the AVL Tree and Red-Black Tree, which both have 
 * self-balancing, have much faster insertion times with the Red-Black Tree performing slightly better due to its less strict balancing requirements. 
 * In the sorted dataset, all three trees have faster insertion times, as the sorted library requires minimal rearrangement. The Binary Tree is the quickest in this case, 
 * followed by the AVL Tree then the Red-Black Tree. This shows that in sorted data scenarios, the Binary Tree’s structure can keep up with balanced trees for insertion.
 * 
 * For search operations, the self-balancing trees perform well in both unsorted and sorted datasets. In the unsorted dataset, the Red-Black Tree
 * has the best search time, closely followed by the AVL Tree. The Binary Tree, without balancing, is much slower. 
 * This difference shows the advantage of balanced trees in reducing search depth and search time. In the sorted dataset, 
 * the Red-Black Tree again performs best, with the Binary Tree and AVL Tree not far behind. The consistency in preformance in both unsorted 
 * and sorted datasets suggests that Red-Black Trees are ideal for search-intensive applications, 
 * while AVL Trees also perform well with minimal added complexity.
 * 
 * For the deletion operations, the Binary Tree is again the slowest in the unsorted dataset, compared to the AVL Tree and Red-Black Tree. 
 * The Red-Black Tree’s slightly faster deletion time is due to its efficient and less strict balancing, which requires fewer rotations 
 * during deletion. In sorted data, the Binary Tree and Red-Black Tree are close in performanceds. The AVL Tree is slower because of its stricter balancing 
 * requirements, which can increase the number of rotations during deletions.
 * 
 * The Red-Black Tree is best for applications involving frequent insertions and deletions due to its efficient and less restrictive balancing method. 
 * The AVL Tree is best used for applications where fast, consistent search times are critical, because of the benefits from its strict balancing, 
 * though it may be less effective for deletion-heavy tasks. 
 * The Binary Tree, while slower in unsorted contexts, performs most efficiently for datasets that are already sorted, making it a good option 
 * for simpler applications with moderate performance requirements.
 */

import java.io.File;
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        // read from txt files
        String books = readFromFile("SciFiLiBooks.txt");
        String sorted = readFromFile("SciFiLiSorted.txt");

        // initalize trees
        // unsorted trees
        BinaryTree unsortedbinaryTree = new BinaryTree();
        AVLTree unsortedavlTree = new AVLTree();
        RedBlackTree unsortedredBlackTree = new RedBlackTree();
        // sorted trees
        BinaryTree sortedbinaryTree = new BinaryTree();
        AVLTree sortedavlTree = new AVLTree();
        RedBlackTree sortedredBlackTree = new RedBlackTree();

        // insert into trees
        System.out.println("Inserting into unsorted trees...");
        insertTrees(books, unsortedbinaryTree, unsortedavlTree, unsortedredBlackTree);
        System.out.println("");

        // search unsorted trees 
        System.out.println("Searching unsorted trees for nonexistent book...");
        searchTrees(books, unsortedbinaryTree, unsortedavlTree, unsortedredBlackTree, "The Maze Runner");
        System.out.println("");

        // remove from unsorted trees 
        System.out.println("Removing 'War of the Worlds' from unsorted trees...");
        removeTrees(books, unsortedbinaryTree, unsortedavlTree, unsortedredBlackTree, "War of the Worlds");
        System.out.println("");

        // create sorted trees 
        System.out.println("Inserting into sorted trees...");
        insertTrees(sorted, sortedbinaryTree, sortedavlTree, sortedredBlackTree);
        System.out.println("");

        // search sorted trees
        System.out.println("Searching in sorted trees for nonexistent book...");
        searchTrees(sorted, sortedbinaryTree, sortedavlTree, sortedredBlackTree, "The Maze Runner");
        System.out.println("");

        // remove from sorted trees 
        System.out.println("Removing 'War of the Worlds' from sorted trees...");
        removeTrees(sorted, sortedbinaryTree, sortedavlTree, sortedredBlackTree, "War of the Worlds");
        System.out.println("");

    }

    // method to read from txt files
    public static String readFromFile(String filename){
        try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            String text = "";
            while(scanner.hasNextLine()){
                text += scanner.nextLine();
            }
            scanner.close();
            return text;
        } catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    // insert into trees
    public static void insertTrees(String l, BinaryTree binaryTree, AVLTree avlTree, RedBlackTree redBlackTree){
        String[] bookArray = l.split("\n");
        
        // start timer for inserting into binary tree
        long startbinaryTime = System.nanoTime();
        // insert books into binary tree
        for(String book : bookArray){
            binaryTree.insert(book);
        }
        // end timer for inserting into binary tree
        long endbinaryTime = System.nanoTime();
        System.out.println(" Time taken to insert into binary tree: " + (endbinaryTime - startbinaryTime) + " nanoseconds");
        // start timer for inserting into AVL tree
        long startavlTime = System.nanoTime();
        // insert books into AVL tree
        for (String book : bookArray){
            avlTree.insert(book);
        }
        // end timer for inserting into AVL tree
        long endavlTime = System.nanoTime();
        System.out.println(" Time taken to insert into AVL tree: " + (endavlTime - startavlTime) + " nanoseconds");
        // start timer for inserting into RedBlack tree
        long startredBlackTime = System.nanoTime();
        // insert books into RedBlack tree
        for (String book : bookArray){
            redBlackTree.insert(book);
        }
        // end timer for inserting into RedBlack tree
        long endredBlackTime = System.nanoTime();
        System.out.println(" Time taken to insert into RedBlack tree: " + (endredBlackTime - startredBlackTime) + " nanoseconds");
    }

    // search trees
    public static void searchTrees(String l, BinaryTree binaryTree, AVLTree avlTree, RedBlackTree redBlackTree, String search){
        // start timer for searching binary tree
        long startbinaryTime = System.nanoTime();
        // search for book in binary tree
        binaryTree.search(search);
        // end timer for searching binary tree
        long endbinaryTime = System.nanoTime();
        System.out.println(" Time taken to search in binary tree: " + (endbinaryTime - startbinaryTime) + " nanoseconds");
        // start timer for searching AVL tree
        long startavlTime = System.nanoTime();
        // search for book in AVL tree
        avlTree.search(search);
        // end timer for searching AVL tree
        long endavlTime = System.nanoTime();
        System.out.println(" Time taken to search in AVL tree: " + (endavlTime - startavlTime) + " nanoseconds");
        // start timer for searching RedBlack tree
        long startredBlackTime = System.nanoTime();
        // search for book in RedBlack tree
        redBlackTree.search(search);
        // end timer for searching RedBlack tree
        long endredBlackTime = System.nanoTime();
        System.out.println(" Time taken to search in RedBlack tree: " + (endredBlackTime - startredBlackTime) + " nanoseconds");
    }
    // remove from trees
    public static void removeTrees(String l, BinaryTree binaryTree, AVLTree avlTree, RedBlackTree redBlackTree, String remove){
        // start timer for removing from binary tree
        long startbinaryTime = System.nanoTime();
        // remove book from binary tree
        binaryTree.remove(remove);
        // end timer for removing from binary tree
        long endbinaryTime = System.nanoTime();
        System.out.println(" Time taken to remove from binary tree: " + (endbinaryTime - startbinaryTime) + " nanoseconds");
        // start timer for removing from AVL tree
        long startavlTime = System.nanoTime();
        // remove book from AVL tree
        avlTree.remove(remove);
        // end timer for removing from AVL tree
        long endavlTime = System.nanoTime();
        System.out.println(" Time taken to remove from AVL tree: " + (endavlTime - startavlTime) + " nanoseconds");
        // start timer for removing from RedBlack tree
        long startredBlackTime = System.nanoTime();
        // remove book from RedBlack tree
        redBlackTree.remove(remove);
        // end timer for removing from RedBlack tree
        long endredBlackTime = System.nanoTime();
        System.out.println(" Time taken to remove from RedBlack tree: " + (endredBlackTime - startredBlackTime) + " nanoseconds");
    }
    
    
    
}