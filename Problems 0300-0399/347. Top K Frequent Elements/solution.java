class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        int n = nums.length;
        List<Integer>[] buckets = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            buckets[i] = new ArrayList();
        }
        for (int num : freq.keySet()) {
            buckets[freq.get(num)].add(num);
        }

        int i = 0;
        int currFreq = n;
        int[] res = new int[k];
        while (i < k) {
            for (int num : buckets[currFreq]) {
                res[i++] = num;
            }
            currFreq--;
        }
        return res;

        // Map<Integer, Integer> freq = new HashMap<>();
        // for (int num : nums) {
        //     freq.put(num, freq.getOrDefault(num, 0) + 1);
        // }
        // Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // for (int num : freq.keySet()) {
        //     minHeap.offer(new int[] { freq.get(num), num });

        //     if (minHeap.size() > k) {
        //         minHeap.poll();
        //     }
        // }

        // int[] res = new int[k];
        // for (int i = 0; i < k; i++) {
        //     int[] top = minHeap.poll();
        //     res[i] = top[1];
        // }

        // return res;
    }
}