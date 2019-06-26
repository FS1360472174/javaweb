package plan;

import java.util.Arrays;

/**
 * @author cnstonefang@gmail.com
 */
public class Climb {
    public static void main(String[] args) {
        //System.out.println(climbStairs(4));
       // int[] rob1 = new int[] {2,7,9,3,1};
        //System.out.println(rob(rob1));
        //int[] rob2 = new int[] {1,2,3,1};
       // System.out.println(rob(rob2));
        int[] array = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(array));
        int [] array2 = new int[] {-1,-2};
        System.out.println(maxSubArray(array2));
    }

    /**
     * 爬梯子https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/23/dynamic-programming/54/
     * dp[n] = dp[n-2] + dp[n-1]
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if(n < 1) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }
        int dp[] = new int [] {1,2};
        int result = dp[0] + dp[1];
        for (int i = 3; i <= n; i++) {
            result = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = result;
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/23/dynamic-programming/57/
     * dp[i] = max(dp[i-2] + nums[i], dp[i-1])
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (nums.length ==0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length  ==2) {
            return Math.max(nums[0],nums[1]);
        }

        int dp[] = new int[] {nums[0], Math.max(nums[0], nums[1])};
        int result=0;
        for (int i = 2; i < nums.length; i++) {
            result = Math.max(dp[0] + nums[i], dp[1]);
            dp[0] = dp[1];
            dp[1] = result;
        }
        return result;
    }

    /**
     * dp[n] = max[dp[n-1]+n,n]
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }
        int result =  Math.max(nums[0]+nums[1],nums[1]);
        int dp = result;
        int max = Math.max(result, nums[0]);
        for (int i = 2; i< nums.length; i++) {
            result = Math.max(dp+nums[i],nums[i]);
            dp = result;
            // 如果比历史的大，就替换
            if (result > max) {
                max = result;
            }
        }
        return max;
    }

}
