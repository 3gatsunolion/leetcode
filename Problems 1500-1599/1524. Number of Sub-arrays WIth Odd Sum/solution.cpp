class Solution {
public:
    int numOfSubarrays(vector<int>& arr) {
        // odd + odd = even
        // odd + even = odd
        // even + even = even

        int odd = 0, even = 0;
        int mod = 1e9 + 7;
        int count = 0;
        for (int num : arr) {
            if (num % 2 == 0) {
                even++;
            } else {
                swap(odd, even);
                odd++;
                // int tmp = odd;
                // odd = even + 1;
                // even = tmp;
            }
            count += odd;
            count %= mod;
        }
        return count;
    }

    int numOfSubarraysDP(vector<int>& arr) {
        // odd + odd = even
        // odd + even = odd
        // even + even = even

        int n = arr.size();
        // even[i] = number of subarrays ending at i with even
        // sum
        vector<int> even(n);
        vector<int> odd(n);

        int mod = 1e9 + 7;

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                even[i] = arr[i] % 2 == 0;
                odd[i] = arr[i] % 2 != 0;
            } else {
                // even
                if (arr[i] % 2 == 0) {
                    odd[i] = odd[i-1];
                    even[i] = 1 + even[i-1];
                } else {
                    odd[i] = 1 + even[i-1];
                    even[i] = odd[i-1];
                }
            }
            odd[i] %= mod;
            even[i] %= mod;
            count += odd[i];
            count %= mod;
        }

        return count;
    }
};