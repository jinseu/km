public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) return false;
        int n = matrix[0].length;
        
        int left = 0;
        int right = m * n -1;
        int mid = 0;
        int i = 0;
        int j = 0;
        while(left <= right)
        {
        	mid = (left + right)/2;
        	i = mid/n;
        	j = mid%n;       	
        	if(matrix[i][j] == target)
        		return true;
        	else if(matrix[i][j] > target)
        		right = mid-1;
        	else
        		left = mid+1;
        }
        return false;    
    }
}