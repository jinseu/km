class Solution:

    def countPrimes(self, n):
        isPrime = [True] * max(n, 2)
        isPrime[0], isPrime[1] = False, False
        x = 2
        while x * x < n:
            if isPrime[x]:
                p = x * x
                while p < n:
                    isPrime[p] = False
                    p += x
            x += 1
        return sum(isPrime)
```
two solution
```
		
import math

class Solution(object):
    def countPrimes(self, n):
        if n <= 2:
            return 0
        if n == 3:
            return 1
        prime = [True] * n
        k = 0
        prime[0] = False
        prime[1] = False
        prime[2] = True
        k = 2
        sqrt = int(math.sqrt(n))+1
        while k <= sqrt:
            x = k*k
            if x < n and prime[x] == True:
                while x < n:
                    prime[x] = False
                    x += k
            k += 1
        count = 0
        for p in prime:
            if p == True:
                count += 1
        return count