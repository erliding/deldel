/**
 For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a
 rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
 Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 Format
 The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected
 edges (each edge is a pair of labels).
 You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0]
 and thus will not appear together in edges.
 Example 1:
 Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
  0
  |
  1
 / \
2   3
 return [1]
 Example 2:
 Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 0  1  2
  \ | /
    3
    |
    4
    |
    5
 return [3, 4]
 Note:
 (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices
 are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
 (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 **/

public class Solution {
//  The root of an MHT has to be the middle point (or two middle points) of the longest path of the tree.
//  Though multiple longest paths can appear in an unrooted tree, they must share the same middle point(s).
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    List<Integer> mhtNodes = new ArrayList<>();
    if (n == 0) return mhtNodes;
    List<Set<Integer>> neighbors = new ArrayList<>();
    for (int i = 0; i < n; i++) neighbors.add(new HashSet<Integer>());
    for (int[] edge : edges) {
      neighbors.get(edge[0]).add(edge[1]);
      neighbors.get(edge[1]).add(edge[0]);
    }
    int[] pre = new int[n];
    int oneEnd = longestPathBfs(0, neighbors, new boolean[n], pre);
    int otherEnd = longestPathBfs(oneEnd, neighbors, new boolean[n], pre);
    List<Integer> longest = new ArrayList<>();
    do {
      longest.add(otherEnd);
    } while ((otherEnd = pre[otherEnd]) != -1);
    mhtNodes.add(longest.get(longest.size() / 2));
    if (longest.size() % 2 == 0) {
      mhtNodes.add(longest.get(longest.size() / 2 - 1));
    }
    return mhtNodes;
  }

  // dfs causes stack overflow
  void longestPath(int node, List<Set<Integer>> neighbors, boolean[] visited, List<Integer> path, List<Integer> longest) {
    visited[node] = true;
    path.add(node);
    for (int child : neighbors.get(node)) {
      if (!visited[child]) longestPath(child, neighbors, visited, path, longest);
    }
    if (path.size() > longest.size()) {
      longest.clear();
      longest.addAll(new ArrayList<Integer>(path));
    }
  }

  // use pre array for backtrace
  int longestPathBfs(int node, List<Set<Integer>> neighbors, boolean[] visited, int[] pre) {
    pre[node] = -1;
    Queue<Integer> q = new ArrayDeque<>();
    q.offer(node);
    visited[node] = true;
    // return last node in the queue, which must be the node with longest dist.
    // Can instead pass a int[] dists to record distance of every node to the root, dist[node] = 1 + dist[pre[node]]
    int last = node;
    while (!q.isEmpty()) {
      last = q.poll();
      for (int child : neighbors.get(last)) {
        if (!visited[child]) {
          q.offer(child);
          visited[child] = true;
          pre[child] = last;
        }
      }
    }
    return last;
  }
}

/**
 * Alternatively, one can solve this problem directly by tree dp.
 * Let dp[i] be the height of the tree when the tree root is i.
 * We compute dp[0] ... dp[n - 1] by tree dp in a dfs manner.
 *
 * Arbitrarily pick a node, say node 0, as the root, and do a dfs.
 * When reach a node u, and let T be the subtree by removing all u's descendant (see the right figure below).
 * We maintain a variable acc that keeps track of the length of the longest path in T with one endpoint being u.
 * Then dp[u] = max(height[u], acc)
 * Note, acc is 0 for the root of the tree.
 *
 *                 |                 |
 *                 .                 .
 *                /|\               /|\
 *               * u *             * u *
 *                /|\
 *               / | \
 *              *  v  *
 *
 *  . denotes a single node, and * denotes a subtree (possibly empty).
 *
 *  Now it remains to calculate the new acc for any of u's child, v.
 *  It is easy to see that the new acc is the max of the following
 *
 *  1) acc + 1 --- extend the previous path by the edge uv;
 *  2) max(height[v'] + 2), where v != v' --- see below for an example.
 *
 *                 u
 *                /|
 *               / |
 *              v' v
 *              |
 *              .
 *              .
 *              .
 *              |
 *              .
 *
 * In fact, the second case can be computed in O(1) time instead of spending a time proportional to the degree of u.
 * Otherwise, the runtime can be quadratic when the degree of some node is Omega(n).
 * The trick here is to maintain two heights of each node, the largest height (the conventional height), and the second largest height
 * (the height of the node after removing the branch w.r.t. the largest height).
 *
 * Therefore, after the dfs, all dp[i]'s are computed, and the problem can be answered trivially.
 * The total runtime is still O(n).
 */
public class Solution2 {
  int n;
  List<Integer>[] e;
  int[] height1;
  int[] height2;
  int[] dp;

  private void dfs(int u, int parent) {
    height1[u] = height2[u] = -Integer.MIN_VALUE / 10;
    for (int v : e[u])
      if (v != parent) {
        dfs(v, u);
        int tmp = height1[v] + 1;
        if (tmp > height1[u]) {
          height2[u] = height1[u];
          height1[u] = tmp;
        } else if (tmp > height2[u]) {
          height2[u] = tmp;
        }
      }
    height1[u] = Math.max(height1[u], 0); // in case u is a leaf.
  }

  private void dfs(int u, int parent, int acc) {
    dp[u] = Math.max(height1[u], acc);
    for (int v : e[u])
      if (v != parent) {
        int newAcc = Math.max(acc + 1, (height1[v] + 1 == height1[u] ? height2[u] : height1[u]) + 1);
        dfs(v, u, newAcc);
      }
  }

  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    if (n <= 0) return new ArrayList<>();
    if (n == 1) return Arrays.asList(0);

    this.n = n;
    e = new List[n];
    for (int i = 0; i < n; i++)
      e[i] = new ArrayList<>();
    for (int[] pair : edges) {
      int u = pair[0];
      int v = pair[1];
      e[u].add(v);
      e[v].add(u);
    }

    height1 = new int[n];
    height2 = new int[n];
    dp = new int[n];

    dfs(0, -1);
    dfs(0, -1, 0);

    int min = dp[0];
    for (int i : dp)
      if (i < min) min = i;

    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < n; i++)
      if (dp[i] == min) ans.add(i);
    return ans;
  }
}

/*
1) A tree is an undirected graph in which any two vertices are connected by exactly one path.
(2) Any connected graph who has n nodes with n-1 edges is a tree.
(3) The degree of a vertex of a graph is the number of edges incident to the vertex.
(4) A leaf is a vertex of degree 1. An internal vertex is a vertex of degree at least 2.
(5) A path graph is a tree with two or more vertices that is not branched at all.
(6) A tree is called a rooted tree if one vertex has been designated the root.
(7) The height of a rooted tree is the number of edges on the longest downward path between root and a leaf.

We start from every end, by end we mean vertex of degree 1 (aka leaves). We let the pointers move the same
speed. When two pointers meet, we keep only one of them, until the last two pointers meet or one step away
we then find the roots.
It is easy to see that the last two pointers are from the two ends of the longest path in the graph.

The actual implementation is similar to the BFS topological sort. Remove the leaves, update the degrees of inner
vertexes. Then remove the new leaves. Doing so level by level until there are 2 or 1 nodes left.
 */
public class Solution3 {
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    if (n == 1) return Collections.singletonList(0);

    List<Set<Integer>> adj = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
    for (int[] edge : edges) {
      adj.get(edge[0]).add(edge[1]);
      adj.get(edge[1]).add(edge[0]);
    }

    List<Integer> leaves = new ArrayList<>();
    for (int i = 0; i < n; ++i)
      if (adj.get(i).size() == 1) leaves.add(i);

    while (n > 2) {
      n -= leaves.size();
      List<Integer> newLeaves = new ArrayList<>();
      for (int i : leaves) {
        int j = adj.get(i).iterator().next();
        adj.get(j).remove(i);
        if (adj.get(j).size() == 1) newLeaves.add(j);
      }
      leaves = newLeaves;
    }
    return leaves;
  }
}
