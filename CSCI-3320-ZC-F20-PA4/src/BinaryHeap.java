import java.util.Scanner;

public class BinaryHeap{

    public static void main (String[] args){
        int choice = 1;
        Scanner input = new Scanner(System.in);

        //user menu
        System.out.println("Enter heap elements: ");
        String elements = input.nextLine();

        String arr[]= elements.split(", ");
        int intArr[] = new int[arr.length];
        for (int i = 0; i < intArr.length; ++i) {
            intArr[i] = Integer.parseInt(arr[i]);
        }

        myHeap myHeap = new myHeap(intArr, 2);
        while(choice > 0 && choice < 8){
            System.out.printf("\n");
            System.out.print("Press (1) to build heap, (2) for insert, (3) for deleteMin, "+
                    "(4) to remove a key, (5) to change a key value, (6) to print heap, (7) to quit: ");
            choice = input.nextInt();
            if(choice !=8) {
                switch(choice){
                    case 1: //buildHeap
                        myHeap.buildHeap();
                        break;
                    case 2: //insert
                    System.out.print("Enter element to insert: ");
                        int e = input.nextInt();
                        System.out.printf("Element: %d", e);
                        myHeap.insert(e);
                        break;
                    case 3: // deleteMin
                    myHeap.deleteMin();
                        //        myHeap.printOutput();
                        break;
                    case 4: // delete key
                    System.out.print("Enter key: ");
                        int key = input.nextInt();
                        myHeap.remove(key);
                        myHeap.buildHeap();
                        break;
                    case 5: //changeValue
                    System.out.print("Which key would you like to replace?: ");
                        int oldKey = input.nextInt();
                        System.out.print("What would you like to replace it with?: ");
                        int newKey = input.nextInt();
                        myHeap.changeValue(oldKey, newKey);
                        break;
                    case 6: //print
                        myHeap.printOutput();
                        break;
                    case 7: //quit
                    System.out.printf("Program Terminated");
                        break;
                }
            }
            else{
                System.out.println("Program Terminated");
                break;
            }
        }
    }
}