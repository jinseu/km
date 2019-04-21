/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
           
           while(head != null && head.val == val)
           {
        	   head = head.next;
           }
           if(head == null)
        	   return null;
           ListNode current = head.next;
           ListNode prev = head;
           while(current != null)
           {
        	   if(current.val == val)
        	   {
        		   current=current.next;
        		   prev.next = current;
        	   }
        	   else
        	   {
        	      current = current.next;
        	      prev = prev.next;
        	      
        	   }
           }
           return head;        
    }
}