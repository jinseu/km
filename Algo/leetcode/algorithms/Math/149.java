/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
import java.util.*;
public class Solution {
    public int maxPoints(Point[] points) {
        int maxPoint = 0;
        for(int i = 0;i < points.length;i++)
        {
        	int maxk = 1;
        	int same = 0;
        	Point pointA = points[i];
        	Hashtable<Double,Integer> ht = new Hashtable<Double,Integer>();
        	for(int j = 0;j < points.length;j++)
        	{
        		Point pointB = points[j];
        		if(i == j)
        			continue;
        		else if(pointA.x == pointB.x && pointA.y == pointB.y)
        			same++;
        		else if(pointA.x == pointB.x)
        			maxk++;
        		else
        		{
        			double k = (double)(pointA.y - pointB.y)/(pointA.x - pointB.x);
        			if(ht.get(k) == null)
        			   ht.put(k, 2);
        			else
        			{
        			   int tem = ht.get(k);
        			   tem ++;
        			   ht.remove(k);
        			   ht.put(k, tem);//ht.replace(k,tem);
        			}
        		}
        			
        	}
        	Collection<Integer> rs = ht.values();
        	Iterator<Integer> it = rs.iterator();
        	while(it.hasNext())
        	{        		
                int tem2 = it.next();
        		if(maxk < tem2)
        			maxk = tem2;
        	}
        	maxk += same;
        	if(maxk > maxPoint)
        		maxPoint = maxk;
        	
        }
        
        return maxPoint;
    }
}