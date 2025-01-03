/*
 * Mikey Dimucci
 * Program 2
 * AVL tree
 */
public class AVLTree extends BinaryTree {

    // Node class with additional height and balanceFactor fields
    private class Node {
        int key, height, balanceFactor;
        Node left, right;

        Node(int key) {
            this.key = key;
            // New nodes are initially added at leaf
            this.height = 1; 
        }
    }

    private Node root;

    // Get the height of the node
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    // Update the height of the node
    private void setHeight(Node node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    // Get the balance factor of the node
    private int getBalanceFactor(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    // Perform a left rotation
    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        // Perform rotation
        x.left = y;
        y.right = T2;

        // Update heights
        setHeight(y);
        setHeight(x);

        // Return new root
        return x;
    }

    // Perform a right rotation
    private Node rotateRight(Node x) {
        Node y = x.left;
        Node T2 = y.right;

        // Perform rotation
        y.right = x;
        x.left = T2;

        // Update heights
        setHeight(x);
        setHeight(y);

        // Return new root
        return y;
    }

    // Public method to insert a key into the AVL tree
    public void insert(int key) {
        root = insert(root, key);
    }

    // Recursive method to insert a key into the subtree rooted with node
    private Node insert(Node node, int key) {
        // Perform the normal BST insertion
        if (node == null) {
            return new Node(key);
        }
        // compare the data to be inserted with the current node's data to the left
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node; // Duplicate keys are not allowed
        }

        // Update height of this ancestor node
        setHeight(node);

        // Get the balance factor of this ancestor node to check whether this node became unbalanced
        node.balanceFactor = getBalanceFactor(node);

        // If the node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (node.balanceFactor > 1 && key < node.left.key) {
            return rotateRight(node);
        }

        // Right Right Case
        if (node.balanceFactor < -1 && key > node.right.key) {
            return rotateLeft(node);
        }

        // Left Right Case
        if (node.balanceFactor > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right Left Case
        if (node.balanceFactor < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // Public method to remove a key from the AVL tree
    public void remove(int key) {
        root = remove(root, key);
    }

    // Recursive method to remove a key from the subtree rooted with node
    private Node remove(Node node, int key) {
        // Perform the normal BST deletion
        if (node == null) {
            return node;
        }
        // compare the data to be inserted with the current node's data to the left
        if (key < node.key) {
            node.left = remove(node.left, key);
        } else if (key > node.key) {
            node.right = remove(node.right, key);
        } else {
            // Node with only one child or no child
            if ((node.left == null) || (node.right == null)) {
                Node temp = null;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                // No child case
                if (temp == null) {
                    temp = node;
                    node = null;
                // One child case
                } else { 
                    // Copy the contents of the non-empty child
                    node = temp; 
                }
            } else {
                // Node with two children Get the inorder successor (smallest in the right subtree)
                Node temp = minValueNode(node.right);

                // Copy the inorder successor's data to this node
                node.key = temp.key;

                // Delete the inorder successor
                node.right = remove(node.right, temp.key);
            }
        }

        // If the tree has only one node then just return
        if (node == null) {
            return node;
        }

        // Update height of the current node
        setHeight(node);

        // Get the balance factor of this node
        node.balanceFactor = getBalanceFactor(node);

        // If the node becomes unbalanced, then there are 4 cases
        // I am God's biggest geeks4geeks fan
        // Left Left Case
        if (node.balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rotateRight(node);
        }

        // Left Right Case
        if (node.balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right Right Case
        if (node.balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return rotateLeft(node);
        }

        // Right Left Case
        if (node.balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Utility function to get the node with the minimum key value found in that tree
    private Node minValueNode(Node node) {
        Node current = node;

        // Loop down to find the leftmost leaf
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }
}