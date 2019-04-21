class Solution {
public:
    int strStr(string haystack, string needle) {
            int i,j;  
         for (i = j = 0; haystack[i] && needle[j];) {  
             if (haystack[i] == needle[j]) {  
                 ++i;  
                ++j;  
             } 
             else {  
                i = i - j + 1;  
                 j = 0;  
             }  
         }  
         return needle[j] ? -1 : (i - j);          
    }
};