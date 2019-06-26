package array;

/**
 * @author cnstonefang@gmail.com
 */
public class LeetcodeArray {
    public static  void main(String[] args) {
        int[] nums1 = new int[] { 0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums1));
        int[] nums2 = new int[] {1,1,2};
        System.out.println(removeDuplicates(nums2));
    }

    /**
     * 这道题思路不清晰，写的不好
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int count =0;
        int value = 0;
        for (int i = 1; i< nums.length- count; i++) {
            if (nums[i] == nums[i-1]) {
                value = nums[i];
                for (int j = i; j < nums.length - count-1; j ++) {
                    nums[j] = nums[j+1];
                }
                nums[nums.length - count -1] = value;
                count += 1;
                i--;
            }
        }
        return nums.length - count;
    }
}
