import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int value : nums) {
            set.add(value);
        }

        int longest = 0;

        for (int value : set) {
            if (!set.contains(value - 1)) {
                int current = value;
                int count = 1;

                while (set.contains(current + 1)) {
                    current++;
                    count++;
                }

                longest = Math.max(count, longest);
            }
        }

        return longest;
    }
}