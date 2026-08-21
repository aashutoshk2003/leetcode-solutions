# LeetCode #347 - Top K Frequent Elements

**Difficulty:** Medium

**Topic:** HashMap

**Pattern:** Frequency Counting + PriorityQueue

---

## 📝 Problem

Given an integer array `nums` and an integer `k`, return the `k` most frequent elements.

You may return the answer in any order.

---

## 💭 Approach

I use a combination of **HashMap** and **PriorityQueue**.

The main pattern in this solution is **frequency counting using HashMap**.

### Step 1 — Count Frequencies

First, I use a `HashMap` to store:

```text
number → frequency
```

For every value in the array:

```java
map.put(value, map.getOrDefault(value, 0) + 1);
```

This increases the frequency of each number.

For example:

```text
nums = [1, 1, 1, 2, 2, 3]
```

The HashMap becomes:

```text
1 → 3
2 → 2
3 → 1
```

### Step 2 — Use PriorityQueue

After calculating the frequencies, I use a `PriorityQueue` as a **Max Heap**.

The priority is based on the frequency stored in the HashMap:

```java
(a, b) -> map.get(b) - map.get(a)
```

This makes the element with the highest frequency appear at the top of the PriorityQueue.

### Step 3 — Get Top K Elements

I add every unique value from the HashMap into the PriorityQueue.

Then I remove the top element `k` times using:

```java
pq.poll()
```

Each polled element is one of the `k` most frequent elements.

---

## 🔄 Algorithm

1. Create a `HashMap`.
2. Traverse the array and count the frequency of every value.
3. Create a `PriorityQueue` configured as a Max Heap based on frequency.
4. Add every unique value from the HashMap into the PriorityQueue.
5. Create a result array of size `k`.
6. Remove the highest-frequency element from the PriorityQueue using `poll()`.
7. Store it in the result array.
8. Repeat until `k` elements are collected.
9. Return the result.

---

## 🔍 Example Walkthrough

Consider:

```text
nums = [1, 1, 1, 2, 2, 3]
k = 2
```

### Step 1 — Count Frequencies

Traverse the array.

```text
1 → 3
2 → 2
3 → 1
```

HashMap:

```text
{
    1 → 3,
    2 → 2,
    3 → 1
}
```

---

### Step 2 — Add Values to PriorityQueue

Add:

```text
1
2
3
```

The PriorityQueue is ordered by frequency.

So the highest-frequency element is at the top:

```text
1 → frequency 3
```

Then:

```text
2 → frequency 2
3 → frequency 1
```

---

### Step 3 — Get First Element

```java
pq.poll()
```

returns:

```text
1
```

Result:

```text
[1, _]
```

---

### Step 4 — Get Second Element

Again:

```java
pq.poll()
```

returns:

```text
2
```

Result:

```text
[1, 2]
```

Since:

```text
k = 2
```

we stop.

Final output:

```text
[1, 2]
```

---

## 💻 Java Solution

```java
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int value : nums) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> map.get(b) - map.get(a));

        for (int value : map.keySet()) {
            pq.add(value);
        }

        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        return result;
    }
}
```

---

## ⏱️ Complexity

Let:

```text
n = number of elements in nums
u = number of unique elements
```

### Time Complexity

Counting frequencies:

```text
O(n)
```

Adding all unique elements to the PriorityQueue:

```text
O(u log u)
```

Removing the top `k` elements:

```text
O(k log u)
```

Overall:

```text
O(n + u log u + k log u)
```

Since:

```text
u <= n
```

this can be expressed as:

```text
O(n log n)
```

in the worst case.

### Space Complexity

The HashMap stores up to `u` unique elements:

```text
O(u)
```

The PriorityQueue also stores up to `u` elements:

```text
O(u)
```

The result array stores `k` elements:

```text
O(k)
```

Overall:

```text
O(u + k)
```

Since `k <= u`:

```text
O(u)
```

---

## 💡 Key Learning

- `HashMap` is useful for **frequency counting**.
- `getOrDefault()` makes frequency counting concise:

```java
map.put(value, map.getOrDefault(value, 0) + 1);
```

- `PriorityQueue` can be used to select elements based on their frequency.
- The HashMap answers:

```text
How many times does each value occur?
```

- The PriorityQueue answers:

```text
Which value currently has the highest frequency?
```

- This problem combines two data structures to solve the complete problem.

---

## 🔗 Connection With HashMap

This problem is important for understanding why we use HashMap for frequency counting.

Without HashMap, we would repeatedly search the array to count how many times each value occurs.

With HashMap:

```text
value → frequency
```

we can calculate all frequencies in one traversal.

This is a common HashMap pattern that appears in many DSA problems.

---

## ⚠️ Important Points

- The HashMap contains only unique values as keys.
- `getOrDefault()` returns the existing frequency or `0` if the value has not been seen.
- The PriorityQueue is configured as a Max Heap based on frequency.
- `pq.poll()` removes and returns the element with the highest frequency.
- Only `k` elements need to be removed from the PriorityQueue.
- The order of the final result does not matter.

---

## 📌 LeetCode

**Problem:** Top K Frequent Elements

**LeetCode Number:** 347

**Difficulty:** Medium

**Topics:** Array, HashMap, PriorityQueue

[LeetCode Problem #347 - Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)

---