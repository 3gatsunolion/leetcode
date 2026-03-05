class Solution {
    public:
        double averageWaitingTime(vector<vector<int>>& customers) {
            int n = customers.size();
            int currTime = 0;
            double waitingTime = 0;
            for (int i = 0; i < n; i++) {
                int arrival = customers[i][0];
                int time = customers[i][1];
                if (currTime < arrival) currTime = arrival;
                currTime += time;
                waitingTime += currTime - arrival;
            }
    
            return 1.0 * waitingTime / n;
        }
    };