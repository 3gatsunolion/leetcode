class Solution:
    def compressedString(self, word: str) -> str:
        if not word:
            return ""
        comp = []
        prev = ""
        same = 0
        for i, c in enumerate(word):
            if i == 0 or (prev == c and same < 9):
                same += 1
            
            else:
                comp.append(f"{same}{prev}")
                same = 1
            prev = c

        comp.append(f"{same}{prev}")

        return "".join(comp)