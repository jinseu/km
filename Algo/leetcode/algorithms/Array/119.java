public class Solution {
    public List<Integer> getRow(int rowIndex) {
    	int i = 0;
    	int j = rowIndex;
    	List<Integer> res = new ArrayList<Integer>();
    	if(rowIndex < 0)
    		return res;
    	int[] r = new int[rowIndex + 1];
    	long tem = 1;
        while(i <= j)
        {
        	r[i] = (int) tem;
        	r[j] = (int) tem;
        	i++;        	
        	tem = tem * j/ i;
        	j--;
        }
        for(int k : r)
        	res.add(k);
        return res;   
    }
}