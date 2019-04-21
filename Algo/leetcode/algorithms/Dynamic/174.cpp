class Solution {
public:
    int calculateMinimumHP(vector<vector<int>>& dungeon) {
        vector<vector<int>> dp(dungeon);
		int m = dp.size();
		int n = dp[0].size();
		dp[m-1][n-1] = max( 0 - dungeon[m-1][n-1], 0);
		for(int i = n-2;i >= 0;i--)
		{
			dp[m-1][i] = max(dp[m-1][i+1] - dungeon[m-1][i], 0);
		}
	    for(int i = m-2;i >= 0;i--)
		{
			dp[i][n-1] = max(dp[i+1][n-1] - dungeon[i][n-1], 0);
		}
		for(int i = m-2; i>= 0;i--)
		{
			for(int j = n - 2;j>=0;j--)
			{
				dp[i][j] = max( min(dp[i][j+1], dp[i+1][j]) - dungeon[i][j], 0);
			}
		}
		return dp[0][0] + 1;        
    }
};