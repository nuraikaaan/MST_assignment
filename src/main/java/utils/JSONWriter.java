package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONWriter {

    public static class GraphResult {
        public int graphId;
        public int vertexCount;
        public int edgeCount;
        public AlgorithmResult prim;
        public AlgorithmResult kruskal;

        public GraphResult(int graphId, int vertexCount, int edgeCount,
                           AlgorithmResult prim, AlgorithmResult kruskal) {
            this.graphId = graphId;
            this.vertexCount = vertexCount;
            this.edgeCount = edgeCount;
            this.prim = prim;
            this.kruskal = kruskal;
        }
    }

    public static class AlgorithmResult {
        public List<String> mstEdges;
        public double totalCost;
        public int operationsCount;
        public double executionTimeMs;

        public AlgorithmResult(List<String> mstEdges, double totalCost,
                               int operationsCount, double executionTimeMs) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operationsCount = operationsCount;
            this.executionTimeMs = executionTimeMs;
        }
    }

    public static void writeResultsToJSON(String fileName, List<GraphResult> graphResults) {
        JSONArray resultsArray = new JSONArray();

        for (GraphResult result : graphResults) {
            JSONObject graphObj = new JSONObject();
            graphObj.put("graph_id", result.graphId);

            JSONObject inputStats = new JSONObject();
            inputStats.put("vertices", result.vertexCount);
            inputStats.put("edges", result.edgeCount);
            graphObj.put("input_stats", inputStats);

            // Prim
            JSONObject primObj = new JSONObject();
            primObj.put("mst_edges", result.prim.mstEdges);
            primObj.put("total_cost", result.prim.totalCost);
            primObj.put("operations_count", result.prim.operationsCount);
            primObj.put("execution_time_ms", result.prim.executionTimeMs);
            graphObj.put("prim", primObj);

            // Kruskal
            JSONObject kruskalObj = new JSONObject();
            kruskalObj.put("mst_edges", result.kruskal.mstEdges);
            kruskalObj.put("total_cost", result.kruskal.totalCost);
            kruskalObj.put("operations_count", result.kruskal.operationsCount);
            kruskalObj.put("execution_time_ms", result.kruskal.executionTimeMs);
            graphObj.put("kruskal", kruskalObj);

            resultsArray.put(graphObj);
        }

        JSONObject root = new JSONObject();
        root.put("results", resultsArray);

        try {
            FileWriter file = new FileWriter(fileName);
            file.write(root.toString(4)); // 4 = pretty print format
            file.close();
            System.out.println(" JSON file created: " + fileName);
        } catch (IOException e) {
            System.out.println(" Error writing JSON file: " + e.getMessage());
        }

    }
}
