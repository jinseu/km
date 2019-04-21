class TrieNodes {

	boolean isword;
	String word;
	TrieNodes[] arr;
    public TrieNodes() 
    {
        isword = false;
        arr = new TrieNodes[26];
    }
}
public class Solution {
	private boolean[][] map;
    private TrieNodes root;
    private List<String> res;
    private char[][] board;
    int m;
    int n;
    public List<String> findWords(char[][] boards, String[] words) {
        res = new ArrayList<String>();
        root = new TrieNodes();
        board =boards;
    	if(board.length == 0)
    		return res;
    	m = board.length;
    	n = board[0].length;
        map = new boolean[m][n];
        for(int i=0;i<words.length;i++)
        	insert(words[i]);
        triesearch(board,root);
        return res;
    }
    private void triesearch(char[][] board,TrieNodes curr) {
		for(int i = 0;i < m;i++)
		{
			for(int j = 0;j < n;j++)
			{
				for(int k = 0; k<26;k++)
					if(curr.arr[k] != null && board[i][j]==('a'+k))
						DFS(i,j,curr.arr[k]);
			}
		}
		
	}
	private void DFS(int i,int j,TrieNodes curr) 
	{
		if(curr.isword == true)
		{
			res.add(curr.word);
			curr.isword =false;
		}
		map[i][j] = true;
		for(int w = 0;w < 26;w++)
		{
			if((i+1) < m && map[i+1][j]==false && curr.arr[w] != null && board[i+1][j]-'a' == w)
			{
				DFS(i+1,j,curr.arr[w]);
			}
			if((i-1) > -1 && map[i-1][j]==false && curr.arr[w] != null && board[i-1][j]-'a' == w)
			{
				DFS(i-1,j,curr.arr[w]);
			}
			if((j+1) < n && map[i][j+1]==false && curr.arr[w] != null && board[i][j+1]-'a' == w)
			{
				DFS(i,j+1,curr.arr[w]);
			}
			if((j-1) > -1 && map[i][j-1]==false && curr.arr[w] != null && board[i][j-1]-'a' == w)
			{
				DFS(i,j-1,curr.arr[w]);
			}		
		}
		map[i][j] = false;

	}
	public void insert(String word)
    {
    	TrieNodes prev = root;
    	TrieNodes curr = null;
    	int i = 0;
    	int index = 0;
    	while(i < word.length())
    	{
    		index = word.charAt(i)-'a';
    		curr = prev.arr[index];
    		if(curr == null)
    		{
    			curr = new TrieNodes();
    			prev.arr[index] = curr;
    		}
    		prev = curr;
    		i++;
    	}
    	prev.isword = true;
    	prev.word = word;
    	
    }
}