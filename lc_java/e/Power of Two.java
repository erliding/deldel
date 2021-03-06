/**
 * Given an integer, write a function to determine if it is a power of two.
 */

public class Solution {
  public boolean isPowerOfTwo(int n) {
    if (n <= 0) return false; // ERROR: edge case
    return ((n - 1) & n) == 0;
  }
}
