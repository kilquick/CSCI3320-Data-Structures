/*
  Author >>        Tyler Zoucha >> tzoucha@unomaha.edu
  Program Title >> Binary Search Tree (BST)
  Class >>         CSCI3320-820, Fall 2020
  Assignment >>    CSCI-3320-ZC-F20-PA2

  Objective >>  Main interface for the BinarySearchTree to CompareTo itself
 */

/**
 * Init class to interface BinarySearchTree
 * @author Tyler Zoucha >> tzoucha@unomaha.edu
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    // Initialize BinaryNode object
    private BinaryNode<AnyType> root;

    // Constructor to init null BinarySearchTree
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Method Name >>       insert
     * Description >>       Inserts value into the Binary Tree
     * @param   x AnyType   Datatype that can implement Comparable<? super AnyType>
     * No return value      <n/a></n/a>
     */
    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * Method Name >>       remove
     * Description >>       Removes value from the Binary Tree
     * @param   x AnyType   Datatype that can implement Comparable<? super AnyType>
     * No return value      <n/a></n/a>
     */
    public void remove(AnyType x) {
        root = remove(x, root);
    }


    /**
     * Method Name >>       isEmpty
     * Description >>       Tests if the Binary Tree is empty
     * No input parameters  <n/a></n/a>
     * @return  boolean     True if BST is null, otherwise false
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Method Name >>       printTree
     * Description >>       Prints Binary Tree if not empty, else state empty
     * No input parameters  <n/a></n/a>
     * No return value      <n/a></n/a>
     */
    public void printTree() {
        if (isEmpty())
            System.out.println("The Binary Tree is currently empty.");
        else {
            printTree(root);
        }
    }

    /**
     * Method Name >>       insert
     * Description >>       Insert node into tree
     * @param  x   AnyType  item to insert into subtree
     * @param  t BinaryNode subtree root node
     * @return  BinaryNode  subtree new root node
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);         // compare values

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else;   // Do nothing
        return t;
    }

    /**
     * Method Name >>       remove
     * Description >>       Remove node from subtree
     * @param  x   AnyType  item to remove from subtree
     * @param  t BinaryNode subtree root node
     * @return  BinaryNode  subtree new root node
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return null;   // Item not found; do nothing

        int compareResult = x.compareTo(t.element);         // compare values

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null)         // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * Method Name >>       findMin
     * Description >>       Finds minimum value of tree
     * @param  t BinaryNode Subtree root
     * @return  BinaryNode  Node with minimum value
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    /**
     * Internal method to print a subtree in sorted order.
     * Method Name >>       printTree
     * Description >>       Print a subtree in sorted order.
     * @param  t BinaryNode Subtree root
     * Return values >>     <n/a></n/a>
     */
    private void printTree(BinaryNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    /**
     * Method Name >>       numLeaves
     * Description >>       Returns the number of leaves in the binary tree.
     * @param  t BinaryNode The node to check
     * @return   int        The number of leaves in the binary tree.
     */
    public int numLeaves(BinaryNode<AnyType> t) {
        if (t != null) {
            if (t.right == null && t.left == null) {
                return 1;
            } else {
                return numLeaves(t.left) + numLeaves(t.right);
            }
        }
        return 0;
    }

    /**
     * Method Name >>       numOneChildNodes
     * Description >>       Returns the number of nodes in the tree with only one child.
     * @param t BinaryNode  The Node to check
     * @return int          The number of nodes in the tree with only one child.
     */
    public int numOneChildNodes(BinaryNode<AnyType> t) {
        if (t==null) {
            return 0;
        }
        int num = 0;
        if ( onlyOneNull(t.left, t.right) ) {
            num = 1;
        }
        return numOneChildNodes(t.left) + numOneChildNodes(t.right) + num;
    }

    /**
     * Method Name >>           onlyOneNull
     * Description >>           Checks if one of two nodes is null
     * @param node1 BinaryNode  Node1 to check/compare
     * @param node2 BinaryNode  Node2 to check/compare
     * @return  boolean         True if only one node is null, else false
     */
    private boolean onlyOneNull(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2) {
        return (node1 != null && node2 == null)
                || (node1 == null && node2 != null);
    }

    /**
     * Method Name >>       numTwoChildrenNodes
     * Description >>       Returns the number of nodes that contain only two children.
     * @param t BinaryNode  Node to check
     * @return int          number of nodes that contain two children
     */
    public int numTwoChildrenNodes ( BinaryNode<AnyType> t){
        if (t==null)
            return 0;
        int isFull = 0;
        if (t.left != null && t.right != null)
            isFull = 1;
        return isFull + numTwoChildrenNodes(t.left) + numTwoChildrenNodes(t.right);
    }

    /**
     * Method Name >>       getRoot
     * Description >>       Gets root of tree
     * No input parameters  <n/a></n/a>
     * @return BinaryNode   Root node
     */
    public BinaryNode<AnyType> getRoot() {
        return this.root;
    }

    /**
     * Method Name >>       levelOrder
     * Description >>       Called to print the level order traversal of the tree
     * No input parameters  <n/a></n/a>
     * No return values     <n/a></n/a>
     */
    public void levelOrder() {
        System.out.println("\n Level order traversal:\n");
        int height = getHeight(root);
        for (int i = 0; i < height + 1; i++) {
            printLevel(root, i);
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Method name >>       getHeight
     * Description >>       Gets tree height
     * @param t  BinaryTree Root node of tree
     * @return int          Tree height
     */
    private int getHeight(BinaryNode<AnyType> t) {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(getHeight(t.left), getHeight(t.right));
    }

    /**
     * Method name >>       printLevel
     * Description >>       Prints even level of tree. Used in conjunction with levelOrder()
     * @param t BinaryNode  The current node to get tree level of
     * @param i int         Tree level to print
     */
    private void printLevel(BinaryNode<AnyType> t, int i) {
        if (t != null) {
            if (i == 0) {
                System.out.print(t.element + " ");
            } else if (i > 0) {
                printLevel(t.left, i-1);
                printLevel(t.right, i-1);
            }
        }
    }
}