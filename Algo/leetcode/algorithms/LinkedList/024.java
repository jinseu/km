/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
       if(head == null || head.next == null)
        	return head;
        
        ListNode res = new ListNode(0);
        res.next = head;
        
        ListNode prev = res;
        ListNode curr = head.next;
        
        while(curr != null)
        {
        	ListNode tem = curr.next;
        	//curr.next = tem.next;
        	curr.next = prev.next;
        	prev.next = curr;
        	curr.next.next = tem;
        	
        	curr = curr.next;
    		
    		  curr = curr.next;
    		  prev = prev.next;
    		
        	if(curr != null)
        	{
        		curr = curr.next;
        		prev = prev.next;
        	}
        	
        }
        return res.next;        
    }
}
