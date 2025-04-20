class Solution {
public:
    vector<int> asteroidCollision(vector<int>& asteroids) {
        vector<int> res;
        for (int asteroid : asteroids) {
            if (res.empty() || res[res.size()-1] < 0 == asteroid < 0 || res[res.size()-1] < 0) {
                res.push_back(asteroid);
            } else {
                // look for collisions (smaller)
                while (!res.empty() && res[res.size()-1] > 0 && res[res.size()-1] < abs(asteroid)) {
                    res.pop_back();
                }

                // same size collision (don't add asteroid because
                // it's exploded)
                if (!res.empty() && res[res.size()-1] > 0 && abs(res[res.size()-1]) == abs(asteroid)) {
                    res.pop_back();
                } else if (res.empty() || res[res.size()-1] < 0 == asteroid < 0) {
                    res.push_back(asteroid);
                }
            }
        }
        return res;
    }
};