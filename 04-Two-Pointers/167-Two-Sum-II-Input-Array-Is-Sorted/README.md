# LeetCode #167 - Two Sum II - Input Array Is Sorted

**Difficulty:** Medium

**Topic:** Two Pointers

**Pattern:** Two Pointers on Sorted Array

---

## 📝 Problem

Given a **1-indexed** array of integers `numbers` that is already sorted in non-decreasing order, find two numbers such that they add up to a specific `target`.

Return the indices of the two numbers.

The problem guarantees that exactly one solution exists.

The returned indices must be **1-based**.

---

## 💭 Approach

I use the **Two Pointer** technique.

Because the array is already sorted, I can use two pointers:

```text
left  → beginning of the array
right → end of the array
```

At every step, calculate:

```text
sum = nums[left] + nums[right]
```

Then there are three possible cases.

### Case 1 — Sum equals target

```text
sum == target
```

We found the required pair.

Return:

```text
[left + 1, right + 1]
```

because the problem uses **1-based indexing**.

---

### Case 2 — Sum is smaller than target

```text
sum < target
```

We need a larger sum.

Because the array is sorted, moving `left` to the right gives us a larger value.

Therefore:

```text
left++
```

---

### Case 3 — Sum is greater than target

```text
sum > target
```

We need a smaller sum.

Because the array is sorted, moving `right` to the left gives us a smaller value.

Therefore:

```text
right--
```

---

## 🔄 Algorithm

1. Set:

```text
left = 0
right = nums.length - 1
```

2. Continue while:

```text
left < right
```

3. Calculate:

```text
sum = nums[left] + nums[right]
```

4. If:

```text
sum == target
```

return:

```text
[left + 1, right + 1]
```

5. If:

```text
sum < target
```

move the left pointer:

```text
left++
```

6. If:

```text
sum > target
```

move the right pointer:

```text
right--
```

7. Continue until the pair is found.
8. Return the result.

---

## 🔍 Example Walkthrough

Consider:

```text
numbers = [2, 7, 11, 15]
target = 9
```

Initially:

```text
left = 0
right = 3
```

So:

```text
numbers[left]  = 2
numbers[right] = 15
```

Calculate:

```text
2 + 15 = 17
```

Since:

```text
17 > 9
```

we need a smaller sum.

Move `right`:

```text
right--
```

Now:

```text
left = 0
right = 2
```

---

### Step 2

Values:

```text
2 + 11 = 13
```

Again:

```text
13 > 9
```

Move `right`:

```text
right--
```

Now:

```text
left = 0
right = 1
```

---

### Step 3

Values:

```text
2 + 7 = 9
```

Now:

```text
sum == target
```

We found the answer.

The current indices are:

```text
left = 0
right = 1
```

But the problem requires **1-based indices**.

Therefore:

```text
left + 1  = 1
right + 1 = 2
```

Final output:

```text
[1, 2]
```

---

## 🧠 Why Does the Two Pointer Approach Work?

The important reason is that the array is **sorted**.

Suppose we have:

```text
[2, 7, 11, 15]
 ↑           ↑
left       right
```

If:

```text
nums[left] + nums[right] > target
```

we know the sum is too large.

Since `right` points to the largest current value, moving `left` would only make the sum larger.

Therefore, we move:

```text
right--
```

Similarly, if:

```text
nums[left] + nums[right] < target
```

the sum is too small.

Moving `right` left would make the sum smaller, which is not useful.

So we move:

```text
left++
```

The sorted order allows us to eliminate unnecessary possibilities.

---

## 💻 Java Solution

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            }
        }

        return new int[]{};
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

The `left` pointer only moves forward and the `right` pointer only moves backward.

Each element is considered at most a limited number of times.

Therefore:

```text
O(n)
```

### Space Complexity

```text
O(1)
```

Only two pointers and a few variables are used.

No additional data structure is required.

---

## 💡 Key Learning

The most important idea is:

> **When an array is sorted, consider whether Two Pointers can eliminate unnecessary comparisons.**

The decision rules are:

```text
sum == target → answer found

sum < target → left++

sum > target → right--
```

The sorted order tells us which pointer to move.

---

## 🔗 Connection With Previous Two Sum

This problem is especially important because it is a variation of **LeetCode #1 - Two Sum**.

### LeetCode #1 - Two Sum

The array is not necessarily sorted.

We used:

```text
HashMap
```

Pattern:

```text
number → index
```

Time:

```text
O(n)
```

---

### LeetCode #167 - Two Sum II

The array is already sorted.

We can use:

```text
Two Pointers
```

Pattern:

```text
left → beginning
right → end
```

Time:

```text
O(n)
```

---

### Main Difference

```text
Unsorted array
      ↓
HashMap

Sorted array
      ↓
Two Pointers
```

This is an important pattern-recognition skill.

---

## ⚠️ Important Points

- The input array is already sorted in non-decreasing order.
- The array is **1-indexed** according to the problem.
- Java arrays are **0-indexed**, so return:

```java
left + 1
right + 1
```

- If the sum is too small, move `left`.
- If the sum is too large, move `right`.
- Do not use an additional HashMap because the sorted property allows the Two Pointer approach.
- The problem guarantees exactly one solution.

---

## 📌 LeetCode

**Problem:** Two Sum II - Input Array Is Sorted

**LeetCode Number:** 167

**Difficulty:** Medium

**Topics:** Array, Two Pointers

[LeetCode Problem #167 - Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

---