public class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num == 1 || num == 0)
            return false;
        int sum = 1;
        for(int i = 2; i * i <= num; i++)
        {
            if(num % i == 0)
            {
                int k = num /i;
                if (k != i)
                    sum += i;
                sum += num /i;
            }
        }
        if (sum == num)
            return true;
        else
            return false;
    }
}
