function isPowerOfThree(n: number): boolean {
    // return n > 0 && (n === 1 || (n % 3 === 0 && isPowerOfThree(n / 3)));

    return n > 0 && Math.pow(3, Math.round(Math.log(n) / Math.log(3))) === n;
    
    // // Iterative
    // if (n <= 0) return false;
    // while (n % 3 === 0) {
    //     n /= 3;
    // }
    // return n === 1;
};