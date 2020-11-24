import java.util.ArrayList;

/**
 * This class represents a Vertex in a graph.
 */

public class Vertex {
    // Instance variables
    private String name;
    private ArrayList<Edge> edgeList;

    //Variables added for dijstra
    boolean known;
    int dist;
    Vertex path;

    /**
     * Constructor
     *
     * @param name
     *            - name of the vertex
     */
    public Vertex(String name) {
        this.name = name;
        this.edgeList = new ArrayList<Edge>();
    }

    /**
     * Adds an edge from this vertex to another if it is not already present.
     *
     * @param edge
     *            - edge
     * @return - Returns true if edge is added, false if edge is already present
     *         in the list. Throws exception if edge does not contain this
     *         vertex.
     */
    public boolean addEdge(Edge edge) {
        // Check if edge contains this vertex
        if (edge.containsVertex(this)) {
            // Check if edgeList does not contain edge
            if (!edgeList.contains(edge)) {
                edgeList.add(edge);
                return true;
            } else
                return false;
        } else
            throw new IllegalArgumentException(
                    "Edge does not contain vertex " + this);
    }

    /**
     * Returns the edgeList
     */
    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Vertex))
            return false;
        else {
            Vertex other = (Vertex) obj;
            if (name.equalsIgnoreCase(other.name))
                return true;
            else
                return false;
        }
    }

    @Override
    public String toString() {
        return name;
    }

}