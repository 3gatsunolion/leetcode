function characterReplacement(s: string, k: number): number {
    const freq = Array(26).fill(0);

    let left = 0;
    let currMaxCount = 0;
    let res = 0;
    for (let right = 0; right < s.length; right++) {
        const charCode = s[right].charCodeAt(0) - 'A'.charCodeAt(0);
        freq[charCode]++;

        if (currMaxCount < freq[charCode]) {
            currMaxCount = freq[charCode];
        }

        if (right - left + 1 - currMaxCount > k) {
            freq[s[left].charCodeAt(0) - 'A'.charCodeAt(0)]--;
            left++;
        }

        res = Math.max(res, right - left + 1);
    }

    return res;
};