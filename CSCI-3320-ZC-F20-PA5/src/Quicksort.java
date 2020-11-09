//Include the needed package
import java.io.*;

//Class
public class Quicksort {

    //Method lesser()
    private static boolean lesser(Comparable x, Comparable y) {
        return x.compareTo(y) < 0;                      //Return
    }

    //Method whetherSorted()
    public static boolean whetherSorted(Comparable[] array) {
        for (int i = 1; i < array.length; i++ )         //Loop()()
            if (lesser(array[i], array[i-1]))           //Check condition
                return false;                           //Return
        return true;                                    //Return
    }

    //Method exchange()
    private static void exchange(Comparable[] array, int i, int j) {
        Comparable swap = array[i];                     //Perform swap operation
        array[i] = array[j];
        array[j] = swap;
    }

    //Method threewaypartitionsort()
    public static void threewaypartitionsort(Comparable[] array) {
        threewaypartitionsort(array, 0, array.length - 1);  //Recursive function call
    }

    //Method threewaypartitionsort()
    private static void threewaypartitionsort (Comparable[] array, int low, int high) {
        if (high <= low)                                //Check condition
            return;                                     //Return
        int lesserThan = low, greaterThan = high;       //Declare variables and initialize
        Comparable x = array[low];                      //Assign
        int i = low;

        while (i <= greaterThan) {                      //Data to be searched
            int data = array[i].compareTo(x);
            if (data < 0)                               //Check condition - lesser than pivot
                exchange(array, lesserThan++, i++);     //Function call
            else if (data > 0)                          //Check condition - greater than pivot
                exchange(array, i, greaterThan--);      //Function call
            else                                        //Equal
                i++;                                    //Increment
        }
        threewaypartitionsort(array, low, lesserThan - 1);      //Function call
        threewaypartitionsort(array, greaterThan + 1, high);    //Function call
    }

    //Driver
    public static void main(String[] args) {
        Integer inputData[] = new Integer[] {5, 5, 7, 3, 5, 1, 6, 2, 4, 8}; //Input array
        threewaypartitionsort(inputData);                                   //Function call
        System.out.print("Quick sort using three way partitioning: ");      //Display
        for (Integer x: inputData) {                                        //Loop
            System.out.printf("%s ",x);                                     //Display
        }
    }
}