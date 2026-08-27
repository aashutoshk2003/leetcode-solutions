# LeetCode #643 - Maximum Average Subarray I

**Difficulty:** Easy

**Topic:** Sliding Window

**Pattern:** Fixed-Size Sliding Window

---

## 📝 Problem

Given an integer array `nums` consisting of `n` elements and an integer `k`, find the contiguous subarray whose length is equal to `k` and has the maximum average value.

Return the maximum average value.

An answer with an error less than `10^-5` from the actual answer is accepted.

---

## 💭 Approach

I use the **Sliding Window** technique.

The problem asks us to find the maximum average of a subarray with a **fixed size `k`**.

Since every subarray has the same length `k`, maximizing the average is equivalent to maximizing the sum.

Because:

```text
average = sum / k
```

and `k` is constant, the window with the largest sum will also have the largest average.

Instead of calculating the sum of every window from scratch, I maintain the sum of the current window.

When the window moves one position to the right:

1. Remove the element leaving the window.
2. Add the new element entering the window.

So:

```text
sum = sum - nums[left]
```

then:

```text
sum = sum + nums[right]
```

This allows each new window to be calculated in `O(1)` time.

---

## 🔑 Main Idea

The window always contains exactly `k` elements.

For example:

```text
nums = [1, 12, -5, -6, 50, 3]
k = 4
```

First window:

```text
[1, 12, -5, -6]
```

Then move the window:

```text
[12, -5, -6, 50]
```

Then:

```text
[-5, -6, 50, 3]
```

Instead of calculating each sum again, we remove the element leaving the window and add the new element entering it.

---

## 🔄 Algorithm

1. Calculate the sum of the first `k` elements.
2. Store this sum as `maxSum`.
3. Initialize:

```text
left = 0
right = k - 1
```

4. Move the window while `right` is not at the last index.
5. Remove the element at `left`:

```text
sum -= nums[left]
```

6. Move `left` forward:

```text
left++
```

7. Move `right` forward:

```text
right++
```

8. Add the new element:

```text
sum += nums[right]
```

9. Update the maximum sum:

```text
maxSum = Math.max(maxSum, sum)
```

10. Continue until all possible windows have been checked.
11. Return:

```text
maxSum / k
```

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [1, 12, -5, -6, 50, 3]
k = 4
```

### Step 1 — First Window

Take the first `4` elements:

```text
[1, 12, -5, -6]
```

Calculate:

```text
sum = 1 + 12 - 5 - 6
sum = 2
```

So:

```text
maxSum = 2
```

Window:

```text
left  = 0
right = 3
```

---

### Step 2 — Move Window

Current window:

```text
[1, 12, -5, -6]
```

The element leaving the window is:

```text
1
```

Remove it:

```text
sum = 2 - 1
sum = 1
```

Move `left`:

```text
left = 1
```

Move `right`:

```text
right = 4
```

New element entering the window:

```text
50
```

Add it:

```text
sum = 1 + 50
sum = 51
```

New window:

```text
[12, -5, -6, 50]
```

Update:

```text
maxSum = 51
```

---

### Step 3 — Move Window Again

Current window:

```text
[12, -5, -6, 50]
```

Remove:

```text
12
```

```text
sum = 51 - 12
sum = 39
```

Move pointers:

```text
left = 2
right = 5
```

Add the new element:

```text
3
```

```text
sum = 39 + 3
sum = 42
```

New window:

```text
[-5, -6, 50, 3]
```

`maxSum` remains:

```text
51
```

---

### Final Answer

The maximum window sum is:

```text
51
```

The window size is:

```text
k = 4
```

Therefore:

```text
maximum average = 51 / 4
                = 12.75
```

Final output:

```text
12.75000
```

---

## 🧠 Why Can We Compare Sums Instead of Averages?

This is an important observation.

Every window contains exactly `k` elements.

The average is:

```text
average = sum / k
```

Since `k` never changes, the window with the larger sum will always have the larger average.

For example:

```text
Window A:
sum = 20
k = 4

average = 20 / 4 = 5
```

```text
Window B:
sum = 30
k = 4

average = 30 / 4 = 7.5
```

Because the window size is fixed:

```text
larger sum → larger average
```

Therefore, we only need to track the maximum sum.

---

## 💻 Java Solution

```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;

        int left = 0;
        int right = k - 1;

        while (right < nums.length - 1) {
            sum -= nums[left];
            left++;

            right++;
            sum += nums[right];

            maxSum = Math.max(maxSum, sum);
        }

        return (double) maxSum / k;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

The first `k` elements are processed once.

After that, each new window removes one element and adds one element.

Therefore, every element is processed a constant number of times.

Overall:

```text
O(n)
```

### Space Complexity

```text
O(1)
```

Only a few variables are used.

No additional data structure is required.

---

## 💡 Key Learning

- A **Sliding Window** is useful when working with contiguous subarrays or substrings.
- When the window size is fixed, we can maintain the current window instead of recalculating it.
- Remove the element leaving the window:

```text
sum -= nums[left]
```

- Add the element entering the window:

```text
sum += nums[right]
```

- Since `k` is fixed:

```text
maximum sum = maximum average
```

for the purpose of finding the best window.
- This reduces the solution from repeatedly calculating window sums to `O(n)` time.

---

## 🔗 Connection With Two Pointers

Sliding Window is closely related to the Two Pointer technique.

Both use pointers such as:

```text
left
right
```

But the goal is different.

### Two Pointers

Usually the pointers move based on a condition.

Example:

```text
sum < target → left++
sum > target → right--
```

### Sliding Window

The pointers represent a **window** over a contiguous section of the array.

Here the window has a fixed size:

```text
right - left + 1 = k
```

When the window moves:

```text
remove left element
        ↓
move window
        ↓
add new right element
```

This problem is our introduction to the **Sliding Window** pattern.

---

## ⚠️ Important Points

- The subarray must be contiguous.
- The window size is always exactly `k`.
- Calculate the first window before starting the sliding process.
- Remove the element leaving the window.
- Add the new element entering the window.
- Keep track of the maximum window sum.
- Convert the final sum to `double` before division:

```java
(double) maxSum / k
```

This ensures decimal division instead of integer division.
- Negative numbers are allowed.

---

## 📌 LeetCode

**Problem:** Maximum Average Subarray I

**LeetCode Number:** 643

**Difficulty:** Easy

**Topics:** Array, Sliding Window

[LeetCode Problem #643 - Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/)

---