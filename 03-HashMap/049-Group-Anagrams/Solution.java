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