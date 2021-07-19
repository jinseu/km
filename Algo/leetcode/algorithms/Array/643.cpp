class Solution {
public:
    double findMaxAverage(vector<int>& nums, int k) {
        int i = 0;
        int total = INT_MIN;
        int curTotal = 0;

        for (; i < k && i < nums.size(); i ++) {
            curTotal += nums[i];
        }
        total = curTotal;
        for (; i < nums.size(); i++) {
            curTotal += (nums[i] - nums[i - k]);
            if (curTotal > total) {
                total = curTotal;
            }
        }
        return (double) total / k;
    }
};