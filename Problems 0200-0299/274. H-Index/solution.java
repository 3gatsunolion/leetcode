class Solution {
    public int hIndex(int[] citations) {
        // Observation: h-index is capped at citations.length
        int numPapers = citations.length;
        
        int[] buckets = new int[numPapers + 1];
        for (int numCitations : citations) {
            if (numCitations > numPapers) numCitations = numPapers;
            buckets[numCitations]++;
        }

        int count = 0;
        for (int h = numPapers; h >= 0; h--) {
            count += buckets[h];

            if (count >= h) return h;
        }

        return -1;
    }
}