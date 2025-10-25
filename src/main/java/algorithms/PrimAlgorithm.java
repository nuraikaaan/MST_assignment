package algorithms;

import graph.Edge;
import graph.Graph;

import java.util.*;

public class PrimAlgorithm {

    private int operations;

    public PrimAlgorithm() {
        this.operations = 0;
    }

    public Result run(Graph graph) {
        List<Edge> mstEdges = new ArrayList<>();
        operations = 0;

        List<String> vertices = graph.getVertices();
        if (vertices.isEmpty()) return new Result(mstEdges, 0, operations, 0);

        Set<String> visited = new HashSet<>();
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String vertex : vertices) adj.put(vertex, new ArrayList<>());

        for (Edge e : graph.getEdges()) {
            adj.get(e.getFrom()).add(e);
            adj.get(e.getTo()).add(e);
        }

        String startVertex = vertices.get(0); // deterministic
        visited.add(startVertex);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        pq.addAll(adj.get(startVertex));

        long startTime = System.nanoTime();

        while (!pq.isEmpty() && visited.size() < vertices.size()) {
            Edge edge = pq.poll();

            String nextVertex = null;
            if (!visited.contains(edge.getFrom())) nextVertex = edge.getFrom();
            else if (!visited.contains(edge.getTo())) nextVertex = edge.getTo();

            if (nextVertex != null) {
                visited.add(nextVertex);
                mstEdges.add(edge);
                operations++; // count only added edges

                for (Edge e : adj.get(nextVertex)) {
                    String neighbor = e.getFrom().equals(nextVertex) ? e.getTo() : e.getFrom();
                    if (!visited.contains(neighbor)) pq.add(e);
                }
            }
        }

        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1_000_000.0;
        int totalCost = mstEdges.stream().mapToInt(Edge::getWeight).sum();

        return new Result(mstEdges, totalCost, operations, timeMs);
    }

    public static class Result {
        public List<Edge> edges;
        public int cost;
        public int operations;
        public double timeMs;

        public Result(List<Edge> edges, int cost, int operations, double timeMs) {
            this.edges = edges;
            this.cost = cost;
            this.operations = operations;
            this.timeMs = timeMs;
        }
    }
}



