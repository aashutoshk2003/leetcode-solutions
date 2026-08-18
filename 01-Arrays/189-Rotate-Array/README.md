# LeetCode #189 - Rotate Array

**Difficulty:** Medium

**Topic:** Array, Two Pointers, Array Reversal

---

## 📝 Problem

Given an integer array `nums`, rotate the array to the right by `k` steps.

Each element should be shifted `k` positions to the right.

The rotation should be performed **in-place** without using another array.

---

## 💭 Approach

I use the **Array Reversal Algorithm** to rotate the array in-place.

First, I reduce `k` using:

```text
k = k % n
```

This is important because rotating an array by its length brings it back to the same position.

For example, if:

```text
n = 7
k = 9
```

then:

```text
k = 9 % 7 = 2
```

So rotating the array by `9` positions is the same as rotating it by `2` positions.

After normalizing `k`, I divide the array into two parts:

```text
First Part  = 0 to n-k-1
Second Part = n-k to n-1
```

My `reverse()` method receives the indexes in reverse order:

```text
start = right index
end   = left index
```

Therefore, I perform three reversals:

1. Reverse the last `k` elements.
2. Reverse the first `n-k` elements.
3. Reverse the entire array.

This produces the required right rotation.

---

## 🔄 Algorithm

1. Store the length of the array in `n`.

2. Normalize `k`:

```text
k = k % n
```

3. Reverse the last `k` elements:

```java
reverse(nums, n - 1, n - k);
```

4. Reverse the first `n-k` elements:

```java
reverse(nums, n - k - 1, 0);
```

5. Reverse the entire array:

```java
reverse(nums, n - 1, 0);
```

6. The array is now rotated to the right by `k` positions.

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [1, 2, 3, 4, 5, 6, 7]
k = 3
```

Array length:

```text
n = 7
```

Normalize `k`:

```text
k = 3 % 7
k = 3
```

### Step 1 — Reverse Last `k` Elements

Reverse:

```text
[5, 6, 7]
```

into:

```text
[7, 6, 5]
```

Array becomes:

```text
[1, 2, 3, 4, 7, 6, 5]
```

---

### Step 2 — Reverse First `n-k` Elements

The first `n-k` elements are:

```text
[1, 2, 3, 4]
```

Reverse them:

```text
[4, 3, 2, 1]
```

Array becomes:

```text
[4, 3, 2, 1, 7, 6, 5]
```

---

### Step 3 — Reverse the Entire Array

Reverse:

```text
[4, 3, 2, 1, 7, 6, 5]
```

The array becomes:

```text
[5, 6, 7, 1, 2, 3, 4]
```

Final result:

```text
[5, 6, 7, 1, 2, 3, 4]
```

The array has been successfully rotated to the right by `3` positions.

---

## 🔧 Reverse Helper Method

The `reverse()` method reverses a specific portion of the array.

In this implementation:

```text
start = right-side index
end   = left-side index
```

The method swaps the elements at `start` and `end`.

After every swap:

```text
start--
end++
```

The process continues while:

```text
start > end
```

Example:

```text
[1, 2, 3, 4]
```

Starting with:

```text
start = 3
end = 0
```

First swap:

```text
4 ↔ 1
```

Result:

```text
[4, 2, 3, 1]
```

Move the pointers:

```text
start = 2
end = 1
```

Second swap:

```text
3 ↔ 2
```

Result:

```text
[4, 3, 2, 1]
```

The portion is now completely reversed.

---

## 💻 Java Solution

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, n - 1, n - k);
        reverse(nums, n - k - 1, 0);
        reverse(nums, n - 1, 0);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start > end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start--;
            end++;
        }
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

The array is reversed in three parts, but each reversal takes linear time relative to the number of elements being processed.

Overall:

```text
O(k) + O(n-k) + O(n)
```

which simplifies to:

```text
O(n)
```

### Space Complexity

```text
O(1)
```

The array is modified in-place.

Only a few variables are used for swapping and tracking indexes, so no additional array or data structure is required.

---

## 💡 Key Learning

- Array rotation can be performed efficiently using **reversal**.
- We do not need an extra array to rotate elements.
- `%` can be used to normalize `k` when `k` is greater than the array length.
- Reversing different sections of an array can produce the required rotation.
- A helper method makes repeated reversal operations easier and keeps the main method clean.
- This approach provides both `O(n)` time and `O(1)` extra space.

---

## ⚠️ Important Points

- Always normalize `k` using:

```java
k = k % n;
```

- The last `k` elements start from index:

```text
n - k
```

- The first part ends at index:

```text
n - k - 1
```

- In this implementation, the `reverse()` parameters are passed as:

```text
reverse(array, rightIndex, leftIndex)
```

rather than the more common:

```text
reverse(array, leftIndex, rightIndex)
```

- Therefore, the loop condition is:

```java
while (start > end)
```

and the pointers move as:

```java
start--;
end++;
```

- The rotation is performed completely **in-place**.

---

## 📌 LeetCode

**Problem:** Rotate Array

**LeetCode Number:** 189

**Difficulty:** Medium

**Topics:** Array, Two Pointers, Array Reversal

---