function findLucky(arr: number[]): number {
    const n = arr.length;
    const freq = Array(n + 1).fill(0);

    for (const num of arr) {
        if (num > n) continue;
        freq[num]++;
    }

    // 0 can never be lucky
    for (let count = n; count > 0; count--) {
        if (freq[count] === count) return count;
    }
    return -1;
};