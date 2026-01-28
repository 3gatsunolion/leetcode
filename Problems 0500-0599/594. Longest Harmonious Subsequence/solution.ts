function findLHS(nums: number[]): number {
    const freq: Map<number, number> = new Map();

    for (const num of nums) {
        freq.set(num, (freq.get(num) || 0) + 1);
    }

    let res = 0;
    for (const [num, count] of freq.entries()) {
        if (freq.has(num + 1)) {
            res = Math.max(res, count + (freq.get(num + 1) ?? 0));
        }
    }

    return res;

    // nums.sort((a, b) => a - b);
    // let start = 0;
    // let end = 0;
    // const n = nums.length;
    // let res = 0;
    // while (end < n) {
    //     const diff = nums[end] - nums[start];
    //     if (diff === 1) {
    //         res = Math.max(res, end - start + 1);
    //         end++;
    //     } else if (diff < 1) {
    //         end++;
    //     } else {
    //         start++;
    //     }
    // }
    // return res;
};