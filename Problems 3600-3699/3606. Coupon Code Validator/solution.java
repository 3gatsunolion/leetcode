class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<String[]> valid = new ArrayList<>();

        for (int i = 0; i < code.length; i++) {
            if (!isActive[i]) continue;
            if (!code[i].matches("^[a-zA-Z0-9_]+$")) continue;
            if (!businessLine[i].equals("electronics") &&
                !businessLine[i].equals("grocery") &&
                !businessLine[i].equals("pharmacy") &&
                !businessLine[i].equals("restaurant")) continue;
            valid.add(new String[] {businessLine[i], code[i]});
        }

        valid.sort((a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }

            return a[0].compareTo(b[0]);
        });

        List<String> res = new ArrayList<>();
        for (String[] coupon : valid) {
            res.add(coupon[1]);
        }

        return res;
    }
}