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

I use a `HashSet` to store the elements that have already been visited.

A `HashSet` stores only unique values.

Instead of separately using `contains()` and `add()`, I use the return value of the `add()` method.

The `HashSet.add()` method:

- Returns `true` if the value was successfully added because it did not already exist.
- Returns `false` if the value already exists in the set.

Therefore, for every element:

```text
if (!set.add(value))
```

means that the value is already present in the set, so a duplicate has been found.

If `add()` returns `true`, the value is new and the traversal continues.

---

## 🔄 Algorithm

1. Create a `HashSet`.
2. Traverse the array using a for-each loop.
3. Try to add the current value to the set.
4. If `set.add(value)` returns `false`:
    - The value already exists.
    - Return `true`.
5. If the value is successfully added, continue.
6. If the entire array is processed without finding a duplicate, return `false`.

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
value = 1
```

Execute:

```text
set.add(1)
```

Since `1` does not exist:

```text
add() → true
```

Set becomes:

```text
{1}
```

Continue.

---

### Step 2

Current value:

```text
value = 2
```

Execute:

```text
set.add(2)
```

Since `2` does not exist:

```text
add() → true
```

Set becomes:

```text
{1, 2}
```

Continue.

---

### Step 3

Current value:

```text
value = 3
```

Execute:

```text
set.add(3)
```

Since `3` does not exist:

```text
add() → true
```

Set becomes:

```text
{1, 2, 3}
```

Continue.

---

### Step 4

Current value:

```text
value = 1
```

Execute:

```text
set.add(1)
```

But `1` already exists.

Therefore:

```text
add() → false
```

The condition:

```java
!set.add(value)
```

becomes:

```text
!false → true
```

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

Each value is successfully added:

```text
1 → true
2 → true
3 → true
4 → true
```

No `add()` operation returns `false`.

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
            if (!set.add(value)) {
                return true;
            }
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

`HashSet.add()` takes `O(1)` average time.

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
- `HashSet.add()` can directly tell us whether a value is new or already present.
- `add()` returns:
    - `true` → value was not present and was added.
    - `false` → value was already present.
- Therefore, we can combine the duplicate check and insertion into one operation.
- This avoids writing:

```java
if (set.contains(value)) {
    return true;
}

set.add(value);
```

and instead use:

```java
if (!set.add(value)) {
    return true;
}
```

---

## ⚠️ Important Points

- `HashSet` stores only unique values.
- `add()` returns `false` when the value already exists.
- The `!` operator converts `false` into `true`, allowing us to detect duplicates directly.
- No index or frequency information is required for this problem.
- We can return immediately when a duplicate is found.

---

## 📌 LeetCode

**Problem:** Contains Duplicate

**LeetCode Number:** 217

**Difficulty:** Easy

**Topics:** Array, HashSet

[LeetCode Problem #217 - Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)

---