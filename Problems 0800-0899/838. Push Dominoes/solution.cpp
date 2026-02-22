class Solution {
    public:
        string pushDominoes(string dominoes) {
            dominoes = 'L' + dominoes + 'R';
            int lastPush = 0;
            string res = "";
            for (int i = 1; i < dominoes.length(); i++) {
                if (dominoes[i] == '.') continue;
    
                if (lastPush > 0) {
                    res += dominoes[lastPush];
                }
    
                int numBetween = i - lastPush - 1;
                if (dominoes[i] == dominoes[lastPush]) {
                    res += string(numBetween, dominoes[i]);
                } else if (dominoes[i] == 'R' && dominoes[lastPush] == 'L') {
                    res += string(numBetween, '.');
                } else {
                    res += string(numBetween/2, 'R');
                    res += string(numBetween % 2, '.');
                    res += string(numBetween/2, 'L');
                }
    
                lastPush = i;
            }
            return res;
        }
    };