/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * <p>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 * <p>
 * Note:
 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 * <p>
 * Follow up:
 * Could you solve it in linear time?
 */

public class Solution {
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (k == 0) return new int[0];
    int[] max = new int[nums.length - k + 1];
    Deque<Integer> window = new ArrayDeque<>();
    for (int i = 0; i < nums.length; i++) {
      while (!window.isEmpty() && window.peekFirst() + k <= i) window.pollFirst();
      while (!window.isEmpty() && nums[window.peekLast()] <= nums[i]) window.pollLast();
      window.addLast(i);
      if (i >= k - 1) {
        max[i - k + 1] = nums[window.peekFirst()];
      }
    }
    return max;
  }
}