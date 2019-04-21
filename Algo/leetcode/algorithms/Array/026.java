public class Solution {
    public int removeDuplicates(int[] A) {
        int i = 0;
        int j = 0;
        if(A.length == 0)
        	return 0;
        while(i < A.length - 1)
        {
        	if(A[i] == A[i+1])
        	{
        		i++;
        	}
        	else
        	{
        		A[j] = A[i];
        		i++;
        		j++;
        		
        	}
        }
        A[j] = A[i];
        j++;
        return j;  
    }
}