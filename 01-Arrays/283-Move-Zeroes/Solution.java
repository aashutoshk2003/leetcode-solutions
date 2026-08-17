class Solution {
    public void moveZeroes(int[] arr) {
        int position = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[position] = arr[i];
                if (position != i) {
                    arr[i] = 0;
                }
                position++;
            }
        }
    }
}