import java.util.Scanner;

/* Class HeapSortWithRange */
public class Heapsort
{
    private static int SIZE;

    /* Sort Function */
    public static void sortwithrange(int origin_arr[], int low, int high)
    {
        int size_arr = origin_arr.length;
        int range_arr[] = new int[ size_arr ];

        int inc=0;
        for (int i = 0; i < size_arr; i++)
        {
            if(origin_arr[i]>=low && origin_arr[i]<=high){
                range_arr[inc] = origin_arr[i];
                inc++;
            }
        }

        for(int i=0; i<range_arr.length; i++){
            origin_arr[i] = range_arr[i];
        }

        heapify(origin_arr);
        for (int i = SIZE; i > 0; i--)
        {
            swaparray(origin_arr,0, i);
            SIZE = SIZE-1;
            maxheaparr(origin_arr, 0);
        }
    }


    public static void heapify(int origin_arr[])
    {
        SIZE = origin_arr.length-1;
        for (int i = SIZE/2; i >= 0; i--)
            maxheaparr(origin_arr, i);
    }

    public static void maxheaparr(int origin_arr[], int i)
    {
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= SIZE && origin_arr[left] > origin_arr[i])
            max = left;
        if (right <= SIZE && origin_arr[right] > origin_arr[max])
            max = right;

        if (max != i)
        {
            swaparray(origin_arr, i, max);
            maxheaparr(origin_arr, max);
        }
    }
    /* To swap two numbers in an array */
    public static void swaparray(int origin_arr[], int i, int j)
    {
        int tmp = origin_arr[i];
        origin_arr[i] = origin_arr[j];
        origin_arr[j] = tmp;
    }
    /* Main method */
    public static void main(String[] args)
    {
        Scanner scan = new Scanner( System.in );
        System.out.println("Heap Sort With Range\n");
        int n, i, low_val, high_val;

        /* Accept number of elements */
        System.out.println("Enter number of integer elements");
        n = scan.nextInt();

        /* Make array of n elements */
        int origin_arr[] = new int[ n ];
        int temp_arr[] = new int[ n ];

        /* Accept elements */
        System.out.println("\nEnter "+ n +" integer elements");
        for (i = 0; i < n; i++)
            origin_arr[i] = scan.nextInt();

        /*Get range*/
        System.out.println("Enter ranges, low:");
        low_val = scan.nextInt();
        System.out.println("Enter high(it should be greater than low element):");
        high_val = scan.nextInt();
        if(high_val<low_val)
        {
            do{
                System.out.println("Invalid range");
                System.out.println("Enter high(it should be greater than low element):");
                high_val = scan.nextInt();
            }while(high_val<low_val);
        }

        /*Copy original array to temp array*/
        temp_arr = origin_arr.clone();
        sortwithrange(temp_arr, low_val, high_val);

        /*sort and display the array with in range*/
        System.out.println("\nElements after sorting ");
        for (i = 0; i < n; i++)
            if(temp_arr[i]>0)
                System.out.print(temp_arr[i]+" ");


        System.out.println();
    }
}