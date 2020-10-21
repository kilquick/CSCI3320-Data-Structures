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

class mymyHeap
{

    //Made binary
    int d = 2;
    /**
     * Construct the binary heap.
     */
    public mymyHeap( )
    {
        this( DEFAULT_CAPACITY );
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public mymyHeap( int capacity )
    {
        currentSize = 0;
        array = new int [ capacity + 1 ];
    }

    /**
     * Construct the binary heap given an array of items.
     */
    public mymyHeap( int [] items, int var )
    {
        this.d = var;
        currentSize = items.length;
        array = new int[ ( currentSize + 2 ) * 11 / 10 ];

        int i = 1;
        for( int item : items )
            array[ i++ ] = item;
        buildHeap( );
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    public void insert(int x )
    {
        int exist = 0;
        for(int i = 1; i <= currentSize; i++){
            if(array[i] == x){
                System.out.printf("%d already exists in the heap\n", x);
                exist = 1;
            }
        }
        if (exist == 0){

            if( currentSize == array.length - 1 )
                enlargeArray( array.length * 2 + 1 );

            // Percolate up
            int hole = ++currentSize;
            for( array[ 0 ] = x; x <( array[ (hole + (d-2)) / d ] ); hole = (hole + (d-2))/d )
                array[ hole ] = array[ ((hole + (d-2))/d)];
            array[ hole ] = x;
        }
    }

    public void printOutput(){

        System.out.printf("Output: Heap (d= %d): ", d);

        for (int i = 1; i <= currentSize; ++i)
            System.out.printf("%d ", array[i]);

        System.out.printf("\n");
    }
    public void setD(int newD){
        this.d = newD;
    }

    private void enlargeArray( int newSize )
    {
        int [] old = array;
        array = new int[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public int findMin( )
    {
        //if( isEmpty( ) )
        //throw new UnderflowException( );
        return array[ 1 ];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */



    public int deleteMin( )
    {
        //if( isEmpty( ) )
        //  throw new UnderflowException( );

        int minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    public void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;      // Number of elements in heap
    private int [ ] array; // The heap array

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {
        int child;
        int tmp = array[ hole ];

        for( ; ((hole * d) - (d-2)) <= currentSize; hole = child )
        {
            child = ((hole * d) - (d-2));
            if( child != currentSize && array[ child + 1 ] < ( array[ child ] ))
                child++;
            if( array[ child ]< ( tmp ))
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
    public void remove(int key) {
        for(int i = 1; i <= currentSize; i++){
            if ( i == 1){
                if (key == array[i])
                    deleteMin();
            }
            else if (array[i] == key){
                array[ i ] = array[ currentSize-- ];
                percolateDown( i );
            }
            //System.out.printf("%d not found", key);
        }
    }

    public void changeValue(int o, int n){
        for(int i = 1; i <= currentSize; i++){
            if (array[i] == o){
                array[ i ] = n;
                percolateDown( i );
            }
            //System.out.printf("%d not found", key);
        }
    }
}