# LeetCode #349 - Intersection of Two Arrays

**Difficulty:** Easy

**Topic:** HashSet

**Pattern:** Set Intersection

---

## 📝 Problem

Given two integer arrays `nums1` and `nums2`, return an array containing their intersection.

Each element in the result must be **unique**.

The order of the result does not matter.

---

## 💭 Approach

I use `HashSet` to efficiently find the common elements between the two arrays.

First, I store all unique values from `nums1` in a `HashSet`.

Then I traverse `nums2` and check whether each value exists in the first set.

If the value exists:

```text
set.contains(value)
```

then it is present in both arrays, so I add it to another `HashSet` called `result`.

Using a second `HashSet` ensures that the result contains each common value only once.

Finally, I create an integer array with the size of the result set and copy all values from the `result` set into the array.

---

## 🔄 Algorithm

1. Create a `HashSet` called `set`.
2. Traverse `nums1` and add every value to `set`.
3. Create another `HashSet` called `result`.
4. Traverse `nums2`.
5. For each value:
    - Check whether it exists in `set`.
6. If it exists:
    - Add it to `result`.
7. Create an `int[]` with the size of `result`.
8. Traverse `result` and copy its values into the array.
9. Return the resulting array.

---

## 🔍 Example Walkthrough

Consider:

```text
nums1 = [1, 2, 2, 1]
nums2 = [2, 2]
```

### Step 1 — Store `nums1`

Add all values from `nums1` to the first HashSet.

```text
nums1 = [1, 2, 2, 1]
```

The HashSet contains only unique values:

```text
set = {1, 2}
```

---

### Step 2 — Traverse `nums2`

First value:

```text
2
```

Check:

```text
set.contains(2)
```

Result:

```text
true
```

So add `2` to `result`.

```text
result = {2}
```

Second value:

```text
2
```

Again:

```text
set.contains(2) → true
```

Try to add `2` again.

Because `result` is a HashSet, it remains:

```text
result = {2}
```

Therefore, duplicates are automatically removed.

---

### Step 3 — Convert Result to Array

The result set contains:

```text
{2}
```

Create:

```text
answer = new int[1]
```

Copy the value:

```text
answer = [2]
```

Final output:

```text
[2]
```

---

## 🔍 Another Example

```text
nums1 = [4, 9, 5]
nums2 = [9, 4, 9, 8, 4]
```

First HashSet:

```text
set = {4, 9, 5}
```

While processing `nums2`:

```text
9 → exists → add to result
4 → exists → add to result
9 → already in result
8 → does not exist
4 → already in result
```

Result:

```text
{9, 4}
```

Possible output:

```text
[9, 4]
```

The order does not matter.

---

## 💻 Java Solution

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();

        for (int value : nums1) {
            set.add(value);
        }

        Set<Integer> result = new HashSet<>();

        for (int value : nums2) {
            if (set.contains(value)) {
                result.add(value);
            }
        }

        int i = 0;
        int[] answer = new int[result.size()];

        for (int value : result) {
            answer[i] = value;
            i++;
        }

        return answer;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n + m)
```

where:

- `n` = length of `nums1`
- `m` = length of `nums2`

We traverse both arrays once.

HashSet lookup and insertion take `O(1)` average time.

The result set is also traversed once to create the final array.

Therefore, the overall average time complexity is:

```text
O(n + m)
```

### Space Complexity

```text
O(n + m)
```

The first HashSet stores unique values from `nums1`, and the result HashSet stores the common values.

---

## 💡 Key Learning

- `HashSet` is useful when we only care about **unique values**.
- `HashSet.contains()` allows fast average `O(1)` lookup.
- A HashSet automatically removes duplicate values.
- A second HashSet can be used to store the unique intersection.
- This problem demonstrates how a Set can be used to find common elements between two collections.
- The order of elements does not matter, so `HashSet` is suitable for storing the result.

---

## ⚠️ Important Points

- The result must contain only unique values.
- Duplicate values in either array should not create duplicate values in the result.
- `HashSet` automatically handles duplicate values.
- The first set contains unique values from `nums1`.
- The `result` set contains values that exist in both arrays.
- The order of the returned result does not matter.
- The final `HashSet` must be converted to an `int[]` because the LeetCode method expects an array.

---

## 📌 LeetCode

**Problem:** Intersection of Two Arrays

**LeetCode Number:** 349

**Difficulty:** Easy

**Topics:** Array, HashSet

[LeetCode Problem #349 - Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/)

---