class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
	sort(nums.begin(),nums.end());
	int len = nums.size();
	int closet = 0;
	int d = INT_MAX;
	for( int i = 0 ;i < (len - 2);i++)
	{
		if(i > 0 && nums[i] == nums[i-1])
			continue;
		int l = i+1;
		int r = len-1;
		while(l < r)
		{
			 int temc = nums[i] + nums[l] +nums[r];
			 int temd = temc - target;
             if(abs(temd) < d)
			 {
				 closet = temc;
				 d = abs(temd);
				 if(d == 0)
					 return closet;
			 }
			 else if(temd > 0)
			 {
				 r--;
			 }
			 else if(temd < 0)
			 {
				 l++;
			 }
                
		}
	}
	return closet;      
    }
};