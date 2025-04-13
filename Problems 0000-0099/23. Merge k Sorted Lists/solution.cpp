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
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        if (l1 == NULL || l2 == NULL) {
            return (l1 != NULL) ? l1 : l2;
        }

        if (l1->val <= l2->val) {
            l1->next = mergeTwoLists(l1->next, l2);
            return l1;
        } else {
            l2->next = mergeTwoLists(l1, l2->next);
            return l2;
        }
    }

    ListNode* mergeKListsDivideAndConquer(vector<ListNode*>& lists, int start, int end) {
        // if (start > end) {
        //     return NULL;
        // }
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode* left = mergeKListsDivideAndConquer(lists, start, mid);
        ListNode* right = mergeKListsDivideAndConquer(lists, mid+1, end);
        return mergeTwoLists(left, right);
    }

    ListNode* mergeKLists(vector<ListNode*>& lists) {
        if (lists.empty()) {
            return NULL;
        }
        int n = lists.size();
        return mergeKListsDivideAndConquer(lists, 0, n-1);

        // auto compare = [] (ListNode* l, ListNode*r) {
        //     return l->val > r->val;
        // };
    
        // // minheap (default c++ pq is maxheap)
        // priority_queue<ListNode*, vector<ListNode*>, decltype(compare)> pq;

        // for (auto node : lists) {
        //     if (node) {
        //         pq.push(node);
        //     }
        // }

        // ListNode merged;
        // ListNode* prev = &merged;

        // while (pq.size()) {
        //     prev->next = pq.top();
        //     prev = prev->next;
        //     pq.pop();
        //     if (prev->next) {
        //         pq.push(prev->next);
        //     }

        // }

        // return merged.next;

    }
};