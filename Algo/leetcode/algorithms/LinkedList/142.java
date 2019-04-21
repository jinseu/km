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
    public ListNode detectCycle(ListNode head) {
    	if(head == null)
    		return null;
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
               s = head;
               while(s != f)
               {
                   s = s.next;// -> next;
                   f = f.next;// -> next;
               }
               return s;
           }
                
    	}
        return null;          
    }
}