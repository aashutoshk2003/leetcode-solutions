# LeetCode #219 - Contains Duplicate II

**Difficulty:** Easy

**Topic:** HashMap

**Pattern:** HashMap Lookup

---

## 📝 Problem

Given an integer array `nums` and an integer `k`, determine whether there are two distinct indices `i` and `j` such
that:

```text
nums[i] == nums[j]
```

and:

```text
|i - j| <= k
```

Return `true` if such a pair exists. Otherwise, return `false`.

---

## 💭 Approach

I use a `HashMap` to store each number along with its **most recent index**.

The HashMap stores:

```text
number → index
```

While traversing the array:

1. Check whether the current number already exists in the HashMap.
2. If it exists, get its previous index.
3. Calculate the distance between the current index and the previous index:

```text
i - previousIndex
```

4. If the distance is less than or equal to `k`, return `true`.
5. Otherwise, update the number's index in the HashMap with the current index.

The important point is that I update the index every time the number appears.

This means the HashMap always contains the **latest index** of each number.

If the latest previous occurrence is already more than `k` positions away, an older occurrence cannot be closer, so
updating to the current index is enough.

---

## 🔄 Algorithm

1. Create a `HashMap` to store:

```text
number → latest index
```

2. Traverse the array using index `i`.
3. Check whether `nums[i]` already exists in the HashMap.
4. If it exists:
    - Get its previous index.
    - Calculate:

```text
i - previousIndex
```

5. If:

```text
i - previousIndex <= k
```

return `true`.

6. Update the current number's index:

```text
map.put(nums[i], i)
```

7. Continue until the entire array is processed.
8. If no valid pair is found, return `false`.

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [1, 2, 3, 1]
k = 3
```

Initially:

```text
map = {}
```

### Step 1 — `i = 0`

Current value:

```text
nums[0] = 1
```

`1` does not exist in the map.

Store:

```text
map = {
    1 → 0
}
```

---

### Step 2 — `i = 1`

Current value:

```text
nums[1] = 2
```

`2` does not exist in the map.

Store:

```text
map = {
    1 → 0,
    2 → 1
}
```

---

### Step 3 — `i = 2`

Current value:

```text
nums[2] = 3
```

`3` does not exist in the map.

Store:

```text
map = {
    1 → 0,
    2 → 1,
    3 → 2
}
```

---

### Step 4 — `i = 3`

Current value:

```text
nums[3] = 1
```

`1` already exists.

Previous index:

```text
previousIndex = 0
```

Calculate:

```text
i - previousIndex
= 3 - 0
= 3
```

Since:

```text
3 <= k
3 <= 3
```

the condition is satisfied.

Therefore:

```text
return true
```

---

## 🔍 Example Where Result Is False

Consider:

```text
nums = [1, 2, 3, 1, 2, 3]
k = 2
```

When `1` appears again:

```text
index difference = 3 - 0
                 = 3
```

Since:

```text
3 > 2
```

it is not a valid nearby duplicate.

The same check happens for `2` and `3`.

No pair satisfies the condition.

Therefore:

```text
Output = false
```

---

## 💻 Java Solution

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int previousKey = map.get(nums[i]);

                if (i - previousKey <= k) {
                    return true;
                }
            }

            map.put(nums[i], i);
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

`HashMap` lookup and insertion take `O(1)` average time.

Therefore, the overall time complexity is:

```text
O(n)
```

### Space Complexity

```text
O(n)
```

In the worst case, the HashMap can contain every unique element in the array.

---

## 💡 Key Learning

- A `HashMap` can store both a value and useful information related to that value.
- Here, the HashMap stores:

```text
number → latest index
```

- We can use the stored index to calculate the distance between duplicate values.
- Instead of searching backward through the array, the HashMap gives us the previous index directly.
- Updating the index allows the map to always keep the **most recent occurrence**.
- This is an example of using a HashMap to optimize an array problem from a brute-force search to `O(n)` average time.

---

## ⚠️ Important Points

- The problem requires **distinct indices**.
- The same value must occur at both indices.
- The index difference must satisfy:

```text
i - previousIndex <= k
```

- Always update the number's index after checking the previous occurrence.
- Keeping the latest index is useful because it gives the smallest possible distance to the current occurrence.
- If the latest occurrence is already more than `k` positions away, an older occurrence cannot be closer.

---

## 📌 LeetCode

**Problem:** Contains Duplicate II

**LeetCode Number:** 219

**Difficulty:** Easy

**Topics:** Array, HashMap

[LeetCode Problem #219 - Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/)

---