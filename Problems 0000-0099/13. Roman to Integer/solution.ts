const mp = {
    'I': 1,
    'V': 5,
    'X': 10,
    'L': 50,
    'C': 100,
    'D': 500,
    'M': 1000
};

function romanToInt(s: string): number {
    let num = 0;
    for (let i = 0; i < s.length; i++) {
        if (i < s.length - 1 && mp[s[i]] < mp[s[i + 1]]) {
            num -= mp[s[i]];
        } else {
            num += mp[s[i]];
        }
    }
    return num;
};