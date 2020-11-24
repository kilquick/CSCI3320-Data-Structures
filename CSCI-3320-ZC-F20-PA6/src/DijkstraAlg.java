/*
  Author >>        Tyler Zoucha >> tzoucha@unomaha.edu
  Program Title >> PA6 extra
  Class >>         CSCI3320-820, Fall 2020
  Assignment >>    CSCI-3320-ZC-F20-PA6
  Objective >>  BinaryNode object to traverse through Binary Search Tree
            >>
            >>  I swear that just earlier today this program was functioning smoothly, but after I went through it adding
                my comments, now that I'm testing it it's breaking during runtime. I really really wish that I had
                the time to work/fix this, but with the assignment being due in 20 minutes...this disclaimer will probably
                still be at the header of my source code.

                For some reason, the graph doesn't initialize now.

                I spoke too soon because NOW IT'S NOT COMPILING AND I FEAR I WON'T GET ANY POINTS
                I commented out the line that is now giving me compilation errors to hopefully get credit for the effort.
                I know if I had more time and wasn't drowning in all my classes barely getting through it I could solve this.

 */


import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class DijkstraAlg {
    private static int n;
    private int edgeCount;
    private Integer[] prev;
    private List<List<Edge>> graph;//

    public static class Edge {
        int next;
        double cost;
        public Edge(int next, double cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    /**
     * Initializes the algorithm/adjacency lists by providing the graph size and a starting node
     */
    private void initialize() {
        int nodes = n;

        createEmptyGraph(nodes);
    }


    // Construct an empty graph of n nodes; including source and sink nodes
    private void createEmptyGraph(int n) {
        graph = new ArrayList<>(this.n);
        for (int i = 0; i < this.n; i++)
            graph.add(new ArrayList<>());       // Create empty adjacency list of same size
    }

    /**
     * Adds an edge to the graph
     * @param from  - starting node for edge
     * @param to    - designation node for edge
     * @param cost  - cost of the edge
     */
    public void addEdge(int from, int to, int cost) {
        edgeCount++;
        graph.get(from).add(new Edge(to, cost));
    }

    /**
     * After edges are added to the graph, this method returns the constructed graph
     */
    public List<List<Edge>> getGraph() {
        return graph;
    }

    /**
     * Dijkstra's Algorithm on a directed graph to find the shortest path from a starting node to an ending node.
     * If no path, then the return value is set to Double.POSITIVE_INFINITY
     * @param start
     * @param end
     * @return
     */
    public double dijkstraAlgorithm(int start, int end) {

        // Using an Indexed Priority Queue ensures the next promising node to visit
        int degree = edgeCount / n;
        MinIndexedDHeap<Double> indexedQueue = new MinIndexedDHeap<>(degree, n);
        indexedQueue.insert(start, 0.0);

        // Maintain an array of the minimum distance to each node
        double[] dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);

        boolean[] visited = new boolean[n];
        prev = new Integer[n];

        // The heart of the the Algorithm
        while(!indexedQueue.isEmpty()) {                        // While the queue is not empty
            int nodeID = indexedQueue.peekMinKeyIndex();        // Finds node key index from ID

            visited[nodeID] = true;                              // Mark as visited to know to skip later
            double minValue = indexedQueue.pollMinValue();      // Stores the associated minimum value

            if (minValue > dist[nodeID]) continue;          // Only interested in the shortest path

            for (Edge edge : graph.get(nodeID)) {
                if (visited[edge.next]) continue;

                double newDist = dist[nodeID] + edge.cost;
                if (newDist < dist[edge.next]) {
                    prev[edge.next] = nodeID;
                    dist[edge.next] = newDist;

                    if (!indexedQueue.contains(edge.next))
                        indexedQueue.insert(edge.next, newDist);
                    else
                        indexedQueue.decrease(edge.next, newDist);
                }
            }
            if (nodeID == end) return dist[end];
        }
        return Double.POSITIVE_INFINITY;
    }

    public List<Integer> reconstructPath(int start, int end) {
        if (end < 0 || end >= n)
            throw new IllegalArgumentException("Invalid node index");
        if (start < 0 || start >= n)
            throw new IllegalArgumentException("Invalid node index");
        List<Integer> path = new ArrayList<>();
        double dist = dijkstraAlgorithm(start, end);
        if (dist == Double.POSITIVE_INFINITY) return path;
        for (Integer at = end; at != null; at = prev[at])
            path.add(at);
        Collections.reverse(path);
        return path;
    }

    // Main method for user interaction and to drive program
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String userNodes, userCost;
        boolean valid = false;
        do {
            System.out.println("Please enter nodes separated by commas; i.e., A,B,C,D,E,F,G");
            userNodes = input.nextLine();

            if (userNodes.contains(" "))
                userNodes = userNodes.replaceAll("\\s","");
            else
                break;
        } while (!valid);

        String[] nodes = userNodes.split(",");
        //initialize();
        System.out.println("Input edges with weights to determine pathways; i.e., AB 2, AD 1, BE 10, ... GE 1");
        System.out.println("(Note: The space between the nodes and weight isn't necessary >> Program supports either.)");
        userCost = input.nextLine();
        if (userCost.contains(" "))
            userCost = userCost.replaceAll("\\s","");//removes all white space for parsing
        String[] weight = userCost.split(",");                 //Stores source, destination, and cost in an index
        String parsed = weight.toString();// Use string array as easy pointer of data, but parsing to string for algorithm

        for (int i = 0; i < weight.length; i++)
            new DijkstraAlg().addEdge(parsed.charAt(i), parsed.charAt(i+1), parsed.charAt(i+2));

        System.out.println("Enter the starting node for the algorithm; i.e., B");
        System.out.println("(enter 0 to stop");
        System.out.println("The shortest known paths are:");
    }


    private static class MinIndexedDHeap<T extends Comparable<T>> {

        // Current number of elements in the heap.
        private int sz;

        // Maximum number of elements in the heap.
        private final int N;

        // The degree of every node in the heap.
        private final int D;

        // Lookup arrays to track the child/parent indexes of each node.
        private final int[] child, parent;

        // The Position Map (pm) maps Key Indexes (ki) to where the position of that
        // key is represented in the priority queue in the domain [0, sz).
        public final int[] pm;

        // The Inverse Map (im) stores the indexes of the keys in the range
        // [0, sz) which make up the priority queue. It should be noted that
        // 'im' and 'pm' are inverses of each other, so: pm[im[i]] = im[pm[i]] = i
        public final int[] im;

        // The values associated with the keys. It is very important  to note
        // that this array is indexed by the key indexes (aka 'ki').
        public final Object[] values;

        // Initializes a D-ary heap with a maximum capacity of maxSize.
        public MinIndexedDHeap(int degree, int maxSize) {
            if (maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");

            D = max(2, degree);
            N = max(D + 1, maxSize);

            im = new int[N];
            pm = new int[N];
            child = new int[N];
            parent = new int[N];
            values = new Object[N];

            for (int i = 0; i < N; i++) {
                parent[i] = (i - 1) / D;
                child[i] = i * D + 1;
                pm[i] = im[i] = -1;
            }
        }

        public int size() {
            return sz;
        }

        public boolean isEmpty() {
            return sz == 0;
        }

        public boolean contains(int ki) {
            keyInBoundsOrThrow(ki);
            return pm[ki] != -1;
        }

        public int peekMinKeyIndex() {
            isNotEmptyOrThrow();
            return im[0];
        }

        public int pollMinKeyIndex() {
            int minki = peekMinKeyIndex();
            delete(minki);
            return minki;
        }

        @SuppressWarnings("unchecked")
        public T peekMinValue() {
            isNotEmptyOrThrow();
            return (T) values[im[0]];
        }

        public T pollMinValue() {
            T minValue = peekMinValue();
            delete(peekMinKeyIndex());
            return minValue;
        }

        public void insert(int ki, T value) {
            if (contains(ki)) throw new IllegalArgumentException("index already exists; received: " + ki);
            valueNotNullOrThrow(value);
            pm[ki] = sz;
            im[sz] = ki;
            values[ki] = value;
            swim(sz++);
        }

        @SuppressWarnings("unchecked")
        public T valueOf(int ki) {
            keyExistsOrThrow(ki);
            return (T) values[ki];
        }

        @SuppressWarnings("unchecked")
        public T delete(int ki) {
            keyExistsOrThrow(ki);
            final int i = pm[ki];
            swap(i, --sz);
            sink(i);
            swim(i);
            T value = (T) values[ki];
            values[ki] = null;
            pm[ki] = -1;
            im[sz] = -1;
            return value;
        }

        @SuppressWarnings("unchecked")
        public T update(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            final int i = pm[ki];
            T oldValue = (T) values[ki];
            values[ki] = value;
            sink(i);
            swim(i);
            return oldValue;
        }

        // Strictly decreases the value associated with 'ki' to 'value'
        public void decrease(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            if (less(value, values[ki])) {
                values[ki] = value;
                swim(pm[ki]);
            }
        }

        // Strictly increases the value associated with 'ki' to 'value'
        public void increase(int ki, T value) {
            keyExistsAndValueNotNullOrThrow(ki, value);
            if (less(values[ki], value)) {
                values[ki] = value;
                sink(pm[ki]);
            }
        }

        /* Helper functions */

        private void sink(int i) {
            for (int j = minChild(i); j != -1; ) {
                swap(i, j);
                i = j;
                j = minChild(i);
            }
        }

        private void swim(int i) {
            while (less(i, parent[i])) {
                swap(i, parent[i]);
                i = parent[i];
            }
        }

        // From the parent node at index i find the minimum child below it
        private int minChild(int i) {
            int index = -1, from = child[i], to = min(sz, from + D);
            for (int j = from; j < to; j++) if (less(j, i)) index = i = j;
            return index;
        }

        private void swap(int i, int j) {
            pm[im[j]] = i;
            pm[im[i]] = j;
            int tmp = im[i];
            im[i] = im[j];
            im[j] = tmp;
        }

        // Tests if the value of node i < node j
        @SuppressWarnings("unchecked")
        private boolean less(int i, int j) {
            return ((Comparable<? super T>) values[im[i]]).compareTo((T) values[im[j]]) < 0;
        }

        @SuppressWarnings("unchecked")
        private boolean less(Object obj1, Object obj2) {
            return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
        }

        @Override
        public String toString() {
            List<Integer> lst = new ArrayList<>(sz);
            for (int i = 0; i < sz; i++) lst.add(im[i]);
            return lst.toString();
        }

        /* Helper functions to make the code more readable. */

        private void isNotEmptyOrThrow() {
            if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        }

        private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
            keyExistsOrThrow(ki);
            valueNotNullOrThrow(value);
        }

        private void keyExistsOrThrow(int ki) {
            if (!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
        }

        private void valueNotNullOrThrow(Object value) {
            if (value == null) throw new IllegalArgumentException("value cannot be null");
        }

        private void keyInBoundsOrThrow(int ki) {
            if (ki < 0 || ki >= N)
                throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
        }
    }
}