class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
        int maxPos = 0, numChunks = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr[i] > maxPos) {
                maxPos = arr[i];
            }
            if (maxPos == i) numChunks++;
        }
        return numChunks;
    }
};