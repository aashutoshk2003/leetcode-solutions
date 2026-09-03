# 155. Min Stack

**Difficulty:** Medium  
**Topic:** Stack  
**Pattern:** Two Stacks

---

## Problem

Design a stack data structure that supports the following operations in **constant time O(1)**:

- `push(value)` — Push an element onto the stack.
- `pop()` — Remove the element on top of the stack.
- `top()` — Get the element on top of the stack.
- `getMin()` — Retrieve the minimum element in the stack.

All operations must work in **O(1) time**.

---

## Approach

We use **two stacks**:

1. `mainStack` → stores all the values normally.
2. `minStack` → stores only the values that are currently minimum.

The main idea is:

> Whenever we push a value that is smaller than or equal to the current minimum, we also push it into `minStack`.

This allows `minStack.peek()` to always give us the current minimum element in **O(1)** time.

---

## Main Idea

Normally, if we only use one stack, finding the minimum element would require checking all elements.

For example:

```text
mainStack = [5, 3, 7, 2]
```

The minimum is `2`.

If we had to search for the minimum every time `getMin()` was called, it would take **O(n)** time.

Instead, we maintain another stack:

```text
mainStack = [5, 3, 7, 2]
minStack  = [5, 3, 2]
```

The top of `minStack` is always the current minimum.

Therefore:

```text
getMin() → minStack.peek()
```

takes **O(1)** time.

---

## Algorithm

### `push(value)`

1. Push `value` into `mainStack`.
2. Check whether `minStack` is empty.
3. If it is empty, push `value` into `minStack`.
4. Otherwise, compare `value` with the current minimum:
    - If `value <= minStack.peek()`, push it into `minStack`.
    - Otherwise, do nothing.

---

### `pop()`

1. Remove the top value from `mainStack`.
2. Store the removed value.
3. Compare the removed value with `minStack.peek()`.
4. If they are equal, remove the top value from `minStack` as well.

This keeps both stacks synchronized with respect to the current minimum.

---

### `top()`

Simply return:

```java
mainStack.peek()
```

---

### `getMin()`

Simply return:

```java
minStack.peek()
```

Because `minStack` always keeps the current minimum at its top.

---

## Example Walkthrough

Consider these operations:

```text
push(5)
push(3)
push(7)
push(2)
getMin()
pop()
getMin()
```

### Step 1: `push(5)`

```text
mainStack = [5]
minStack  = [5]
```

`minStack` is empty, so `5` becomes the minimum.

---

### Step 2: `push(3)`

```text
mainStack = [5, 3]
minStack  = [5, 3]
```

`3 <= 5`, so `3` is also pushed into `minStack`.

Current minimum:

```text
3
```

---

### Step 3: `push(7)`

```text
mainStack = [5, 3, 7]
minStack  = [5, 3]
```

`7 > 3`, so we do not push `7` into `minStack`.

Current minimum is still:

```text
3
```

---

### Step 4: `push(2)`

```text
mainStack = [5, 3, 7, 2]
minStack  = [5, 3, 2]
```

`2 <= 3`, so `2` is pushed into `minStack`.

Current minimum:

```text
2
```

---

### Step 5: `getMin()`

We simply look at:

```text
minStack.peek()
```

So:

```text
getMin() = 2
```

---

### Step 6: `pop()`

The top of `mainStack` is `2`.

After removing it:

```text
mainStack = [5, 3, 7]
```

Since the removed value `2` is equal to `minStack.peek()`:

```text
minStack = [5, 3]
```

Now the current minimum becomes:

```text
3
```

---

### Step 7: `getMin()`

```text
getMin() = 3
```

So the two stacks correctly maintain the current minimum after every operation.

---

## Handling Duplicate Minimum Values

The condition is:

```java
value <= minStack.peek()
```

The `<=` is important.

Consider:

```text
push(2)
push(2)
push(3)
```

The stacks become:

```text
mainStack = [2, 2, 3]
minStack  = [2, 2]
```

Both `2`s are stored in `minStack`.

Now if we pop one `2`:

```text
mainStack = [2, 2]
minStack  = [2]
```

The minimum is still:

```text
2
```

This is why we must use `<=` instead of only `<`.

---

## Java Solution

```java
import java.util.Stack;

class MinStack {

    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    public MinStack() {
        minStack = new Stack<>();
        mainStack = new Stack<>();
    }

    public void push(int value) {
        mainStack.push(value);

        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    public void pop() {
        int removedValue = mainStack.pop();

        if (removedValue == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
```

---

## Complexity

Let `n` be the number of elements in the stack.

| Operation | Time | Space |
|-----------|------|-------|
| `push()` | O(1) | O(n) |
| `pop()` | O(1) | O(n) |
| `top()` | O(1) | O(n) |
| `getMin()` | O(1) | O(n) |

### Overall

- **Time Complexity:** O(1) for every operation
- **Space Complexity:** O(n)

The extra space is used by `minStack`.

---

## Key Learning

The main lesson from this problem is:

> A second data structure can maintain additional information so that an expensive operation becomes O(1).

Here:

```text
mainStack → maintains actual stack values
minStack  → maintains minimum values
```

Instead of searching for the minimum every time, we maintain the minimum while performing `push()` and `pop()`.

This is a very important **Stack pattern** for interviews.

---

## Pattern Connection

This problem builds directly on the basic Stack concept.

Previously, we used a stack to solve problems where we needed:

```text
LIFO
Last In → First Out
```

In this problem, we go one step further.

We use the stack not only to store data but also to maintain additional information about the data.

This idea appears frequently in advanced stack problems.

---

## Important Points

- Use two stacks.
- `mainStack` stores every value.
- `minStack` stores values that can become the minimum.
- `minStack.peek()` always gives the current minimum.
- Use `<=` while pushing into `minStack` to correctly handle duplicate minimum values.
- When popping, remove from `minStack` only when the removed value equals its top.
- Every operation runs in O(1).
- Extra space is O(n).

---

## 📌 LeetCode

**Problem:** Min Stack

**LeetCode Number:** 155

**Difficulty:** Medium

**Topics:** Stack, Design

[LeetCode Problem #155 - Min Stack](https://leetcode.com/problems/min-stack/)

---