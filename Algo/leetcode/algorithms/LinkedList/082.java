/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
    	if(head == null || head.next == null)
    		return head;
        ListNode curr = head.next;
        ListNode prev = head;
      
        while(curr != null)
        {
        	if(curr.val == prev.val)
        	{
        		prev.next = curr.next;
        		curr = curr.next;
        	}
        	else
        	{
        		prev = prev.next;
        		curr = curr.next;
        	}
        }
        return head;        
    }
}