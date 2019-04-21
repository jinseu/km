/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        ListNode cu   = node;
        ListNode prev = null;
        while(cu.next != null)
        {
        	prev = cu;
        	cu = cu.next;
        	prev.val = cu.val;        	
        }
        prev.next = null;
        return ;        
    }
}