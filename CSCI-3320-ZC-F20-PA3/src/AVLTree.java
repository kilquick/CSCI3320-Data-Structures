import java.util.Scanner;

public class AVLTree {
    class Node {
        int key, height;
        Node left, right;
        Node(int d) {
            key = d;
            height = 1;
        }
    }
    static Node root;
    // A utility function to get height of the tree
    int height(Node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }
    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
        System.out.println("Right rotation at node : "+y.key);
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
    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
        System.out.println("Left rotation at node: "+x.key);
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
    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }
    Node insert(Node node, int key) {
        /* 1. Perform the normal BST rotation */
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
       /* 3. Get the balance factor of this ancestor node to check whether
   this node became unbalanced */
        int balance = getBalance(node);
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }
        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }
        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        /* return the (unchanged) node pointer */
        return node;
    }
   /* Given a non-empty binary search tree, return the node with minimum
   key value found in that tree. Note that the entire tree does not
   need to be searched. */
    Node minValueNode(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    Node deleteNode(Node root, int key) {
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
    // A utility function to print preorder traversal of the tree.
    // The function also prints height of every node
    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }
    void printLevelOrder()
    {
        int h = height(root);
        //System.out.println(h);
        int i;
        for (i=1; i<=h; i++)
            printGivenLevel(root, i);
        System.out.println();
    }
   /* Compute the "height" of a tree -- the number of
nodes along the longest path from the root node
down to the farthest leaf node.*/
    /* Print nodes at the given level */
    void printGivenLevel (Node root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.key + " ");
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner sc = new Scanner(System.in);
        int num;
        while(true){
            System.out.println();
            System.out.println("1. Insert a element into the tree.");
            System.out.println("2. Remove item from tree");
            System.out.println("3. LevelOrder");
            System.out.println("4. Exit - Exit Program");
            int op = sc.nextInt();
            switch(op){
                case 1:
                    System.out.print("Enter element to insert: ");
                    num = sc.nextInt();
                    root = tree.insert(root, num);
                    break;
                case 2:
                    System.out.print("Enter element to delete: ");
                    num = sc.nextInt();
                    root = tree.deleteNode(root, num);
                    break;
                case 3:
                    tree.printLevelOrder();
                case 4:
                    break;
                default:
                    System.out.println("invalid option!!");
            }
            if(op == 4)
                break;
        }
        System.out.println("\n");
    }
}