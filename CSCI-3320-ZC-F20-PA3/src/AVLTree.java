/*
  Author >>        Tyler Zoucha >> tzoucha@unomaha.edu
  Program Title >> AVL Tree
  Class >>         CSCI3320-820, Fall 2020
  Assignment >>    CSCI-3320-ZC-F20-PA3

  Objective >>  This program constructs an AVL tree and inserts/removes nodes into/from it.
                Most of the code implemented in this program was actually a reimplementation of my BST code from PA2
                altered for the characteristics of an AVL tree.
 */

import java.util.Scanner;


/**
 * Init class
 * @author Tyler Zoucha >> tzoucha@unomaha.edu
 */
public class AVLTree {

    /**
     *  Init global AVL tree during runtime with root Node object
     */
    public static AVLTree tree = new AVLTree();
    static Node root;

    // Node constructor
    class Node {
        int key, height;
        Node left, right;
        Node(int d) {
            key = d;
            height = 0;
        }
    }

    /**
     * Function to get height of subtree with root node N
     * @param N Node        Current node to find height of
     * @return int          Tree height
     */
    private int height(Node N) {
        if (N == null) {
            return -1;
        } else
            return 1 + Math.max(height(N.left), height(N.right));
    }

    /**
     * Function to get maximum of two integers
     * @param a int     Int to compare
     * @param b int     Int to compare
     * @return int      Max of compared Ints
     */
    private int max(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * Function to right rotate subtree rooted with y
     * @param y Node    Root node
     * @return Node     New root node after rotate
     */
    private Node rightRotate(Node y) {
        System.out.println("** Right rotation at node : " + y.key);
        Node x = y.left;
        Node T2 = x.right;
        // Perform rotation
        x.right = y;
        y.left = T2;
        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        // Return new root
        return x;
    }


    /**
     * Function to left rotate subtree rooted with x
     * @param x Node    Root node
     * @return          New root node after rotate
     */
    private Node leftRotate(Node x) {
        System.out.println("** Left rotation at node: " + x.key);
        Node y = x.right;
        Node T2 = y.left;
        // Perform rotation
        y.left = x;
        x.right = T2;
        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        // Return new root
        return y;
    }

    /**
     * Function to get Balance Factor of node N
     * @param N Node    Root node of subtree
     * @return int      Balance factor of subtree
     */
    private int getBalance(Node N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    /**
     * Insert new node into the tree.
     * @param node Node         New node
     * @param key int           New value
     * @return
     */
    private Node insert(Node node, int key) {
        /* 1. Perform the normal BST insertion */
        if (node == null) {
            return (new Node(key));
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }
        /* 2. Update height of this ancestor node */
        node.height = max(height(node.left), height(node.right)) + 1;

        /*3. Get the balance factor of this ancestor node to check whether this node became unbalanced */
        int balance = getBalance(node);                 /* If unbalanced, then there are 4 cases */
        if (balance > 1 && key < node.left.key) {       // Left Left Case
            return rightRotate(node);
        }
        if (balance < -1 && key > node.right.key) {     // Right Right Case
            return leftRotate(node);
        }
        if (balance > 1 && key > node.left.key) {       // Left Right Case
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {     // Right Left Case
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        /* return the (unchanged) node pointer */
        return node;
    }

    /**
     * Insert value into node
     * @param key       Value to be inserted
     */
    private void insert(int key) {
        root = insert(root, key);
    }

    /**
     * Find subtree node with minimum value
     * @param node Node         Root of subtree
     * @return                  Node with minimum value
     */
    private Node minValueNode(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Function to delete AVL Tree node
     * @param root Node         Root of subtree
     * @param key               Key value to delete
     * @return                  Node to delete
     */
    private Node deleteNode(Node root, int key) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null) {
            return root;
        }
        // If the key to be deleted is smaller than the root's key,
        // then it lies in left subtree
        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        }
        // If the key to be deleted is greater than the root's key,
        // then it lies in right subtree
        else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        }
        // if key is same as root's key, then this is the node
        // to be deleted
        else {
            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }
                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                {
                    root = temp; // Copy the contents of the non-empty child
                }
            } else {
                // node with two children: Get the inorder successor (smallest
                // in the right subtree)
                Node temp = minValueNode(root.right);
                // Copy the inorder successor's data to this node
                root.key = temp.key;
                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.key);
            }
        }
        // If the tree had only one node then return
        if (root == null) {
            return root;
        }
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }
        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }
        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    /**
     * Called to print the level order traversal of the tree
     * No input parameters  <n/a></n/a>
     * No return values     <n/a></n/a>
     */
    private void levelOrder() {
        int h = height(root);
        for (int i=0; i<=h+1; i++){
            printLevel(root, i);
            System.out.println();
        }
    }

    /**
     * Prints even level of tree. Used in conjunction with levelOrder()
     * @param root Node         The current node to get tree level of
     * @param level int         Tree level to print
     */
    private void printLevel (Node root, int level) {
        if (root != null) {
            if (level == 0)
                System.out.print(root.key + " ");
            else if (level > 0)
            {
                printLevel(root.left, level-1);
                printLevel(root.right, level-1);
            }
        }
    }

    /**
     * Main method to drive AVL Tree program
     * @param args
     */
    public static void main(String[] args) {
        System.out.println();
        while(true){
            showMenu();
            Scanner scn = new Scanner(System.in);
            String input = scn.next();
            int userOp = 0;
            int inputNum;
            while (true) {
                try {
                    userOp = Integer.parseInt(input);              // Sanitize user input
                    if (userOp < 1 || userOp > 4) {
                        throw new NumberFormatException();          // Throw Exception for incorrect user input
                    } else {                                        // Successful user input.
                        break;                                      // Breaks out of innermost while loop
                    }
                } catch (NumberFormatException ex) {                // Catch Exception if user input is incorrect
                    System.out.println("\n!!! YOU'VE ENTERED AN INCORRECT VALUE !!!\n" +
                            "Please select a valid option from the menu and try again.\n");
                    showMenu();
                    input = scn.next();
                }
            }
            switch(userOp){
                case 1:
                    makeTreeNodes();
                    break;
                case 2:
                    System.out.print("\n>> Enter element to delete: ");
                    inputNum = scn.nextInt();
                    root = tree.deleteNode(root, inputNum);
                    break;
                case 3:
                    tree.levelOrder();
                    break;
                case 4:
                    scn.close();
                    System.exit(0);
                default:
                    scn.close();
                    System.out.println("!!! Unsupported Operation Detected. System Quit.");
                    System.exit(1);
            }
        }
    }

    /**
     * Constructs a new AVL tree validating user input adding each data value
     */
    private static void makeTreeNodes() {
        tree = new AVLTree();                                   // Inits null tree
        String[] inputValues = new String[0];
        Scanner scn = new Scanner(System.in);
        boolean prompt;

         /*
         Assignment explicitly requires user data input for nodes to be separated by only a comma without space
          */
        do {
            System.out.print("\n>> Enter your element(s) without spaces:  ");
            String values = scn.nextLine();
            if (values.contains(", ")) { // I added this check for personal preference of accidental spaces
                System.out.println("\nYou've added a space after a comma somewhere.\n" +
                        "Please separate your values using only commas.");
                prompt = true;
            } else {
                if (values.contains(" ")) {                    // Checks for whitespace the user may have entered
                    System.out.println("\nYou've added a space between values.\n" +
                            "Please try again using commas only.");
                    prompt = true;
                }
                else {
                    if (values.contains(",")) {
                        inputValues = values.split(",");   // Splits each number in user list at the commas
                    } else {
                        inputValues = values.split("\\s"); // Needed to catch if only one number was added
                    }                                            // since one number wouldn't have any commas
                    prompt = false;
                }
            }
        } while (prompt);                                          // loops until input is validated

        for (String x : inputValues) {                             // iterate through all user values
            try {
                tree.insert(Integer.parseInt(x));                  // Fills the Tree
            } catch (NumberFormatException ex) {
                System.out.println("**Skipping invalid input '" + x + "'**");  // If non-int item made it through the
            }                                                              // simple checks then that item is skipped
        }
    }

    /**
     * Prints out user prompt to interact with the program
     */
    private static void showMenu() {
        System.out.println(">> Enter choice [1-4] from the menu below:\n");
        System.out.println("   1) Insert Element(s)");
        System.out.println("   2) Remove Element");
        System.out.println("   3) Print Level Order");
        System.out.println("   4) Exit - Exit Program");
        System.out.print("\n   Your input:  ");
    }
}