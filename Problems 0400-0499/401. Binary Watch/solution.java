class Solution {
    // public List<String> readBinaryWatch(int turnedOn) {
    //     List<String> times = new ArrayList<>();
    //     for (int h = 0; h < 12; h++) {
    //         for (int m = 0; m < 60; m++) {
    //             if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
    //                 times.add(String.format("%d:%02d", h, m));
    //             }
    //         }
    //     }

    //     return times;
    // }

    // Time complexity: 2^10 -> Either add led light or don't
    // Total of 10 led lights -> O(1)
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> times = new ArrayList<>();
        
        // Indices 0-3: hour
        // Indices 4-9: minutes
        int[] leds = {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
        backtrack(0, 0, 0, leds, turnedOn, times);
        return times;
    }

    private void backtrack(int hours,
                           int minutes,
                           int ledIdx,
                           int[] leds,
                           int numLedsLeft,
                           List<String> times) {
        if (numLedsLeft == 0) {
            if (hours <= 11 && minutes <= 59) {
                StringBuilder sb = new StringBuilder();
                sb.append(hours)
                  .append(":")
                  .append(minutes < 10 ? "0" + minutes : minutes);
                times.add(sb.toString());
            }
            return;
        }

        for (int i = ledIdx; i < leds.length; i++) {
            if (isHour(i)) {
                hours += leds[i];
            } else {
                minutes += leds[i];
            }
            backtrack(hours, minutes, i + 1, leds, numLedsLeft - 1, times);
            if (isHour(i)) {
                hours -= leds[i];
            } else {
                minutes -= leds[i];
            }
        }
    }

    private boolean isHour(int i) {
        return i >= 0 && i <= 3;
    }
}