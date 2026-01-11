function groupAnagrams(strs: string[]): string[][] {
    const groups = new Map<string, string[]>();

    for (const word of strs) {
        const wordKey = word.split("").sort().join("");
        if (!groups.has(wordKey)) {
            groups.set(wordKey, []);
        }
        groups.get(wordKey)?.push(word);
    }
    return Array.from(groups.values());
};