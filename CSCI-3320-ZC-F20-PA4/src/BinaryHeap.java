/*
  Author >>        Tyler Zoucha >> tzoucha@unomaha.edu
  Program Title >> Binary Heap
  Class >>         CSCI3320-820, Fall 2020
  Assignment >>    CSCI-3320-ZC-F20-PA4

  Objective >>  This program constructs a heap (priority queue) using arrays (rather than pointers)
 */



import java.util.Scanner;

/**
 * Init class
 * @author Tyler Zoucha >>  tzoucha@unomaha.edu
 */
public class BinaryHeap{
    private static myHeap heap = new myHeap(null, 2);      // init heap to take in max 20 integers

    /**
     * Main method that drives the program
     * @param args
     */
    public static void main (String[] args){
        System.out.println();
        while(true){
            showMenu();
            Scanner input = new Scanner(System.in);
            String in = input.next();
            int choice = 0;
            while (true) {
                try {
                    choice = Integer.parseInt(in);                  // Sanitize user input
                    if (choice < 1 || choice > 7) {
                        throw new NumberFormatException();          // Throw Exception for incorrect user input
                    } else {                                        // Successful user input.
                        break;                                      // Breaks out of innermost while loop
                    }
                } catch (NumberFormatException ex) {                // Catch Exception if user input is incorrect
                    System.out.println("\n!!! YOU'VE ENTERED AN INCORRECT VALUE !!!\n" +
                            "Please select a valid option from the menu and try again.\n");
                    showMenu();
                    in = input.next();
                }
            }
            switch(choice){
                    case 1:                                         // buildHeap (using linear algorithm), then print
                        newHeap();
                        heap.buildHeap();
                        heap.printOutput();
                        break;
                    case 2:                                         // insert (using percolate up), then print
                        System.out.print("Enter element to insert: ");
                        int e = input.nextInt();
                        System.out.printf("Element: %d", e);
                        heap.insert(e);
                        heap.printOutput();
                        break;
                    case 3:                                         // deleteMin, then print
                        heap.deleteMin();
                        heap.printOutput();
                        break;
                    case 4:                                         // delete key, then print
                        System.out.print("Enter key to remove: ");
                        int key = input.nextInt();
                        heap.remove(key);
                        heap.printOutput();
                        break;
                    case 5:                                         // changeValue, then print
                        System.out.print("Which key would you like to change?: ");
                        int oldKey = input.nextInt();
                        System.out.print("What would you like to change it with?: ");
                        int newKey = input.nextInt();
                        heap.changeValue(oldKey, newKey);
                        heap.printOutput();
                        break;
                    case 6:                                         // print
                        heap.printOutput();
                        break;
                    case 7:                                         // quit
                        System.out.printf("Program Terminated");
                        input.close();
                        System.exit(0);
                        break;
                    default:                                        // default case if error
                        input.close();
                        System.out.println("!!! Unsupported Operation Detected. System Quit.");
                        System.exit(1);
            }
        }
    }

    /**
     * Prompts for user input of heap element and create a new arbitrary heap
     */
    private static void newHeap() {
        Scanner input = new Scanner(System.in);
        System.out.println();

        // Stores user input into String elements
        System.out.print("Enter heap elements: ");
        String elements = input.nextLine();
        elements = elements.replaceAll(",", ", ");  //Handles user input separated by only commas
                                                                    //Replaces "," with ", " to properly split intArr[]

        // Separates user numbers and create priority queue
        String arr[]= elements.split(", ");
        int intArr[] = new int[arr.length];
        for (int i = 0; i < intArr.length; ++i) {
            intArr[i] = Integer.parseInt(arr[i]);
        }

        heap = new myHeap(intArr, 2);                   // New heap
    }

    /**
     * This method is called before/after user input to tell user what to do
     */
    private static void showMenu() {
        System.out.println("Press (1) to build heap, (2) for insert, (3) for deleteMin, "+
                "(4) to remove a key, (5) to change a key value, (6) to print heap, (7) to quit: ");
        System.out.print("Your input: ");
    }
}