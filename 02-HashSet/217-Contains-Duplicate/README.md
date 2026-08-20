# LeetCode #217 - Contains Duplicate

**Difficulty:** Easy

**Topic:** HashSet

**Pattern:** HashSet Lookup

---

## 📝 Problem

Given an integer array `nums`, return `true` if any value appears at least twice in the array.

Return `false` if every element appears only once.

---

## 💭 Approach

I use a `HashSet` to keep track of the elements that have already been visited.

A `HashSet` stores only unique values.

While traversing the array:

- Check whether the current value already exists in the `HashSet`.
- If it exists, a duplicate has been found, so return `true`.
- If it does not exist, add the current value to the `HashSet`.
- Continue until the entire array is processed.

If the complete array is traversed without finding an existing value in the set, return `false`.

---

## 🔄 Algorithm

1. Create a `HashSet`.
2. Traverse the array using a for-each loop.
3. For each value:
    - Check whether the value already exists in the set.
4. If the value exists:
    - Return `true`.
5. Otherwise:
    - Add the value to the set.
6. Continue until all elements are processed.
7. If no duplicate is found, return `false`.

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [1, 2, 3, 1]
```

Initially:

```text
set = {}
```

### Step 1

Current value:

```text
1
```

`1` does not exist in the set.

Add it:

```text
set = {1}
```

---

### Step 2

Current value:

```text
2
```

`2` does not exist in the set.

Add it:

```text
set = {1, 2}
```

---

### Step 3

Current value:

```text
3
```

`3` does not exist in the set.

Add it:

```text
set = {1, 2, 3}
```

---

### Step 4

Current value:

```text
1
```

`1` already exists in the set.

Therefore:

```text
return true
```

Final output:

```text
true
```

---

## 🔍 Example Where No Duplicate Exists

Consider:

```text
nums = [1, 2, 3, 4]
```

The set is updated as:

```text
{1}
{1, 2}
{1, 2, 3}
{1, 2, 3, 4}
```

No value appears again.

Therefore:

```text
Output = false
```

---

## 💻 Java Solution

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int value : nums) {
            if (set.contains(value)) {
                return true;
            }

            set.add(value);
        }

        return false;
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

`HashSet` lookup and insertion take `O(1)` average time.

Therefore, the overall time complexity is:

```text
O(n)
```

### Space Complexity

```text
O(n)
```

In the worst case, all elements are unique and the HashSet stores all `n` elements.

---

## 💡 Key Learning

- A `HashSet` is useful when we only need to know whether a value has already appeared.
- Unlike a `HashMap`, a `HashSet` stores only the values, not key-value pairs.
- The main idea is:

```text
Have I seen this value before?
```

- `contains()` checks whether the value already exists.
- `add()` stores a new value.
- We can return immediately when a duplicate is found, so we do not need to process the remaining elements.

---

## ⚠️ Important Points

- `HashSet` stores only unique values.
- We check `contains()` before `add()`.
- If a value already exists, it means the array contains a duplicate.
- If the entire array is processed without finding a duplicate, return `false`.
- No index information is required for this problem, so `HashSet` is sufficient.

---

## 📌 LeetCode

**Problem:** Contains Duplicate

**LeetCode Number:** 217

**Difficulty:** Easy

**Topics:** Array, HashSet

[LeetCode Problem #217 - Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)

---