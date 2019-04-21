public class Solution {
	int res;
	public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        init(board,n);
        res = 0;
        slove(board,0,n);
        return res;
    }
    private void slove(char[][] board, int i, int n) {
    	if(i == n)
    	{
    		res++;
    		return ;
    	}
        for(int k = 0; k < n;k++)
        {
        	board[i][k] = 'Q';
        	if(check(board,i,k,n))
        	{
        		slove(board,i+1,n);
        	}
        	board[i][k] = '.';
        }
	}
	private boolean check(char[][] board,int i,int k,int n) {
		int count = 0;
		for(int j = 0;j < n;j++)
		{
			if(board[i][j] == 'Q')
				count++;
		}
		if(count > 1)
			return false;
		count = 0;
		for(int j = 0;j < n;j++)
		{
			if(board[j][k] == 'Q')
				count++;
		}
		if(count > 1)
			return false;
		count = 0;
		int temx = i + 1;
		int temy = k + 1;
		for(;temx < n && temy < n;temx++,temy++)
		{
			if(board[temx][temy] == 'Q')
				count++;
		}
		temx = i;
		temy = k;
		for(;temx > -1 && temy > -1;temx--,temy--)
		{
			if(board[temx][temy] == 'Q')
				count++;
		}
		if(count > 1)
			return false;
		count = 0;
		temx = i;
		temy = k;
		for(;temx < n && temy > -1;temx++,temy--)
		{
			if(board[temx][temy] == 'Q')
				count++;
		}
		temx = i - 1;
		temy = k + 1;		
		for(;temx > -1 && temy < n;temx--,temy++)
		{
			if(board[temx][temy] == 'Q')
				count++;
		}
		if(count > 1)
			return false;
		return true;
	}
	private void init(char[][] board, int n) {
		for(int i =0;i<n;i++)
			for(int j =0;j<n;j++)
				board[i][j] = '.';
	}
}