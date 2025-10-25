package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {

    public static class ResultRow {
        public int graphId;
        public String algorithm;
        public double totalCost;
        public int operationsCount;
        public double executionTime;

        public ResultRow(int graphId, String algorithm, double totalCost, int operationsCount, double executionTime) {
            this.graphId = graphId;
            this.algorithm = algorithm;
            this.totalCost = totalCost;
            this.operationsCount = operationsCount;
            this.executionTime = executionTime;
        }
    }

    public static void writeResultsToCSV(String fileName, List<ResultRow> results) {
        try (FileWriter writer = new FileWriter(fileName)) {

            writer.append("Graph ID,Algorithm,MST Cost,Operations,Execution Time (ms)\n");

            for (ResultRow row : results) {
                writer.append(String.valueOf(row.graphId))
                        .append(",")
                        .append(row.algorithm)
                        .append(",")
                        .append(String.valueOf(row.totalCost))
                        .append(",")
                        .append(String.valueOf(row.operationsCount))
                        .append(",")
                        .append(String.valueOf(row.executionTime))
                        .append("\n");
            }

            System.out.println("CSV file created successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }
    }
}
