import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.peek();

                if (
                        c == ')' && top != '(' ||
                                c == '}' && top != '{' ||
                                c == ']' && top != '['
                ) {
                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}