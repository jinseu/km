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
	public ListNode divideSortList(ListNode head,int leftsize)
	{
		ListNode right = head;
		int j = 0;
		while(j < leftsize)
		{
			right = right.next;
			j++;
		}
		return right;
	}
	public ListNode merge(ListNode left,ListNode right,int leftsize,int rightsize)
	{
		ListNode head = null;
		ListNode current = null;
		int i = 0;
		int j = 0;
		if(left.val > right.val )
		{
		    head = right;
		    right = right.next;
		    current = head;
		    j ++;
		}
		else
		{
			head = left;
			left = left.next;
			current = head;
			i ++;
		}
		while(i < leftsize && j < rightsize)
		{
			if(left.val > right.val )
			{
			    current.next = right;
			    right = right.next;
			    j ++;
			}
			else
			{
			    current.next = left;
			    left = left.next;
			    i ++;
			}
			current = current.next;
		}
		if(i == leftsize)
		{
			current.next = right;
		}
		else
		{
			current.next = left;
		}
		return head;
	}
	public ListNode mergeSort(ListNode head,int length)
	{
    	ListNode right = null;
    	ListNode nh = null;
    	if(length > 1)
    	{
    	    int leftsize = length/2;
    	    int rightsize = length - leftsize;
    	    right = divideSortList(head,leftsize);

    	    head = mergeSort(head,leftsize);
    	    right = mergeSort(right,rightsize);
    	    nh = merge(head,right,leftsize,rightsize);
    	}
    	else
    	{
    		nh = head;
    		nh.next = null;
    	}
    	return nh;
	}
    public ListNode sortList(ListNode head) 
    {
    	//divideSortList(head,right);
    	if( head == null || head.next == null)
    		return head;
    	int length = 0;
    	ListNode cu = head;
    	while(cu != null)
    	{
    		cu= cu.next;
    		length++;
    	}
    	cu = mergeSort(head,length);
    	return cu;
    }
}