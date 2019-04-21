public class Solution {
	List<List<Integer>> res ;
	Stack<Integer> st = new Stack<Integer>();
	int curr = 0;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) 
    {
    	Arrays.sort(candidates);
        res = new ArrayList<List<Integer>>();
        int index = candidates.length-1;//Arrays.binarySearch(candidates, target);
        curr = 0;
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
				while(i > 0 && cands[i-1] == cands[i])
				{
					i--;
				}
			}
			else if(curr < target)
			{
				int k = i-1;
				while(i > 0 && cands[i-1] == cands[i])
				{
					i--;
				}
				DFS(cands,k,target);
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