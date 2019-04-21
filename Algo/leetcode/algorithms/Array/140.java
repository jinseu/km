public class Solution {
    private int[][] resdp;
	private int size;
	String ss;
    public void DFS(int i,StringBuilder sb,List<String> l)
    {
    	for(int j = 0;j < i;j++)
    	{
			if(resdp[i][j] == 1)
			{
				sb.insert(0," ");
				sb.insert(0,(ss.substring(j, i)));
				if(j == 0)
				{
				   l.add(sb.toString());
				}
				else
				{
					DFS(j,sb,l);
				}
				sb.delete(0, i-j+1);
			}
    	}
    }
    public List<String> wordBreak(String s, Set<String> dict) {
          size =s.length();
        ss = s;
        List<String> l = new ArrayList<String>();
        boolean[] res = new boolean[size+1];
        resdp = new int[size+1][size+1];
        for(int i = 0;i<=size;i++)
        {
        	res[i] = false;
        	for(int w = 0; w <= size;w++)
        	{
        		resdp[i][w] = -1;
        	}
        }
        for (int i = 0;i <= size;i++)
        {
        	String sub = s.substring(0, i);
        	if(dict.contains(sub) == true)
        	{
        		res[i] = true;
        		resdp[i][0] = 1;
        		for(int j = 0;j < i;j++)
        		{
        			sub = s.substring(j, i);
        			if(res[j] == true && dict.contains(sub))
        			{
        				resdp[i][j] = 1;
        			}
        					
        		}
        	}
        	else
        	{
        		for(int j = 0;j < i;j++)
        		{
        			sub = s.substring(j, i);
        			if(res[j] == true && dict.contains(sub))
        			{
        				res[i] = true;
        				resdp[i][j] = 1;
        			}
        					
        		}
        	}
        }
        for(int i = 0;i <= size;i++)
        {
        	if(resdp[size][i] == 1)
        	{
        		StringBuilder sb = new StringBuilder();
        		sb.append(s.substring(i, size));
        		if(i != 0)
        		   DFS(i,sb,l);
        		else
        		  l.add(sb.toString());
        	}
        }
        return l;
    }
}
/*
来源于网络的一个答案，实际运行速度要比我的快，主要还是我的算法DFS部分太慢了，可以优化的余地非常大
*/
public class Solution2 {
  public static List<String> wordBreak(String s, Set<String> dict) {
    List<String> dp[] = new ArrayList[s.length()+1];
    dp[0] = new ArrayList<String>();
    for(int i=0; i<s.length(); i++){
       if( dp[i] == null ) 
           continue; //前面的部分必须是可以匹配的
       for(String word:dict){
          int len = word.length();
          int end = i+len;
          if(end > s.length()) 
             continue;
          if(s.substring(i,end).equals(word)){
          if(dp[end] == null){
            dp[end] = new ArrayList<String>();
          }
          dp[end].add(word);//记录上一个位置
        }
      }
    }

    List<String> ans = new LinkedList<String>();
    if(dp[s.length()] == null) return ans; 
    ArrayList<String> tmp = new ArrayList<String>();
    dfsStringList(dp,s.length(),ans, tmp);
    return ans;
  }

  public static void dfsStringList(List<String> dp[],int end,List<String> res, ArrayList<String> tmp){
    if(end <= 0){
      String ans = tmp.get(tmp.size()-1);
      for(int i=tmp.size()-2; i>=0; i--)
        ans += (" " + tmp.get(i) );
      res.add(ans);
      return;
    }
    for(String str:dp[end]){
      tmp.add(str);
      dfsStringList(dp,end-str.length(), res, tmp);
      tmp.remove(tmp.size()-1);
    }
  }
}