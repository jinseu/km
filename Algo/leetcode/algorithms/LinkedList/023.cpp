/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
 	 bool com(ListNode* a, ListNode* b)
	 {
		  return a->val  > b->val;
	 }
class Solution {
public:

    ListNode* mergeKLists(vector<ListNode*>& lists) {
    	     int length = lists.size();
    	     ListNode** res = new ListNode*[length];
    	     ListNode* head = new ListNode(0);
    	     ListNode* curr =  head;
    	     int i = 0;
    	     int j = 0;
    	     while(i < length)
    	     {
    	    	   if(lists[i] != NULL)
    	    	   {
    	    		     res[j] = lists[i];
    	    		     j++;
    	    	   }
    	    	   i++;
    	     }
			 make_heap(&res[0],&res[j],com);
    	     while(j > 0)
    	     {
    	           
    	           pop_heap(&res[0],&res[j],com);
    	           curr->next = res[j-1];
    	           if(res[j-1]->next != NULL)
    	           {
    	                res[j-1] = res[j-1]->next;
						push_heap(&res[0],&res[j],com);
    	           }
    	           else
    	           {
    	        	   j--;
    	           }
				   curr = curr->next;
    	     }
    	     ListNode* node = head->next;
    	     delete head;
    	     return node;         
    }
};