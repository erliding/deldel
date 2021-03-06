/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 **/

public class Solution {
  public int climbStairs(int n) {
    if (n < 3) return n;
    int n1 = 1;
    int n2 = 2;
    int nn = 0;
    for (int i = 3; i <= n; i++) {
      nn = n1 + n2;
      n1 = n2;
      n2 = nn;
    }
    return nn;
  }
}