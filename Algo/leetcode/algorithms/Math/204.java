public class Solution {
    public int countPrimes(int n) {
        if (n < 2)
         {
             return 0;
         }
         boolean[] prime = new boolean[n];
         for(int i = 0; i < prime.length;i++)
         {
        	 prime[i] = true;
         }
         prime[0] = false;
         prime[1] = false;
     
         int result = 0;
         int limit = (int) Math.sqrt(n);

         for (int i = 2; i <= limit; i++)
         {
             if (prime[i])
             {
                 for (int j = i*i; j < n; j += i)
                 {
                     prime[j] = false;
                 }
             }
         }

         for (int i = 0; i < n; i++)
         {
             if (prime[i])
             {
                 result++;
             }
         }

         return result;    
    }
}