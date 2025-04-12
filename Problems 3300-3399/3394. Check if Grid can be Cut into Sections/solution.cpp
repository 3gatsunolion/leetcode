class Solution {
public:
    bool checkValidCuts(int n, vector<vector<int>>& rectangles) {
        // sort by x and y independently to check vertical
        // and horizontal cuts
        int numRect = rectangles.size();
        vector<pair<int, int>> x(numRect);
        vector<pair<int, int>> y(numRect);

        for (int i = 0; i < numRect; i++) {
            x[i] = make_pair(rectangles[i][0], rectangles[i][2]);
            y[i] = make_pair(rectangles[i][1], rectangles[i][3]);
        }

        // try vertical cuts
        sort(x.begin(), x.end());
        // always cut as early as possible
        // as soon as we have one solid rectangle that
        // doesn't merge into another we cut
        int numCuts = 0;
        int currEnd = 0;
        for (int i = 0; i < numRect; i++) {
            auto [start, end] = x[i];
            if (start >= currEnd) numCuts++;
            if (numCuts == 3) return true;
            currEnd = max(currEnd, end);
        }

        // try horizontal cuts
        sort(y.begin(), y.end());
        currEnd = 0;
        numCuts = 0;
        for (int i = 0; i < numRect; i++) {
            auto [start, end] = y[i];
            if (start >= currEnd) numCuts++;
            if (numCuts == 3) return true;
            currEnd = max(currEnd, end);
        }
        return false;
    }
};