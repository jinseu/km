public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] arr = new int[m][n];
        if(obstacleGrid[0][0] == 0)
           arr[0][0] = 1;
        else
           arr[0][0] = 0;
        int i = 0;
        for(i = 1;i < n;i++)
        {
        	if(obstacleGrid[0][i] != 1)
        	   arr[0][i] = arr[0][i-1];
        	else
        	   arr[0][i] = 0;
        }
        for(i = 1; i < m;i++)
        {
        	if(obstacleGrid[i][0] != 1)
         	   arr[i][0] = arr[i-1][0];
         	else
        	   arr[i][0] = 0;
        }
        for(i = 1;i<m;i++)
        {
        	for(int j = 1;j < n;j++)
        	{
                if(obstacleGrid[i][j] != 1)
        			arr[i][j] = arr[i][j-1] + arr[i-1][j];
                else
                	arr[i][j] = 0;
        	}
        }
        return arr[m-1][n-1];     
    }
}