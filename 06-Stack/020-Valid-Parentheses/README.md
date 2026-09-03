# LeetCode #20 - Valid Parentheses

**Difficulty:** Easy

**Topic:** Stack

**Pattern:** Stack + Matching Pairs

---

## 📝 Problem

Given a string `s` containing only the characters:

```text
'('  ')'  '{'  '}'  '['  ']'
```

determine if the input string is valid.

A string is valid if:

1. Every opening bracket is closed by the same type of bracket.
2. Opening brackets are closed in the correct order.
3. Every closing bracket has a corresponding opening bracket.

---

## 💭 Approach

I use a **Stack** to keep track of opening brackets.

The main idea is:

> When I see an opening bracket, I store it in the stack. When I see a closing bracket, it must match the most recently opened bracket.

A Stack follows:

```text
LIFO
Last In, First Out
```

This is exactly what we need because the **most recently opened bracket must be closed first**.

For example:

```text
({[]})
```

The opening brackets are added:

```text
(
(
{
[
```

The closing brackets must then match them in reverse order:

```text
] → [
} → {
) → (
```

The Stack naturally handles this using `peek()` and `pop()`.

---

## 🔑 Main Idea

There are two types of characters.

### Opening Brackets

```text
(
[
{
```

When an opening bracket is found:

```java
stack.push(c);
```

we add it to the stack.

---

### Closing Brackets

```text
)
]
}
```

When a closing bracket is found:

1. Check whether the stack is empty.
2. Look at the top opening bracket.
3. Check whether it matches the closing bracket.
4. If it does not match, return `false`.
5. If it matches, remove it using `pop()`.

---

## 🔄 Algorithm

1. Create an empty Stack of characters.
2. Traverse every character in the string.
3. If the character is an opening bracket:
    - Push it into the stack.
4. Otherwise, it is a closing bracket.
5. If the stack is empty:
    - There is no opening bracket to match it.
    - Return `false`.
6. Get the top opening bracket using:

```text
stack.peek()
```

7. Check whether the top bracket matches the current closing bracket.
8. If it does not match:
    - Return `false`.
9. If it matches:
    - Remove the opening bracket using:

```text
stack.pop()
```

10. Continue processing the string.
11. After processing all characters, check whether the stack is empty.
12. If the stack is empty, return `true`.
13. Otherwise, return `false`.

---

## 🔍 Example Walkthrough

Consider:

```text
s = "()[]{}"
```

Initially:

```text
stack = []
```

### Step 1 — `'('`

Opening bracket.

Push:

```text
stack = [(]
```

---

### Step 2 — `')'`

Closing bracket.

Top:

```text
(
```

It matches:

```text
( → )
```

Pop:

```text
stack = []
```

---

### Step 3 — `'['`

Opening bracket.

Push:

```text
stack = [[]
```

---

### Step 4 — `']'`

Top:

```text
[
```

It matches.

Pop:

```text
stack = []
```

---

### Step 5 — `'{'`

Push:

```text
stack = [{]
```

---

### Step 6 — `'}'`

Top:

```text
{
```

It matches.

Pop:

```text
stack = []
```

---

### Final Check

The stack is empty:

```text
stack.isEmpty() → true
```

Therefore:

```text
Output = true
```

---

## 🔍 Example With Nested Brackets

Consider:

```text
s = "({[]})"
```

Process the opening brackets:

```text
(
(
{
[
```

Stack:

```text
[ (, {, [ ]
```

Now process closing brackets.

### `']'`

Top:

```text
[
```

Match → pop.

```text
[ (, { ]
```

### `'}'`

Top:

```text
{
```

Match → pop.

```text
[ ( ]
```

### `')'`

Top:

```text
(
```

Match → pop.

```text
[]
```

The stack is empty.

Therefore:

```text
Output = true
```

---

## ❌ Invalid Example

Consider:

```text
s = "(]"
```

Process:

```text
'(' → push
```

Stack:

```text
[(]
```

Now:

```text
']'
```

Top of stack:

```text
(
```

But:

```text
( ≠ [
```

The brackets do not match.

Therefore:

```text
Output = false
```

---

## ❌ Another Invalid Example

Consider:

```text
s = "([)]"
```

Process:

```text
(
[
```

Stack:

```text
[(, []
```

Now we get:

```text
')'
```

The top of the stack is:

```text
[
```

But `')'` must match `'('`.

They do not match.

Therefore:

```text
Output = false
```

This demonstrates why **order matters**.

---

## 🧠 Why Does Stack Work Here?

The important property of a Stack is:

```text
LIFO
```

which means:

```text
Last In → First Out
```

Suppose we have:

```text
({[
```

The last opening bracket is:

```text
[
```

Therefore, the first closing bracket must be:

```text
]
```

After that:

```text
{
```

must close with:

```text
}
```

And finally:

```text
(
```

must close with:

```text
)
```

This is exactly the behavior provided by a Stack.

---

## 💻 Java Solution

```java
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
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

We traverse the string once.

Each character is pushed onto or popped from the stack at most once.

Therefore:

```text
O(n)
```

### Space Complexity

```text
O(n)
```

In the worst case, the string may contain only opening brackets.

For example:

```text
((((((
```

All characters would be stored in the stack.

Therefore, the maximum stack size can be `n`.

---

## 💡 Key Learning

- A Stack follows **LIFO — Last In, First Out**.
- Stack is useful when the most recent item must be processed first.
- Opening brackets are pushed.
- Closing brackets are matched with the top of the stack.
- `peek()` checks the top element without removing it.
- `pop()` removes the top element.
- `push()` adds an element to the top.
- `isEmpty()` checks whether the stack contains any elements.
- The final stack must be empty for the parentheses to be valid.

---

## 🔗 Important Stack Pattern

The general pattern is:

```text
Opening bracket
      ↓
    PUSH
      ↓
Closing bracket
      ↓
 Check TOP
      ↓
   Matches?
   ↙     ↘
 YES      NO
  ↓        ↓
 POP     false
```

The key question to ask in Stack problems is:

> **Does the current item need to interact with the most recently added item?**

If yes, a Stack may be a good candidate.

---

## 🔗 Connection With Previous Topics

This is a new DSA pattern in the repository.

Previously:

```text
01-Arrays
02-HashSet
03-HashMap
04-Two-Pointers
05-Sliding-Window
```

Now:

```text
06-Stack
```

The main difference is that Stack introduces a new way of managing data:

```text
LIFO
Last In, First Out
```

---

## ⚠️ Important Points

- Every opening bracket must have a matching closing bracket.
- The order of brackets matters.
- Always check `stack.isEmpty()` before using `peek()`.
- `peek()` only looks at the top element.
- `pop()` removes the top element.
- If a closing bracket does not match the top opening bracket, return `false`.
- After processing the entire string, the stack must be empty.
- An input containing only opening brackets is invalid because those brackets were never closed.

---

## 📌 LeetCode

**Problem:** Valid Parentheses

**LeetCode Number:** 20

**Difficulty:** Easy

**Topics:** String, Stack

[LeetCode Problem #20 - Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

---