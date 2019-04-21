/*
  Runtime: 399 ms
*/
public class Solution {
    public boolean isValidSudoku(char[][] board) 
    {
    	boolean res = true;
    	for(int i = 0;i <9;i++ )
    	{
           res = checkRow(board,i);
           if(res ==false)
        	   return false;
           res = checkCol(board,i);
           if(res ==false)
        	   return false;
           res = checkSquare(board,i);
           if(res ==false)
        	   return false;
    	}
    	return true;
    }
	private boolean checkSquare(char[][] board, int i) {
		boolean[] arr = new boolean[9];
		for(int j = 0;j < 9;j++)
		{
			char tem = board[(i/3)*3 + j/3][(i%3)*3 + j%3];
			if (tem != '.')
			{
				if(arr[tem - '1'] == true)
					return false;
				else
				   arr[tem - '1'] = true;
			}
		}
		return true;
	}
	private boolean checkCol(char[][] board, int i) {
		boolean[] arr = new boolean[9];
		for(int j = 0;j < 9;j++)
		{
			char tem = board[j][i];
			if (tem != '.')
			{
				if(arr[tem - '1'] == true)
					return false;
				else
				   arr[tem - '1'] = true;
			}
		}
		return true;
	}
	private boolean checkRow(char[][] board, int i) {
		boolean[] arr = new boolean[9];
		for(int j = 0;j < 9;j++)
		{
			char tem = board[i][j];
			if (tem != '.')
			{
				if(arr[tem - '1'] == true)
					return false;
				else
				   arr[tem - '1'] = true;
			}
		}
		return true;
	}
}