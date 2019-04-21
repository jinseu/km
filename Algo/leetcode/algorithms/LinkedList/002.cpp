/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        int carry = 0;
		ListNode* res = new ListNode(0);
		ListNode* head = res;
		int val = 0;
		while(l1 != 0 && l2 != 0)
		{
			val = l1->val + l2->val + carry;
			carry = 0;
			if(val >= 10)
			{
				val = val % 10;
				carry = 1;
			}
			res->next = new ListNode(val);
			res->next->next = 0;

			res = res->next;
			l1  = l1->next;
			l2  = l2->next;

		}
		ListNode* tem = 0;
		if(l1 != 0)
		{
			tem = l1;
		}
		else
		{
			tem = l2;
		}
		while(tem != 0)
		{
            val = tem->val +carry;
			if(val >= 10)
			{
				val = val%10;
				carry = 1;
			}
			else
			{
				carry = 0;
			}
			res->next = new ListNode(val);
			res->next->next = 0;
			tem = tem->next;
			res = res->next;
		}
		if(carry == 1)
		{
			res->next = new ListNode(carry);
			res->next->next = 0;
		}
		res = head->next;
		delete head;
		return res;        
    }
};