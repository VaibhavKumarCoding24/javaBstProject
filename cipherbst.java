public class cipherbst {
    private Node root;

    // Constructor to initialize the binary search tree
    public cipherbst() {
        root = null;
    }

    // Public method to insert a key into the BST
    public void insert(int key) {
        root = insertRec(root, key);
    }

    // Helper method to insert a key into the BST recursively
    private Node insertRec(Node root, int key) {
        // If the tree is empty, create a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Otherwise, recursively insert the key in the correct position
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        // Return the unchanged root node
        return root;
    }

    // Public method to search for a key in the BST
    public boolean search(int key) {
        return searchRec(root, key);
    }

    // Helper method to search for a key in the BST recursively
    private boolean searchRec(Node root, int key) {
        // Base case: root is null or key is present at root
        if (root == null) {
            return false;
        }

        if (root.key == key) {
            return true;
        }

        // Key is smaller than root's key, search in the left subtree
        if (root.key > key) {
            return searchRec(root.left, key);
        }

        // Key is larger than root's key, search in the right subtree
        return searchRec(root.right, key);
    }

    // Public method to delete a key from the BST
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    // Helper method to delete a key from the BST recursively
    private Node deleteRec(Node root, int key) {
        // Base case: if the tree is empty
        if (root == null) {
            return root;
        }

        // Recursively find the key to be deleted
        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: get the inorder successor (smallest in the right subtree)
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    // Method to find the smallest value in a subtree
    private int minValue(Node root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    // Public method to perform inorder traversal of the BST
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    // Helper method to perform inorder traversal of the BST recursively
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Inner class representing a node in the BST
    private class Node {
        int key;
        Node left, right;

        // Constructor to create a new node
        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // Main method to demonstrate the functionality of the BST
    public static void main(String[] args) {
        cipherbst bst = new cipherbst();
        bst.insert(45);
        bst.insert(25);
        bst.insert(15);
        bst.insert(35);
        bst.insert(65);
        bst.insert(55);
        bst.insert(75);

        System.out.println("Inorder traversal of the BST:");
        bst.inorder();

        System.out.println("Searching for 35:");
        boolean found = bst.search(35);
        if (found) {
            System.out.println("Found!");
        } else {
            System.out.println("Not found!");
        }

        System.out.println("Deleting 15:");
        bst.delete(15);

        System.out.println("Inorder traversal of the BST after deletion:");
        bst.inorder();
    }
}

