class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        map<char,int> alpha;
        int start = 0;
        int max = 0;
        int i = 0;
        for( i =0 ;i < s.length();i++)
        {
            if(alpha.find(s[i]) == alpha.end())
            {
                alpha[s[i]] = i;
                //max++;
            }
            else
            {
                
                if( (i- start) > max){
                   max = i -start;
                }
		start = alpha[s[i]]+1 > start ? alpha[s[i]]+1 : start;
                alpha[s[i]] = i; 
            }
        }
        if( (i- start) > max)
            max = i -start;
        return max;   
    }
};
