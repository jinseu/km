public class Solution {
    public int removeElement(int[] A, int elem) {
        int i  = 0;
       int j = 0;
       while(i < A.length)
       {
    	   if(A[i] == elem)
    	   {
    		   
    		   i++;
    	   }
    	   else
    	   {
    		   A[j]=A[i];
    		   i++;
    		   j++;
    	   }
       }
       return j;       
    }
}