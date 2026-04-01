class Solution {
    public String sortVowels(String s) {
        int[] vowelCount = new int[128];
        char[] sChar = s.toCharArray();
        boolean hasVowel = false;
        for (int i = 0; i < sChar.length; i++) {
            char c = sChar[i];
            if (isVowel(c)) {
                vowelCount[(int) c]++;
                hasVowel = true;
            }
        }

        if (!hasVowel) return s;

        int v = 0;
        for (int i = 0; i < sChar.length; i++) {
            char c = sChar[i];
            if (isVowel(c)) {
                while (vowelCount[v] == 0) v++;
                sChar[i] = (char) v;
                vowelCount[v]--;
            }
        }

        return new String(sChar);
        
        // List<Character> vowels = new ArrayList<>();
        // for (int i = 0; i < s.length(); i++) {
        //     char c = s.charAt(i);
        //     if (isVowel(c)) vowels.add(c);
        // }

        // vowels.sort((a, b) -> (int) a - (int) b);
        // int v = 0;
        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < s.length(); i++) {
        //     char c = s.charAt(i);
        //     if (isVowel(c)) {
        //         sb.append(vowels.get(v++));
        //     } else {
        //         sb.append(s.charAt(i));
        //     }
        // }
        // return sb.toString();
    }

    private boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}