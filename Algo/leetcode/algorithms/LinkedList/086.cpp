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
    ListNode* partition(ListNode* head, int x) {
    if(head == NULL)
            return head;

        ListNode *sm = new ListNode(0), *sm_tail = sm;
        ListNode *bg = new ListNode(0), *bg_tail = bg;
        ListNode *cur = head;

        while(cur) {
            if(cur->val < x) {
                sm_tail->next = cur;
                cur     = cur->next;
                sm_tail = sm_tail->next;
                sm_tail->next = NULL;
            }else{
                bg_tail->next = cur;
                bg_tail = bg_tail->next;
                cur = cur->next;
                bg_tail->next = NULL;
            }
        }
        sm_tail->next = bg->next;
        return sm->next;        
    }
};