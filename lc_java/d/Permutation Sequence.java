/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * Note: Given n will be between 1 and 9 inclusive.
 **/

public class Solution {
  public String getPermutation(int n, int k) {
    k--;
    String ret = "";
    boolean[] used = new boolean[n];
    while (n > 0) {
      int ith = k / fact(n - 1); // base = (n - 1)!
      k %= fact(n - 1);
      int i = 0;
      for (; i < used.length; i = (i + 1) % used.length) {
        if (!used[i] && ith-- == 0) break;
      }
      ret += (i + 1);
      used[i] = true;
      n--;
    }
    return ret;
  }

  int fact(int n) { // can store primes in an array
    if (n == 0) return 1;
    else return n * fact(n - 1);
  }
}

public class Solution {
  public String getPermutation(int n, int k) {
    int[] factorials = new int[n + 1];
    factorials[0] = 1;
    for (int i = 1; i <= n; i++) {
      factorials[i] = factorials[i - 1] * i;
    }
    boolean[] used = new boolean[n + 1];
    StringBuilder ret = new StringBuilder();
    k--; // BUG: Must subtract 1, because in fact 1-st number "123" corresponds to 0 in the normal sense, "132" corresponds to 1
    while (n > 0) {
      int base = factorials[n - 1];
      int digit = k / base + 1;
      k = k % base;
      int i = 0;
      while (digit > 0) {
        i++;
        if (!used[i]) digit--;
      }
      ret.append(i);
      used[i] = true;
      n--;
    }
    return ret.toString();
  }
}