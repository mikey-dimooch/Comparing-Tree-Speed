/*
 * Mikey Dimucci
 * Program 2
 * Red Black tree
 */
public class RedBlackTree extends BinaryTree {
    
    // Red Black Tree Properties
    // Node Color: Each node is either red or black.
    // Root Property: The root of the tree is always black.
    // Red Property: Red nodes cannot have red children (no two consecutive red nodes on any path).
    // Black Property: Every path from a node to its descendant null nodes (leaves) has the same number of black nodes.
    // Leaf Property: All leaves (NIL nodes) are black.

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // Node class with color field
    // inherit from binary tree
    // add a color field to the node class
    private class Node {
        int data;
        Node left, right, parent;
        boolean color; // true for RED, false for BLACK

        Node(int data) {
            this.data = data;
            this.color = RED; // New nodes are red by default
        }
    }

    private Node root;

    public RedBlackTree() {
        root = null;
    }

    // Get the color of a node
    // getcolor() and setcolor(int n)
    private boolean getColor(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    // Set the color of a node
    private void setColor(Node node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    // NOTE: every time color changes change setcolor() in insert/remove
    // rotate left/right methods for rotations + balancing
    // Rotate left at node
    private void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (node.right != null) {
            node.right.parent = node;
        }
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        rightChild.left = node;
        node.parent = rightChild;
    }

    // Rotate right at node
    private void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (node.left != null) {
            node.left.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.left) {
            node.parent.left = leftChild;
        } else {
            node.parent.right = leftChild;
        }
        leftChild.right = node;
        node.parent = leftChild;
    }

    // Insert a new node with given data
    // overload insert/remove to add balancing
    public void insert(int data) {
        Node newNode = new Node(data);
        root = insertRecursive(root, newNode);
        fixInsert(newNode);
    }

    // Recursive function to insert a new node
    private Node insertRecursive(Node root, Node node) {
        if (root == null) {
            return node;
        }
        if (node.data < root.data) {
            root.left = insertRecursive(root.left, node);
            root.left.parent = root;
        } else if (node.data > root.data) {
            root.right = insertRecursive(root.right, node);
            root.right.parent = root;
        }
        return root;
    }

    // Fix the red-black tree after insertion
    private void fixInsert(Node node) {
        Node parent = null;
        Node grandparent = null;
        while (node != root && getColor(node) == RED && getColor(node.parent) == RED) {
            parent = node.parent;
            grandparent = parent.parent;
            // Parent is left child of grandparent
            if (parent == grandparent.left) {
                Node uncle = grandparent.right;
                // uncle is red, recolor
                if (getColor(uncle) == RED) {
                    setColor(uncle, BLACK);
                    setColor(parent, BLACK);
                    setColor(grandparent, RED);
                    node = grandparent;
                } else {
                    // uncle is black
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateRight(grandparent);
                    boolean tempColor = getColor(parent);
                    setColor(parent, getColor(grandparent));
                    setColor(grandparent, tempColor);
                    node = parent;
                }
            } else {
                Node uncle = grandparent.left;
                // uncle is red, recolor
                if (getColor(uncle) == RED) {
                    setColor(uncle, BLACK);
                    setColor(parent, BLACK);
                    setColor(grandparent, RED);
                    node = grandparent;
                } else {
                    // uncle is black
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateLeft(grandparent);
                    boolean tempColor = getColor(parent);
                    setColor(parent, getColor(grandparent));
                    setColor(grandparent, tempColor);
                    node = parent;
                }
            }
        }
        setColor(root, BLACK);
    }

    // Remove a node with given data
    public void remove(int data) {
        Node node = deleteNode(root, data);
        fixDelete(node);
    }

    // Recursive function to delete a node
    private Node deleteNode(Node root, int data) {
        if (root == null) {
            return root;
        }
        if (data < root.data) {
            root.left = deleteNode(root.left, data);
        } else if (data > root.data) {
            root.right = deleteNode(root.right, data);
        } else {
            if (root.left == null || root.right == null) {
                return root;
            }
            Node temp = minValueNode(root.right);
            root.data = temp.data;
            root.right = deleteNode(root.right, temp.data);
        }
        return root;
    }

    // Find the node with the minimum value
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // color does not feel like a real word anymore
    // balances the tree after deletion
    // i definitely spent way too long debugging this with copilot because i cannot read
    // debugged with copilot
    // source: https://www.geeksforgeeks.org/red-black-tree-set-3-delete-2/
    private void fixDelete(Node node) {
        Node sibling;
        while (node != root && getColor(node) == BLACK) {
            if (node == node.parent.left) {
                sibling = node.parent.right;
                // if sibling is red
                if (getColor(sibling) == RED) {
                    setColor(sibling, BLACK);
                    setColor(node.parent, RED);
                    rotateLeft(node.parent);
                    sibling = node.parent.right;
                }
                // if both children of sibling are black
                if (getColor(sibling.left) == BLACK && getColor(sibling.right) == BLACK) {
                    setColor(sibling, RED);
                    node = node.parent;
                } else {
                    // if sibling's right child is black
                    if (getColor(sibling.right) == BLACK) {
                        setColor(sibling.left, BLACK);
                        setColor(sibling, RED);
                        rotateRight(sibling);
                        sibling = node.parent.right;
                    }
                    setColor(sibling, getColor(node.parent));
                    setColor(node.parent, BLACK);
                    setColor(sibling.right, BLACK);
                    rotateLeft(node.parent);
                    node = root;
                }
            } else {
                sibling = node.parent.left;
                // if sibling is red
                if (getColor(sibling) == RED) {
                    setColor(sibling, BLACK);
                    setColor(node.parent, RED);
                    rotateRight(node.parent);
                    sibling = node.parent.left;
                }
                // if both children of sibling are black
                if (getColor(sibling.left) == BLACK && getColor(sibling.right) == BLACK) {
                    setColor(sibling, RED);
                    node = node.parent;
                } else {
                    // if sibling's left child is black
                    if (getColor(sibling.left) == BLACK) {
                        setColor(sibling.right, BLACK);
                        setColor(sibling, RED);
                        rotateLeft(sibling);
                        sibling = node.parent.left;
                    }
                    setColor(sibling, getColor(node.parent));
                    setColor(node.parent, BLACK);
                    setColor(sibling.left, BLACK);
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        setColor(node, BLACK);
}
}