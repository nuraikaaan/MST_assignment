package graph;

import java.util.*;

public class Graph {
    private final List<String> vertices;
    private final List<Edge> edges;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addVertex(String v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
        }
    }

    public void addEdge(String from, String to, int weight) {
        edges.add(new Edge(from, to, weight));
        addVertex(from);
        addVertex(to);
    }

    public List<String> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
