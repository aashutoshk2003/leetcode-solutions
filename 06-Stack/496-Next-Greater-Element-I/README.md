# 496. Next Greater Element I

**Difficulty:** Easy  
**Topic:** Stack  
**Pattern:** Monotonic Stack + HashMap

---

## Problem Statement

You are given two arrays of integers:

- `nums1`
- `nums2`

`nums1` is a subset of `nums2`.

For every element in `nums1`, find the **next greater element** of that element in `nums2`.

The next greater element of an element `x` is the first element to the right of `x` that is greater than `x`.

If no greater element exists, return `-1`.

---

## Example

```text
Input:
nums1 = [4, 1, 2]
nums2 = [1, 3, 4, 2]

Output:
[-1, 3, -1]
```

Explanation:

- For `4`, there is no greater element to its right → `-1`
- For `1`, the next greater element is `3`
- For `2`, there is no greater element to its right → `-1`

Therefore:

```text
[-1, 3, -1]
```

---

## Approach

We use two data structures:

1. **Stack** → to efficiently find the next greater element.
2. **HashMap** → to store the next greater element for every number in `nums2`.

The important idea is to process `nums2` from **left to right**.

We maintain a stack containing elements for which we have **not found a greater element yet**.

Whenever the current number is greater than the element at the top of the stack, we have found the next greater element for that stack element.

---

## Why Do We Need a Stack?

Suppose:

```text
nums2 = [1, 3, 4, 2]
```

Start with an empty stack.

### Process `1`

```text
stack = [1]
```

We do not know the next greater element of `1` yet.

---

### Process `3`

Current number:

```text
3
```

Stack top:

```text
1
```

Since:

```text
3 > 1
```

we found the next greater element of `1`.

So:

```text
map[1] = 3
```

Then remove `1` from the stack.

Push `3`:

```text
stack = [3]
```

---

### Process `4`

Current number:

```text
4
```

Stack top:

```text
3
```

Since:

```text
4 > 3
```

we found the next greater element of `3`.

```text
map[3] = 4
```

Remove `3` and push `4`:

```text
stack = [4]
```

---

### Process `2`

Current number:

```text
2
```

Stack top:

```text
4
```

Since:

```text
2 < 4
```

we cannot determine the next greater element of `4`.

So we simply push `2`:

```text
stack = [4, 2]
```

---

## Remaining Elements

After processing all elements of `nums2`, some elements may still be inside the stack.

For example:

```text
stack = [4, 2]
```

There is no element to their right that is greater than them.

Therefore:

```text
map[4] = -1
map[2] = -1
```

Now the map contains:

```text
1 → 3
3 → 4
4 → -1
2 → -1
```

---

## Algorithm

### Step 1: Create a Stack and HashMap

```java
Stack<Integer> stack = new Stack<>();
Map<Integer, Integer> map = new HashMap<>();
```

The stack stores elements whose next greater element has not been found yet.

The map stores:

```text
number → next greater element
```

---

### Step 2: Traverse `nums2`

For every number `num` in `nums2`:

```java
for (int num : nums2)
```

Check whether the current number is greater than the stack's top.

```java
while (!stack.isEmpty() && num > stack.peek())
```

If it is greater:

1. Remove the smaller element.
2. Store the current number as its next greater element.

```java
int smaller = stack.pop();
map.put(smaller, num);
```

Continue doing this while the current number is greater than the stack top.

Finally, push the current number:

```java
stack.push(num);
```

---

### Step 3: Handle Remaining Stack Elements

After processing all of `nums2`, any elements remaining in the stack do not have a greater element to their right.

Therefore:

```java
while (!stack.isEmpty()) {
    map.put(stack.pop(), -1);
}
```

---

### Step 4: Build the Result

Now we already know the next greater element for every number in `nums2`.

We only need the answers for elements in `nums1`.

For every element:

```java
result[i] = map.get(nums1[i]);
```

Finally, return `result`.

---

## Step-by-Step Walkthrough

Consider:

```text
nums1 = [4, 1, 2]
nums2 = [1, 3, 4, 2]
```

### Start

```text
stack = []
map = {}
```

### Read `1`

```text
stack = [1]
map = {}
```

### Read `3`

`3 > 1`

```text
map[1] = 3
stack = [3]
```

### Read `4`

`4 > 3`

```text
map[3] = 4
stack = [4]
```

### Read `2`

`2 < 4`

```text
stack = [4, 2]
```

No greater element has been found for `4` or `2`.

### Finish

Remaining elements:

```text
4
2
```

Therefore:

```text
map[4] = -1
map[2] = -1
```

Final map:

```text
1 → 3
3 → 4
4 → -1
2 → -1
```

Now process `nums1`:

```text
nums1 = [4, 1, 2]
```

Look up each value:

```text
4 → -1
1 → 3
2 → -1
```

Final result:

```text
[-1, 3, -1]
```

---

## Why Is This a Monotonic Stack?

The stack maintains elements in a decreasing order from bottom to top.

For example:

```text
[4, 2]
```

The elements are decreasing as we move toward the top.

When a larger number arrives:

```text
5
```

we remove all smaller elements:

```text
[4, 2]
   ↓
5 > 2 → remove 2
5 > 4 → remove 4
```

Then:

```text
stack = [5]
```

This is the key idea behind the **Monotonic Stack** pattern.

---

## Important Observation

The `while` loop may look like it makes the solution `O(n²)` because there is a loop inside another loop.

But it is actually **O(n)**.

Why?

Every element is:

- pushed into the stack exactly once
- popped from the stack at most once

Therefore, across the entire algorithm, the total number of stack operations is proportional to `n`.

So:

```text
Push operations = O(n)
Pop operations  = O(n)
```

Therefore the total time complexity is:

```text
O(n)
```

---

## Complexity Analysis

Let:

- `n = nums2.length`
- `m = nums1.length`

### Time Complexity

Processing `nums2`:

```text
O(n)
```

Processing `nums1`:

```text
O(m)
```

Therefore:

```text
O(n + m)
```

### Space Complexity

The stack can contain up to `n` elements:

```text
O(n)
```

The HashMap can also contain up to `n` elements:

```text
O(n)
```

Therefore:

```text
O(n)
```

---

## Key Learning

The main lesson from this problem is the **Monotonic Stack pattern**.

When a problem asks for something like:

- next greater element
- next smaller element
- previous greater element
- previous smaller element

a **Monotonic Stack** should immediately come to mind.

Instead of repeatedly searching to the right for a greater element, we process the array once and use the stack to remember elements that are still waiting for their answer.

---

## Pattern

```text
Array
  ↓
Need next greater/smaller element
  ↓
Monotonic Stack
  ↓
Process elements once
  ↓
Use HashMap if answers need to be looked up later
```

---

## Important Points

- Process `nums2`, because it contains all the elements.
- Use a stack to keep elements waiting for their next greater element.
- When `num > stack.peek()`, the current `num` is the answer for the stack's top.
- Use a `while` loop because one current element can be the next greater element for multiple previous elements.
- Remaining stack elements get `-1`.
- Use a `HashMap` to store the calculated answers.
- Finally, use `nums1` to build the result.
- Every element is pushed once and popped at most once.
- Overall time complexity is **O(n + m)**.
- This is a classic **Monotonic Stack** problem.

---

## Java Solution

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums2) {

            while (!stack.isEmpty() && num > stack.peek()) {
                int smaller = stack.pop();
                map.put(smaller, num);
            }

            stack.push(num);
        }

        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }
}
```

---

## 📌 LeetCode

**Problem:** Next Greater Element I

**LeetCode Number:** 496

**Difficulty:** Easy

**Topics:** Array, Hash Table, Stack, Monotonic Stack

[LeetCode Problem #496 - Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/)

---