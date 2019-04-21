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
    public void reorderList(ListNode head) 
    {
        LinkedList<ListNode> ll = new LinkedList<ListNode>();
        if(head == null)
        	return ;
        while(head != null)
        {
        	ll.addLast(head);
        	head = head.next;
        }
        head = ll.removeFirst();
        ListNode current = head;
        while(ll.size() != 0)
        {
        	current.next = ll.removeLast();
        	current = current.next;
        	if(ll.size() != 0)
        	{
            	current.next = ll.removeFirst();
            	current = current.next;
        	}
        }
        current.next = null;
    }
}