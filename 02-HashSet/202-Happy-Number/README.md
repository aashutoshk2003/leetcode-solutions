# LeetCode #202 - Happy Number

**Difficulty:** Easy

**Topic:** HashSet

**Pattern:** Cycle Detection

---

## 📝 Problem

A happy number is a number defined by the following process:

Starting with a positive integer `n`, repeatedly replace the number with the sum of the squares of its digits.

For example:

```text
19
```

becomes:

```text
1² + 9²
= 1 + 81
= 82
```

Then:

```text
82
→ 8² + 2²
→ 64 + 4
→ 68
```

Continue this process.

If the process eventually reaches:

```text
1
```

then the number is a **happy number**.

If the process enters a cycle that never reaches `1`, then the number is not a happy number.

Return `true` if `n` is happy, otherwise return `false`.

---

## 💭 Approach

I use a `HashSet` to detect whether a number has already appeared during the process.

The important problem here is that the process can enter an **infinite cycle**.

For example, if a number appears again:

```text
n → ... → n
```

then the same sequence will repeat forever.

To detect this:

- Store every number encountered in the `HashSet`.
- Before processing a number, check whether it already exists in the set.
- If it exists, a cycle has been detected, so return `false`.
- Otherwise, add it to the set and calculate the sum of the squares of its digits.
- Continue until `n` becomes `1`.

If `n` reaches `1`, return `true`.

---

## 🔄 Algorithm

1. Create a `HashSet` to store previously seen numbers.
2. Continue while `n` is not `1`.
3. Check whether `n` already exists in the set.
4. If it exists:
    - A cycle has been detected.
    - Return `false`.
5. Add `n` to the set.
6. Calculate the sum of the squares of its digits:
    - Get the last digit using:

```text
digit = n % 10
```

- Add its square to `sum`.
- Remove the last digit using:

```text
n = n / 10
```

7. Set `n = sum`.
8. Repeat the process.
9. If `n` becomes `1`, return `true`.

---

## 🔍 Example Walkthrough

Consider:

```text
n = 19
```

Initially:

```text
set = {}
```

### Step 1

Current number:

```text
19
```

`19` is not in the set.

Add it:

```text
set = {19}
```

Calculate the sum of digit squares:

```text
1² + 9²
= 1 + 81
= 82
```

Now:

```text
n = 82
```

---

### Step 2

Current number:

```text
82
```

`82` is not in the set.

Add it:

```text
set = {19, 82}
```

Calculate:

```text
8² + 2²
= 64 + 4
= 68
```

Now:

```text
n = 68
```

---

### Step 3

Current number:

```text
68
```

Add it to the set.

Calculate:

```text
6² + 8²
= 36 + 64
= 100
```

Now:

```text
n = 100
```

---

### Step 4

Current number:

```text
100
```

Calculate:

```text
1² + 0² + 0²
= 1
```

Now:

```text
n = 1
```

The loop ends.

Therefore:

```text
Output = true
```

---

## 🔍 Cycle Example

Consider a number that eventually enters a cycle.

The process may look like:

```text
... → A → B → C → A → B → C → ...
```

When `A` appears for the second time:

```text
set.contains(A)
```

returns:

```text
true
```

Therefore, a cycle has been detected.

The algorithm returns:

```text
false
```

This prevents the program from running forever.

---

## 💻 Java Solution

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        while (n != 1) {
            if (set.contains(n)) {
                return false;
            }

            set.add(n);

            int sum = 0;

            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n = n / 10;
            }

            n = sum;
        }

        return true;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(log n)
```

The number of digits in `n` is `O(log n)`, and each step calculates the sum of the squares of those digits.

The process eventually reaches `1` or enters a cycle, so only a limited number of transformed values are processed.

### Space Complexity

```text
O(log n)
```

The `HashSet` stores the previously encountered values used for cycle detection.

---

## 💡 Key Learning

- A `HashSet` can be used for **cycle detection**, not only for duplicate checking.
- The important question is:

```text
Have I seen this state before?
```

- If a state appears again, the process has entered a cycle.
- `HashSet.contains()` allows us to detect repeated states efficiently.
- The `%` operator can be used to extract the last digit.
- Integer division by `10` removes the last digit.
- The process can be broken into two parts:
    - Generate the next number.
    - Detect whether a number has been seen before.

---

## ⚠️ Important Points

- Stop immediately when `n == 1`.
- Check whether the current `n` already exists in the set before processing it.
- Add the current `n` to the set before calculating the next value.
- `% 10` extracts the last digit.
- `/ 10` removes the last digit.
- A repeated value means the process is cycling and will never reach `1`.
- The `HashSet` prevents an infinite loop by detecting the cycle.

---

## 📌 LeetCode

**Problem:** Happy Number

**LeetCode Number:** 202

**Difficulty:** Easy

**Topics:** HashSet, Math, Cycle Detection

[LeetCode Problem #202 - Happy Number](https://leetcode.com/problems/happy-number/)

---