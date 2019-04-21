public class Solution {
	private String str;
	private boolean[][] map;
	private boolean tag;
	private int l;
    public boolean exist(char[][] board, String word) 
    {
    	tag = false;
    	str = word;
    	l = str.length();
    	map = new boolean[board.length][board[0].length];
    	for(int i =0;i<board.length;i++)
    	{
    		for(int j = 0;j < board[i].length;j++)
    		{
    			DFS(board,i,j,0);
    			if(tag == true)
    				return true;
    		}
    	}
    	return false;
        
    }
	private void DFS(char[][] grid,int i,int j,int k) 
	{
		if(tag == true)
			return ;
		if(k < l && grid[i][j] == str.charAt(k) && map[i][j] == false)
		{
			map[i][j] = true;
			if(k == l-1)
				tag = true;
		}
		else
			return ;
		if((i+1) < grid.length)
		{
			DFS(grid,i+1,j,k+1);
		}
		if((j+1) < grid[i].length)
		{
			DFS(grid,i,j+1,k+1);
		}
		if((i-1) >= 0)
		{
			DFS(grid,i-1,j,k+1);
		}
		if((j-1) >= 0)
		{
			DFS(grid,i,j-1,k+1);
		}
		if(tag == false)
		{
			map[i][j] = false;
		}
	}
}