class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        boolean negative = (numerator > 0) ^ (denominator > 0);
        long num = Math.abs((long) numerator);
        long denom = Math.abs((long) denominator);

        long quot = num / denom;
        long rem = num % denom;

        StringBuilder sb = new StringBuilder();
        if (negative) {
            sb.append("-");
        }
        sb.append(String.valueOf(quot));

        if (rem == 0) {
            return sb.toString();
        }

        sb.append(".");
        Map<Long, Integer> seen = new HashMap<>();
        long gcd = gcd(num, denom);
        num /= gcd;
        denom /= gcd;
        rem = num % denom;
        seen.put(rem, sb.length());

        while (rem != 0) {
            rem *= 10;

            quot = rem / denom;
            rem = rem % denom;

            if (seen.containsKey(rem)) {
                sb.insert(seen.get(rem), "(");
                sb.append(quot);
                sb.append(")");
                break;
            }
            sb.append(String.valueOf(quot));
            seen.put(rem, sb.length());
        }

        return sb.toString();
    }

    private long gcd(long a, long b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}