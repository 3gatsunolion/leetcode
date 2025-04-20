class MyCalendarTwo {
    vector<pair<int, int>> single_booked;
    vector<pair<int, int>> double_booked;
public:
    // map<int, int> count;
    MyCalendarTwo() {

    }

    bool book(int startTime, int endTime) {
        for (const pair<int, int>& bk : double_booked) {
            if (max(startTime, bk.first) < min(endTime, bk.second)) {
                return false;
            }
        }

        for (auto& [start, end] : single_booked) {
            if (max(startTime, start) < min(endTime, end)) {
                double_booked.push_back({max(startTime, start), min(endTime, end)});
            }
        }

        single_booked.push_back({startTime, endTime});
        return true;
    }
    
    // bool book(int startTime, int endTime) {
    //     count[startTime]++;
    //     count[endTime]--;
    //     int booked = 0;
    //     for (auto it = count.begin(); it != count.end(); it++) {
    //         booked += it->second;
    //         if (booked == 3) {
    //             // evict event
    //             count[startTime]--;
    //             count[endTime]++;
    //             return false;
    //         }

    //     }
    //     return true;
    // }
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo* obj = new MyCalendarTwo();
 * bool param_1 = obj->book(startTime,endTime);
 */