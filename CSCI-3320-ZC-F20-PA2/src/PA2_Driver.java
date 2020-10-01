/*
  Author >>        Tyler Zoucha >> tzoucha@unomaha.edu
  Program Title >> Binary Search Tree (BST)
  Class >>         CSCI3320-820, Fall 2020
  Assignment >>    CSCI-3320-ZC-F20-PA2

  Objective >>  This is the main driver file consisting of the main method driving the creation of a binary search
                    tree from a user's input.
 */
import java.util.Scanner;


/**
 * Init class
 * @author Tyler Zoucha >> tzoucha@unomaha.edu
 */
public class PA2_Driver {

    /**
     * Init global binary search tree during runtime.
     */
    public static BinarySearchTree<Integer> tree = new BinarySearchTree<>();

    /**
     * The main method of the program where the menu prompt lives for user IO
     * @param args String array from command line arguments.
     */
    public static void main(String[] args) {
        while (true) {                                              // Keeps program running until exit code
            showMenu();                                             // Prompts user with program menu to init BST
            Scanner scn = new Scanner(System.in);
            String input = scn.next();
            int userInt;
            while (true) {
                try {
                    userInt = Integer.parseInt(input);              // Sanitize user input
                    if (userInt < 1 || userInt > 8) {
                        throw new NumberFormatException();          // Throw Exception for incorrect user input
                    } else {                                        // Successful user input.
                        break;                                      // Breaks out of innermost while loop
                    }
                } catch (NumberFormatException ex) {                // Catch Exception if user input is incorrect
                    System.out.println("\n!!! YOU'VE ENTERED AN INCORRECT VALUE !!!\n" +
                            "Please select a valid option from the menu and try again.\n");
                    showMenu();                                     // Re-prompt menu after catch
                    input = scn.next();
                }
            }

            /* Take's user's validated input and runs through the Binary Search Tree */
            switch (userInt) {
                case 1 -> {
                    makeTreeNodes();                                        // Build tree nodes from user input
                    System.out.println("\n***Binary Tree Node(s) Refreshed and Updated***\n");
                }
                case 2 -> tree.printTree();                                 // Print tree in-order
                case 3 -> System.out.println("The tree contains "           // Number of leaves in tree
                        + tree.numLeaves(tree.getRoot()) + " leaves.");
                case 4 -> System.out.println("The tree contains "           // # of nodes with one child node
                        + tree.numOneChildNodes(tree.getRoot()) + " single child nodes");
                case 5 -> System.out.println("The tree contains "           // # of nodes with two children nodes
                        + tree.numTwoChildrenNodes(tree.getRoot()) + " two children nodes");
                case 6 -> tree.levelOrder();                                // Print tree in level order traversal
                case 7 -> {
                    System.out.println("Which number would you like to remove from the tree?");
                    tree.remove(scn.nextInt());                             // Tries to remove object element
                }
                case 8 -> {
                    scn.close();                                            // Close scanner input stream
                    System.exit(0);                                   // Shutdown JVM
                }
                default -> {
                    System.out.println("Unsupported Operation Detected. System Quit."); // Necessary for switch statement syntax
                    System.exit(1);
                }
            }
        }
    }

    /**
     * Method Name >>       makeTreeNodes
     * Description >>       Constructs a new binary search tree validating user input adding each data value.
     * No input parameters  <n/a></n/a>
     * No return value      <n/a></n/a>
     */
    public static void makeTreeNodes() {
         tree = new BinarySearchTree<>();                                   // Inits null tree
         String[] inputValues = new String[0];
         Scanner scn = new Scanner(System.in);
         boolean prompt;

         /*
         Assignment explicitly requires user data input for nodes to be separated by only a comma without space
          */
         do {
             System.out.print("\nEnter the values to add to the tree separated only by a comma without spaces: ");
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
                 tree.insert(Integer.parseInt(x));                  // Fills the Binary Search Tree
             } catch (NumberFormatException ex) {
                 System.out.println("Skipping invalid input '" + x + "'");  // If non-int item made it through the
             }                                                              // simple checks then that item is skipped
         }
     }

    /**
     * Method Name >>       showMenu
     * Description >>       Prints out user prompt to interact with the program
     * No input parameters  <n/a></n/a>
     * No return value      <n/a></n/a>
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
        System.out.print("\n   Your input:  ");
    }
}

