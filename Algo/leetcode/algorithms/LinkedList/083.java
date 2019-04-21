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
    	ListNode curr = head;
    	ListNode res = new ListNode(0);
    	ListNode prev = res;
    	res.next = head;
    	while(curr != null)
    	{
    		while(curr.next != null && prev.next.val==curr.next.val)
    		{
    			curr = curr.next;
    		}
    		if(prev.next==curr)  
    		{ 
    	            prev = prev.next;  
    	    }  
    	    else
    	    {  
    	            prev.next = curr.next;  
    	    }  
    	    curr = curr.next;
    	}
    	return res.next;
    }
}