class Solution {
    private wSums: number[];

    constructor(w: number[]) {
        this.wSums = [...w];

        // [2, 5, 1, 10]
        // 1, 2 | 3, 4, 5, 6, 7 | 8 | 9 10 11 12 13 14 15 16 17 18
        // As you see in above example, when we choose a random number
        // from 1 to 18, the probability of choosing index 1 is 5 / 18
        // since we can choose five numbers (3, 4, 5, 6, 7)
        for (let i = 1; i < w.length; i++) {
            this.wSums[i] += this.wSums[i-1];
        }
    }

    pickIndex(): number {
        // Math.random() -> random number [0, 1)
        const num = Math.floor(Math.random() * this.wSums[this.wSums.length - 1]) + 1;
        let lo = 0;
        let hi = this.wSums.length - 1;

        while (lo < hi) {
            const mid = Math.floor((lo + hi) / 2);

            if (this.wSums[mid] < num) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = new Solution(w)
 * var param_1 = obj.pickIndex()
 */