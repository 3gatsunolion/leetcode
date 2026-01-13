function maxFreeTime(eventTime: number, startTime: number[], endTime: number[]): number {
    // 1. Move meeting between two other meetings, so that the gap will be
    // left + meetingTime + right
    // 2. Slide meeting to left or right, so gap will be left + right
    const n = startTime.length;
    // const gaps: number[] = new Array(n + 1);
    // gaps[0] = startTime[0];
    // gaps[n] = eventTime - endTime[n - 1];
    // for (let i = 1; i < n; i++) {
    //     gaps[i] = startTime[i] - endTime[i - 1];
    // }

    const largestRightGap: number[] = Array(n + 1).fill(0);
    let currGap = eventTime - endTime[n - 1];
    for (let i = n - 2; i >= 0; i--) {
        largestRightGap[i] = Math.max(largestRightGap[i + 1], currGap);
        currGap = startTime[i + 1] - endTime[i];
    }

    let largestLeftGap = 0;
    let res = 0;
    for (let i = 0; i < n; i++) {
        const left = i === 0 ? 0 : endTime[i - 1];
        const right = i === n - 1 ? eventTime : startTime[i + 1];
        const meetingTime = endTime[i] - startTime[i];
        if (largestLeftGap >= meetingTime || largestRightGap[i] >= meetingTime) {
            res = Math.max(res, right - left);
        } else {
            res = Math.max(res, right - left - meetingTime);
        }
        largestLeftGap = Math.max(largestLeftGap, startTime[i] - (i === 0 ? 0 : endTime[i - 1]));
    }

    return res;
};