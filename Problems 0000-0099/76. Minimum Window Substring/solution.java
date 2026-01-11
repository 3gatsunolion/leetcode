class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> targetFreq = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            targetFreq.put(ch, targetFreq.getOrDefault(ch, 0) + 1);
        }

        int charRemaining = t.length();
        int minLenSoFar = s.length() + 1;
        int left = 0;
        int start = 0;
        for (int right = 0; right < s.length(); right++) {
            char currChar = s.charAt(right);
            if (targetFreq.containsKey(currChar)) {
                if (targetFreq.get(currChar) > 0) {
                    charRemaining--;
                }
                targetFreq.put(currChar, targetFreq.get(currChar) - 1);
            }

            while (charRemaining == 0) {
                int currLen = right - left + 1;
                if (currLen < minLenSoFar) {
                    minLenSoFar = currLen;
                    start = left;
                }

                currChar = s.charAt(left++);
                if (targetFreq.containsKey(currChar)) {
                    targetFreq.put(currChar, targetFreq.get(currChar) + 1);
                    if (targetFreq.get(currChar) > 0) {
                        charRemaining++;
                    }
                }
            }
        }

        if (minLenSoFar == s.length() + 1) {
            return "";
        }

        return s.substring(start, start + minLenSoFar);

        // if (s.length() < t.length()) {
        //     return "";
        // }

        // Map<Character, Integer> sFreq = new HashMap<>();
        // Map<Character, Integer> targetFreq = new HashMap<>();

        // for (int i = 0; i < t.length(); i++) {
        //     char ch = t.charAt(i);
        //     targetFreq.put(ch, targetFreq.getOrDefault(ch, 0) + 1);
        // }

        // int reqNeeded = targetFreq.size(), reqMet = 0;
        // int left = 0;
        // int start = 0;
        // int minLenSoFar = s.length() + 1;
        // for (int right = 0; right < s.length(); right++) {
        //     sFreq.put(s.charAt(right), sFreq.getOrDefault(s.charAt(right), 0) + 1);
        //     if (sFreq.get(s.charAt(right)).intValue() == targetFreq.getOrDefault(s.charAt(right), 0).intValue()) {
        //         reqMet++;
        //     }

        //     while (reqMet == reqNeeded) {
        //         int currLen = right - left + 1;
        //         if (currLen < minLenSoFar) {
        //             minLenSoFar = currLen;
        //             start = left;
        //         }

        //         char ch = s.charAt(left++);
        //         sFreq.put(ch, sFreq.get(ch) - 1);

        //         if (targetFreq.containsKey(ch) && sFreq.get(ch).intValue() < targetFreq.get(ch).intValue()) {
        //             reqMet--;
        //         }
        //     }
        // }

        // if (minLenSoFar == s.length() + 1) {
        //     return "";
        // }

        // return s.substring(start, start + minLenSoFar);
    }
}