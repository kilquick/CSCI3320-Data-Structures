import java.util.Scanner;

// Java program for implementation of Heap Sort
public class Heapsort {
    public void sort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    // Driver program
    public static void main(String args[]) {
        // int arr[] = {12, 11, 13, 5, 6, 7};
        //int n = arr.length;

        int n;///variable declaration
        Scanner sc = new Scanner(System.in);

        //reading number
        System.out.println("Enter size of the array:");
        n =sc.nextInt();



        System.out.println("Enter array elements : ");

        int arr[] = new int[n];//array declaration
        int i;
        for(i=0;i<n;i++)
            arr[i] =sc.nextInt();//inserting into array.........


        //asking low,high values..
        int low,high;

        System.out.print("Enter low value : ");
        low = sc.nextInt();
        System.out.print("Enter high value : ");
        high = sc.nextInt();

        if(low<n && high < n && low<=high) {

            int narr[] = new int[high-low+1];//duplicate array for sorting...
            i=0;
            int l = low;
            while(l<=high) {
                narr[i++]=arr[l++];//copying
            }

            Heapsort ob = new Heapsort();
            ob.sort(narr);//performing heapsort....


            //now copying sorted range values to original array
            i=0;
            l = low;
            while(l<=high) {
                arr[l++]=narr[i++];//copying
            }

            System.out.println("Sorted array from given range:");
            System.out.println("Low :"+low+" High :"+high+" is :");
            printArray(arr);
        }
        else
            System.out.println("You have entered invalid range try Again..\n");
    }
}