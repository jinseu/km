//runtime 312 ms
public class Solution {
	   public List<List<Integer>> lli;
	   public void nextPermutation(int[] num) 
	    {
	        if (num.length < 2) 
	        {
	            return;
	        }
	        int i, k;
	        for (i = num.length - 2; i >= 0; --i) {
	            if (num[i] < num[i + 1]) {
	                break;
	            }
	        }
	        if(i == -1)
	        {
	        	reverse(num,i+1,num.length-1);
	        	return ;
	        }
	        for (k = num.length - 1; k > i; --k) {
	           if (num[k] > num[i]) {
	                break;
	           }
	        }
	        swap(num,i,k);
	        reverse(num,i+1,num.length-1);
	    }
		private void reverse(int[] num, int i, int j) {
			while(j > i)
			{
				swap(num,i,j);
				i++;
				j--;
			}
			
		}
		private void swap(int[] num, int pfind, int pmin) {
			int tem = num[pfind];
			num[pfind] = num[pmin];
			num[pmin] = tem;
			
		}
	    public List<List<Integer>> permute(int[] num) 
	    {
	        boolean[] ans = new boolean[num.length];
	        Stack<Integer> st = new Stack<Integer>();
	        lli = new ArrayList<List<Integer>>();
	        int size = 1;
	        for(int i = 1;i <= num.length;i++)
	        	size *= i;
	        for(int i = 0;i < size;i++)
	        {
	        	List<Integer> li = new ArrayList<Integer>();
	        	for(int e : num)
	        		li.add(e);
	        	lli.add(li);
	        	nextPermutation(num);
	        }
	        return lli;
	    }
}
//Runtime: 516 ms
public class Solution {
    public List<List<Integer>> lli = new ArrayList<List<Integer>>();
	public void DFS(boolean[] ans, int[] num,Stack<Integer> st) {
		
		for(int i =0;i<num.length;i++)
		{
			if(ans[i] == false)
			{
			    st.push(num[i]);
			    ans[i] = true;
			    if(st.size() == ans.length)
			    {
			    	List<Integer> cli = new ArrayList<Integer>();
			    	for(int k : st)
			    		cli.add(k);
			    	lli.add(cli);
			    }
			    else
			    {
			       DFS(ans,num,st);
			    }
			    ans[i] = false;
			    st.pop();
			    
			}
		}
	}

    public List<List<Integer>> permute(int[] num) 
    {
        boolean[] ans = new boolean[num.length];
        Stack<Integer> st = new Stack<Integer>();
        DFS(ans,num,st);
        return lli;
    }
}