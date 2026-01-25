function trap(height: number[]): number {
    // // Brute force
    // const n = height.length;
    // let water = 0;
    // for (let i = 1; i < n - 1; i++) {
    //     const maxLeft = Math.max(...height.slice(0, i));
    //     const maxRight = Math.max(...height.slice(i + 1, n));
    //     water += Math.max(0, Math.min(maxLeft, maxRight) - height[i]);
    // }
    // return water;
    
    const n = height.length;
    if (n < 3) return 0;

    let water = 0;
    let left = 0;
    let right = n - 1;
    let maxLeftSoFar = height[0];
    let maxRightSoFar = height[n - 1];

    while (left < right) {
        // If we work from the side that has the smaller maxSoFar,
        // then we know the height of water is bound by that smaller
        // maxSoFar. We know from that point to the other side
        // the max height is maxSoFar, but how do we know it's not
        // taller based on the other larger maxSoFar. This is because
        // we know the true maxHeight from this point to the other side
        // will have to be AT LEAST maxSoFar, and we need the minimum
        // maxLeft and maxRight, so we know how much water is trapped
        // just from the one maxSoFar. But it has to be on the smaller
        // maxSoFar side, it won't work other side, since we don't know
        // if there's a larger maxSoFar on the other side that hasn't
        // come up yet

        // At each iteration, one of left and right is pointing to
        // their respective maxSoFar. If height[left] < height[right],
        // height[right] is maxRightSoFar, so we work from left to right
        if (height[left] < height[right]) {
            maxLeftSoFar = Math.max(maxLeftSoFar, height[left]);
            water += maxLeftSoFar - height[left];
            left++;
        } else {
            maxRightSoFar = Math.max(maxRightSoFar, height[right]);
            water += maxRightSoFar - height[right];
            right--;
        }
    }
    return water;
};