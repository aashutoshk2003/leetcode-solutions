import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int value : nums) {
            if (set.contains(value)) {
                return true;
            }

            set.add(value);
        }

        return false;
    }
}