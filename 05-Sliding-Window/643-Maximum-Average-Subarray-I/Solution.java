class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;

        int left = 0;
        int right = k - 1;

        while (right < nums.length - 1) {
            sum -= nums[left];
            left++;

            right++;
            sum += nums[right];

            maxSum = Math.max(maxSum, sum);
        }

        return (double) maxSum / k;
    }
}