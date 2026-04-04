function longestSubsequenceRepeatedK(s: string, k: number): string {
    const freq: Record<string, number> = {};
    for (const c of s) {
        freq[c] = (freq[c] || 0) + 1;
    }

    const cand = Object.keys(freq)
                        .filter(c => freq[c] >= k)
                        .sort((a, b) => b.localeCompare(a));
    
    const q: string[] = [...cand];
    let res: string = "";
    while (q.length > 0) {
        const curr: string = q.shift() || "";
        if (curr.length > res.length) res = curr;

        for (const c of cand) {
            const next = curr + c;
            if (isKRepeated(s, next, k)) q.push(next);
        }
    }
    return res;
};

function isKRepeated(s: string, pattern: string, k: number): boolean {
    let i = 0;
    let count = 0;
    for (const c of s) {
        if (c === pattern[i]) {
            i++;

            if (i === pattern.length) {
                count++;
                i = 0;

                if (count === k) return true;
            }
        }
    }

    return false;
};