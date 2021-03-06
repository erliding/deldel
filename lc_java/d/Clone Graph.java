/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * <p>
 * <p>
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * <p>
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * <p>
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * <p>
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 * <p>
 * 1
 * / \
 * /   \
 * 0 --- 2
 * / \
 * \_/
 **/

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) return null;
    return clone(node, new HashMap<UndirectedGraphNode, UndirectedGraphNode>());
  }

  UndirectedGraphNode clone(UndirectedGraphNode n, Map<UndirectedGraphNode, UndirectedGraphNode> cloned) {
    UndirectedGraphNode c = cloned.get(n);
    if (c == null) {
      c = new UndirectedGraphNode(n.label);
      cloned.put(n, c);
      for (UndirectedGraphNode nei : n.neighbors) {
        c.neighbors.add(clone(nei, cloned));
      }
    }
    return c;
  }
}

public class Solution {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) return null; // ERROR: edge case
    Map<UndirectedGraphNode, UndirectedGraphNode> cloned = new HashMap();
    return clone(node, cloned);
  }

  // cloned servers as a mark for visited, if not visited then clone and explore all of its edges
  private UndirectedGraphNode clone(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> cloned) {
    UndirectedGraphNode copy = cloned.get(node);
    if (copy != null) return copy;

    copy = new UndirectedGraphNode(node.label);
    cloned.put(node, copy);
    for (UndirectedGraphNode neighbor : node.neighbors) {
      copy.neighbors.add(clone(neighbor, cloned));
    }
    return copy;
  }
}
