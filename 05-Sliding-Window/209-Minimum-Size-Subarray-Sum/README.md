# LeetCode #209 - Minimum Size Subarray Sum

**Difficulty:** Medium

**Topic:** Sliding Window

**Pattern:** Variable-Size Sliding Window

---

## 📝 Problem

Given an array of positive integers `nums` and a positive integer `target`, return the minimal length of a contiguous subarray whose sum is greater than or equal to `target`.

If no such subarray exists, return `0`.

---

## 💭 Approach

I use a **Variable-Size Sliding Window**.

Unlike a fixed-size sliding window, the window size changes depending on whether its sum satisfies the required condition.

The goal is to find the **smallest window** whose sum is:

```text
sum >= target
```

I maintain two pointers:

```text
left
right
```

The `right` pointer expands the window by adding new elements.

Whenever:

```text
sum >= target
```

the current window is valid.

At this point, I try to make the window smaller by moving `left` forward.

This continues until:

```text
sum < target
```

Then I expand the window again using `right`.

The important pattern is:

```text
Expand → condition satisfied → Shrink → update answer
```

---

## 🔑 Main Idea

The window represents:

```text
nums[left ... right]
```

For every new `right`:

```text
sum += nums[right]
```

Once:

```text
sum >= target
```

the current window is valid.

Now I try to shrink it:

```text
sum -= nums[left]
left++
```

Before removing the left element, I calculate the current window length:

```text
right - left + 1
```

and update the minimum length.

This allows us to find the smallest valid window.

---

## 🔄 Algorithm

1. Initialize:

```text
left = 0
sum = 0
minLength = Integer.MAX_VALUE
```

2. Move `right` from `0` to the end of the array.
3. Add the current element:

```text
sum += nums[right]
```

4. While:

```text
sum >= target
```

do the following:

5. Calculate the current window length:

```text
currentLength = right - left + 1
```

6. Update the minimum:

```text
minLength = Math.min(minLength, currentLength)
```

7. Remove the leftmost element:

```text
sum -= nums[left]
```

8. Move `left` forward:

```text
left++
```

9. Continue shrinking until:

```text
sum < target
```

10. Continue expanding with `right`.
11. If no valid window was found, return `0`.
12. Otherwise, return `minLength`.

---

## 🔍 Example Walkthrough

Consider:

```text
target = 7
nums = [2, 3, 1, 2, 4, 3]
```

Initially:

```text
left = 0
sum = 0
minLength = ∞
```

---

### Step 1 — `right = 0`

Add:

```text
2
```

Window:

```text
[2]
```

Sum:

```text
2
```

Since:

```text
2 < 7
```

continue expanding.

---

### Step 2 — `right = 1`

Add:

```text
3
```

Window:

```text
[2, 3]
```

Sum:

```text
5
```

Since:

```text
5 < 7
```

continue expanding.

---

### Step 3 — `right = 2`

Add:

```text
1
```

Window:

```text
[2, 3, 1]
```

Sum:

```text
6
```

Still:

```text
6 < 7
```

continue expanding.

---

### Step 4 — `right = 3`

Add:

```text
2
```

Window:

```text
[2, 3, 1, 2]
```

Sum:

```text
8
```

Now:

```text
8 >= 7
```

The window is valid.

Current length:

```text
right - left + 1
= 3 - 0 + 1
= 4
```

Update:

```text
minLength = 4
```

Now shrink the window.

Remove:

```text
nums[left] = 2
```

New sum:

```text
8 - 2 = 6
```

Move:

```text
left = 1
```

Now:

```text
sum = 6
```

Since:

```text
6 < 7
```

stop shrinking.

---

### Step 5 — `right = 4`

Add:

```text
4
```

Current window:

```text
[3, 1, 2, 4]
```

Sum:

```text
10
```

Since:

```text
10 >= 7
```

the window is valid.

Current length:

```text
4 - 1 + 1 = 4
```

`minLength` remains:

```text
4
```

Shrink.

Remove:

```text
3
```

New sum:

```text
10 - 3 = 7
```

Move:

```text
left = 2
```

The window is now:

```text
[1, 2, 4]
```

Length:

```text
4 - 2 + 1 = 3
```

Since:

```text
sum = 7 >= 7
```

it is still valid.

Update:

```text
minLength = 3
```

Shrink again.

Remove:

```text
1
```

New sum:

```text
7 - 1 = 6
```

Move:

```text
left = 3
```

Now:

```text
sum = 6 < 7
```

Stop shrinking.

---

### Step 6 — `right = 5`

Add:

```text
3
```

Current window:

```text
[2, 4, 3]
```

Sum:

```text
9
```

Since:

```text
9 >= 7
```

current length:

```text
5 - 3 + 1 = 3
```

`minLength` remains:

```text
3
```

Shrink.

Remove:

```text
2
```

New sum:

```text
7
```

Move:

```text
left = 4
```

Window:

```text
[4, 3]
```

Length:

```text
5 - 4 + 1 = 2
```

Update:

```text
minLength = 2
```

Shrink again.

Remove:

```text
4
```

New sum:

```text
3
```

Move:

```text
left = 5
```

Now:

```text
3 < 7
```

Stop.

---

## ✅ Final Answer

The smallest valid subarray is:

```text
[4, 3]
```

Its sum is:

```text
4 + 3 = 7
```

Its length is:

```text
2
```

Therefore:

```text
Output = 2
```

---

## 🧠 Why Do We Use `while` Instead of `if`?

This is one of the most important parts of this problem.

We use:

```java
while (sum >= target)
```

instead of:

```java
if (sum >= target)
```

because once the window becomes valid, we need to keep shrinking it **as much as possible**.

For example:

```text
[2, 3, 1, 2, 4]
```

Suppose:

```text
sum >= target
```

We don't know whether removing one element will still keep the window valid.

So we repeatedly shrink:

```text
[2, 3, 1, 2, 4]
      ↓
[3, 1, 2, 4]
      ↓
[1, 2, 4]
      ↓
...
```

Every valid window is considered before removing its leftmost element.

Therefore:

```text
while (sum >= target)
```

allows us to find the **minimum possible window** ending at the current `right`.

---

## 🧠 Why Does Shrinking Work?

The array contains **positive integers**.

This is extremely important.

When we move `right` forward:

```text
sum increases
```

When we move `left` forward:

```text
sum decreases
```

Therefore, once:

```text
sum >= target
```

we can safely try removing elements from the left.

Because all numbers are positive, removing an element cannot increase the sum.

This property makes the sliding window approach possible.

---

## 💻 Java Solution

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {

            sum += nums[right];

            while (sum >= target) {

                int currentLength = right - left + 1;

                minLength = Math.min(minLength, currentLength);

                sum -= nums[left];

                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

At first glance, there are nested loops:

```text
for
    while
```

which might look like:

```text
O(n²)
```

But it is actually `O(n)`.

The reason is that:

- `right` moves from left to right at most `n` times.
- `left` also moves from left to right at most `n` times.

Neither pointer ever moves backward.

Therefore, the total number of pointer movements is at most:

```text
2n
```

So:

```text
Time = O(n)
```

### Space Complexity

```text
O(1)
```

Only a few variables are used.

No additional data structure is required.

---

## 💡 Key Learning

This problem introduces the **Variable-Size Sliding Window** pattern.

The general pattern is:

```text
Expand window
      ↓
Condition becomes valid
      ↓
Shrink window
      ↓
Update answer
      ↓
Continue
```

For this problem:

```text
Expand → sum increases
Shrink → sum decreases
```

The condition is:

```text
sum >= target
```

The goal is:

```text
minimum window length
```

---

## 🔗 Connection With Previous Sliding Window Problem

### LeetCode #643 - Maximum Average Subarray I

That problem uses a:

```text
Fixed-Size Sliding Window
```

The window size is always:

```text
k
```

Pattern:

```text
Add new element
Remove old element
```

---

### LeetCode #209 - Minimum Size Subarray Sum

This problem uses a:

```text
Variable-Size Sliding Window
```

The window size changes depending on the condition.

Pattern:

```text
Expand
→ sum >= target
→ shrink
→ update minimum
```

---

## 📊 Fixed vs Variable Sliding Window

| Type | Example | Window Size |
|---|---|---|
| Fixed Size | #643 Maximum Average Subarray I | Always `k` |
| Variable Size | #209 Minimum Size Subarray Sum | Changes based on condition |

---

## ⚠️ Important Points

- The array contains **positive integers**.
- The positive-integer condition is important for this sliding window approach.
- Use `while`, not `if`, to shrink the window repeatedly.
- Calculate the current window length before removing `nums[left]`.
- `right` expands the window.
- `left` shrinks the window.
- `minLength` stores the smallest valid window found.
- If `minLength` is still `Integer.MAX_VALUE`, no valid subarray exists.
- In that case, return `0`.

---

## 📌 LeetCode

**Problem:** Minimum Size Subarray Sum

**LeetCode Number:** 209

**Difficulty:** Medium

**Topics:** Array, Sliding Window

[LeetCode Problem #209 - Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)

---