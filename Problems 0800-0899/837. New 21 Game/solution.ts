function new21Game(n: number, k: number, maxPts: number): number {
    // When Alice is done playing, her points can be in range [K, K+maxPts-1].
    // She can't get past K+maxPts because she stops playing when she is at K or more.

    // Number of points will ALWAYS be <= n in this case
    if (k === 0 || n >= k + maxPts) return 1;

    // dp[i] -> probability alice will get i points after any amount of moves
    const dp = new Array(n + 1);
    dp[0] = 1; // 100% probability to have 0 points after 0 moves

    let res = 0;
    let runSum = 1;
    for (let i = 1; i <= n; i++) {
        dp[i] = runSum / maxPts;
        // If i >= k, Alice has to stop, so we stop adding probability
        if (i < k) {
            runSum += dp[i];
        } else {
            res += dp[i];
        }
        if (i >= maxPts) {
            runSum -= dp[i - maxPts];
        }
    }

    return res;

    // for (let i = 1; i <= n; i++) {
    //     let prob = 0;
    //     for (let pts = 1; pts <= Math.min(i, maxPts); pts++) {
    //         if (i - pts < k) {
    //             prob += dp[i - pts];
    //         }
    //     }
    //     prob /= maxPts;
    //     dp[i] = prob;
    // }

    // let res = 0;
    // for (let i = k; i <= n; i++) {
    //     res += dp[i];
    // }
    // return res;
};