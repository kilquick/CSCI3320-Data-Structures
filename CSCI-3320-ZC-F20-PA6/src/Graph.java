import java.util.ArrayList;

/**
 * This class represents a graph with vertices and edges.
 */

public class Graph {
    private ArrayList<Vertex> vertexList;
    private ArrayList<Edge> edgeList;

    public Graph() {
        this.vertexList = new ArrayList<Vertex>();
        this.edgeList = new ArrayList<Edge>();
    }

    /**
     * Adds vertex to the list if it is not already present.
     *
     * @param v
     *            - vertex to be added
     * @return - Returns true if vertex is added, false otherwise
     */
    public boolean addVertex(Vertex v) {
        // Check if vertex is already present
        if (!vertexList.contains(v)) {
            vertexList.add(v);
            return true;
        }
        return false;
    }


    /**
     * Adds an edge to the list if it is not already present.
     *
     * @param e
     *            - edge to be added
     * @return - Returns true if edge is added, false otherwise
     */
    public boolean addEdge(Edge e) {
        // Check if edge is already present
        if (!edgeList.contains(e)) {
            // Try to add edge to the from vertex
            if (e.getFrom().addEdge(e)) {
                edgeList.add(e);
            } else
                return false;
        }
        return false;
    }

    /**
     * Displays the graph
     */
    public void displayGraph() {
        if (vertexList.size() > 0) {
            for (Vertex v : vertexList) {
                // Check if vertex v has edges
                if (v.getEdgeList().size() > 0) {
                    System.out.println("Vertex " + v
                            + " has edges to the following vertices:");
                    // Display edges
                    for (Edge e : v.getEdgeList()) {
                        System.out.println("\tVertex " + e.getTo()
                                + " with weight " + e.getWeight());
                    }
                } else
                    System.out.println("Vertex " + v
                            + " has no outgoing edges to any of the vertices.");
                System.out.println();
            }
        }
    }

    /*
     * Implementation of the dijstra method
     */
    void dijkstra( Vertex s )
    {
        ArrayList<Vertex> unknownVertexList = new ArrayList<Vertex>();
        for(Vertex v : vertexList)
        {
            v.dist = Integer.MAX_VALUE;
            unknownVertexList.add(v);
        }
        s.dist = 0;
        while( unknownVertexList.size()>0 )
        {
            Vertex v = getMinVertex(unknownVertexList);
            //v.known = true;
            unknownVertexList.remove(v);
            // for each Vertex w adjacent to v
            for(Edge e : v.getEdgeList()){
                Vertex w;
                w = (e.getTo().equals(v))?e.getFrom():e.getTo();
                int cvw = e.getWeight();

                if( v.dist + cvw < w.dist )
                {
                    w.dist = v.dist + cvw;
                    w.path = v;
                }
            }
        }
    }

    /*
     * This method returns the vertex with minimum distance in unknownVertexList
     */
    Vertex getMinVertex(ArrayList<Vertex> vertexList){
        int minDist = Integer.MAX_VALUE;
        Vertex minVertex = null;
        for(Vertex v: vertexList){
            if(v.dist < minDist){
                minDist = v.dist;
                minVertex = v;
            }
        }
        return minVertex;
    }

    ArrayList<Vertex> getPath(Vertex w){
        ArrayList<Vertex> path = new ArrayList<Vertex>();
        path.add(0,w);
        Vertex prev=w.path;
        while(prev!=null){
            path.add(0,prev);
            prev = prev.path;
        }
        return path;
    }
}