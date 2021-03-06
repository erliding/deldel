/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
  Deque<TreeNode> st = new ArrayDeque<>();

  public BSTIterator(TreeNode root) {
    while(root != null) {
      st.push(root);
      root = root.left;
    }
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return !st.isEmpty();
  }

  /** @return the next smallest number */
  public int next() {
    TreeNode top = st.pop();
    int next = top.val;
    top = top.right;
    while(top != null) {
      st.push(top);
      top = top.left;
    }
    return next;
  }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */