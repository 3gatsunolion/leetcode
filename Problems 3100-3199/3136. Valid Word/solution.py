class Solution:
    def isValid(self, word: str) -> bool:
        if len(word) < 3:
            return False
        
        vowels = 0
        consonants = 0

        for c in word:
            if c.isalpha():
                if c.lower() in 'aeiou':
                    vowels += 1
                else:
                    consonants += 1
            elif not c.isdigit():
                return False
        
        if vowels and consonants:
            return True
        return False