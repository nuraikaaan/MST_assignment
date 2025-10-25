package utils;

import graph.Graph;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {

    public static Graph readGraph(String filePath, int graphIndex) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject obj = new JSONObject(content);
        JSONArray graphs = obj.getJSONArray("graphs");
        JSONObject g = graphs.getJSONObject(graphIndex);

        Graph graph = new Graph();
        JSONArray edges = g.getJSONArray("edges");
        for (int i = 0; i < edges.length(); i++) {
            JSONObject e = edges.getJSONObject(i);
            graph.addEdge(e.getString("from"), e.getString("to"), e.getInt("weight"));
        }

        return graph;
    }

    public static List<Graph> readAllGraphs(String filePath) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject obj = new JSONObject(content);

        List<Graph> graphsList = new ArrayList<>();


        if (obj.has("graphs")) {
            JSONArray graphs = obj.getJSONArray("graphs");
            for (int i = 0; i < graphs.length(); i++) {
                JSONObject g = graphs.getJSONObject(i);
                Graph graph = new Graph();

                JSONArray edges = g.getJSONArray("edges");
                for (int j = 0; j < edges.length(); j++) {
                    JSONObject e = edges.getJSONObject(j);
                    graph.addEdge(e.getString("from"), e.getString("to"), e.getInt("weight"));
                }
                graphsList.add(graph);
            }
        } else {

            for (String key : obj.keySet()) {
                JSONArray graphs = obj.getJSONArray(key);
                for (int i = 0; i < graphs.length(); i++) {
                    JSONObject g = graphs.getJSONObject(i);
                    Graph graph = new Graph();

                    JSONArray edges = g.getJSONArray("edges");
                    for (int j = 0; j < edges.length(); j++) {
                        JSONObject e = edges.getJSONObject(j);
                        graph.addEdge(e.getString("from"), e.getString("to"), e.getInt("weight"));
                    }
                    graphsList.add(graph);
                }
            }
        }

        return graphsList;
    }
}
