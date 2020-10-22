import java.util.Scanner;

public class BinaryHeap{
    private static myHeap heap = new myHeap(null, 2);

    public static void main (String[] args){
        System.out.println();
        while(true){
            showMenu();
            Scanner input = new Scanner(System.in);
            String in = input.next();
            int choice = 0;
            while (true) {
                try {
                    choice = Integer.parseInt(in);              // Sanitize user input
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
                    case 1: //buildHeap
                        newHeap();
                        heap.buildHeap();
                        heap.printOutput();
                        break;
                    case 2: //insert
                        System.out.print("Enter element to insert: ");
                        int e = input.nextInt();
                        System.out.printf("Element: %d", e);
                        heap.insert(e);
                        heap.printOutput();
                        break;
                    case 3: // deleteMin
                        heap.deleteMin();
                        heap.printOutput();
                        break;
                    case 4: // delete key
                        System.out.print("Enter key: ");
                        int key = input.nextInt();
                        heap.remove(key);
                        heap.printOutput();
                        break;
                    case 5: //changeValue
                        System.out.print("Which key would you like to change?: ");
                        int oldKey = input.nextInt();
                        System.out.print("What would you like to change it with?: ");
                        int newKey = input.nextInt();
                        heap.changeValue(oldKey, newKey);
                        heap.printOutput();
                        break;
                    case 6: //print
                        heap.printOutput();
                        break;
                    case 7: //quit
                        System.out.printf("Program Terminated");
                        System.exit(0);
                        break;
                    default:
                        input.close();
                        System.out.println("!!! Unsupported Operation Detected. System Quit.");
                        System.exit(1);
            }
        }
    }

    private static void newHeap() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter heap elements: ");
        String elements = input.nextLine();

        String arr[]= elements.split(", ");
        int intArr[] = new int[arr.length];
        for (int i = 0; i < intArr.length; ++i) {
            intArr[i] = Integer.parseInt(arr[i]);
        }

        heap = new myHeap(intArr, 2);
    }

    private static void showMenu() {
        System.out.println("Press (1) to build heap, (2) for insert, (3) for deleteMin, "+
                "(4) to remove a key, (5) to change a key value, (6) to print heap, (7) to quit: ");
        System.out.print("Your input: ");
    }
}