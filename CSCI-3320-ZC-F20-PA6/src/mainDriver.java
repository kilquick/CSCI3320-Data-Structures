import java.util.Scanner;

// Acts as the front end for the user to interact with
public class mainDriver {
    private static int size;

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        String userInput;
        boolean valid = false;
        do {
            System.out.println("Please enter nodes separated by commas; i.e., A,B,C,D,E,F,G);
            userInput = input.nextLine();

            if (userInput.contains(""))
                System.out.println("\nPlease try again without any space.\n");
            else
                break;
        } while (!valid);

        String[] elements = userInput.split(",");
        size = elements.length;
        System.out.println("Input edges with weights to determine pathways; i.e., AB 2, AD 1, BE 10, ... GE 1" +);
        System.out.println("Enter the starting node for the algorithm; i.e., B");
        System.out.println("The shortest known paths are:");
    }
}
