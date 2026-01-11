import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        char[] stack = new char[s.length()];
        int index = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack[++index] = ')';
            } else if (ch == '{') {
                stack[++index] = '}';
            } else if (ch == '[') {
                stack[++index] = ']';
            } else if (index == -1 || stack[index--] != ch) {
                return false;
            }
            // if (ch == '(' || ch == '{' || ch == '[') {
            //     stack[++index] = ch;
            // } else {
            //     if (index == -1) {
            //         return false;
            //     }
            //     char open = stack[index--];
            //     if (open == '(' && ch != ')' ||
            //         open == '{' && ch != '}' ||
            //         open == '[' && ch != ']') {
            //             return false;
            //         }
            // }
        }

        return index == -1;

        // Stack<Character> stack = new Stack<>();
        // // Map<Character, Character> match = new HashMap<>(Map.of(
        // //     '(', ')',
        // //     '{', '}',
        // //     '[', ']'
        // // ));

        // for (int i = 0; i < s.length(); i++) {
        //     char ch = s.charAt(i);
        //     if (ch == ')' || ch == '}' || ch == ']') {
        //         // if (stack.isEmpty() || match.get(stack.peek()) != ch) {
        //         //     return false;
        //         // }
        //         // stack.pop();
        //         if (stack.isEmpty()) {
        //             return false;
        //         }
        //         char open = stack.pop();
        //         if (open == '(' && ch != ')' ||
        //             open == '{' && ch != '}' ||
        //             open == '[' && ch != ']') {
        //                 return false;
        //             }
        //     } else {
        //         stack.push(ch);
        //     }
        // }

        // return stack.isEmpty();
    }
}