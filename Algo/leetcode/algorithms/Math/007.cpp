class Solution {
public:
    int reverse(int x) {
        long long int res  = 0;
        int flags = 1;
        if(x < 0)
        {
           flags = -1;
           x    *= -1;
        }
        while(x >= 10)
        {
           res += x%10;
           x   /= 10;
           res *= 10;
        }
        long long int temp = flags * (res+x);
        if( (temp > (long long int)INT_MAX) || (temp < (long long int)INT_MIN) )
        	 return 0;
        else
           return (int)temp;
    }
};