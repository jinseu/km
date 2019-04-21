/*
Runtime: 16 ms
*/
class Solution {
public:
    int maximumGap(vector<int>& num) {
        if(num.empty() || num.size() < 2)
            return 0;
        sort(num.begin(), num.end());
        int maxGap = num[1]-num[0];
        for(int i = 2; i < num.size(); i ++)
        {
            int curGap = num[i]-num[i-1];
            maxGap = (maxGap>=curGap)?maxGap:curGap;
        }
        return maxGap;        
    }
};
/*
Runtime: 12 ms
来源于网络
*/
class Solution {
public:
    int maximumGap(vector<int>& num) {
        if (num.size() < 2) 
			return 0;
        // 1. 算出用的桶数：取平均间隔，再用最大值和最小值之差除以间隔，得到桶数
        // 因为假设所有值都是平均分布的时候，如此取桶数可得时间复杂度是0(n)
        auto maxVal = *max_element(num.begin(), num.end());
        auto minVal = *min_element(num.begin(), num.end());
        int agGap = ceil((double)(maxVal - minVal) / (num.size()-1)); // 平均间隔
        int bucketCount = ceil((double)(maxVal - minVal) / agGap);
        // 2. 记录每个桶的最大值和最小值
        vector<pair<int, int> > buckets(bucketCount, make_pair(INT_MIN, INT_MAX)); // 初始化桶
        for (auto val : num){
            if (val == maxVal || val == minVal) continue;
            int bucketNum = (val - minVal) / agGap;
            if (val > buckets[bucketNum].first) 
                buckets[bucketNum].first = val; // 存储最大值
            if (val < buckets[bucketNum].second) buckets[bucketNum].second = val; // 存储最小值
        }
        // 3. 算出最大间隔
        int maxGap(0), lastMax(minVal);
        for (auto bucket : buckets){
            if (bucket.first == INT_MIN) continue; // 空桶
            int curMax(bucket.first), curMin(bucket.second);
            maxGap = max(maxGap, curMin - lastMax);
            lastMax = curMax;
        }
        maxGap = max(maxGap, maxVal - lastMax);
        return maxGap;
    }
};