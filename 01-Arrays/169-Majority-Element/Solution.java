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