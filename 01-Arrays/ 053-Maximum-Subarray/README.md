# LeetCode #53 - Maximum Subarray

**Difficulty:** Medium

**Topic:** Array

**Pattern:** Kadane's Algorithm

---

## 📝 Problem

Given an integer array `nums`, find the subarray with the largest sum and return its sum.

A **subarray** is a contiguous part of the array.

For example:

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

The subarray:

```text
[4, -1, 2, 1]
```

has the maximum sum:

```text
4 + (-1) + 2 + 1 = 6
```

Therefore:

```text
Output = 6
```

---

## 💭 Approach

I use **Kadane's Algorithm**.

The main idea is to maintain two values:

```text
currentSum → maximum subarray sum ending at the current position

maxSum → maximum subarray sum found so far
```

For every element, I have two choices:

1. Start a new subarray from the current element.
2. Add the current element to the existing subarray.

So I calculate:

```text
currentSum = max(nums[i], currentSum + nums[i])
```

This means:

```text
If nums[i] is better than extending the previous subarray,
start a new subarray.

Otherwise,
continue the previous subarray.
```

After calculating `currentSum`, update the overall maximum:

```text
maxSum = Math.max(maxSum, currentSum)
```

At the end, `maxSum` contains the maximum subarray sum.

---

## 🔄 Algorithm

1. Initialize:

```text
currentSum = nums[0]
maxSum = nums[0]
```

2. Traverse the array from index `1`.
3. For every element, calculate:

```text
currentSum = max(nums[i], currentSum + nums[i])
```

4. Update:

```text
maxSum = max(maxSum, currentSum)
```

5. Continue until the entire array is processed.
6. Return `maxSum`.

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

Initially:

```text
currentSum = -2
maxSum = -2
```

### Step 1 — `i = 1`

```text
nums[i] = 1
```

Compare:

```text
1
-2 + 1 = -1
```

Choose the larger value:

```text
currentSum = 1
```

Update:

```text
maxSum = 1
```

---

### Step 2 — `i = 2`

```text
nums[i] = -3
```

Compare:

```text
-3
1 + (-3) = -2
```

Choose:

```text
currentSum = -2
```

`maxSum` remains:

```text
1
```

---

### Step 3 — `i = 3`

```text
nums[i] = 4
```

Compare:

```text
4
-2 + 4 = 2
```

Choose:

```text
currentSum = 4
```

Update:

```text
maxSum = 4
```

---

### Step 4 — `i = 4`

```text
nums[i] = -1
```

Compare:

```text
-1
4 + (-1) = 3
```

Choose:

```text
currentSum = 3
```

`maxSum` remains:

```text
4
```

---

### Step 5 — `i = 5`

```text
nums[i] = 2
```

Calculate:

```text
2
3 + 2 = 5
```

Choose:

```text
currentSum = 5
```

Update:

```text
maxSum = 5
```

---

### Step 6 — `i = 6`

```text
nums[i] = 1
```

Calculate:

```text
1
5 + 1 = 6
```

Choose:

```text
currentSum = 6
```

Update:

```text
maxSum = 6
```

---

### Step 7 — `i = 7`

```text
nums[i] = -5
```

Calculate:

```text
-5
6 + (-5) = 1
```

Choose:

```text
currentSum = 1
```

`maxSum` remains:

```text
6
```

---

### Step 8 — `i = 8`

```text
nums[i] = 4
```

Calculate:

```text
4
1 + 4 = 5
```

Choose:

```text
currentSum = 5
```

`maxSum` remains:

```text
6
```

Final:

```text
maxSum = 6
```

Therefore:

```text
Output = 6
```

The maximum subarray is:

```text
[4, -1, 2, 1]
```

---

## 🧠 Why Do We Choose `Math.max(nums[i], currentSum + nums[i])`?

This is the most important part of Kadane's Algorithm.

Suppose:

```text
currentSum = -5
nums[i] = 4
```

We have two choices:

### Continue the previous subarray

```text
-5 + 4 = -1
```

### Start a new subarray

```text
4
```

Clearly:

```text
4 > -1
```

So we should discard the previous negative sum and start from `4`.

That's exactly what this does:

```java
currentSum = Math.max(nums[i], currentSum + nums[i]);
```

In simple words:

> **If the previous subarray hurts the sum, start fresh. Otherwise, continue it.**

---

## 💻 Java Solution

```java
class Solution {
    public int maxSubArray(int[] nums) {

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {

            currentSum = Math.max(
                nums[i],
                currentSum + nums[i]
            );

            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

The array is traversed exactly once.

### Space Complexity

```text
O(1)
```

Only two variables are used:

```text
currentSum
maxSum
```

No additional array or data structure is required.

---

## 💡 Key Learning

- **Kadane's Algorithm** finds the maximum sum of a contiguous subarray in `O(n)` time.
- At every element, decide whether to:
    - Continue the current subarray.
    - Start a new subarray.
- `currentSum` represents the best sum ending at the current index.
- `maxSum` represents the best sum found anywhere so far.
- A negative running sum can be harmful to future elements, so it may be better to start a new subarray.
- This is an important example of maintaining a small amount of state while traversing an array.

---

## 🔗 Important Pattern

The core formula is:

```text
currentSum = max(current element,
                 currentSum + current element)
```

Then:

```text
maxSum = max(maxSum, currentSum)
```

Think of it as:

```text
Can I make the current subarray better by extending it?

YES → continue it
NO  → start fresh
```

---

## ⚠️ Important Points

- The subarray must be **contiguous**.
- We are finding the **maximum sum**, not the maximum number of elements.
- `currentSum` should represent the best subarray ending at the current index.
- `maxSum` should represent the best answer found so far.
- Initialize both values with `nums[0]` instead of `0`.
- This is important when all elements are negative.

For example:

```text
nums = [-5, -2, -8]
```

The answer is:

```text
-2
```

not:

```text
0
```

Therefore, initializing with the first element correctly handles all-negative arrays.

---

## 📌 LeetCode

**Problem:** Maximum Subarray

**LeetCode Number:** 53

**Difficulty:** Medium

**Topics:** Array, Kadane's Algorithm

[LeetCode Problem #53 - Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)

---