package array;

/**
 * @author cnstonefang@gmail.com
 */
public class SortedArray {

    public static void main(String[] args) {
        int [] nums = new int[] {1,1,2,2,3,3,4,4,5,8,8};
        System.out.println(singleNonDuplicate(nums));
        int [] nums2 = new int[] {1,1,2};
        System.out.println(singleNonDuplicate(nums2));
    }

    /**
     * 找出有序数组中唯一只出现过1次的数字
     * @param nums
     * @return
     */
    public static int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length;
        int i =  (start + end)/2;
        while(end -start >1) {
            if (nums[i] != nums[i-1] && nums[i] != nums[i+1]){
                break;
            }

            if (nums[i] == nums[i+1]) {
                if (i %2 == 0) {
                    start = i +2;
                } else {
                    end = i;
                }

            } else if (nums[i] == nums[i-1]) {
                if (i % 2 == 0) {
                    end = i-1;
                } else {
                    start = i + 1;
                }
            }
            i = (start + end)/2;
        }
        return nums[i];
    }
}
