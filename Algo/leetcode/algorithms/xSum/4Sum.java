public class Solution {
    List<List<Integer>> ret;  
    int len;
    int tar;
    public List<List<Integer>> fourSum(int[] num, int target) 
    {
    	ret = new ArrayList<List<Integer>>();
        if (num == null || num.length < 3) 
        	return ret;            
        Arrays.sort(num);
        len = num.length;
        tar = target;
        for(int i = 0; i < len-3;i++)
        {
            if (i > 0 && num[i] == num[i-1]) 
            	continue;  
            threeSum(num, i+1, len-1, num[i]);   	
        }
        return ret;
    }
    private void threeSum(int[] num, int i, int j, int k) 
    {   
        for (int ii = i; ii < len-2; ii++) {  
            if (ii > i && num[ii] == num[ii-1]) 
            	continue;  
            find(num, ii+1, len-1, k,num[ii]);
        }  
    }  
      
    public void find(int[] num, int begin, int end, int k1, int k2) {  
        int l = begin, r = end;  
        while (l < r) {  
            if (num[l] + num[r] + k1 + k2 == tar) {  
                List<Integer> ans = new ArrayList<Integer>();  
                ans.add(k1);
                ans.add(k2);
                ans.add(num[l]);  
                ans.add(num[r]);  
                ret.add(ans); //放入结果集中  
                while (l < r && num[l] == num[l+1]) l++;  
                while (l < r && num[r] == num[r-1]) r--;  
                l++;  
                r--;  
            } else if (num[l] + num[r] + k1 + k2 < tar) {  
                l++;  
            } else {  
                r--;  
            }  
        }  
    }  
}