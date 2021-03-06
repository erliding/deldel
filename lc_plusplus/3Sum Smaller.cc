// Given an array of n integers nums and a target, find the number of index
// triplets i, j, k with 0 <= i < j < k < n that satisfy the condition
// nums[i] + nums[j] + nums[k] < target.
//
// For example, given nums = [-2, 0, 1, 3], and target = 2.
//
// Return 2. Because there are two triplets which sums are less than 2:
//
// [-2, 0, 1]
// [-2, 0, 3]
// Follow up:
// Could you solve it in O(n2) runtime?


class Solution {
public:
    int threeSumSmaller(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int count = 0;
        for (size_t i = 0; i + 2 < nums.size(); ++i) {
            auto j = i + 1;
            auto k = nums.size() - 1;
            auto sum = target - nums[i];
            while (j < k) {
                if (nums[j] + nums[k] < sum) {
                    count += k - j;
                    ++j;
                } else {
                    --k;
                }
            }
        }
        return count;
    }
};
