# LeetCode #1480 - Running Sum of 1d Array

**Difficulty:** Easy

**Topic:** Array, Prefix Sum

---

## 📝 Problem

Given an array `nums`, define the running sum of the array as:

```text
runningSum[i] = nums[0] + nums[1] + ... + nums[i]
```

Return the running sum of `nums`.

---

## 💭 Approach

I use a `sum` variable to keep track of the running sum while traversing the array.

For each element:

1. Add the current element to `sum`.
2. Replace the current element in the array with the calculated `sum`.

This allows the original array to be converted directly into the running sum array without creating a separate array.

For example:

```text
nums = [1, 2, 3, 4]
```

The calculation is:

```text
1
1 + 2 = 3
3 + 3 = 6
6 + 4 = 10
```

So the final array becomes:

```text
[1, 3, 6, 10]
```

---

## 🔄 Algorithm

1. Initialize `sum = 0`.
2. Traverse the array from left to right.
3. Add the current element to `sum`.
4. Store `sum` back into the current array position.
5. Continue until the entire array is processed.
6. Return the modified array.

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [1, 2, 3, 4]
```

Initially:

```text
sum = 0
```

### Step 1 — `i = 0`

Current value:

```text
nums[0] = 1
```

Add it to `sum`:

```text
sum = 0 + 1
sum = 1
```

Store it:

```text
nums[0] = 1
```

Array:

```text
[1, 2, 3, 4]
```

---

### Step 2 — `i = 1`

Current value:

```text
nums[1] = 2
```

Add it to `sum`:

```text
sum = 1 + 2
sum = 3
```

Store it:

```text
nums[1] = 3
```

Array:

```text
[1, 3, 3, 4]
```

---

### Step 3 — `i = 2`

Current value:

```text
nums[2] = 3
```

Add it to `sum`:

```text
sum = 3 + 3
sum = 6
```

Store it:

```text
nums[2] = 6
```

Array:

```text
[1, 3, 6, 4]
```

---

### Step 4 — `i = 3`

Current value:

```text
nums[3] = 4
```

Add it to `sum`:

```text
sum = 6 + 4
sum = 10
```

Store it:

```text
nums[3] = 10
```

Final array:

```text
[1, 3, 6, 10]
```

---

## 💻 Java Solution

```java
class Solution {
    public int[] runningSum(int[] nums) {
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            nums[i] = sum;
        }

        return nums;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

The array is traversed exactly once.

### Space Complexity

```text
O(1)
```

No additional array is created. The input array is modified in-place.

---

## 💡 Key Learning

- A running sum can be maintained using a single variable.
- The current array can be modified directly instead of creating another array.
- This is a simple example of the **Prefix Sum** concept.
- Each position contains the sum of all elements from the beginning up to that position.
- Prefix sums are useful for solving many problems involving cumulative values and range calculations.

---

## ⚠️ Important Points

- `sum` stores the cumulative sum of all processed elements.
- The current value must be added to `sum` before replacing `nums[i]`.
- The array is modified in-place.
- The original values after the current index are still unchanged when they are processed.
- The final array contains the running sum at every index.

---

## 📌 LeetCode

**Problem:** Running Sum of 1d Array

**LeetCode Number:** 1480

**Difficulty:** Easy

**Topics:** Array, Prefix Sum

---