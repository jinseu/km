public class Solution {
public List<String> letterCombinations(String digits) 
    {
    	List<String> res = new ArrayList<String>();  
        String[] map = new String[10];  
        map[0] = "";  
        map[1] = "";  
        map[2] = "abc";  
        map[3] = "def";  
        map[4] = "ghi";  
        map[5] = "jkl";  
        map[6] = "mno";  
        map[7] = "pqrs";  
        map[8] = "tuv";  
        map[9] = "wxyz"; 
        char[]  middleTemp = new char[digits.length()];  
        if(digits.length() == 0)
        	return res;
        dfsGetStr(digits, 0, middleTemp, map, res);
        return res;
    }
	private void dfsGetStr(String digits, int i, char[] middleTemp,
			String[] map, List<String> res) {
		 if(i == digits.length()) {  
	            res.add(new String(middleTemp));  
	            return ;  
	        }  
	        char strChar = digits.charAt(i);  
	        for(int k=0; k<map[strChar-'0'].length(); k++) {  
	        	middleTemp[i] = map[strChar-'0'].charAt(k);  
	            dfsGetStr(digits, i+1, middleTemp, map, res);  
	        } 
		
	}
}