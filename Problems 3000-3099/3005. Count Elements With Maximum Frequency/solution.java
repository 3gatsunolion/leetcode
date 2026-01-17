class Solution {
    public int maxFrequencyElements(int[] nums) {
        int n = nums.length;
        int maxNum = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > maxNum) {
                maxNum = num;
            }
        }

        int[] numFreq = new int[maxNum + 1];
        int maxFreq = 0;
        for (int num : nums) {
            if (++numFreq[num] > maxFreq) {
                maxFreq = numFreq[num];
            }
        }
        
        int count = 0;
        for (int freq : numFreq) {
            if (freq == maxFreq) {
                count++;
            }
        }

        return count * maxFreq;
        // int n = nums.length;
        // Map<Integer, Integer> numFreq = new HashMap<>();
        // for (int num : nums) {
        //     numFreq.put(num, numFreq.getOrDefault(num, 0) + 1);
        // }
        // int[] buckets = new int[n + 1];
        // for (int num : numFreq.keySet()) {
        //     int count = numFreq.get(num);
        //     buckets[count] += count;
        // }

        // for (int freq = n; freq >= 0; freq--) {
        //     if (buckets[freq] > 0) {
        //         return buckets[freq];
        //     }
        // }

        // return 0;
    }
}