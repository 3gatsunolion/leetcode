class Solution:
    def answerString(self, word: str, numFriends: int) -> str:
        # Find lexicographically largest substring where
        # len of substring is no larger than len(word) - numFriends + 1
        # Longer substring will result in larger lexicograph if same
        # prefix
        n = len(word)
        m = n - numFriends + 1
        if numFriends == 1:
            return word
        
        return max(word[i:i+m] for i in range(n))