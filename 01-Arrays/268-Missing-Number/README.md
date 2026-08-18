# LeetCode #268 - Missing Number

**Difficulty:** Easy

**Topic:** Array, Math

---

## 📝 Problem

Given an array `nums` containing `n` distinct numbers taken from the range `[0, n]`, return the only number in the range that is missing from the array.

The numbers are distinct and exactly one number from the range `[0, n]` is missing.

---

## 💭 Approach

I use the **sum approach**.

The array contains `n` numbers, but the complete range contains numbers from `0` to `n`.

So, the expected numbers are:

```text
0, 1, 2, ..., n
```

I calculate two sums:

- `expectedSum` → sum of all numbers from `0` to `n`
- `actualSum` → sum of all numbers present in the array

Since exactly one number is missing:

```text
Missing Number = expectedSum - actualSum
```

The missing number is therefore obtained by subtracting the actual array sum from the expected sum.

---

## 🔄 Algorithm

1. Store the array length in `n`.
2. Initialize `expectedSum = 0`.
3. Initialize `actualSum = 0`.
4. Calculate the sum of all numbers from `0` to `n` and store it in `expectedSum`.
5. Traverse the array and calculate the sum of all existing elements.
6. Store this sum in `actualSum`.
7. Return:

```text
expectedSum - actualSum
```

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [3, 0, 1]
```

The array length is:

```text
n = 3
```

The complete range should be:

```text
[0, 1, 2, 3]
```

### Step 1 — Calculate Expected Sum

```text
0 + 1 + 2 + 3 = 6
```

So:

```text
expectedSum = 6
```

### Step 2 — Calculate Actual Sum

The array contains:

```text
3 + 0 + 1 = 4
```

So:

```text
actualSum = 4
```

### Step 3 — Find Missing Number

```text
expectedSum - actualSum
= 6 - 4
= 2
```

Therefore:

```text
Output = 2
```

---

## 💻 Java Solution

```java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = 0;
        int actualSum = 0;

        for (int i = 0; i <= n; i++) {
            expectedSum += i;
        }

        for (int value : nums) {
            actualSum += value;
        }

        return expectedSum - actualSum;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

The solution performs two linear traversals:

- One loop to calculate the expected sum.
- One loop to calculate the actual sum.

Therefore, the overall time complexity is:

```text
O(n)
```

### Space Complexity

```text
O(1)
```

Only a few integer variables are used, so no additional data structure is required.

---

## 💡 Key Learning

- When a problem contains a complete numerical range with exactly one missing value, comparing the **expected sum** with the **actual sum** can be useful.
- The array does not need to be sorted.
- No extra array or `HashSet` is required.
- This is an example of using a mathematical observation to solve an array problem efficiently.
- The solution works in **O(n) time and O(1) extra space**.

---

## ⚠️ Important Points

- The numbers are from the range `[0, n]`.
- Exactly one number is missing.
- All numbers in the array are distinct.
- `n` is equal to `nums.length`.
- The expected sum must include `n`, so the loop condition is:

```java
i <= n
```

- The missing number is found using:

```text
expectedSum - actualSum
```

---

## 📌 LeetCode

**Problem:** Missing Number

**LeetCode Number:** 268

**Difficulty:** Easy

**Topics:** Array, Math

---