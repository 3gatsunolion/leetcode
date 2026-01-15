function divideArray(nums: number[], k: number): number[][] {
    // Best chance of achieving condition if sort it and split
    // in this order, so that elements have less chance of exceeding
    // difference of more than k
    nums.sort((a, b) => a - b);

    const res: number[][] = [];
    for (let i = 0; i < nums.length - 2; i += 3) {
        if ((nums[i + 2] - nums[i]) > k) return [];
        res.push([nums[i], nums[i+1], nums[i+2]]);
    }

    return res;

    // return nums.reduce((accum, num, i) => {
    //     const index = Math.floor(i / 3);
    //     if (accum.length === index) {
    //         accum.push([]);
    //     }
    //     const subArray = accum[index];
    //     subArray.push(num);
    //     return accum;
    // }, []);
};