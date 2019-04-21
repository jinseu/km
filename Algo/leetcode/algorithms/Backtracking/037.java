public class Solution {
	boolean res = false;
    public void solveSudoku(char[][] board)
    {
        res = false;
        solve(board,0);       
    }
    public void solve(char[][] board,int pos)
    {
    	if(pos == 81)
    	{
    		res= true;
    		return ;
    	}
    	//printboard(board);
        HashSet<Character> number = new HashSet<Character>();
        int m = pos / 9;
        int n = pos % 9;
        if(board[m][n] == '.')
        {
                	 initNumber(number);
                     getNumber(board,m,n,number);
                     Iterator<Character> it = number.iterator();
                     if(!it.hasNext())
                    	 return ;
                     while(it.hasNext())
                     {
            		    board[m][n] = it.next();
            		    solve(board,pos+1);
            		    if(res == true)
            		    	return ;
            		    board[m][n] = '.';
            		 }
         }
         else
         {
        	 solve(board,pos+1);
         }
    }
	private void getNumber(char[][] board, int i,int j, HashSet<Character> number) 
	{
		char tem;
		for(int k = 0;k < 9;k++)
		{
			tem = board[(i/3)*3 +k/3][(j/3)*3 +k%3];
			if (tem != '.')
			{
				number.remove(tem);
			}
		}
		for(int k = 0;k < 9;k++)
		{
			tem = board[i][k];
			if (tem != '.')
				number.remove(tem);
			tem = board[k][j];
			if(tem != '.')
				number.remove(tem);
		}
	}
	private void initNumber(HashSet<Character> number) {
		for(int k = 0;k< 9;k++)
		{
			number.add((char) (k+'1'));
		}		
	}
}