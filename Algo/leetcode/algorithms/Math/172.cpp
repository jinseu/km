class Solution {
public:
    int trailingZeroes(int n) 
    {
       int power = 5;
	   int res =0;
	   while(power <= n)
	   {
           res += n/power;
		   n = n/power;
	   }
	   return res;
    }
};