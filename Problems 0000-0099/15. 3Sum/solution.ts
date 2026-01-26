function threeSum(nums: number[]): number[][] {
    // IMPORTANT: By default JS sorts numbers lexicographically
    // as if they were strings
    nums.sort((a, b) => a - b);

    const n = nums.length;
    const res = [];

    for (let i = 0; i < n - 2; i++) {
        if (i > 0 && nums[i] === nums[i - 1]) continue;

        if (nums[i] > 0) break;
        
        let l = i + 1;
        let r = n - 1;

        while (l < r) {
            const sumVal = nums[i] + nums[l] + nums[r];

            if (sumVal === 0) {
                res.push([nums[i], nums[l], nums[r]]);

                // Avoid duplicates
                while (l < r && nums[l] === nums[l + 1]) {
                    l++;
                }
                l++;

                while (l < r && nums[r] === nums[r - 1]) {
                    r--;
                }
                r--;
            } else if (sumVal < 0) {
                l++;
            } else {
                r--;
            }
        }
    }

    return res;
};