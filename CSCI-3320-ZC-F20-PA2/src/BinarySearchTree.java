/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 *
 * @author Tyler Zoucha
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    /**
     * The tree root.
     */
    private BinaryNode<AnyType> root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        root = remove(x, root);
    }


    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty())
            System.out.println("Binary Tree is currently empty.");
        else {
            printTree(root);
        }
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return null;   // Item not found; do nothing

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
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
     *
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t) {

        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    /**
     * Returns the number of leaves in the binary tree.
     * @param t The node to check, usually if a recursive call, otherwise the root node.
     * @return The number of leaves in the supplied binary tree.
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
     * Returns the number of nodes in the tree with only one child.
     * @param t The Node to check (when recursive, otherwise the root node).
     * @return The number of nodes in the tree with only one child.
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

    private boolean onlyOneNull(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2) {
        return (node1 != null && node2 == null)
                || (node1 == null && node2 != null);
    }

    /**
     * Returns the number of two children nodes in a binary tree.
     * @param t The root node
     * @return the number of full nodes in the tree
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
     * Getter for the root of the binary tree.
     * @return The root of this tree object..
     */
    public BinaryNode<AnyType> getRoot() {
        return this.root;
    }

    /**
     * Internal method to compute height of a subtree.
     *
     * @param t the node that roots the subtree.
     */
    private int height(BinaryNode<AnyType> t) {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    /**
     * The public level order traversal function. Calls 'printLevel'
     */
    public void levelOrder() {
        System.out.println("\n Level order traversal:\n");
        int height = this.height(root);
        for (int i = 0; i < height + 1; i++) {
            printLevel(root, i);
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints a given level in the binary tree
     * @param t The current node
     * @param i the level to print
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