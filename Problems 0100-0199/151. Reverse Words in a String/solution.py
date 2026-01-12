class Solution:
    def reverseWords(self, s: str) -> str:
        chars = list(s)
        chars.reverse()

        n = len(chars)
        i = 0
        right = 0
        while i < n:
            while i < n and chars[i] == ' ':
                i += 1

            if i == n:
                break

            if right > 0:
                chars[right] = ' '
                right += 1
            
            left = right
            while i < n and chars[i] != ' ':
                chars[right] = chars[i]
                i += 1
                right += 1

            self.reverse(chars, left, right-1)
        
        chars = chars[:right]
        return "".join(chars)
        
    def reverse(self, s, l, r):
        while l < r:
            s[l], s[r] = s[r], s[l]
            l += 1
            r -= 1