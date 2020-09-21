import java.util.Scanner;

public class PA2_Driver {

    /**
     * The current binary search tree for the current interactive session of the program.
     */
    public static BinarySearchTree<Integer> tree = new BinarySearchTree<>();

    /**
     * The main method of the program.
     * @param args The arguments from the command line.
     */
    public static void main(String[] args) {
        while (true) {
            /* Get user input from the command line */
            showMenu();
            Scanner scn = new Scanner(System.in);
            String input = scn.next();
            int parsed;
            while (true) {
                try {
                    parsed = Integer.parseInt(input);
                    if (parsed < 1 || parsed > 8) {
                        throw new NumberFormatException();
                    } else {
                        break;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("\nInvalid Option.\n" +
                            "Please select a valid option from the menu.");
                    showMenu();
                    input = scn.next();
                }
            }

            /* Parse the validated input into a command */
            switch (parsed) {
                case 1 -> {
                    makeTreeNodes();
                    System.out.println("\n***Binary Tree Node(s) Refreshed and Updated***\n");
                }
                case 2 -> tree.printTree();
                case 3 -> System.out.println("The tree contains "
                        + tree.numLeaves(tree.getRoot()) + " leaves.");
                case 4 -> System.out.println("The tree contains "
                        + tree.numOneChildNodes(tree.getRoot()) + " single child nodes");
                case 5 -> System.out.println("The tree contains "
                        + tree.numTwoChildrenNodes(tree.getRoot()) + " two children nodes");
                case 6 -> tree.levelOrder();
                case 7 -> {
                    System.out.println("Which number would you like to remove from the tree?");
                    tree.remove(scn.nextInt());
                }
                case 8 -> {
                    scn.close();
                    System.exit(0);
                }
                default -> {
                    System.out.println("unsupported operation");
                    System.exit(1);
                }
            }
        }
    }


     /**
     * Constructs a new (global) binary search tree.
     */
     public static void makeTreeNodes() {
         tree = new BinarySearchTree<>();
         String[] inputValues = new String[0];
         Scanner scn = new Scanner(System.in);
         boolean prompt;
         do {
             System.out.print("\nEnter the values to add to the tree separated only by commas: ");
             String values = scn.nextLine();
             if (values.contains(", ")) {
                 System.out.println("\nYou've added a space between values.\n" +
                         "Please separate your values with commas only.");
                 prompt = true;
             } else {
                 if (values.contains(" ")) {
                     System.out.println("\nYou've added a space between values.\n" +
                             "Please try again with commas only.");
                     prompt = true;
                 }
                 else {
                     if (values.contains(",")) {
                         inputValues = values.split(",");
                     } else {
                         inputValues = values.split("\\s");
                     }
                     prompt = false;
                 }
             }
         } while (prompt);

         for (String x : inputValues) {
             try {
                 tree.insert(Integer.parseInt(x));
             } catch (NumberFormatException ex) {
                 System.out.println("Skipping invalid input '" + x + "'");
             }
         }
     }


    /**
     * Provides the user with a menu of options to select from.
     */
    private static void showMenu() {
        System.out.println(">> Enter choice [1-8] from the menu below:\n");
        System.out.println("   1) Insert node(s)");
        System.out.println("   2) Print tree (in-order)");
        System.out.println("   3) Print number of leaves in tree");
        System.out.println("   4) Print the number of nodes in T that contain only one child");
        System.out.println("   5) Print the number of nodes in T that contain two children");
        System.out.println("   6) Print the level order traversal of the tree");
        System.out.println("   7) Delete a node");
        System.out.println("   8) Exit program");
        System.out.print("\n   You're input: ");
    }
}

