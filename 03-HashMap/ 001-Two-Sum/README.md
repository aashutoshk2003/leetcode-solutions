# LeetCode #1 - Two Sum

**Difficulty:** Easy

**Topic:** HashMap

**Pattern:** HashMap Lookup

---

## 📝 Problem

Given an integer array `nums` and an integer `target`, return the indices of the two numbers whose sum is equal to `target`.

Each input is guaranteed to have exactly one solution.

The same element cannot be used twice.

---

## 💭 Approach

I use a **HashMap** to store the numbers that I have already visited along with their indexes.

Instead of checking every possible pair like the brute-force approach, I calculate the value needed to reach the target.

For the current number:

```text
needed = target - nums[i]
```

Then I check whether `needed` already exists in the HashMap.

- If `needed` exists, I have found the two required numbers.
- If `needed` does not exist, I store the current number and its index in the HashMap.

The HashMap allows me to check whether the required number has already been seen in constant average time.

---

## 🔄 Algorithm

1. Create a HashMap to store:

```text
number → index
```

2. Traverse the array from left to right.
3. For each element, calculate:

```text
needed = target - nums[i]
```

4. Check whether `needed` exists in the HashMap.
5. If it exists:
    - Get its index using `map.get(needed)`.
    - Return:

```text
[map.get(needed), i]
```

6. If it does not exist:
    - Store the current number and its index:

```text
map.put(nums[i], i)
```

7. Continue until the pair is found.
8. If no pair is found, return an empty array.

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [2, 7, 11, 15]
target = 9
```

Initially:

```text
map = {}
```

### Step 1 — `i = 0`

Current value:

```text
nums[0] = 2
```

Calculate:

```text
needed = 9 - 2
needed = 7
```

Check the HashMap:

```text
7 does not exist
```

So store:

```text
map = {
    2 → 0
}
```

---

### Step 2 — `i = 1`

Current value:

```text
nums[1] = 7
```

Calculate:

```text
needed = 9 - 7
needed = 2
```

Check the HashMap:

```text
2 exists
```

The index of `2` is:

```text
map.get(2) = 0
```

Current index:

```text
i = 1
```

Therefore:

```text
return [0, 1]
```

Final output:

```text
[0, 1]
```

---

## 💻 Java Solution

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int needed = target - nums[i];

            if (map.containsKey(needed)) {
                return new int[]{map.get(needed), i};
            } else {
                map.put(nums[i], i);
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
O(n)
```

The array is traversed only once.

HashMap lookup and insertion take `O(1)` average time.

### Space Complexity

```text
O(n)
```

In the worst case, the HashMap can store almost every element of the array.

---

## 💡 Key Learning

- A HashMap can reduce the time required to search for a required value.
- Instead of checking every possible pair, we store previously visited elements.
- The main idea is:

```text
needed = target - current value
```

- The HashMap stores:

```text
number → index
```

- `containsKey()` checks whether the required number has already been seen.
- `get()` retrieves the index of the required number.
- This changes the brute-force approach from `O(n²)` time to `O(n)` average time by using extra space.

---

## ⚠️ Important Points

- Check for `needed` **before** storing the current element.
- This prevents using the same element twice.
- The HashMap stores the index along with the number because the problem asks for indexes.
- The problem guarantees exactly one valid solution.
- HashMap provides average `O(1)` lookup and insertion.

---

## 🔗 LeetCode

**Problem:** Two Sum

**LeetCode Number:** 1

**Difficulty:** Easy

**Topic:** HashMap

[LeetCode Problem #1 - Two Sum](https://leetcode.com/problems/two-sum/)

---