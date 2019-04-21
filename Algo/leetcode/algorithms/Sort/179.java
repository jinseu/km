public class Solution {
	class Mylarge implements Comparator<String>
	{

		@Override
		public int compare(String a, String b) {
	        String ab = a.concat(b);  
	        String ba = b.concat(a);  
	        return (int) (Long.parseLong(ab) - Long.parseLong(ba));   
		}
		
	}
    public String largestNumber(int[] nums) 
    {
    	int n = nums.length;  
        if (n < 1) 
        	return "";  
          
        String[] strs = new String[n];  
        for (int i = 0; i < n; i++) {  
            strs[i] = String.valueOf(nums[i]);  
        }  
          
        Arrays.sort(strs, new Mylarge());  
          
        String ans = "";  
        for (int i = n - 1; i >= 0; i--) {  
            ans = ans.concat(strs[i]);  
        }  
          
        int i = 0;  
        while (i < n && ans.charAt(i) == '0') {  
            i++;  
        }  
        if (i == n) 
        	return "0";  
          
        return ans.substring(i);  
    }
}