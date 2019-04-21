public class Solution {
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	for(int i = 1; i <= numRows;i++)
    	{
    		List<Integer> ll = new ArrayList<Integer>();    		
    		for(int j =1;j <= i;j++)
    		{
    			if(j == 1)
    				ll.add(1);
    			else if(j == i)
    				ll.add(1);
    			else 
    			{
    				ll.add(res.get(i-2).get(j-2) + res.get(i-2).get(j-1));
    			}
    			
    		}
    		res.add(ll);
    	}
    	return res;        
    }
}