/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 */

public class Solution {
  public int maxProfit(int[] prices) {
    int n = prices.length;
    if (n == 0) return 0;
    int[] prefixProf = new int[n];
    int buy = prices[0];
    for (int i = 1; i < n; i++) {
      prefixProf[i] = Math.max(prefixProf[i - 1], prices[i] - buy);
      buy = Math.min(buy, prices[i]);
    }
    int[] suffixProf = new int[n];
    int sell = prices[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      suffixProf[i] = Math.max(suffixProf[i + 1], sell - prices[i]);
      sell = Math.max(sell, prices[i]);
    }
    int p = 0;
    for (int i = 0; i < n; i++) {
      p = Math.max(prefixProf[i] + suffixProf[i], p);
    }
    return p;
  }
}

public class Solution {
  public int maxProfit(int[] prices) {
    if (prices.length == 0) return 0;
    int len = prices.length;
    int[] premin = new int[len];
    int[] suffmax = new int[len];
    premin[0] = prices[0];
    suffmax[len - 1] = prices[len - 1];
    for (int i = 1; i < len; i++) {
      premin[i] = Math.min(prices[i], premin[i - 1]);
    }
    for (int i = len - 2; i >= 0; i--) {
      suffmax[i] = Math.max(suffmax[i + 1], prices[i]);
    }
    int[] left = new int[len]; // max profit for making one transaction on interal [0, i]
    for (int i = 1; i < len; i++) {
      left[i] = Math.max(left[i - 1], prices[i] - premin[i]); // sell before i, or sell at i
    }
    int[] right = new int[len]; // max profit for making one transaction on interal [i, n - 1]
    for (int i = len - 2; i >= 0; i--) {
      right[i] = Math.max(right[i + 1], suffmax[i] - prices[i]); // buy after i, or buy at i
    }
    int profit = 0;
    for (int i = 0; i < len; i++) {
      profit = Math.max(profit, left[i] + right[i]);
    }
    return profit;
  }
}
