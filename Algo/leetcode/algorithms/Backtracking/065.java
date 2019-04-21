public class Solution {
	final int INVALID = 0;       // 0 Include: Alphas, '(', '&' ans so on  
	final int SPACE   = 1;       // 1  
	final int SIGN    = 2;       // 2 '+','-'  
	final int DIGIT   = 3;       // 3 numbers  
	final int DOT     = 4;       // 4 '.'  
	final int EXPONENT= 5;       // 5 'e' 'E'  

    public boolean isNumber(String s) 
    {
        int[][] transTable = new int[9][];
        transTable[0] = new int[]{-1,  0,  3,  1,  2, -1};
        transTable[1] = new int[]{-1,  8, -1,  1,  4,  5};// 
        transTable[2] = new int[]{-1, -1, -1,  4, -1, -1};
        transTable[3] = new int[]{-1, -1, -1,  1,  2, -1};
        transTable[4] = new int[]{-1,  8, -1,  4, -1,  5};
        transTable[5] = new int[]{-1, -1,  6,  7, -1, -1};
        transTable[6] = new int[]{-1, -1, -1,  7, -1, -1};
        transTable[7] = new int[]{-1,  8, -1,  7, -1, -1};
        transTable[8] = new int[]{-1,  8, -1, -1, -1, -1};
        /*
        {  
        //0INVA,1SPA,2SIG,3DI,4DO,5E  
            //0初始无输入或者只有space的状态  
            -1,  8, -1,  1,  4,  5,//1输入了数字之后的状态  
            -1, -1, -1,  4, -1, -1,//2前面无数字，只输入了Dot的状态  
            -1, -1, -1,  1,  2, -1,//3输入了符号状态  
            -1,  8, -1,  4, -1,  5,//4前面有数字和有dot的状态  
            -1, -1,  6,  7, -1, -1,//5'e' or 'E'输入后的状态  
            -1, -1, -1,  7, -1, -1,//6输入e之后输入Sign的状态  
            -1,  8, -1,  7, -1, -1,//7输入e后输入数字的状态  
            -1,  8, -1, -1, -1, -1,//8前面有有效数输入之后，输入space的状态  
        };
        */  
        int state = 0;
        int i = 0;
        
        while ( i < s.length())  
        {
        	char curr = s.charAt(i);
            int input = INVALID;  
            if (curr == ' ') 
            	input = SPACE;  
            else if (curr == '+' || curr == '-') 
            	input = SIGN;  
            else if (Character.isDigit(curr)) 
            	input = DIGIT;  
            else if (curr == '.') 
            	input = DOT;  
            else if (curr == 'e' || curr == 'E') 
            	input = EXPONENT;  
            state = transTable[state][input];  
            if (state == -1) 
            	return false;  
            i++;  
        }  
        return state == 1 || state == 4 || state == 7 || state == 8;
    }
}
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public boolean isNumber(String s) {
if(s.trim().isEmpty()){  
            return false;  
        }  
        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";  
        if(s.trim().matches(regex)){  
            return true;  
        }else{  
            return false;  
        }         
    }
}