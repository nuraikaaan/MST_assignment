import algorithms.*;
import graph.*;
import utils.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Graph> inputGraphs = JSONReader.readAllGraphs("src/main/resources/input.json");

            List<CSVWriter.ResultRow> csvResults = new ArrayList<>();
            List<JSONWriter.GraphResult> jsonResults = new ArrayList<>();

            int graphIndex = 1;

            for (Graph graph : inputGraphs) {
                System.out.println("\n=== Graph " + graphIndex + " ===");

                // --- Run Prim Algorithm ---
                PrimAlgorithm prim = new PrimAlgorithm();
                PrimAlgorithm.Result primResult = prim.run(graph);

                // --- Run Kruskal Algorithm ---
                KruskalAlgorithm kruskal = new KruskalAlgorithm();
                KruskalAlgorithm.Result kruskalResult = kruskal.run(graph);

                // --- Print summary ---
                System.out.println("Prim MST cost: " + primResult.cost);
                System.out.println("Kruskal MST cost: " + kruskalResult.cost);

                // --- Save to CSV ---
                csvResults.add(new CSVWriter.ResultRow(
                        graphIndex,
                        "Prim",
                        primResult.cost,
                        primResult.operations,
                        primResult.timeMs
                ));

                csvResults.add(new CSVWriter.ResultRow(
                        graphIndex,
                        "Kruskal",
                        kruskalResult.cost,
                        kruskalResult.operations,
                        kruskalResult.timeMs
                ));

                List<String> primEdgeList = primResult.edges.stream().map(Edge::toString).toList();
                List<String> kruskalEdgeList = kruskalResult.edges.stream().map(Edge::toString).toList();

                jsonResults.add(new JSONWriter.GraphResult(
                        graphIndex,
                        graph.getVertices().size(),
                        graph.getEdges().size(),
                        new JSONWriter.AlgorithmResult(primEdgeList, primResult.cost,
                                primResult.operations, primResult.timeMs),
                        new JSONWriter.AlgorithmResult(kruskalEdgeList, kruskalResult.cost,
                                kruskalResult.operations, kruskalResult.timeMs)
                ));

                graphIndex++;
            }

            CSVWriter.writeResultsToCSV("comparison.csv", csvResults);
            JSONWriter.writeResultsToJSON("output.json", jsonResults);

            System.out.println("\n All results saved to comparison.csv and output.json");

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}

