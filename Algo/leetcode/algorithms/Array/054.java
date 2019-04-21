public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
    	ArrayList<Integer> li = new ArrayList<Integer>();
    	if(matrix.length == 0 || matrix[0].length==0)
    		return li;
    	int m = matrix.length;
    	int n = matrix[0].length;
    	int e = m < n ? m : n;
    	
    	int ax = 0;
    	int ay = 0;
    	int bx = m - 1;
    	int by = n - 1;
    	
    	e = e/2;
    	while(e > 0)
    	{
            for(int i=ay;i <= by; i++)
                li.add(matrix[ax][i]);
            for(int i=ax+1;i <= bx; i++)
            	li.add(matrix[i][by]);
            for(int i=by-1;i >= ay; i--)
            	li.add(matrix[bx][i]);
            for(int i=bx-1; i > ax; --i)
            	li.add(matrix[i][ay]);
            ax++;
            ay++;
            bx--;
            by--;
            e--;
        }
    	if(ax==bx)
    	{
    		for(int i=ay;i<=by;i++)
    			li.add(matrix[ax][i]);
    	 }
    	else if(ay==by)
    	{
    		for(int i=ax;i<=bx;i++)
    			li.add(matrix[i][ay]);
    	}
    	
    	return li;        
    }
}