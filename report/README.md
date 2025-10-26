SE-2434 Nurai Nariman  


Assignment 3: Optimization of a City Transportation Network (Minimum Spanning Tree) 

1. Objective 

The goal of this assignment was to optimize a city transportation network by constructing a Minimum Spanning Tree (MST) using Prim’s and Kruskal’s algorithms. This minimizes the total cost of connecting all districts while ensuring that every district is reachable. 

Additionally, the assignment evaluates performance, operation counts, and efficiency for both algorithms across multiple graph sizes. 

 

- Prim’s Algorithm – builds the MST by growing a single tree starting from a vertex. 

- Kruskal’s Algorithm – builds the MST by repeatedly adding the smallest edge that does not form a cycle. 

- Prim O(V2)  or  O(ElogV), Kruskal  O(ElogE). 

- Prim is often better for dense graphs, Kruskal can be better for sparse graphs. 

 

 

2. Input Data 

- Three categories of graphs were used:
<img width="601" height="93" alt="Снимок экрана 2025-10-26 в 10 41 25 PM" src="https://github.com/user-attachments/assets/51f63d47-7351-4950-888e-3ea8734844b5" />
 

Input data stored in JSON files (input.json) 

Each edge contains from, to, and weight (construction cost) 

3. Algorithm Implementations 

 

Prim’s Algorithm 

Uses adjacency list and PriorityQueue for selecting the minimum edge efficiently. 

Only edges connecting new vertices are added to the queue. 

Counts operations for each edge selection. 

Measures execution time using System.nanoTime() converted to milliseconds. 

Optimizations: 

Adjacency list pre-created → avoids repeated computeIfAbsent. 

PriorityQueue stores only relevant edges → reduces unnecessary comparisons. 

Adds edges only to unvisited vertices → fewer operations and memory usage. 

 

Kruskal’s Algorithm 

Uses Disjoint Set Union (DSU) with path compression and union by rank. 

Sorts all edges once → O(E log E). 

Adds edges to MST only if they connect different components. 

Counts operations for find and union. 

Measures execution time in milliseconds. 

Optimizations: 

DSU optimizes union/find → near O(E). 

Early exit if MST has V-1 edges → avoids unnecessary processing. 

 

4. Summary Table
<img width="610" height="318" alt="Снимок экрана 2025-10-26 в 10 41 58 PM" src="https://github.com/user-attachments/assets/6cf76aee-9227-4ccf-a52b-fc959436e198" />

Before/After optimization 

<img width="607" height="284" alt="Снимок экрана 2025-10-26 в 10 42 20 PM" src="https://github.com/user-attachments/assets/5de69b69-1ab6-49a3-8381-607336f499c6" />

Observations: 

MST Costs are identical for Prim and Kruskal → correctness verified. 

Kruskal generally has lower execution time on larger graphs. 

Prim has fewer operations on smaller graphs, but the gap closes as graph size increases. 

Both algorithms scale well, but graph representation affects Prim more (dense adjacency lists are heavier to process). 

 

5.Plots 

1. Execution time(ms) vs Graph id  
<img width="608" height="356" alt="Снимок экрана 2025-10-26 в 10 42 41 PM" src="https://github.com/user-attachments/assets/596c840b-6d5b-47ca-9f17-c8adee3e5e70" />

- After optimizations 
<img width="605" height="328" alt="Снимок экрана 2025-10-26 в 10 43 02 PM" src="https://github.com/user-attachments/assets/4ff09130-092d-4c8e-aff2-4bfacb6caf35" />

2. Operations vs Graph id
<img width="612" height="376" alt="Снимок экрана 2025-10-26 в 10 43 31 PM" src="https://github.com/user-attachments/assets/13809ead-3542-49ea-9ac4-fb61f90e8335" />

5. Testing 

Automated tests using JUnit verified: 

Correctness 

MST cost identical for Prim and Kruskal. ✅ 

MST has V - 1 edges. ✅ 

MST is acyclic. ✅ 

MST connects all vertices. ✅ 

Disconnected graphs handled gracefully (returns empty MST). ✅ 

Performance & Consistency 

Execution time ≥ 0 ms. ✅ 

Operation counts consistent with algorithm behavior. ✅ 

Reproducible results on repeated runs. ✅ 

 

 

6. Graph Data Structure (Bonus) 

Graph.java 

Represents an undirected, weighted graph. 

Stores vertices in a Set<String> and edges in a List<Edge>. 

Provides methods to add vertices, add edges, and retrieve edges for MST algorithms. 

Edge.java 

Represents an edge connecting two vertices with a weight. 

Implements Comparable<Edge> to allow sorting by weight for Kruskal’s algorithm. 

Provides toString() for human-readable MST output. 

Using these classes ensures proper OOP design and makes the algorithms flexible for any input graph. 

Operation counts and execution time metrics included for performance analysis. 

Code is easily extendable for weighted, directed, or disconnected graphs. 

 

9. Conclusion 

In this project, both Prim’s and Kruskal’s algorithms for computing Minimum Spanning Trees (MSTs) were successfully implemented and tested using a custom Java graph structure. The key outcomes are as follows: 

Correctness: 

Both algorithms consistently produced MSTs with identical total costs across all test graphs. 

The resulting MST edges match the expected minimal connections, demonstrating the correctness of the implementations. 

Performance Analysis: 

Kruskal’s algorithm generally performed faster than Prim’s for the tested graphs, especially as the graph size increased, due to simpler edge selection and fewer priority queue operations. 

Prim’s algorithm, while slightly slower on larger graphs, remains efficient and correctly grows the MST from a starting vertex. 

Execution time and operation counts were measured for each algorithm, providing a quantitative comparison of efficiency. 

Software Design: 

The custom Graph and Edge classes promote modularity, maintainability, and code reuse. 

The implementation follows object-oriented principles, including encapsulation, abstraction, and clear separation of responsibilities. 

Using a Disjoint Set Union (DSU) with path compression and union by rank ensures Kruskal’s algorithm is optimized for larger graphs. 

Data Integration and Output: 

Results are stored in both CSV and JSON formats, allowing easy analysis and verification of MST computation. 

Each graph’s structure, MST edges, total cost, execution time, and operation count are clearly documented, supporting reproducibility and transparency. 

Bonus Achievement: 

By designing and integrating a custom graph structure that works seamlessly with both MST algorithms, the project demonstrates a deeper understanding of software architecture and graph-based algorithm design. 

 

Graph density (dense vs sparse): 

Prim is more efficient for dense graphs with many edges. 

Kruskal is better for sparse graphs with fewer edges. 

Edge representation: 

If the graph is stored as an adjacency list, Prim with a priority queue can efficiently compute the MST. 

If the graph is stored as a list of edges, Kruskal is simpler to implement and often faster. 

Implementation complexity: 

Prim requires a priority queue and is slightly more complex to implement. 

Kruskal is simpler when using DSU / Union-Find, especially if the edges are already sorted. 

 

 

 

 

 
 
