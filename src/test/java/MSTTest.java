import algorithms.*;
import graph.*;
import utils.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MSTTest {

    @Test
    public void testPrimAndKruskalHaveSameCost() throws Exception {
        Graph graph = JSONReader.readGraph("src/main/resources/test_input.json", 0);
        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        var p = prim.run(graph);
        var k = kruskal.run(graph);

        assertEquals(p.cost, k.cost);
        assertEquals(graph.getVertices().size() - 1, p.edges.size());
        assertTrue(p.cost > 0);
        assertTrue(k.timeMs >= 0);
    }
}
