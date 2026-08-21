# LeetCode #560 - Subarray Sum Equals K

**Difficulty:** Medium

**Topic:** HashMap

**Pattern:** Prefix Sum + HashMap Frequency

---

## 📝 Problem

Given an integer array `nums` and an integer `k`, return the total number of continuous subarrays whose sum equals `k`.

A subarray must contain consecutive elements from the array.

---

## 💭 Approach

I use a combination of **Prefix Sum** and **HashMap**.

The main idea is to keep track of the prefix sums that we have already seen.

For the current position, suppose the current prefix sum is:

```text
sum
```

We need a previous prefix sum such that:

```text
sum - previousSum = k
```

Rearranging:

```text
previousSum = sum - k
```

Therefore, for every current prefix sum, I calculate:

```text
requiredSum = sum - k
```

Then I check whether `requiredSum` already exists in the HashMap.

The HashMap stores:

```text
prefix sum → frequency
```

The frequency is important because the same prefix sum can occur multiple times, and each occurrence can create a valid subarray.

---

## 🔑 Main Idea

Suppose:

```text
prefixSum[j] = current sum
```

and an earlier prefix sum is:

```text
prefixSum[i] = current sum - k
```

Then:

```text
prefixSum[j] - prefixSum[i] = k
```

which means the subarray between those two positions has sum `k`.

Therefore:

```text
requiredSum = currentSum - k
```

If that required prefix sum has appeared before, we have found one or more valid subarrays.

---

## 🔄 Algorithm

1. Initialize:

```text
sum = 0
count = 0
```

2. Create a HashMap to store:

```text
prefix sum → frequency
```

3. Add:

```text
map.put(0, 1)
```

This represents an empty prefix with sum `0`.

4. Traverse the array.
5. Add the current value to `sum`:

```text
sum += value
```

6. Calculate:

```text
requiredSum = sum - k
```

7. Check whether `requiredSum` exists in the HashMap.
8. If it exists:
    - Add its frequency to `count`.
9. Store the current prefix sum:

```text
map.put(sum, map.getOrDefault(sum, 0) + 1)
```

10. Continue until the entire array is processed.
11. Return `count`.

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [1, 1, 1]
k = 2
```

Initially:

```text
sum = 0
count = 0
```

HashMap:

```text
map = {
    0 → 1
}
```

The `0 → 1` entry represents the empty prefix.

---

### Step 1 — Value = 1

Update prefix sum:

```text
sum = 1
```

Calculate:

```text
requiredSum = 1 - 2
requiredSum = -1
```

`-1` does not exist in the map.

Store current prefix sum:

```text
map = {
    0 → 1,
    1 → 1
}
```

---

### Step 2 — Value = 1

Update:

```text
sum = 2
```

Calculate:

```text
requiredSum = 2 - 2
requiredSum = 0
```

`0` exists in the map:

```text
map.get(0) = 1
```

Therefore:

```text
count = count + 1
count = 1
```

Store prefix sum:

```text
map = {
    0 → 1,
    1 → 1,
    2 → 1
}
```

The subarray is:

```text
[1, 1]
```

---

### Step 3 — Value = 1

Update:

```text
sum = 3
```

Calculate:

```text
requiredSum = 3 - 2
requiredSum = 1
```

`1` exists in the map:

```text
map.get(1) = 1
```

Therefore:

```text
count = 2
```

The second valid subarray is:

```text
[1, 1]
```

So the final result is:

```text
Output = 2
```

---

## 🔍 Why Do We Store Frequencies?

This is very important.

Consider:

```text
nums = [0, 0, 0]
k = 0
```

The prefix sums are:

```text
0
0
0
```

The same prefix sum occurs multiple times.

So the HashMap stores:

```text
0 → 4
```

including the initial prefix sum.

When a required prefix sum appears multiple times, each occurrence represents a different possible starting point for a valid subarray.

That's why we use:

```text
prefix sum → frequency
```

instead of simply:

```text
prefix sum → index
```

---

## 🔑 Why `map.put(0, 1)`?

This line is extremely important:

```java
map.put(0, 1);
```

It represents a prefix sum of `0` before we process any elements.

Consider:

```text
nums = [2]
k = 2
```

After processing `2`:

```text
sum = 2
```

Required sum:

```text
requiredSum = 2 - 2
requiredSum = 0
```

Because the map initially contains:

```text
0 → 1
```

we find a valid subarray:

```text
[2]
```

Without:

```java
map.put(0, 1);
```

we would miss subarrays that start from index `0`.

---

## 💻 Java Solution

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int count = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int value : nums) {
            sum += value;

            int requiredSum = sum - k;

            if (map.containsKey(requiredSum)) {
                count += map.get(requiredSum);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
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

HashMap lookup and insertion take `O(1)` average time.

Therefore:

```text
O(n)
```

### Space Complexity

```text
O(n)
```

In the worst case, the HashMap can contain up to `n + 1` different prefix sums.

---

## 💡 Key Learning

- Prefix Sum helps us track the cumulative sum while traversing the array.
- HashMap helps us remember previously seen prefix sums.
- The key equation is:

```text
currentSum - previousSum = k
```

Therefore:

```text
previousSum = currentSum - k
```

- The HashMap stores:

```text
prefix sum → frequency
```

- We store the **frequency**, not just the index, because the same prefix sum can occur multiple times.
- `map.put(0, 1)` handles subarrays that start at index `0`.
- This approach works even when the array contains negative numbers.

---

## 🔗 Connection With Previous HashMap Problems

Previously, we used HashMap to store different kinds of information:

### Two Sum

```text
number → index
```

### Contains Duplicate II

```text
number → latest index
```

### Top K Frequent Elements

```text
number → frequency
```

### Isomorphic Strings

```text
character → character
```

### Group Anagrams

```text
sorted string → List<String>
```

Here we use:

```text
prefix sum → frequency
```

The important HashMap skill is not memorizing one specific structure.

The important question is:

> **What information do I need to remember from the previous elements?**

For this problem, we need to remember how many times each prefix sum has appeared.

---

## ⚠️ Important Points

- The array can contain positive, zero, and negative numbers.
- Do not assume the array is sorted.
- Use:

```java
map.put(0, 1);
```

before starting the traversal.
- Calculate:

```text
requiredSum = sum - k
```

before storing the current prefix sum.
- Add the frequency of `requiredSum` to `count`.
- Store the current prefix sum after checking for the required sum.
- Multiple occurrences of the same prefix sum represent multiple possible subarrays.

---

## 📌 LeetCode

**Problem:** Subarray Sum Equals K

**LeetCode Number:** 560

**Difficulty:** Medium

**Topics:** Array, HashMap, Prefix Sum

[LeetCode Problem #560 - Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

---