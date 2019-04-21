# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):

    def addTwoNumbers(self, l1, l2):

        """

        :type l1: ListNode

        :type l2: ListNode

        :rtype: ListNode

        """
        i = 0;
        arr = 0
        head = ListNode(0)
        curr = head
        head1 = l1
        head2 = l2

        while head1 != None and head2 != None :
            res = head1.val + head2.val + arr
            if res >= 10:
                res = res % 10
                arr = 1
            else :
                arr = 0
            tem = ListNode(res)
            curr.next = tem
            curr = curr.next
            head1 = head1.next
            head2 = head2.next
        if head1 == None :
            ex = head2
        else :
            ex = head1
        while ex != None :
            res = ex.val + arr
            if res >= 10:
                res = res % 10
                arr = 1
            else :
                arr = 0
            tem = ListNode(res)
            curr.next = tem
            curr = curr.next
            ex = ex.next

        if arr == 1 :
            tem = ListNode(arr)
            curr.next = tem
            curr = curr.next
        return head.next    
