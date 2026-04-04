function maximumDifference(nums: number[]): number {
    let minSoFar = Number.MAX_SAFE_INTEGER;
    let res = -1;
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] > minSoFar) {
            res = Math.max(res, nums[i] - minSoFar);
        }
        minSoFar = Math.min(minSoFar, nums[i]);
    }
    return res;
};