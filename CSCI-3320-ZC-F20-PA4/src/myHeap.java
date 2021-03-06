public class myHeap {
    int d = 2;                                          // Binary heap
    private static final int DEFAULT_CAPACITY = 20;     // Takes up to 20 integers
    private int currentSize;                            // Number of elements in heap
    private int[] array;                                // The heap array

    /**
     * Construct the binary heap.
     */
    public myHeap() {
        this(DEFAULT_CAPACITY);
        makeEmpty();
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public myHeap(int capacity) {
        currentSize = 0;
        array = new int[capacity + 1];
    }

    /**
     * Construct the binary heap given an array of items.
     * @param items the priority queue of items
     * @param var   respective priority queue
     */
    public myHeap(int[] items, int var) {
        this.d = var;
        if (items == null){
            currentSize = -1;
        }
        else{
            currentSize = items.length;
        }
        array = new int[(currentSize + 2) * (DEFAULT_CAPACITY + 1) / (DEFAULT_CAPACITY)];

        int i = 1;
        if (items == null){
            array[0] = -1;
        } else {
            for (int item : items)
                array[i++] = item;
        }
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * @param x the item to insert.
     */
    public void insert(int x) {
        int exist = 0;
        for (int i = 1; i <= currentSize; i++) {
            if (array[i] == x) {
                System.out.println();
                System.out.printf("%d already exists in the heap\n", x);
                exist = 1;
            }
        }
        if (exist == 0) {
            if (currentSize == array.length - 1)
                enlargeArray(array.length * 2 + 1);

            // Percolate up
            int hole = ++currentSize;
            for (array[0] = x; x < (array[(hole + (d - 2)) / d]); hole = (hole + (d - 2)) / d)
                array[hole] = array[((hole + (d - 2)) / d)];
            array[hole] = x;
        }


    }

    /**
     * Prints the heap
     */
    public void printOutput() {
        System.out.printf("\nThe heap has the following elements: ", d);

        for (int i = 1; i <= currentSize; ++i)
            System.out.printf("%d ", array[i]);

        System.out.print("\n\n");
    }

    /**
     * Needed when the heap grows outside its previous boundary.
     * Copies priority queue into a new priority queue
     * @param newSize   The queue's new size
     */
    private void enlargeArray(int newSize) {
        int[] old = array;
        array = new int[newSize];
        for (int i = 0; i < old.length; i++)
            array[i] = old[i];
    }

    /**
     * Find the smallest item in the priority queue.
     *
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public int findMin() {
        if(isEmpty())
        System.exit(1);
        return array[1];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item in priority queue
     */
    public int deleteMin() {
        if (isEmpty()) {
            System.exit(1);
        }
        int minItem = findMin();
        array[0] = array[currentSize--];
        percolateDown(0);
        return minItem;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    public void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--)
            percolateDown(i);
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty() {
        currentSize = 0;
    }

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown(int hole) {
        int child;
        int tmp = array[hole];

        for (; ((hole * d) - (d - 2)) <= currentSize; hole = child) {
            child = ((hole * d) - (d - 2));
            if (child != currentSize && array[child + 1] < (array[child]))
                child++;
            if (array[child] < (tmp))
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }

    /**
     * Itereates through array to see if the key exists and flags removed if it exists or not
     * Then percolates down the queue to delete the key in question. If not found, no deletion.
     * @param key   The key to be deleted
     */
    public void remove(int key) {
        boolean exists = false;
        for (int i = 1; i <= currentSize; i++) {
            if (i == 1) {
                if (key == array[i]){
                    deleteMin();
                    exists = true;
                }
            } else if (array[i] == key) {
                array[i] = array[currentSize--];
                percolateDown(i);
                exists = true;
            }
        }
        if (exists == false){
            System.out.println();
            System.out.println("*** Element not found. Nothing changed. ***");
        }
    }

    /**
     * Percolates down the queue for the key to replace
     * @param oldKey     The key to be replaced
     * @param newKey     The replacement key
     */
    public void changeValue(int oldKey, int newKey) {
        for (int i = 1; i <= currentSize; i++) {
            if (array[i] == oldKey) {
                array[i] = newKey;
                percolateDown(i);
            }
        }
    }
}
