public class Solution {
	List<List<Integer>> res ;
	Stack<Integer> st = new Stack<Integer>();
	int curr = 0;
    public List<List<Integer>> combinationSum(int[] candidates, int target) 
    {
    	Arrays.sort(candidates);
        res = new ArrayList<List<Integer>>();
        int index = candidates.length-1;//Arrays.binarySearch(candidates, target);
        curr = 0;
        if( index < 0 )
        	index = -1 * (index + 1);  
        DFS(candidates,index,target);
        
        return res;
    }
	private void DFS(int[] cands, int index, int target) {
		for(int i = index; i >= 0;i--)
		{
			st.push(cands[i]);
			curr += cands[i];
			if(curr == target)
			{
				putRes();
			}
			else if(curr < target)
			{
				DFS(cands,i,target);
			}
			st.pop();
			curr -= cands[i];
		}
		
	}
	private void putRes() {
		Iterator<Integer> it = st.iterator();
		LinkedList<Integer> arr = new LinkedList<Integer>();
		while(it.hasNext())
		{
			arr.addFirst(it.next());
		}
		res.add(arr);
		
	}
}