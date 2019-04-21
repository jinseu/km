/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) 
    {
        ListNode pre = head;
        int i = 0;
        while(pre != null && i < n+1)
        {
        	pre = pre.next;
        	i++;
        }
        if(i < n)
        {
        	return head;
        }
        if(i == n)
        {
        	return head.next;
        }
        ListNode last = head;
        while(pre != null)
        {
        	pre = pre.next;
        	last = last.next;
        }
        last.next = last.next.next;
        return head;
    }
}
