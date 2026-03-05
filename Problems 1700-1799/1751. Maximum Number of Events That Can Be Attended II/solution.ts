function bisectRight(events: number[][], start: number, end: number, target: number): number {
    let lo = start;
    let hi = end;

    while (lo < hi) {
        const mid = Math.floor((lo + hi) / 2);

        if (events[mid][0] <= target) {
            lo = mid + 1;
        } else {
            hi = mid;
        }
    }

    return lo;
};

function maxValue(events: number[][], k: number): number {
    const n: number = events.length;
    const dp: number[][] = Array.from(Array(n + 1), () => Array(k + 1).fill(0));
    events.sort((a, b) => a[0] - b[0]);

    for (let i = n - 1; i >= 0; i--) {
        // Left boundary index of events you can attend if you attend events[i]
        const nextStart = bisectRight(events, i + 1, n, events[i][1]);
        for (let count = 1; count <= k; count++) {
            // Don't attend attend or attend event
            dp[i][count] = Math.max(dp[i + 1][count], events[i][2] + dp[nextStart][count - 1]);
        }
    }

    return dp[0][k];
};