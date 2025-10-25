package algorithms;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();

    public void makeSet(String vertex) {
        parent.put(vertex, vertex);
        rank.put(vertex, 0);
    }

    public String find(String vertex) {
        if (!parent.get(vertex).equals(vertex)) {
            parent.put(vertex, find(parent.get(vertex))); // path compression
        }
        return parent.get(vertex);
    }

    public void union(String v1, String v2) {
        String root1 = find(v1);
        String root2 = find(v2);

        if (root1.equals(root2)) return;

        int rank1 = rank.get(root1);
        int rank2 = rank.get(root2);

        if (rank1 < rank2) {
            parent.put(root1, root2);
        } else if (rank1 > rank2) {
            parent.put(root2, root1);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank1 + 1);
        }
    }
}

