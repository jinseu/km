public class Solution {
	public List<List<Integer>> res;
	public boolean[] flags;
	public int length;
	public void addOrNo(int[] S,int l)
	{
		if(l == S.length)
		{
			res.add(createList(S));
		}
		else
		{
			flags[l] = true;
			addOrNo(S,l+1);
			flags[l] = false;
			addOrNo(S,l+1);
		}
		
	}
	public List<Integer> createList(int[] S)
	{
		ArrayList<Integer> la = new ArrayList<Integer>();
    	for(int i = 0;i<length;i++)
    	{
    		if(flags[i] == true)
    		{
    			la.add(S[i]);
    		}
    	}
    	return la;
	}
    public List<List<Integer>> subsets(int[] S) 
    {
    	res = new ArrayList<List<Integer>>();
    	length = S.length;
    	Arrays.sort(S);
    	flags = new boolean[length];
    	for(int i = 0;i<length;i++)
    	{
    		flags[i] = false;
    	}
    	addOrNo(S,0);
        return res;
    }
}