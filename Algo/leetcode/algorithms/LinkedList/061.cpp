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
    ListNode* rotateRight(ListNode* head, int k) {
        if(head == NULL || k < 0)
            return NULL;
        ListNode *tmp = head;
        int size = 0;
        while(tmp != NULL)
        {
            tmp = tmp->next;
            ++size;
        }
        k = size - (k % size);
        if(k == size)
            return head;
        k = k - 1;
        ListNode *rev = NULL;
        ListNode *pre = head;
        ListNode *token = head;
        ListNode *cur = head;
        int index = 0;
        while(index <= k && cur != NULL)
        {
            pre = cur;
            cur = cur->next;
            ++index;
        }
        rev = cur;
        if(cur == NULL)
            return head;
        while(cur->next != NULL)
            cur = cur->next;
        cur->next = token;
        pre->next = NULL;
        return rev;        
    }
};