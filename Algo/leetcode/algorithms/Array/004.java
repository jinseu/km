public class Solution {
    public double findMedian(int A[],int sa,int m, int B[],int sb, int n, int k)
    {
    	if (m > n)  
    	   return findMedian(B,sb,n, A,sa,m, k);  
    	if (m == 0)  
    	   return B[k - 1];  
    	if (k == 1)  
    	   return Math.min(A[sa], B[sb]);  
    	
    	int pa = Math.min(k / 2, m), pb = k - pa;  
    	if (A[sa + pa - 1] < B[sb + pb - 1])  
    	    return findMedian(A,sa + pa,m-pa, B,sb,n, k-pa);  
    	else if (A[sa + pa - 1] > B[sb + pb - 1])  
    	        return findMedian(A,sa,m, B,sb+pb,n-pb, k-pb);  
    	else  
    	        return A[sa + pa - 1];  
    }
    public double findMedianSortedArrays(int A[], int B[]) 
    {
        int al = A.length + B.length;
        if(al % 2 == 1)
        	return findMedian(A,0,A.length,B,0,B.length,al/2 + 1);
        else
        	return (findMedian(A,0,A.length,B,0,B.length,al/2) +
        			  findMedian(A,0,A.length,B,0,B.length,al/2 +1))/2;
    }
}