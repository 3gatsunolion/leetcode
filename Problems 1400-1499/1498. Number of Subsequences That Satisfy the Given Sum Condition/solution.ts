const MOD = 1e9 + 7;
function numSubseq(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const pows = Array(n).fill(0);
    pows[0] = 1;
    for (let i = 1; i < n; i++) {
        pows[i] = (pows[i - 1] * 2) % MOD;
    }

    let l = 0;
    let r = n - 1;
    let res = 0;
    while (l <= r) {
        const total = nums[l] + nums[r];
        if (total > target) {
            r--;
        } else {
            // 2**(r-l) -> either include or don't include
            res = (res + pows[r - l]) % MOD;
            // res %= MOD;
            l++;
        }
    }

    return res;
};