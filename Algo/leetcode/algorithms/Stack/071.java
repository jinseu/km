public class Solution {
    public String simplifyPath(String path) {
        Stack<String> st = new Stack<String>();
        if(path.length() == 0)
        {
        	return null;
        }
        int curr = 0;
    	for(;curr < path.length();)
    	{
    		int next = path.indexOf('/',curr);
    		String currDir;
    		if(next == -1)
    		{
    		    currDir = path.substring(curr);
    			next = path.length();
    		}
    		else
    		   currDir = path.substring(curr, next);
    		if(currDir.equals("."))
    			curr = next+1;
    		else if(currDir.equals(".."))
    		{
    			if(!st.isEmpty())
    			   st.pop();
    			curr = next+1;
    		}
    		else if(currDir.length() == 0)
    			curr = next+1;
    		else
    		{
    			st.push(currDir);
    			curr = next+1;
    		}
    	}
    	StringBuilder res = new StringBuilder();
    	res.append("/");
    	for(int i = 0;i<st.size();i++)
    	{
    		res.append(st.get(i));
    		if(i != st.size() -1)
    		   res.append("/");
    	}
    	return res.toString(); 
    }
}