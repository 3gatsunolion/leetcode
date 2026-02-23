function maxDiff(num: number): number {
    // 1. For max num, replace first non 9 digit with 9
    // 2. For min num, replace first digit with 1. If first number
    // is already 1, then replace first non zero greater than first char
    // with 0
    let maxNum = num.toString();
    let minNum = num.toString();

    for (const digit of maxNum) {
        if (digit !== '9') {
            maxNum = maxNum.replaceAll(digit, '9');
            break;
        }
    }

    if (minNum[0] !== '1') {
        minNum = minNum.replaceAll(minNum[0], '1');
    } else {
        const firstDigit = minNum[0];
        for (let i = 1; i < minNum.length; i++) {
            if (minNum[i] !== firstDigit && minNum[i] !== '0') {
                minNum = minNum.replaceAll(minNum[i], '0');
                break;
            }
        }
    }

    return Number(maxNum) - Number(minNum);
};