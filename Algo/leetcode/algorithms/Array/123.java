/*
解释：
首先，因为能买2次（第一次的卖可以和第二次的买在同一时间），但第二次的买不能在第一次的卖左边。
所以维护2个表，f1和f2，size都和prices一样大。
意义：
f1[i]表示 -- 截止到i下标为止，左边所做交易能够达到最大profit；
f2[i]表示 -- 截止到i下标为止，右边所做交易能够达到最大profit；
那么，对于f1 + f2，寻求最大即可。
*/
public class Solution {
    public int maxProfit(int[] prices) {
    	if(prices.length == 0 || prices.length == 1){  
            return 0;  
        }            
        int max = 0;  
        int[] left = new int[prices.length]; 
        int[] right = new int[prices.length];
          
        left[0] = 0;  
        int min = prices[0];             
        for(int i=1; i<left.length; i++){  
            left[i] = Math.max(left[i-1], prices[i]-min); 
            min = Math.min(min, prices[i]);
        }  
          
        right[right.length-1] = 0;  
        max = prices[right.length-1];
        for(int i=right.length-2; i>=0; i--){  
            right[i] = Math.max(right[i+1], max-prices[i]); 
            max = Math.max(max, prices[i]); 
        }
        max = 0;    
        for(int i=0; i<prices.length; i++){  
            max = Math.max(max, left[i]+right[i]);  
        }  
          
        return max;          
    }
}