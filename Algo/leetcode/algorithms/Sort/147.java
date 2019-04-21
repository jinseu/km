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
 * 在leetcode147之外，还实现了其他的排序
 */
public class Solution {
    /**
	 * 插入排序
	 */
    public ListNode insertionSortList(ListNode head) {
    	ListNode pre;
    	ListNode current;
    	ListNode nh = head;
    	if(head == null || head.next == null)
    		return head;
    	
    	current = head.next;
    	pre = head;
    	while(current != null)
    	{
    	   if(current.val >= pre.val)
    	   {
    		  current = current.next;
    		  pre = pre.next;
    	   }
    	   else
    	   {
    		   ListNode te1 = nh;
    		   if(te1.val > current.val)
    		   {
    			   nh = current;
    			   pre.next = current.next;
    			   current.next = te1;
    			   current = pre.next;
    		   }
    		   else
    		   {
    		       while(te1.next.val < current.val)
    		       {
    			       te1 = te1.next;
    		       }
    		       pre.next = current.next;
    		       current.next = te1.next;
    		       te1.next = current;
    		       current = pre.next;
    		   }
    	   }
    	   
    	}
        return nh;     
    }
	/**
	 * 冒泡排序
	 */
	public static ListNode bubbleSortList(ListNode head) {
		ListNode end = null;
		ListNode temp = null;
		ListNode current = new ListNode(0);
		current.next = head;
		head = current;
		ListNode temp2 = null;

	    while(end != head)
	    {
	        temp = head;
	        for(current = head; current.next.next != end; current = current.next)
	        {
	            if(current.next.val > current.next.next.val)
	            {
	            	temp2 = current.next;
	            	current.next = temp2.next;
	            	temp2.next = current.next.next;
	            	current.next.next = temp2;
	            	temp = current.next.next;
	            }
	        }
	        end = temp;
	    }

	    return head.next;

	}	
}