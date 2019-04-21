public class Solution {
    public int[][] generateMatrix(int n) {
        int m = n;
        int k = 1;
    	int e = m/2;
    	int[][] matrix = new int[m][m];
    	int ax = 0;
    	int ay = 0;
    	int bx = m - 1;
    	int by = m - 1;
    	
    	while(e > 0)
    	{
            for(int i=ay;i <= by; i++)
                matrix[ax][i] = k++;
            for(int i=ax+1;i <= bx; i++)
            	matrix[i][by] = k++;
            for(int i=by-1;i >= ay; i--)
            	matrix[bx][i] = k++;
            for(int i=bx-1; i > ax; --i)
            	matrix[i][ay] = k++;
            ax++;
            ay++;
            bx--;
            by--;
            e--;
        }
    	if(ax==bx)
    	{
    		for(int i=ay;i<=by;i++)
    			matrix[ax][i] = k++;
    	 }
    	else if(ay==by)
    	{
    		for(int i=ax;i<=bx;i++)
    			matrix[i][ay] = k++;
    	}
    	
    	return matrix;        
    }
}