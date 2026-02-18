function countSubstrings(s: string): number {
    const n = s.length;
    let res = 0;
    for (let i = 0; i < n; i++) {
        res += countPalindromes(s, i, i); // odd
        res += countPalindromes(s, i, i + 1); // even
    }

    return res;

    // const n = s.length;
    // const dp = Array.from({ length: n }, () => Array(n).fill(false));
    // let count = 0;
    // for (let start = n - 1; start >= 0; start--) {
    //     for (let end = start; end < n; end++) {
    //         if (start === end) {
    //             dp[start][end] = true;
    //         } else if (start + 1 === end) {
    //             dp[start][end] = s[start] === s[end];
    //         } else {
    //             dp[start][end] = s[start] === s[end] ? dp[start+1][end-1] : false;
    //         }
    //         count += dp[start][end];
    //     }
    // }
    // return count;
};

function countPalindromes(s: string, i: number, j: number): number {
    let count = 0;
    const n = s.length;
    while (i >= 0 && j < n && s[i] === s[j]) {
        count++;
        i--;
        j++;
    }
    return count;
}