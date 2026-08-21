# LeetCode #49 - Group Anagrams

**Difficulty:** Medium

**Topic:** HashMap

**Pattern:** Grouping by Common Key

---

## 📝 Problem

Given an array of strings `strs`, group the anagrams together.

An **anagram** is a word formed by rearranging the letters of another word.

For example:

```text
"eat"
"tea"
"ate"
```

are anagrams because they contain the same characters with the same frequencies.

The order of the groups does not matter.

---

## 💭 Approach

I use a `HashMap` to group strings that are anagrams of each other.

The main idea is to create a **common key** for every anagram.

For each string:

1. Convert the string into a character array.
2. Sort the character array.
3. Convert it back into a string.
4. Use the sorted string as the HashMap key.
5. Store the original string in the list associated with that key.

For example:

```text
eat → aet
tea → aet
ate → aet
```

All three strings produce the same key:

```text
"aet"
```

Therefore, they are stored in the same list.

The HashMap structure looks like:

```text
"aet" → ["eat", "tea", "ate"]
"ant" → ["tan", "nat"]
"aehnst" → ["hello"]
```

The important idea is:

> **Anagrams produce the same sorted string.**

Therefore, the sorted string can be used as a common key to group them.

---

## 🔄 Algorithm

1. Create a `HashMap` where:

```text
key   → sorted characters
value → list of original strings
```

2. Traverse every string in `strs`.
3. Convert the current string to a character array.
4. Sort the character array.
5. Convert the sorted characters back into a string.
6. Use the sorted string as the HashMap key.
7. Get the existing list for that key using:

```java
map.getOrDefault(sortedString, new ArrayList<>())
```

8. Add the original string to the list.
9. Put the list back into the HashMap.
10. After processing all strings, return:

```java
new ArrayList<>(map.values())
```

---

## 🔍 Example Walkthrough

Consider:

```text
strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
```

Initially:

```text
map = {}
```

### Step 1 — `"eat"`

Convert to characters:

```text
['e', 'a', 't']
```

Sort:

```text
['a', 'e', 't']
```

Create key:

```text
"aet"
```

No list exists for `"aet"`, so create one.

```text
map:
"aet" → ["eat"]
```

---

### Step 2 — `"tea"`

Sort:

```text
"tea" → "aet"
```

The key `"aet"` already exists.

Add `"tea"`:

```text
"aet" → ["eat", "tea"]
```

---

### Step 3 — `"tan"`

Sort:

```text
"tan" → "ant"
```

Create a new group:

```text
"ant" → ["tan"]
```

Map:

```text
"aet" → ["eat", "tea"]
"ant" → ["tan"]
```

---

### Step 4 — `"ate"`

Sort:

```text
"ate" → "aet"
```

The `"aet"` group already exists.

Add `"ate"`:

```text
"aet" → ["eat", "tea", "ate"]
```

---

### Step 5 — `"nat"`

Sort:

```text
"nat" → "ant"
```

Add it to the `"ant"` group:

```text
"ant" → ["tan", "nat"]
```

---

### Step 6 — `"bat"`

Sort:

```text
"bat" → "abt"
```

Create a new group:

```text
"abt" → ["bat"]
```

Final HashMap:

```text
"aet" → ["eat", "tea", "ate"]
"ant" → ["tan", "nat"]
"abt" → ["bat"]
```

The final result is:

```text
[
    ["eat", "tea", "ate"],
    ["tan", "nat"],
    ["bat"]
]
```

The exact order of the groups does not matter.

---

## 💻 Java Solution

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String value : strs) {

            char[] chars = value.toCharArray();
            Arrays.sort(chars);
            String sortedString = new String(chars);

            List<String> list =
                    map.getOrDefault(sortedString, new ArrayList<>());

            list.add(value);
            map.put(sortedString, list);
        }

        return new ArrayList<>(map.values());
    }
}
```

---

## 🔎 Important HashMap Concept

The HashMap has this structure:

```text
Map<String, List<String>>
```

So:

```text
Key   → String
Value → List<String>
```

For example:

```text
"aet" → ["eat", "tea", "ate"]
```

The key identifies the group, while the list stores all strings belonging to that group.

### Why `List<String>` as the Value?

One sorted key can represent multiple original strings.

For example:

```text
eat → aet
tea → aet
ate → aet
```

Therefore, we need:

```text
"aet" → ["eat", "tea", "ate"]
```

instead of:

```text
"aet" → "eat"
```

This is why the HashMap value is a `List<String>`.

---

## 🔑 Why Do We Sort the String?

Anagrams contain exactly the same characters.

For example:

```text
eat
tea
ate
```

After sorting:

```text
eat → aet
tea → aet
ate → aet
```

Since all anagrams produce the same sorted string, the sorted string becomes a reliable common key.

---

## 🔄 Connection With Previous HashMap Problems

In previous problems, we used HashMap to remember different kinds of information:

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

Here:

```text
sorted string → List<String>
```

This is another important HashMap pattern:

> **Use a calculated key to group related values.**

The HashMap itself does not know that two strings are anagrams.

We create a common representation:

```text
sorted string
```

and use that representation as the key.

---

## ⏱️ Complexity

Let:

```text
n = number of strings
k = maximum length of a string
```

### Time Complexity

For every string:

1. Convert to character array:

```text
O(k)
```

2. Sort the characters:

```text
O(k log k)
```

3. HashMap operations:

```text
O(k)
```

Therefore, for `n` strings:

```text
O(n × k log k)
```

The sorting operation is the dominant part.

### Space Complexity

The HashMap stores all strings and their groups.

The auxiliary space is approximately:

```text
O(n × k)
```

because the stored strings contain a total of up to `n × k` characters.

---

## 💡 Key Learning

- A HashMap can be used to **group related values**.
- We can create a common key from an object's properties.
- Anagrams can be identified by sorting their characters.
- The sorted string acts as the **group key**.
- A HashMap value can itself be a collection such as `List<String>`.
- `getOrDefault()` is useful when creating grouped collections.
- The general pattern is:

```text
Create common key
        ↓
Find/Create group
        ↓
Add current value
```

---

## ⚠️ Important Points

- Anagrams must contain the same characters with the same frequencies.
- Sorting gives anagrams the same key.
- The original string must be stored in the list, not the sorted string.
- Multiple strings can have the same sorted key.
- Therefore, the HashMap value is a `List<String>`.
- `map.values()` returns all the grouped lists.
- `new ArrayList<>(map.values())` converts those map values into the required `List<List<String>>` result.

---

## 📌 LeetCode

**Problem:** Group Anagrams

**LeetCode Number:** 49

**Difficulty:** Medium

**Topics:** Array, String, HashMap, Sorting

[LeetCode Problem #49 - Group Anagrams](https://leetcode.com/problems/group-anagrams/)

---