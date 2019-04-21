public class Solution {
    public boolean search(int[] nums, int target) {
        for(int i = 0; i < nums.length; ++i)  
            if(nums[i] == target)  
                return true;  
        return false;             
    }
}

public class Solution {
    public boolean search(int[] nums, int target) {
    	if(0 == nums.length) 
    		return false;
        int left  = 0; 
        int right = nums.length - 1;
        boolean res = rsearch(nums,left,right,target);
        return res;            
    }
	private boolean rsearch(int[] nums, int left, int right, int target) {

        int k;
        boolean res;

        if(left > right) //µİ¹éÖÕÖ¹Ìõ¼ş
        	return false;
        if(left==right && nums[left] == target) 
        	return true;
        if(left==right && nums[left] != target) 
        	return false;

        k=( left + right)/2;

        if(nums[k] == target) 
        	return true;
        res = rsearch(nums,left,k-1,target);
        if(res == true) 
        	return res;
        res = rsearch(nums,k+1,right,target);
        if(res == true) 
        	return true;

        return false;
		
	}
}