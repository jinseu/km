public class Solution {
    public int longestValidParentheses(String s) {
    	boolean[] flags = new boolean[s.length()];
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else if (s.charAt(i) == ')' && !st.empty()) {
            	flags[i] = true;
            	flags[st.peek()] = true;
                st.pop();
            }
        }
        int max_len = 0, cur_len = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (flags[i]) 
            	++cur_len;
            else 
            	cur_len = 0;
            max_len = Math.max(max_len, cur_len);
        }
        return max_len;        
    }
}