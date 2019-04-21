public class Solution {
    public int minPathSum(int[][] grid) {
    	int m = grid.length;
    	int n = grid[0].length;
    	if(m == 0 || n == 0)
    		return 0;
        int[][] dpmin = new int[m][n];
        dpmin[0][0] = grid[0][0];
        for(int i = 1;i<n;i++)
        	dpmin[0][i] = dpmin[0][i-1] + grid[0][i];
        for(int j = 1;j<m;j++)
        	dpmin[j][0] = dpmin[j-1][0] + grid[j][0];
        
        for(int i = 1;i < m; i++)
        	for(int j = 1; j < n; j++)
        	{
        		dpmin[i][j] = Math.min(dpmin[i-1][j],dpmin[i][j-1]) + grid[i][j];
        	}
        return dpmin[m-1][n-1];  
    }
}