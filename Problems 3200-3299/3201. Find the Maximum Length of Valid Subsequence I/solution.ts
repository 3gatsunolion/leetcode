function maximumLength(nums: number[]): number {
    let odd = 0;
    let even = 0;
    let alternatingOdd = 0;
    let alternatingEven = 0;
    for (const num of nums) {
        if (num % 2 === 0) {
            even++;
            alternatingEven = alternatingOdd + 1;
        } else {
            odd++;
            alternatingOdd = alternatingEven + 1;
        }
    }

    return Math.max(even, odd, alternatingEven, alternatingOdd);
};