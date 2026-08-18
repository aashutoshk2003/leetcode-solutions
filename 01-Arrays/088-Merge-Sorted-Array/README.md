# LeetCode #88 - Merge Sorted Array

**Difficulty:** Easy

**Topic:** Array, Two Pointers

---

## 📝 Problem

You are given two sorted integer arrays `nums1` and `nums2`.

- `nums1` has a size of `m + n`.
- The first `m` elements of `nums1` contain valid values.
- The remaining `n` elements of `nums1` are empty spaces represented by `0`.
- `nums2` contains `n` sorted elements.

Merge `nums2` into `nums1` so that `nums1` becomes one sorted array.

The final sorted array must be stored **in-place inside `nums1`**.

---

## 💭 Approach

I use **three pointers** and merge the arrays from **right to left**.

The three pointers are:

- `i` → points to the last valid element of `nums1`.
- `j` → points to the last element of `nums2`.
- `k` → points to the last available position in `nums1`.

Initially:

```text
i = m - 1
j = n - 1
k = m + n - 1
```

Since both arrays are sorted, the largest element will always be at the end of one of the arrays.

So I compare:

```text
nums1[i] and nums2[j]
```

and place the larger element at:

```text
nums1[k]
```

Then move the corresponding pointer backward.

This approach is important because `nums1` already contains enough empty positions at the end. By filling the array from the end, I avoid overwriting the valid elements in `nums1`.

---

## 🔄 Algorithm

1. Initialize:

```text
i = m - 1
j = n - 1
k = m + n - 1
```

2. While both arrays still have elements:

```text
i >= 0 && j >= 0
```

3. Compare:

```text
nums1[i] and nums2[j]
```

4. If `nums1[i]` is greater:
    - Place `nums1[i]` at `nums1[k]`.
    - Decrement `i`.

5. Otherwise:
    - Place `nums2[j]` at `nums1[k]`.
    - Decrement `j`.

6. Decrement `k` after every placement.

7. If elements remain in `nums2`, copy them into `nums1`.

8. No additional loop is required for remaining elements in `nums1` because they are already in their correct positions.

---

## 🔍 Example Walkthrough

Consider:

```text
nums1 = [1, 2, 3, 0, 0, 0]
m = 3

nums2 = [2, 5, 6]
n = 3
```

Initially:

```text
i = 2
j = 2
k = 5
```

So:

```text
nums1[i] = 3
nums2[j] = 6
```

### Step 1

Compare:

```text
3 < 6
```

Place `6` at `nums1[k]`.

```text
nums1 = [1, 2, 3, 0, 0, 6]
```

Move:

```text
j = 1
k = 4
```

---

### Step 2

Compare:

```text
nums1[i] = 3
nums2[j] = 5
```

Since:

```text
3 < 5
```

Place `5` at `nums1[4]`.

```text
nums1 = [1, 2, 3, 0, 5, 6]
```

Move:

```text
j = 0
k = 3
```

---

### Step 3

Compare:

```text
nums1[i] = 3
nums2[j] = 2
```

Since:

```text
3 > 2
```

Place `3` at `nums1[3]`.

```text
nums1 = [1, 2, 3, 3, 5, 6]
```

Move:

```text
i = 1
k = 2
```

---

### Step 4

Compare:

```text
nums1[i] = 2
nums2[j] = 2
```

The condition `nums1[i] > nums2[j]` is false, so place `2` from `nums2`.

```text
nums1 = [1, 2, 2, 3, 5, 6]
```

Move:

```text
j = -1
k = 1
```

Now `nums2` has no remaining elements.

The remaining elements in `nums1` are already in the correct position.

Final result:

```text
[1, 2, 2, 3, 5, 6]
```

---

## 💻 Java Solution

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }

            k--;
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(m + n)
```

Each element is processed at most once.

### Space Complexity

```text
O(1)
```

The merge is performed directly inside `nums1`, so no additional array or data structure is used.

---

## 💡 Key Learning

- When two arrays are sorted, we can use the **Two Pointer** technique.
- Since `nums1` already has empty space at the end, merging from the end prevents overwriting useful values.
- The largest remaining element should always be placed at the current position `k`.
- This is an important example of **in-place array manipulation**.
- The three pointers have different responsibilities:
    - `i` → valid elements of `nums1`
    - `j` → elements of `nums2`
    - `k` → position where the next largest element is placed
- The solution achieves `O(m + n)` time and `O(1)` extra space.

---

## ⚠️ Important Points

- Only the first `m` elements of `nums1` are initially valid.
- The remaining `n` positions are available for the merged result.
- Always start `k` at:

```text
m + n - 1
```

- Start `i` at:

```text
m - 1
```

- Start `j` at:

```text
n - 1
```

- Merge from **right to left**, not left to right.
- If elements remain in `nums2`, they must be copied.
- If elements remain in `nums1`, they do not need to be copied because they are already in the correct position.

---

## 📌 LeetCode

**Problem:** Merge Sorted Array

**LeetCode Number:** 88

**Difficulty:** Easy

**Topics:** Array, Two Pointers

---