class Solution {
public:
    int lengthOfLastWord(string s) {
         int j = s.length()-1;
		 if(j<0)
			 return 0;
		 while( j>=0 &&s[j] == ' ')
		 {
				 j--;
		 }
		 if(j < 0)
			 return 0;
		 int i = 0;
		 while( j>=0 && s[j] != ' ')
		 {
			j--;
			i++;
		 }
		 return i;  
    }
};