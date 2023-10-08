import com.example.assign2_dsa2_dbp_jc.CostedPath;
import com.example.assign2_dsa2_dbp_jc.Dijkstra;
import com.example.assign2_dsa2_dbp_jc.GraphNode;

import com.example.assign2_dsa2_dbp_jc.Station;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    @Test
    public void testDijkstra() {
        GraphNode<Station> node0 = new GraphNode<>(new Station(0));
        GraphNode<Station> node1 = new GraphNode<>(new Station(1));
        GraphNode<Station> node2 = new GraphNode<>(new Station(2));
        GraphNode<Station> node3 = new GraphNode<>(new Station(3));
        GraphNode<Station> node4 = new GraphNode<>(new Station(4));
        GraphNode<Station> node5 = new GraphNode<>(new Station(5));

        node0.connectToNodeUndirected(node1, 2, 1);
        node0.connectToNodeUndirected(node2, 3, 1);
        node1.connectToNodeUndirected(node2, 1, 2);
        node1.connectToNodeUndirected(node3, 2, 1);
        node2.connectToNodeUndirected(node3, 1, 1);
        node2.connectToNodeUndirected(node4, 2, 1);
        node3.connectToNodeUndirected(node4, 2, 1);

        CostedPath cp = Dijkstra.findCheapestPathDijkstra(node0, null, node4.data);

        assertNotNull(cp);
        assertEquals(5, cp.pathCost);
    }
}
