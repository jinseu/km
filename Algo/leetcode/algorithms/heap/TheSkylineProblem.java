/*
   Runtime: 924 ms
*/

public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
    	List<int[]> res = new ArrayList<int[]>();  
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>(){  
            @Override  
            public int compare(Integer a, Integer b) {  
                return b - a;  
            }  
        });  
        List<int[]> bl = new ArrayList<int[]>();  
        for(int i=0; i<buildings.length; i++) {  
            int[] b = buildings[i];      
            bl.add(new int[]{b[0], b[2]});  
            bl.add(new int[]{b[1], -b[2]});  
        }  
          
        Collections.sort(bl, new Comparator<int[]>() {  
            @Override  
            public int compare(int[] a, int[] b) {  
                if(a[0]!=b[0]) return a[0] - b[0];  
                else return b[1] - a[1];  
            }  
        });  
        int pre = 0, cur = 0;  
        for(int i=0; i<bl.size(); i++) {  
            int[] b = bl.get(i);  
            if(b[1]>0) {  
                maxHeap.add(b[1]);  
                cur = maxHeap.peek();  
            } else {  
                maxHeap.remove(-b[1]);  
                cur = (maxHeap.peek()==null) ? 0 : maxHeap.peek();  
            }  
            if(cur!=pre) {  
                res.add(new int[]{b[0], cur});  
                pre = cur;  
            }  
        }         
        return res;            
    }
}