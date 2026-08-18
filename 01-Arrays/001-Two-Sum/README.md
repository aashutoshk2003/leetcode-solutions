# LeetCode #1 - Two Sum

**Difficulty:** Easy

**Topic:** Array, Brute Force

---

## 📝 Problem

Given an integer array `nums` and an integer `target`, return the indices of the two numbers such that they add up to `target`.

Each input is guaranteed to have exactly one solution.

The same element cannot be used twice.

The answer can be returned in any order.

---

## 💭 Approach

I use a **Brute Force approach** with two nested loops.

The first loop selects one element, and the second loop checks every element after it.

For each pair of elements:

```text
nums[i] + nums[j]
```

I check whether their sum is equal to the given `target`.

- If the sum equals `target`, return the indices `i` and `j`.
- If the sum does not equal `target`, continue checking the next pair.
- If no pair is found, return an empty array.

Because `j` starts from `i + 1`, the same element is never used twice and duplicate pairs are avoided.

---

## 🔄 Algorithm

1. Start the first loop with `i = 0`.
2. Start the second loop with `j = i + 1`.
3. Calculate:

```text
nums[i] + nums[j]
```

4. If the sum equals `target`:
    - Return `{i, j}`.
5. Otherwise, continue checking the next pair.
6. Continue until all possible pairs have been checked.
7. If no valid pair is found, return an empty array.

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [2, 7, 11, 15]
target = 9
```

### Step 1

```text
i = 0
nums[i] = 2
```

Now check elements after index `0`.

```text
j = 1
nums[j] = 7
```

Calculate:

```text
2 + 7 = 9
```

Since:

```text
9 == target
```

Return:

```text
[0, 1]
```

Final output:

```text
[0, 1]
```

---

## 💻 Java Solution

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{};
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n²)
```

There are two nested loops.

In the worst case, the solution checks almost every possible pair of elements.

### Space Complexity

```text
O(1)
```

Apart from the returned result, no additional data structure is used.

---

## 💡 Key Learning

- A simple way to solve pair-based array problems is to check every possible pair.
- Two nested loops can be used when the input size allows a brute-force solution.
- Starting `j` from `i + 1` avoids checking the same pair twice.
- The current solution is easy to understand but has `O(n²)` time complexity.
- This problem can also be optimized using a `HashMap`, which reduces the time complexity to `O(n)`.

---

## ⚠️ Important Points

- The same array element cannot be used twice.
- Therefore, `j` starts from `i + 1`.
- The problem guarantees exactly one valid solution.
- The returned values are **indices**, not the actual numbers.
- The current solution checks every possible pair until it finds the answer.

---

## 📌 LeetCode

**Problem:** Two Sum

**LeetCode Number:** 1

**Difficulty:** Easy

**Topics:** Array, Brute Force

---