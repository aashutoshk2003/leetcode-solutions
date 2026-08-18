# LeetCode #169 - Majority Element

**Difficulty:** Easy

**Topic:** Array, Boyer-Moore Voting Algorithm

---

## 📝 Problem

Given an array `nums` of size `n`, return the **majority element**.

The majority element is the element that appears **more than `n / 2` times** in the array.

You may assume that the majority element always exists in the array.

---

## 💭 Approach

I use the **Boyer-Moore Voting Algorithm**.

The idea is to maintain:

- `candidate` → the current possible majority element.
- `count` → the current vote count for the candidate.

The algorithm works by comparing every element with the current candidate.

- If `count == 0`, select the current element as the new candidate.
- If the current element is equal to the candidate, increase `count`.
- Otherwise, decrease `count`.

The majority element appears more than all other elements combined, so it cannot be completely cancelled out by the other elements.

Therefore, after traversing the entire array, the remaining candidate is the majority element.

---

## 🔄 Algorithm

1. Initialize `candidate = nums[0]`.
2. Initialize `count = 0`.
3. Traverse the array from left to right.
4. If `count == 0`, set the current element as the new `candidate`.
5. If the current element equals `candidate`:
    - Increment `count`.
6. Otherwise:
    - Decrement `count`.
7. Continue until the entire array is traversed.
8. Return `candidate`.

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [2, 2, 1, 1, 1, 2, 2]
```

Initially:

```text
candidate = 2
count = 0
```

### Step 1 — `nums[0] = 2`

Since:

```text
count == 0
```

Set:

```text
candidate = 2
```

`2 == candidate`, so:

```text
count = 1
```

---

### Step 2 — `nums[1] = 2`

```text
2 == candidate
```

So:

```text
count = 2
```

---

### Step 3 — `nums[2] = 1`

```text
1 != candidate
```

So:

```text
count = 1
```

---

### Step 4 — `nums[3] = 1`

```text
1 != candidate
```

So:

```text
count = 0
```

---

### Step 5 — `nums[4] = 1`

Since:

```text
count == 0
```

Select:

```text
candidate = 1
```

Then:

```text
1 == candidate
```

So:

```text
count = 1
```

---

### Step 6 — `nums[5] = 2`

```text
2 != candidate
```

So:

```text
count = 0
```

---

### Step 7 — `nums[6] = 2`

Since:

```text
count == 0
```

Select:

```text
candidate = 2
```

Then:

```text
count = 1
```

Final candidate:

```text
2
```

Therefore:

```text
Output = 2
```

---

## 💻 Java Solution

```java
class Solution {
    public int majorityElement(int[] nums) {
        int condidate = nums[0];
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                condidate = nums[i];
            }

            if (nums[i] == condidate) {
                count++;
            } else {
                count--;
            }
        }

        return condidate;
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

### Space Complexity

```text
O(1)
```

Only two variables are used:

- `candidate`
- `count`

No additional data structure is required.

---

## 💡 Key Learning

- The **Boyer-Moore Voting Algorithm** can find the majority element in linear time and constant space.
- A majority element appears more than `n / 2` times.
- Elements that are different from the current candidate effectively cancel one vote of the candidate.
- Because the majority element has more occurrences than all other elements combined, it survives the cancellation process.
- This is an important example of solving an array problem without using extra space such as `HashMap`.

---

## ⚠️ Important Points

- The problem guarantees that a majority element exists.
- Therefore, the final `candidate` can be returned directly.
- `count` represents the current balance of votes for the candidate.
- When `count` becomes `0`, the current candidate has been completely cancelled by different elements, so a new candidate can be selected.
- The algorithm does not need to count the actual frequency of every element.

---

## 📌 LeetCode

**Problem:** Majority Element

**LeetCode Number:** 169

**Difficulty:** Easy

**Topics:** Array, Boyer-Moore Voting Algorithm

---