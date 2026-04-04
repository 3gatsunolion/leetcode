function kthSmallestProduct(nums1: number[], nums2: number[], k: number): number {
    if (nums1.length > nums2.length) {
        return kthSmallestProduct(nums2, nums1, k);
        // [nums1, nums2] = [nums2, nums1];
    }

    // nums1 and nums2 are sorted
    function check(x: number) {
        let total = 0;
        for (const num1 of nums1) {
            // num1 * all numbers of nums2 in order will
            // be ascending since num1 > 0
            if (num1 > 0) {
                total += binarySearchRight(nums2, Math.floor(x / num1));
            }
            // num1 * all numbers of nums2 in order will be decreasing
            else if (num1 < 0) {
                total += nums2.length - binarySearchLeft(nums2, Math.ceil(x / num1));
            }
            // all products will be zero, and the product will be <= x
            // if x >= 0
            else if (num1 === 0 && x >= 0) total += nums2.length; 
        }

        return total;
    }

    let lo = -1e10;
    let hi = 1e10;

    while (lo < hi) {
        const mid = Math.floor((lo + hi) / 2);

        if (check(mid) < k) {
            lo = mid + 1;
        } else {
            hi = mid;
        }
    }

    return lo;
};

function binarySearchLeft(nums: number[], target: number) {
    let lo = 0;
    let hi = nums.length;

    while (lo < hi) {
        const mid = Math.floor((lo + hi) / 2);

        if (nums[mid] < target) {
            lo = mid + 1;
        } else {
            hi = mid;
        }
    }

    return lo;
}

function binarySearchRight(nums: number[], target: number) {
    let lo = 0;
    let hi = nums.length;

    while (lo < hi) {
        const mid = Math.floor((lo + hi) / 2);

        if (nums[mid] <= target) {
            lo = mid + 1;
        } else {
            hi = mid;
        }
    }

    return lo;
}