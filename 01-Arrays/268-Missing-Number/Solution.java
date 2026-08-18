class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = 0;
        int actualSum = 0;

        for (int i = 0; i <= n; i++) {
            expectedSum += i;
        }

        for (int value : nums) {
            actualSum += value;
        }

        return expectedSum - actualSum;
    }
}