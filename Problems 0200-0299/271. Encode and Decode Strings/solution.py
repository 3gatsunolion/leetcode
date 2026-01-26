class Solution:

    def encode(self, strs: List[str]) -> str:
        # Doesn't matter if # appears in any of the strings
        # because we know the format ALWAYS starts with number
        # which we can tell when it ends by finding first #
        # which tells us how many characters to read next
        return "".join([f"{len(s)}#{s}" for s in strs])

    def decode(self, s: str) -> List[str]:
        n = len(s)
        i = 0

        res = []
        while i < n:
            # Read until first #
            strLen = 0
            while i < n and s[i] != '#':
                strLen = strLen * 10 + int(s[i])
                i += 1
            
            i += 1

            word = s[i:i + strLen]
            res.append(word)
            i = i + strLen
        
        return res
