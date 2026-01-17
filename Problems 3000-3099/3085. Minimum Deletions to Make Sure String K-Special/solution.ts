function minimumDeletions(word: string, k: number): number {
    // We either:
    // 1. Delete all characters that are over k difference with
    // character(s) with lowest freq
    // 2. Delete all occurrences of characters with lowest freq
    // so they are no longer part of the equation
    let freq = Array(26).fill(0);

    for (const c of word) {
        freq[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
    }

    freq = freq.filter(f => f > 0);

    let res = word.length;
    for (const minFreq of freq) {
        let curr = 0;
        for (const count of freq) {
            curr += count < minFreq ? count : Math.max(0, count - (minFreq + k));
        }
        res = Math.min(res, curr);
    }

    return res;

    // const freq = new Map();
    // for (const c of word) {
    //     if (!freq.has(c)) {
    //         freq.set(c, 0);
    //     }
    //     freq.set(c, freq.get(c) + 1);
    // }

    // const counter = [...freq.values()];
    // // Each iteration, choose one freq to be the min freq after all
    // // deletions and choose best res
    // let res = word.length;
    // for (const minFreq of counter) {
    //     let curr = 0;
    //     for (const count of counter) {
    //         curr += count < minFreq ? count : Math.max(0, count - (minFreq + k));
    //     }
    //     res = Math.min(res, curr);
    // }

    // return res;
};