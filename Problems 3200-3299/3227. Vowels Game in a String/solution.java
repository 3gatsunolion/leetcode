class Solution {
    public boolean doesAliceWin(String s) {
        // 1. If numVowels = 0, Bob automatically wins, because
        // Alice cannot choose any substring with odd vowels
        // when there's no vowels to choose from
        // 2. If numVowels = odd, Alice wins -> just take whole
        // string, and Bob will be left with empty string
        // 3. If numVowels = even > 0, Alice wins -> just take n - 1
        // vowels leaving just 1 vowel behind. Bob either cannot make
        // a move, or it takes substring with nonvowels. But then
        // next move Alice can clean up and Bob loses
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'a', 'e', 'i', 'o', 'u':
                    return true;
            }
            // if (isVowel(s.charAt(i))) return true;
        }
        return false;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}