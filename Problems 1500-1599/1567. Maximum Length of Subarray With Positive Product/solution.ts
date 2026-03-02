function getMaxLen(nums: number[]): number {
    const n = nums.length;
    let posWindowLen = 0;
    let negWindowLen = 0;
    let res = 0;

    for (const num of nums) {
        if (num === 0) {
            // Reset
            posWindowLen = 0;
            negWindowLen = 0;
        } else if (num > 0) {
            posWindowLen++;
            negWindowLen = negWindowLen > 0 ? negWindowLen + 1 : 0;
        } else {
            const prevPosWindowLen = posWindowLen;
            posWindowLen = negWindowLen > 0 ? negWindowLen + 1 : 0;
            negWindowLen = prevPosWindowLen + 1;
        }
        res = Math.max(res, posWindowLen);
    }

    return res;

    // const n = nums.length;
    // // dp[i] = maximum length of a subarray ending at i with positive product
    // const dp = Array(n).fill(null).map(() => [0, 0]);
    // if (nums[0] > 0) dp[0][0] = 1;
    // if (nums[0] < 0) dp[0][1] = 1;

    // let res = dp[0][0];
    // for (let i = 1; i < n; i++) {
    //     if (nums[i] > 0) {
    //         dp[i][0] = dp[i-1][0] + 1;
    //         dp[i][1] = dp[i-1][1] === 0 ? 0 : dp[i-1][1] + 1;
    //     } else if (nums[i] < 0) {
    //         dp[i][0] = dp[i-1][1] === 0 ? 0 : dp[i-1][1] + 1;
    //         dp[i][1] = dp[i-1][0] + 1;
    //     }

    //     res = Math.max(res, dp[i][0]);
    // }

    // return res;
};