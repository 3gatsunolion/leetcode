/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
 class Solution {
    public:
        bool isPalindrome(ListNode* head) {
            ListNode* slow = head;
            ListNode* fast = head;
    
            while (fast && fast->next) {
                slow = slow->next;
                fast = fast->next->next;
            }
    
            ListNode* prev = slow;
            slow = slow->next;
            prev->next = nullptr;
    
            while (slow) {
                ListNode* tmp = slow->next;
                slow->next = prev;
                prev = slow;
                slow = tmp;
            }
    
            ListNode* head1 = head;
            ListNode* head2 = prev;
    
            while (head2) {
                if (head1->val != head2->val) return false;
                head1 = head1->next;
                head2 = head2->next;
            }
    
            return true;
        }
    };