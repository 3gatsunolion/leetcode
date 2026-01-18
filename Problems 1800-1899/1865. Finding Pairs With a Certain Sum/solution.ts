class FindSumPairs {
    freq1: Map<number, number>;
    freq2: Map<number, number>;
    nums2: number[];

    constructor(nums1: number[], nums2: number[]) {
        this.freq1 = nums1.reduce((freq, num) => {
            freq.set(num, (freq.get(num) ?? 0) + 1);
            return freq;
        }, new Map());
        this.freq2 = nums2.reduce((freq, num) => {
            freq.set(num, (freq.get(num) ?? 0) + 1);
            return freq;
        }, new Map());

        this.nums2 = nums2;
    }

    add(index: number, val: number): void {
        const old = this.nums2[index];
        this.freq2.set(old, this.freq2.get(old) - 1);
        if (this.freq2.get(old) === 0) {
            this.freq2.delete(old);
        } 
        this.nums2[index] += val
        this.freq2.set(this.nums2[index], (this.freq2.get(this.nums2[index]) ?? 0) + 1);
    }

    count(tot: number): number {
        let res = 0;
        for (const [num1, count1] of this.freq1.entries()) {
            res += (this.freq2.get(tot - num1) ?? 0) * count1;
        }
        return res;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * var obj = new FindSumPairs(nums1, nums2)
 * obj.add(index,val)
 * var param_2 = obj.count(tot)
 */