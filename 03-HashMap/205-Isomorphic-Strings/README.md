# LeetCode #205 - Isomorphic Strings

**Difficulty:** Easy

**Topic:** HashMap

**Pattern:** Two-Way Mapping

---

## 📝 Problem

Given two strings `s` and `t`, determine whether they are **isomorphic**.

Two strings are isomorphic if the characters in `s` can be replaced to get `t`.

The mapping must follow these rules:

- Every occurrence of a character must map to the same character.
- Different characters cannot map to the same character.
- A character can map to itself.
- The order of the characters must remain the same.

---

## 💭 Approach

I use **two HashMaps** to maintain the character mapping in both directions.

The two maps are:

```text
mapS → character from s → character from t

mapT → character from t → character from s
```

We need two maps because the mapping must be **one-to-one**.

For example:

```text
s = "egg"
t = "add"
```

The mapping is:

```text
e → a
g → d
```

This is valid.

But consider:

```text
s = "badc"
t = "baba"
```

Here:

```text
b → b
a → a
d → b
```

Both `b` and `d` would map to `b`.

That is not allowed.

Therefore, checking only:

```text
s → t
```

is not enough.

We also need to check:

```text
t → s
```

This is why the solution uses two HashMaps.

---

## 🔄 Algorithm

1. Create two HashMaps:

```text
mapS
mapT
```

2. Traverse both strings using the same index.
3. Get the current characters:

```text
charS = s.charAt(i)
charT = t.charAt(i)
```

4. Check the mapping from `s` to `t`.

If `charS` already exists in `mapS` and its mapped value is not `charT`:

```text
return false
```

5. Check the reverse mapping from `t` to `s`.

If `charT` already exists in `mapT` and its mapped value is not `charS`:

```text
return false
```

6. Store both mappings:

```text
mapS.put(charS, charT)
mapT.put(charT, charS)
```

7. Continue until all characters are processed.
8. If no invalid mapping is found, return `true`.

---

## 🔍 Example Walkthrough

Consider:

```text
s = "egg"
t = "add"
```

Initially:

```text
mapS = {}
mapT = {}
```

### Step 1 — `i = 0`

Characters:

```text
charS = 'e'
charT = 'a'
```

Neither character exists in its map.

Store:

```text
mapS:
e → a

mapT:
a → e
```

---

### Step 2 — `i = 1`

Characters:

```text
charS = 'g'
charT = 'd'
```

Neither character exists in its map.

Store:

```text
mapS:
e → a
g → d

mapT:
a → e
d → g
```

---

### Step 3 — `i = 2`

Characters:

```text
charS = 'g'
charT = 'd'
```

Check `mapS`:

```text
g → d
```

The mapping is correct.

Check `mapT`:

```text
d → g
```

The reverse mapping is also correct.

Continue.

All characters are processed successfully.

Therefore:

```text
Output = true
```

---

## ❌ Example Where Mapping Is Invalid

Consider:

```text
s = "foo"
t = "bar"
```

Initially:

```text
f → b
b → f
```

Next:

```text
o → a
a → o
```

Next:

```text
o → r
```

But `o` was already mapped to:

```text
o → a
```

and now it would need to map to:

```text
o → r
```

This violates the mapping rule.

Therefore:

```text
Output = false
```

---

## ❌ Why Do We Need Two HashMaps?

Consider:

```text
s = "badc"
t = "baba"
```

If we only maintain:

```text
s → t
```

we could create:

```text
b → b
a → a
d → b
c → a
```

Each character from `s` has a mapping.

But the problem says:

> No two characters may map to the same character.

Here:

```text
b → b
d → b
```

Two different characters map to `b`.

Therefore, we also need the reverse mapping:

```text
t → s
```

This second map detects the conflict.

---

## 💻 Java Solution

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> mapS = new HashMap<>();
        Map<Character, Character> mapT = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if (mapS.containsKey(charS) && mapS.get(charS) != charT) {
                return false;
            }

            if (mapT.containsKey(charT) && mapT.get(charT) != charS) {
                return false;
            }

            mapS.put(charS, charT);
            mapT.put(charT, charS);
        }

        return true;
    }
}
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

We traverse the strings once.

Each HashMap lookup and insertion takes `O(1)` average time.

Therefore:

```text
O(n)
```

where `n` is the length of the strings.

### Space Complexity

```text
O(n)
```

In the general case, the HashMaps store the character mappings.

For this problem's bounded ASCII character set, the number of possible distinct characters is bounded, so the practical auxiliary space is constant with respect to `n`. :contentReference[oaicite:1]{index=1}

---

## 💡 Key Learning

- A HashMap can store a **relationship between two values**.
- Here, the relationship is:

```text
character → character
```

- One HashMap checks:

```text
s → t
```

- The second HashMap checks:

```text
t → s
```

- Two-way mapping is important when the relationship must be **one-to-one**.
- `containsKey()` checks whether a character already has a mapping.
- `get()` retrieves the character it was previously mapped to.
- `put()` creates or updates the mapping.

---

## 🔗 Connection With Previous HashMap Problems

In **Two Sum**, we used:

```text
number → index
```

In **Contains Duplicate II**, we used:

```text
number → latest index
```

In **Top K Frequent Elements**, we used:

```text
number → frequency
```

Here, we use:

```text
character → character
```

So the important HashMap idea is not just:

> "Use HashMap."

Instead, ask:

> **"What information do I need to remember for each value?"**

For this problem, we need to remember the character mapping.

---

## ⚠️ Important Points

- Both strings have the same length according to the problem constraints.
- Every character must always map to the same character.
- Two different characters cannot map to the same character.
- This is why two HashMaps are used.
- Always check an existing mapping before updating it.
- Store both directions after validating the current pair.
- If any mapping conflict is found, return `false` immediately.

---

## 📌 LeetCode

**Problem:** Isomorphic Strings

**LeetCode Number:** 205

**Difficulty:** Easy

**Topics:** HashMap, String

[LeetCode Problem #205 - Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/)

---