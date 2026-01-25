class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] broken = new boolean[26];
        for (int i = 0; i < brokenLetters.length(); i++) {
            char c = brokenLetters.charAt(i);
            broken[c - 'a'] = true;
        }

        int res = 0;
        int brokenCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                if (brokenCount == 0) {
                    res++;
                }
                brokenCount = 0;
            } else {
                if (broken[c - 'a']) {
                    brokenCount++;
                }
            }
        }

        return res + (brokenCount == 0 ? 1 : 0);

        // String[] words = text.split(" ");
        // int res = 0;
        // for (String word : words) {
        //     boolean isBroken = false;
        //     for (int i = 0; i < word.length(); i++) {
        //         char c = word.charAt(i);
        //         if (broken[c - 'a']) {
        //             isBroken = true;
        //             break;
        //         }
        //     }

        //     if (!isBroken) res++;
        // }
        // return res;
    }
}