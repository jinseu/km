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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    	List<Interval> res = new ArrayList<Interval>();
    	Interval curr = null;
        for(int i = 0;i < intervals.size();i++)
        {
        	curr = intervals.get(i);
        	if(curr.start <= newInterval.end && curr.end >= newInterval.start ){
        		newInterval.end = Math.max(curr.end, newInterval.end);
        		newInterval.start = Math.min(curr.start, newInterval.start);
        	}
        	else
        		res.add(curr);
        }
        int j = 0;
        while(j < res.size())
        {
        	if(res.get(j).start > newInterval.start)
        	{
        		res.add(j, newInterval);
        		return res;
        	}
        	j++;
        }
		res.add(j, newInterval);
		return res;
    }
}