const VOWELS = new Set(['a', 'e', 'o', 'i', 'u']);
function isValid(word: string): boolean {
    // if (word.length < 3) return false;
    // return /^[a-zA-Z0-9]{3,}$/.test(word);
    if (!/^[a-z0-9]{3,}$/i.test(word)) return false;
    for (const c of word) {
        if (VOWELS.has(c.toLowerCase())) {
            return /[b-df-hj-np-tv-z]+/i.test(word);
        }
    }
    return false;
};