public class Solution {
	List<List<Integer>> res;
	Stack<Integer> curr;
    public List<List<Integer>> combine(int n, int k) 
    {
    	res = new ArrayList<List<Integer>>();
    	curr = new Stack<Integer>();
    	DFS(k,n,0,-1);
    	return res;
    }
	private void DFS(int k, int n, int pos, int cu) 
	{
		if(pos == k)
		{
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for(int i = 0 ;i < curr.size();i++)
			{
				arr.add(curr.get(i));
			}
			res.add(arr);
			return ;
		}
		if(cu >= n)
			return ;
		for(int i= cu+1; i < n;i++)
		{
			curr.push(i+1);
			DFS(k,n,pos+1,i);
			curr.pop();
		}
		
	}
}