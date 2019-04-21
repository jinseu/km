public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length==0) 
            return false;  
          
        int i=0, j=matrix[0].length-1;  
          
        while(i<matrix.length && j>=0) {  
            int x = matrix[i][j];  
            if(target == x) return true;  
            else if(target < x) --j;  
            else ++i;  
        }  
        return false;   
    }
}
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0)
        	return false;
        int n = matrix[0].length;
        if(n == 0)
        	return false;
        for(int i = 0;i < m;i++)
        {
           int res = Arrays.binarySearch(matrix[i], target);
           if(res >= 0)
        	   return true;
        }
        return false;        
    }
}