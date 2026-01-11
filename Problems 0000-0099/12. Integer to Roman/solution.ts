const VALUES = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1];
const ROMAN = ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I'];

function intToRoman(num: number): string {
    let res = "";
    
    for (let i = 0; i < VALUES.length && num > 0; i++) {
        while (num >= VALUES[i]) {
            num -= VALUES[i];
            res += ROMAN[i];
        }
    }
    return res;
};