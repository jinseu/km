public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[] res = new int[size];
        if(size ==  1)
        	return triangle.get(0).get(0);
        res[0] = triangle.get(0).get(0);
        for(int i = 1;i < size;i++)
        {
        	res[i] = triangle.get(i).get(i) + res[i - 1];
        	for(int j = i-1 ;j >0 ;j--)
        	{
        		res[j] = Math.min(res[j], res[j-1]) + triangle.get(i).get(j);
        	}
        	res[0] = triangle.get(i).get(0) + res[0]; 
        }
        int min = res[0];
        for(int w = 0; w < size;w++)
        {
        	if(res[w] < min)
        		min = res[w];
        }
        return min;        
    }
}