
/**
 * An ordered binary tree class.
 * TO DO:
 * 1) Complete the methods indicated.
 * 2) Add a remove method that eliminates the node of a given value
 *    (parameter = data to find and kill; return = void)
 *
 * @author (Mikey Dimucci)
 * @version (2024)
 */
public class BinaryTree
{
    private BinNode root;
    
    /**
     * default constructor 
     * root should be null by default
     */
    public BinaryTree() {
        this.root = null;
    }

    public BinNode getRoot(){
        return this.root;
    }
    
     /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return this.root == null;
    }

     /* Functions to insert data */
    public void insert(String data)
    {
         this.root = insert(this.root, data);
    }

    /* Function to insert data recursively */
    private BinNode insert(BinNode node, String data)
    {
         if (node == null)
             node = new BinNode(data);
         else
         {
          // Recursive case: Compare the data to be inserted with the current node's data
          if (data.compareTo(node.getData()) < 0) {
               node.setLeft(insert(node.getLeft(), data));
          } 
          else {
               node.setRight(insert(node.getRight(), data));
          }            
         }
         return node;
    }     

    /* Function to count number of nodes */
    public int countNodes()
    {
         return countNodes(this.root);
    }
     /* Function to count number of nodes recursively */
     private int countNodes(BinNode r) {
        if (r == null) {
            return 0;
        } else {
            return 1 + countNodes(r.getLeft()) + countNodes(r.getRight());
        }
    }

    /* Function to search for an element */
    public String search(String val) {
        return search(this.root, val);
    }

    /* Function to search for an element recursively */
    private String search(BinNode r, String val) {
        // Base case: If the data is equal to the value, return the data
        if (r.getData().equals(val)) {
                return r.getData();
            }
        return null;
        }

    /* Function to remove a node of a given value
     * (parameter = data to find and kill; return = void)
     * Starting at the root, find the deepest and rightmost node in the binary tree and the node which we want to delete. 
     * Replace the deepest rightmost node’s data with the node to be deleted. 
     * Then delete the deepest rightmost node.
     * Source: https://www.geeksforgeeks.org/deletion-binary-tree/
     * Debugged with github copilot 
     */

    /* Function to remove a node of a given value */
    public void remove(String data) {
        this.root = remove(this.root, data);
    }
    /* Function to remove a node recursively */
    private BinNode remove(BinNode node, String data)
    {
        // Base case: If the node is null, return null
        if (node == null) {
            return null;
        }
        // Recursive case: Compare the data to be removed with the current node's data to the left
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(node.getLeft(), data));
        } 
        // Recursive case: Compare the data to be removed with the current node's data to the right
        else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(node.getRight(), data));
        } 
        else {
            // Node with only one child or no child
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            node.setData(minValue(node.getRight()));

            // Delete the inorder successor
            node.setRight(remove(node.getRight(), node.getData()));
        }

        return node;
        
    }

    /* Function to find the minimum value node in a tree */
    private String minValue(BinNode node) {
        String minv = node.getData();
        // Loop down to find the leftmost leaf
        while (node.getLeft() != null) {
            minv = node.getLeft().getData();
            node = node.getLeft();
        }
        return minv;
    }

    // TOSTRING (equivalents) 
    /* Function for inorder traversal 
     * left root right 
    */
    
    public void inorder()
    {
         inorder(this.root);
    }
    private void inorder(BinNode r)
    {
         if (r != null)
         {
             inorder(r.getLeft());
             System.out.print(r.getData() +" ");
             inorder(r.getRight());
         }
    }

    /* Function for preorder traversal
     * root left right 
     */
    public void preorder()
    {
         preorder(root);
    }
    private void preorder(BinNode r)
    {
         if (r != null)
         {
             System.out.print(r.getData() +" ");
             preorder(r.getLeft());             
             preorder(r.getRight());
         }
    }

    /* Function for postorder traversal
     * left right root
     */
    public void postorder()
    {
         postorder(root);
    }
    private void postorder(BinNode r)
    {
         if (r != null)
         {
             postorder(r.getLeft());             
             postorder(r.getRight());
             System.out.print(r.getData() +" ");
         }
    }
    }
