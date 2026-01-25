class Solution:
    def fullJustify(self, words: List[str], maxWidth: int) -> List[str]:
        row = []
        numCharsUsed = 0

        res = []
        for word in words:
            if numCharsUsed + len(word) + len(row) <= maxWidth:
                row.append(word)
                numCharsUsed += len(word)
            else:
                # Process current row
                res.append(self.justify(row, numCharsUsed, maxWidth))

                # Reset
                row = [word]
                numCharsUsed = len(word)

        if numCharsUsed > 0:
            res.append(self.justify(row, numCharsUsed, maxWidth, True))
        
        return res

    def justify(self, row, numCharsUsed, maxWidth, leftJustify=False):
        spacesLeft = maxWidth - numCharsUsed
        n = len(row)
        if n == 1 or leftJustify:
            return " ".join(row) + " " * (spacesLeft - n + 1)
        
        numSpaces = [spacesLeft // (n - 1)] * (n - 1)
        spacesLeft %= n - 1
        # Round robin to evenly distribute leftover spaces
        for i in range(spacesLeft):
            numSpaces[i] += 1
        
        res = []
        for i in range(n - 1):
            res.append(row[i])
            res.append(" " * numSpaces[i])
        res.append(row[-1])

        return "".join(res)
