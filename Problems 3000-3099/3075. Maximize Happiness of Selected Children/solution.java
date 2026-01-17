class Solution {
    // public long maximumHappinessSum(int[] happiness, int k) {
    //     Arrays.sort(happiness);
    //     int n = happiness.length;
    //     int turns = 0;
    //     long sum = 0;
    //     for (int i = n - 1; i >= n - k; i--) {
    //         if (happiness[i] - turns < 0) break;
    //         sum += happiness[i] - turns;
    //         turns++;
    //     }

    //     return sum;
    // }

    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        quickSelect(happiness, 0, n - 1, n - k);

        long ans = 0L;
        // Since we're increasing penalty each round, we're subtracting
        // at most 1 + 2 + .. + (k - 1) = k * (k - 1) / 2
        if(happiness[n - k] < k - 1) {
            Arrays.sort(happiness, n - k + 1, n);
            for(int i = 0; i < k; ++i) {
                if(happiness[n - 1 - i] <= i) return ans - i * (i - 1L) / 2L;
                ans += happiness[n - 1 - i];
            }
        }
        for(int i = n - k; i < n; ++i) ans += happiness[i];
        return ans - k * (k - 1L) / 2L;
    }

    // Average: O(n), Worst: O(n^2)
    private void quickSelect(int[] nums, int l, int r, int k) {
        if (l >= r) return;

        int[] p = partition(nums, l, r);

        if (k >= p[0] && k <= p[1]) return;
        if (k > p[1]) {
            quickSelect(nums, p[1] + 1, r, k);
        } else {
            quickSelect(nums, l, p[0] - 1, k);
        }
    }

    private int[] partition(int[] nums, int l, int r) {
        int pivotIndex = (int) (Math.random() * (r - l + 1) + l);
        int pivotNum = nums[pivotIndex];
        
        // // Move pivot to the end
        // swap(nums, pivotIndex, r);
        // int p = l;

        // for (int i = l; i < r; i++) {
        //     if (nums[i] <= pivotNum) {
        //         swap(nums, i, p++);
        //     }
        // }

        // swap(nums, p, r);
        // return p;

        // Dutch National flag partitioning for cases like if
        // we have a lot of same number
        int start = l;
        int mid = l;
        int end = r;

        while (mid <= end) {
            if (nums[mid] < pivotNum) {
                swap(nums, start, mid);
                start++;
                mid++;
            } else if (nums[mid] == pivotNum) {
                mid++;
            } else {
                swap(nums, mid, end);
                end--;
            }
        }

        return new int[] {start, mid - 1};
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}