import java.util.Scanner;

// Acts as the front end for the user to interact with
public class mainDriver {
    private int size;

    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        String userInput;
        boolean valid = false;
    do {
        System.out.println("Please enter nodes sepated by commas...");
        userInput=input.nextLine();

        if(userInput.contains(" "))
        System.out.println("\nPlease try again without any space.\n");
        else
        break;
        } while (!valid);

    int elements[] = userInput.split(",");





}
