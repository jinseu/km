class Solution {
    public boolean isPrime(int w) {
        int i = 2;
        while (i * i <= w) {
            if (w % i == 0) {
                return false;
            }
            i += 1;
        }
        return true;
    }
    public int numPrimeArrangements(int n) {
        long maxRes = (int)1E9 + 7;
        int cntP = 0;
        long sumP = 1;
        int cntNP = 1;
        long sumNP = 1;
        for (int i = 2; i <= n; i++ ) {
            if (this.isPrime(i)){
                cntP ++;
                sumP = (sumP * cntP) % maxRes;
            }
            else {
                cntNP ++;
                sumNP = (sumNP * cntNP) % maxRes;
                System.out.printf("%d %d\n", sumNP, cntNP);
            }
        }
        System.out.printf("%d %d\n", sumP, sumNP);
        return (int)((sumP * sumNP) % maxRes);

    }
}