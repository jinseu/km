/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 * Runtime: 608 ms
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curr = headA;
        if(headA == null || headB == null)
        	return null;
        while(curr.next != null)
        {
        	curr = curr.next;
        }
        ListNode end = curr;
        curr.next = headB;
        curr = detectCycle(headA);
        end.next = null;
        return curr;
    }
    public ListNode detectCycle(ListNode head) 
    {
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