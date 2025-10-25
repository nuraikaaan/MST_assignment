package algorithms;

import graph.Edge;
import graph.Graph;

import java.util.*;

public class KruskalAlgorithm {

    private int operations;

    public KruskalAlgorithm() {
        this.operations = 0;
    }

    public Result run(Graph graph) {
        List<Edge> mstEdges = new ArrayList<>();
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getWeight)); // deterministic sort by weight

        DisjointSet dsu = new DisjointSet();
        for (String vertex : graph.getVertices()) dsu.makeSet(vertex);

        long startTime = System.nanoTime();

        for (Edge edge : edges) {
            String root1 = dsu.find(edge.getFrom());
            String root2 = dsu.find(edge.getTo());

            if (!root1.equals(root2)) {
                mstEdges.add(edge);
                dsu.union(root1, root2);
                operations++;
            }

            if (mstEdges.size() == graph.getVertices().size() - 1) break;
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
