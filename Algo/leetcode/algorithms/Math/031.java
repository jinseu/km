public class Solution {
   public void nextPermutation(int[] num) 
    {
        if (num.length < 2) 
        {
            return;
        }
        int i, k;
        for (i = num.length - 2; i >= 0; --i) {
            if (num[i] < num[i + 1]) {
                break;
            }
        }
        if(i == -1)
        {
        	reverse(num,i+1,num.length-1);
        	return ;
        }
        for (k = num.length - 1; k > i; --k) {
           if (num[k] > num[i]) {
                break;
           }
        }
        swap(num,i,k);
        reverse(num,i+1,num.length-1);
    }
	private void reverse(int[] num, int i, int j) {
		while(j > i)
		{
			swap(num,i,j);
			i++;
			j--;
		}
		
	}
	private void swap(int[] num, int pfind, int pmin) {
		int tem = num[pfind];
		num[pfind] = num[pmin];
		num[pmin] = tem;
		
	}
}