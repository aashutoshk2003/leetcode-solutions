# LeetCode #128 - Longest Consecutive Sequence

**Difficulty:** Medium

**Topic:** HashSet

**Pattern:** HashSet Lookup, Sequence Start Detection

---

## 📝 Problem

Given an unsorted integer array `nums`, return the length of the longest consecutive elements sequence.

A consecutive sequence contains numbers that follow each other without gaps.

For example:

```text
[1, 2, 3, 4]
```

is a consecutive sequence of length `4`.

The solution must run in `O(n)` time.

---

## 💭 Approach

I use a `HashSet` to store all the elements of the array.

A `HashSet` provides fast average `O(1)` lookup, so I can quickly check whether a number exists.

After storing all values in the set, I traverse the set and look for the **starting number of a consecutive sequence**.

A number is the start of a sequence when:

```text
value - 1
```

does not exist in the set.

For example:

```text
[1, 2, 3, 4]
```

When checking `1`:

```text
0 does not exist
```

Therefore, `1` is the start of the sequence.

But when checking `2`:

```text
1 exists
```

so `2` is not the start of a new sequence.

Once a sequence start is found, I continue checking:

```text
value + 1
value + 2
value + 3
...
```

using the HashSet until the consecutive sequence ends.

I keep track of the sequence length using `count` and update the longest sequence using `longest`.

---

## 🔄 Algorithm

1. Create a `HashSet`.
2. Add every element from the array to the set.
3. Initialize:

```text
longest = 0
```

4. Traverse every value in the set.
5. Check whether the previous number exists:

```text
set.contains(value - 1)
```

6. If it does not exist, the current value is the start of a consecutive sequence.
7. Initialize:

```text
current = value
count = 1
```

8. Check whether the next consecutive number exists:

```text
set.contains(current + 1)
```

9. If it exists:
    - Increment `current`.
    - Increment `count`.
10. Continue until the sequence ends.
11. Update:

```text
longest = Math.max(count, longest)
```

12. Return `longest`.

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [100, 4, 200, 1, 3, 2]
```

After adding all elements to the HashSet:

```text
set = {100, 4, 200, 1, 3, 2}
```

Now check each value.

### Value = 100

Check:

```text
100 - 1 = 99
```

`99` does not exist.

Therefore, `100` is a sequence start.

Check:

```text
101
```

`101` does not exist.

So:

```text
count = 1
longest = 1
```

---

### Value = 4

Check:

```text
4 - 1 = 3
```

`3` exists.

Therefore, `4` is not the start of a sequence.

Skip it.

---

### Value = 200

Check:

```text
200 - 1 = 199
```

`199` does not exist.

So `200` is a sequence start.

Check:

```text
201
```

It does not exist.

Sequence length:

```text
count = 1
```

---

### Value = 1

Check:

```text
1 - 1 = 0
```

`0` does not exist.

Therefore, `1` is the start of a sequence.

Now check the next values:

```text
2 → exists
3 → exists
4 → exists
5 → does not exist
```

So the sequence is:

```text
1 → 2 → 3 → 4
```

Length:

```text
count = 4
```

Update:

```text
longest = 4
```

---

### Final Result

The longest consecutive sequence is:

```text
[1, 2, 3, 4]
```

Therefore:

```text
Output = 4
```

---

## 💻 Java Solution

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int value : nums) {
            set.add(value);
        }

        int longest = 0;

        for (int value : set) {
            if (!set.contains(value - 1)) {
                int current = value;
                int count = 1;

                while (set.contains(current + 1)) {
                    current++;
                    count++;
                }

                longest = Math.max(count, longest);
            }
        }

        return longest;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

The first loop adds all elements to the HashSet.

The second loop checks each unique value.

The sequence expansion only starts from numbers that have no previous consecutive number, so each consecutive sequence is processed efficiently.

Therefore, the overall average time complexity is:

```text
O(n)
```

### Space Complexity

```text
O(n)
```

The HashSet stores the elements of the array.

---

## 💡 Key Learning

- A `HashSet` allows fast average `O(1)` existence checks.
- We do not need to sort the array.
- Sorting would normally take `O(n log n)`, but the HashSet approach can achieve the required `O(n)` average time.
- The important idea is to find the **start of a sequence**.
- A value is a sequence start when:

```text
value - 1
```

is not present.
- Once a sequence start is found, we check consecutive values using the HashSet.
- This prevents unnecessarily starting a sequence from every number.

---

## ⚠️ Important Points

- The array can be unsorted.
- Duplicate values do not affect the result because the HashSet stores only unique values.
- Always check:

```text
!set.contains(value - 1)
```

before starting a sequence.
- Only sequence-start values should begin the `while` loop.
- `current` tracks the current number in the sequence.
- `count` tracks the current sequence length.
- `longest` stores the maximum sequence length found so far.
- If the input array is empty, `longest` remains `0`.

---

## 📌 LeetCode

**Problem:** Longest Consecutive Sequence

**LeetCode Number:** 128

**Difficulty:** Medium

**Topics:** Array, HashSet

[LeetCode Problem #128 - Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)

---