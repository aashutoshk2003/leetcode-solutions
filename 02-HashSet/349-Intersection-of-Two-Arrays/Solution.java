import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();

        for (int value : nums1) {
            set.add(value);
        }

        Set<Integer> result = new HashSet<>();

        for (int value : nums2) {
            if (set.contains(value)) {
                result.add(value);
            }
        }

        int i = 0;
        int[] answer = new int[result.size()];

        for (int value : result) {
            answer[i] = value;
            i++;
        }

        return answer;
    }
}