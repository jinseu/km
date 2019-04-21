public class Solution {
    public String getPermutation(int n, int k) {
    	int[] nums = new int[n];  
        int pCount = 1;  
        for(int i = 0 ; i < n; ++i) 
        {  
            nums[i] = i + 1;  
            pCount *= (i + 1);  
        }    
        k--;  
        StringBuilder res = new StringBuilder();  
        for(int i = 0 ; i < n; i++) 
        {  
            pCount = pCount/(n-i);  
            int selected = k / pCount;
            char tem = (char) ('0' + nums[selected]);
            res.append(tem);  
              
            for(int j = selected; j < n-i-1; j++)  
                nums[j] = nums[j+1];  
            k = k % pCount;  
        }  
        return res.toString();          
    }
}