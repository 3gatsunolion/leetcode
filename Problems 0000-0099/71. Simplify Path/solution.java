class Solution {
    public String simplifyPath(String path) {
        String[] parts = path.split("/");

        Stack<String> stack = new Stack();
        for (String name : parts) {
            if (name.isEmpty() || name.equals(".")) continue;
            if (name.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(name);
            }
        }

        if (stack.isEmpty()) return "/";

        StringBuilder sb = new StringBuilder();

        for (String name : stack) {
            sb.append("/");
            sb.append(name);
        }
        return sb.toString();
    }
}