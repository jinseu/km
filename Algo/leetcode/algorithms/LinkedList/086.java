/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * runtime:360ms
 */
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null)
        	return null;
        ListNode less = new ListNode(0);
        ListNode currl = less;
        ListNode great = new ListNode(0);
        ListNode currg = great;
        ListNode curr = head;
        while(curr != null)
        {
        	if(curr.val < x)
        	{
        		currl.next = curr;
        		currl = currl.next;
        		curr = curr.next;
        		currl.next = null;
        	}
        	else
        	{
        		currg.next = curr;
        		currg = currg.next;
        		curr = curr.next;
        		currg.next = null;
        	}
        	
        }
        currl.next = great.next;
        return less.next;     
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * runtime:292ms
   only currg.next = null;
 */
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null)
        	return null;
        ListNode less = new ListNode(0);
        ListNode currl = less;
        ListNode great = new ListNode(0);
        ListNode currg = great;
        ListNode curr = head;
        while(curr != null)
        {
        	if(curr.val < x)
        	{
        		currl.next = curr;
        		currl = currl.next;
        		curr = curr.next;
        		//currl.next = null;
        	}
        	else
        	{
        		currg.next = curr;
        		currg = currg.next;
        		curr = curr.next;
        		//currg.next = null;
        	}
        	
        }
        currl.next = great.next;
        currg.next = null;
        return less.next;     
    }
}