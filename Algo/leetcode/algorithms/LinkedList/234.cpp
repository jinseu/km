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
    bool isPalindrome(ListNode* head) {
        ListNode* curr = head;
		stack<ListNode*> st;
		if(curr == NULL)
			return true;
		while(curr!= NULL)
		{
			st.push(curr);
			curr = curr->next;
		}
		while(!st.empty())
		{
			if(head->val != st.top()->val)
				return false;
			st.pop();
			head = head->next;
		}
		return true;        
    }
};