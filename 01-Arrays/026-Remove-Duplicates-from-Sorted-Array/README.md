# LeetCode #26 - Remove Duplicates from Sorted Array

**Difficulty:** Easy

**Topic:** Array, Two Pointers

---

## 📝 Problem

Given a sorted integer array `nums`, remove the duplicates **in-place** such that each unique element appears only once.

The relative order of the unique elements must be maintained.

Return the number of unique elements, `k`.

The first `k` elements of the array should contain the unique elements.

---

## 💭 Approach

Since the array is already **sorted**, duplicate elements are always next to each other.

I use a `position` variable to keep track of where the next unique element should be placed.

The array is traversed using `i` starting from index `1`.

- The first element is always unique, so `position` starts at `1`.
- For every element, compare `arr[i]` with the previous element `arr[i - 1]`.
- If they are different, the current element is unique.
- Place that unique element at `arr[position]`.
- Increment `position`.
- If they are equal, the current element is a duplicate, so skip it.

At the end, `position` represents the number of unique elements.

---

## 🔄 Algorithm

1. Initialize `position = 1`.
2. Start traversing the array from index `1`.
3. Compare `arr[i]` with `arr[i - 1]`.
4. If `arr[i] != arr[i - 1]`:
    - The current element is unique.
    - Store it at `arr[position]`.
    - Increment `position`.
5. If `arr[i] == arr[i - 1]`:
    - The current element is a duplicate.
    - Skip it.
6. Continue until the entire array is traversed.
7. Return `position`.

---

## 🔍 Example Walkthrough

Consider:

```text
arr = [1, 1, 2, 2, 3]
```

Initially:

```text
position = 1
```

### Step 1 — `i = 1`

```text
arr[1] = 1
arr[0] = 1
```

They are equal, so `1` is a duplicate.

Skip it.

```text
position = 1
```

### Step 2 — `i = 2`

```text
arr[2] = 2
arr[1] = 1
```

They are different, so `2` is unique.

Place it at:

```text
arr[position] = arr[2]
arr[1] = 2
```

Array:

```text
[1, 2, 2, 2, 3]
```

Then:

```text
position = 2
```

### Step 3 — `i = 3`

```text
arr[3] = 2
arr[2] = 2
```

They are equal, so this is a duplicate.

Skip it.

```text
position = 2
```

### Step 4 — `i = 4`

```text
arr[4] = 3
arr[3] = 2
```

They are different, so `3` is unique.

Place it at:

```text
arr[2] = 3
```

Array:

```text
[1, 2, 3, 2, 3]
```

Then:

```text
position = 3
```

Final result:

```text
k = 3
```

The first `k` elements contain:

```text
[1, 2, 3]
```

---

## 💻 Java Solution

```java
class Solution {
    public int removeDuplicates(int[] arr) {
        int position = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[position] = arr[i];
                position++;
            }
        }

        return position;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

The array is traversed once.

### Space Complexity

```text
O(1)
```

No extra array or data structure is used. The modifications are performed in-place.

---

## 💡 Key Learning

- A **sorted array** makes duplicate detection easier because duplicates are adjacent.
- We can use a `position` pointer to store unique elements at the beginning of the array.
- The `i` pointer scans the array.
- The `position` pointer tracks where the next unique element should be placed.
- This is an important **two-pointer / slow-fast pointer pattern** for in-place array problems.
- The original relative order of unique elements is preserved.

---

## ⚠️ Important Points

- The array must be **sorted** for this approach to work.
- The first element is always considered unique.
- Therefore, `position` starts at `1`.
- `i` is the scanning pointer.
- `position` is the position where the next unique element is stored.
- The returned `position` represents the number of unique elements.
- Only the first `position` elements are relevant after the operation.
- No extra array is required.

---

## 📌 LeetCode

**Problem:** Remove Duplicates from Sorted Array

**LeetCode Number:** 26

**Difficulty:** Easy

**Topics:** Array, Two Pointers

---