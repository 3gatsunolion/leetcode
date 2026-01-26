const LESS_THAN_20 = ["", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"];
const TENS = ["", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"];
const THOUSANDS = ["", "Thousand", "Million", "Billion"];

function numberToWords(num: number): string {
    if (num === 0) return "Zero";

    let i = 0;
    let res = "";
    while (num > 0) {
        if (num % 1000 !== 0) {
            res = lessThanThousand(num % 1000) + THOUSANDS[i] + " " + res;
        }
        num = Math.floor(num / 1000);
        i++;
    }

    return res.trim();
};

// Once we get to numbers >= 1000, they get split into digits of 3
// i.e. 10, 000, 000 -> And the words are built on these triplets
// So create helper function to return word representation of num
// that are < 1000
function lessThanThousand(num: number): string {
    if (num === 0) {
        return "";
    } else if (num < 20) {
        return LESS_THAN_20[num] + " ";
    } else if (num < 100) {
        return TENS[Math.floor(num / 10)] + " " + lessThanThousand(num % 10);
    } else {
        // 100 - 999
        return LESS_THAN_20[Math.floor(num / 100)] + " Hundred " + lessThanThousand(num % 100);
    }
}