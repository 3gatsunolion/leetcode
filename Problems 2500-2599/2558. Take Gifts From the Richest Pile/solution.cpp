class Solution {
public:
    long long pickGifts(vector<int>& gifts, int k) {
        priority_queue<int> maxHeap;
        for (int gift : gifts) {
            maxHeap.push(gift);
        }

        for (int i = 0; i < k; i++) {
            int gift = maxHeap.top();
            maxHeap.pop();
            int leave = sqrt(gift);
            int take = gift - leave;
            if (leave > 0) {
                maxHeap.push(leave);
            }
        }

        long long remain = 0;
        while (!maxHeap.empty()) {
            remain += maxHeap.top();
            maxHeap.pop();
        }
        return remain;
    }
};