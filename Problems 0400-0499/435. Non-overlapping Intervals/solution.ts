function eraseOverlapIntervals(intervals: number[][]): number {
    if (intervals.length === 0) return 0;

    intervals.sort((a, b) => a[0] - b[0]);

    let removeCount = 0;
    let end = intervals[0][1];
    for (let i = 1; i < intervals.length; i++) {
        if (intervals[i][0] < end) {
            end = Math.min(end, intervals[i][1]);
            removeCount++;
        } else {
            end = intervals[i][1];
        }
    }

    return removeCount;
    // if (intervals.length === 0) return 0;

    // intervals.sort((a, b) => a[1] - b[1]); // sort by end time
    // let end = intervals[0][1];

    // let keep = 1;
    // for (let i = 1; i < intervals.length; i++) {
    //     if (intervals[i][0] >= end) {
    //         end = intervals[i][1];
    //         keep++;
    //     }
    // }

    // return intervals.length - keep;
};