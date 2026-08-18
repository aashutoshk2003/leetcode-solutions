class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, n - 1, n - k);
        reverse(nums, n - k - 1, 0);
        reverse(nums, n - 1, 0);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start > end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start--;
            end++;
        }
    }
}