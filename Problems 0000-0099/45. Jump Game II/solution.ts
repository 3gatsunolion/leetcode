// Brute force: O(n!)
// function jump(nums: number[], pos: number = 0): number {
//     if (pos >= nums.length - 1) return 0;
//     let minSteps = nums.length + 1;
//     for (let step = 1; step <= nums[pos]; step++) {
//         minSteps = Math.min(minSteps, 1 + jump(nums, pos + step));
//     }
//     return minSteps;
// };

// // DP: O(n^2)
// function jump(nums: number[]): number {
//     const n = nums.length;
//     const dp = Array(n).fill(n + 1);
//     dp[0] = 0;

//     for (let pos = 1; pos < n; pos++) {
//         for (let prev = pos - 1; prev >= 0; prev--) {
//             if (nums[prev] + prev < pos) continue;
//             dp[pos] = Math.min(dp[pos], 1 + dp[prev]);
//         }
//     }
//     return dp[n - 1];
// };

// BFS: O(n)
function jump(nums: number[]): number {
    let canReach = 0;
    let currEnd = 0;
    let minJumps = 0;
    for (let i = 0; i < nums.length - 1; i++) {
        canReach = Math.max(canReach, i + nums[i]);
        if (i === currEnd) {
            minJumps++;
            currEnd = canReach;
        }
    }
    return minJumps;
}