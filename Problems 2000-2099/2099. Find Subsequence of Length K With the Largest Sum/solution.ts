function maxSubsequence(nums: number[], k: number): number[] {
    const res: number[] = nums.slice(0, k);

    for (let i = k; i < nums.length; i++) {
        const currMin = Math.min(...res);
        if (nums[i] > currMin) {
            res.splice(res.indexOf(currMin), 1);
            res.push(nums[i]);
        }
    }

    return res;

    // const res = nums.map((num, i) => [num, i])
    //             .sort((a, b) => b[0] - a[0])
    //             .slice(0, k)
    //             .sort((a, b) => a[1] - b[1])
    //             .map(el => el[0]);
    // return res;
};