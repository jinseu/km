/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
if(head == null)
    		return false;
    	ListNode s = head;
    	ListNode f = head;
        while(f != null)
        {
           if(f != null)
        	   f = f.next;
           if(f != null)
        	   f = f.next;
           if(s != null)
        	   s = s.next;
           if(f != null && f == s)
           {
                    return true;
            }
                
    	}
        return false;      
    }
}