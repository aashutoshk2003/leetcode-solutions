# LeetCode #283 - Move Zeroes

**Difficulty:** Easy

**Topic:** Array, Two Pointers

---

## 📝 Problem

Given an integer array `nums`, move all `0`s to the end of the array while maintaining the relative order of the non-zero elements.

The operation must be performed **in-place** without creating another array.

### Example

**Input:**

```text
nums = [0, 1, 0, 3, 12]
```

**Output:**

```text
[1, 3, 12, 0, 0]
```

---

## 💭 Approach

I use a `position` variable to keep track of the position where the next non-zero element should be placed.

The array is traversed using another pointer `i`.

- If `arr[i]` is `0`, nothing is done and the loop continues.
- If `arr[i]` is non-zero, it is placed at `arr[position]`.
- If `position` and `i` are different, the original position `arr[i]` is set to `0`.
- After placing a non-zero element, `position` is increased.

This keeps all non-zero elements in their original relative order and moves the zeroes toward the end.

---

## 🔄 Algorithm

1. Initialize `position = 0`.
2. Traverse the array from left to right using `i`.
3. If `arr[i]` is `0`, skip it.
4. If `arr[i]` is non-zero:
    - Store it at `arr[position]`.
    - If `position != i`, set `arr[i] = 0`.
    - Increment `position`.
5. Continue until the entire array is processed.
6. All non-zero elements are now at the beginning, and all zeroes are at the end.

---

## 🔍 Example Walkthrough

For:

```text
[0, 1, 0, 3, 12]
```

Initially:

```text
position = 0
```

### Step 1 — `i = 0`

```text
arr[0] = 0
```

Zero → skip.

```text
[0, 1, 0, 3, 12]
```

### Step 2 — `i = 1`

```text
arr[1] = 1
```

Non-zero → place it at `arr[position]`.

```text
arr[0] = 1
```

Since `position != i`, set:

```text
arr[1] = 0
```

Array becomes:

```text
[1, 0, 0, 3, 12]
```

Then:

```text
position = 1
```

### Step 3 — `i = 2`

```text
arr[2] = 0
```

Zero → skip.

```text
[1, 0, 0, 3, 12]
```

### Step 4 — `i = 3`

```text
arr[3] = 3
```

Place `3` at `arr[1]`.

Since `position != i`, set `arr[3] = 0`.

Array becomes:

```text
[1, 3, 0, 0, 12]
```

Then:

```text
position = 2
```

### Step 5 — `i = 4`

```text
arr[4] = 12
```

Place `12` at `arr[2]`.

Since `position != i`, set `arr[4] = 0`.

Array becomes:

```text
[1, 3, 12, 0, 0]
```

Final result:

```text
[1, 3, 12, 0, 0]
```

---

## 💻 Java Solution

```java
class Solution {
    public void moveZeroes(int[] arr) {
        int position = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[position] = arr[i];

                if (position != i) {
                    arr[i] = 0;
                }

                position++;
            }
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

The array is traversed only once.

### Space Complexity

```text
O(1)
```

No extra array or data structure is used. The solution modifies the original array in-place.

---

## 💡 Key Learning

- A separate `position` pointer can be used to track where the next valid element should be placed.
- We can modify an array in-place without creating another array.
- The relative order of non-zero elements is preserved.
- This is a useful pattern for problems where we need to **filter or rearrange elements in-place**.

---

## ⚠️ Important Points

- `i` is used to scan the entire array.
- `position` tracks the next position for a non-zero element.
- `position` is always less than or equal to `i`.
- When `position == i`, no extra zero assignment is required.
- The solution uses constant extra space.

---

## 📌 LeetCode

**Problem:** Move Zeroes

**LeetCode Number:** 283

**Difficulty:** Easy

**Topics:** Array, Two Pointers

---