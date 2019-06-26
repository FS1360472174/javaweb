package greedy;

/**
 * @author cnstonefang@gmail.com
 */
public class Parse {
    public static void main(String[] args) {
       int[] nums = new int[] {2,3,1,1,4};
       int [] nums2 = new int[] {3,2,1,0,4};
       int[] nums3 = new int[] {2,0};
       System.out.println(canJump(nums));
       System.out.println(canJump(nums2));
       System.out.println(canJump(nums3));
    }


    /**
     * 通配符匹配，
     * https://leetcode-cn.com/problems/wildcard-matching/description/
     * 这题简单的做法应该就是迭代判断
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        return true;
    }


    /**
     * 跳跃游戏，就是图
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int i;
        int right ;
        int left;
        for (i =0; i< nums.length && nums[i] !=0;) {
            right = i + nums[i];
            left = i + 1;
            for (int j = i+1; j< nums.length && j<= right; j++) {
                if (j + nums[j] >= right) {
                    right = j + nums[j];
                    left = j;
                }
            }
            i = left;
        }
        return i == nums.length;
    }
}
