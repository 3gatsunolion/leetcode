function maximum69Number (num: number): number {
    let numStr = num.toString().split("");
    for (let i = 0; i < numStr.length; i++) {
        if (numStr[i] === '6') {
            numStr[i] = '9';
            break;
        }
    }
    return parseInt(numStr.join(""));
};