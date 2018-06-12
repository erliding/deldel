/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * <p>
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * <p>
 * Return 2. Because there are two triplets which sums are less than 2:
 * <p>
 * [-2, 0, 1]
 * [-2, 0, 3]
 * Follow up:
 * Could you solve it in O(n2) runtime?
 */

public class Solution {
  // index triplets, so no need to care about duplicate values
  public int threeSumSmaller(int[] nums, int target) {
    Arrays.sort(nums);
    int count = 0;
    for (int i = 0; i < nums.length - 2; i++) {
      int j = i + 1;
      int k = nums.length - 1;
      int sum = target - nums[i];
      while (j != k) {
        if (nums[j] + nums[k] < sum) {
          count += k - j;
          j++;
        } else k--;
      }
    }
    return count;
  }
}
