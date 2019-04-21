/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
    	Collections.sort(intervals, new Comparator<Interval>() {
    		  public int compare(Interval s1, Interval s2) {
    		     return s1.start - s2.start;    		        
    		  }
    	});
    	List<Interval> res =new ArrayList<Interval>();
    	Interval curr = null;
    	for(Interval in : intervals)
    	{
    		if(curr == null)
    			curr = in;
    		else
    		{
            	if(curr.start <= in.end && curr.end >= in.start ){
            		curr.end = Math.max(curr.end, in.end);
            		curr.start = Math.min(curr.start, in.start);
            	}
            	else
            	{
            		res.add(curr);
            		curr = in;
            	}
    		}
    	}
    	if(curr != null)
    		res.add(curr);
    	return res;   
    }
}