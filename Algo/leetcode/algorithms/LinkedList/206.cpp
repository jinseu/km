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
    ListNode* reverseList(ListNode* head) {
        ListNode* curr = NULL;
		ListNode* res = NULL;
		while(head != NULL)
		{
			curr = head;
			head = head->next;
			curr->next = res;
			res = curr;
		}
		return res;        
    }
};