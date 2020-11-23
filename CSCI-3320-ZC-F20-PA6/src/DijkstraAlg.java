import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DijkstraAlg {
    private int n;
    private int edgeCount;
    private Integer[] prev;
    private List<List<Edge>> graph;

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
     * @param n - amount of nodes in the graph
     */
    public void initialize(int n) {
        this.n = n;
        createEmptyGraph();
    }

    // Construct an empty graph of n nodes; including source and sink nodes
    private void createEmptyGraph() {
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
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
     * Dijkstra's Algorithm on a directred graph to find the shortest path from a starting node to an ending node.
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
            throw new IllegalArgumentException("Invalide node index");
        List<Integer> path = new ArrayList<>();
        double dist = dijkstraAlgorithm(start, end);
        if (dist == Double.POSITIVE_INFINITY) return path;
        for (Integer at = end; at != null; at = prev[at])
            path.add(at);
        Collections.reverse(path);
        return path;
    }
}
