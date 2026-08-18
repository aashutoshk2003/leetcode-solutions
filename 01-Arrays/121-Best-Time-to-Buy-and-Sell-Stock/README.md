# LeetCode #121 - Best Time to Buy and Sell Stock

**Difficulty:** Easy

**Topic:** Array, Greedy

---

## 📝 Problem

You are given an array `prices` where `prices[i]` represents the price of a stock on the `i`-th day.

Choose **one day to buy** the stock and a **different day in the future to sell** the stock.

Return the maximum profit that can be achieved.

If no profit can be made, return `0`.

---

## 💭 Approach

I use a **single-pass approach** by maintaining two values:

- `minPrice` → the lowest stock price seen so far.
- `maxProfit` → the maximum profit found so far.

For every price in the array:

1. If the current price is lower than `minPrice`, update `minPrice`.
2. Calculate the profit by selling at the current price:

```text
profit = current price - minPrice
```

3. If this profit is greater than `maxProfit`, update `maxProfit`.

The important idea is that we always buy at the **lowest price seen before the current day** and check whether selling on the current day gives a better profit.

Because we traverse the array from left to right, the buying day always comes before the selling day.

---

## 🔄 Algorithm

1. Set the first price as the initial `minPrice`.
2. Set `maxProfit = 0`.
3. Start traversing the array from index `1`.
4. If the current price is smaller than `minPrice`:
    - Update `minPrice`.
5. Calculate:

```text
profit = current price - minPrice
```

6. If `profit` is greater than `maxProfit`:
    - Update `maxProfit`.
7. Continue until the entire array is processed.
8. Return `maxProfit`.

---

## 🔍 Example Walkthrough

Consider:

```text
prices = [7, 1, 5, 3, 6, 4]
```

Initially:

```text
minPrice = 7
maxProfit = 0
```

### Step 1 — `i = 1`

Current price:

```text
1
```

Since:

```text
1 < 7
```

Update:

```text
minPrice = 1
```

Calculate profit:

```text
1 - 1 = 0
```

So:

```text
maxProfit = 0
```

---

### Step 2 — `i = 2`

Current price:

```text
5
```

`5` is not smaller than `minPrice`.

Calculate:

```text
profit = 5 - 1
profit = 4
```

Update:

```text
maxProfit = 4
```

---

### Step 3 — `i = 3`

Current price:

```text
3
```

Calculate:

```text
profit = 3 - 1
profit = 2
```

`2` is smaller than the current maximum profit.

So:

```text
maxProfit = 4
```

---

### Step 4 — `i = 4`

Current price:

```text
6
```

Calculate:

```text
profit = 6 - 1
profit = 5
```

Update:

```text
maxProfit = 5
```

---

### Step 5 — `i = 5`

Current price:

```text
4
```

Calculate:

```text
profit = 4 - 1
profit = 3
```

No update is required.

Final:

```text
maxProfit = 5
```

Therefore:

```text
Output = 5
```

The best transaction is:

```text
Buy at 1
Sell at 6
Profit = 5
```

---

## 💻 Java Solution

```java
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            }

            int profit = prices[i] - minPrice;

            if (maxProfit < profit) {
                maxProfit = profit;
            }
        }

        return maxProfit;
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

Only a few variables are used, so no additional data structure is required.

---

## 💡 Key Learning

- We do not need to check every possible buy-and-sell pair.
- Keeping track of the **minimum price seen so far** allows us to calculate the best possible profit for each selling day.
- The current price acts as the possible selling price.
- `minPrice` represents the best buying opportunity found so far.
- `maxProfit` stores the best profit found so far.
- This is a useful **Greedy / single-pass array pattern**.
- The problem can be solved in `O(n)` time and `O(1)` extra space.

---

## ⚠️ Important Points

- The stock must be bought **before** it is sold.
- Therefore, the minimum price must come from an earlier position.
- We traverse from left to right, which naturally maintains this order.
- If prices continuously decrease, no profit can be made, so the answer remains `0`.
- `minPrice` should be updated before calculating the current profit.
- Only one transaction is allowed: one buy and one sell.

---

## 📌 LeetCode

**Problem:** Best Time to Buy and Sell Stock

**LeetCode Number:** 121

**Difficulty:** Easy

**Topics:** Array, Greedy

---