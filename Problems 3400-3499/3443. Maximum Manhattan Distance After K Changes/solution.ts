function maxDistance(s: string, k: number): number {
    // [N, S] cancel each other out, [E, W] cancel each other out
    // you can treat these pairs independently
    // Modify the least frequent direction from each pair
    let lat = 0;
    let long = 0;
    let res = 0;
    for (let i = 0; i < s.length; i++) {
        const dir = s[i];
        if (dir === 'N') {
            long++;
        } else if (dir === 'S') {
            long--;
        } else if (dir === 'E') {
            lat++;
        } else {
            lat--;
        }
        res = Math.max(res, Math.min(Math.abs(lat) + Math.abs(long) + 2*k, i + 1));
    }

    return res;
};