package array;

/**
 * @author cnstonefang@gmail.com
 */
public class SimpleArray {
    public static  void main(String[] args) {
        int[] nums = new int[] {2, 7, 11, 15};
        int target = 13;
        int[] result = twoSum(nums, target);
        System.out.println(result);

        int[] nums1 = new int [] {1, 2};
        int[] nums2 = new int [] {3, 4};
        double medResult = findMedianSortedArrays(nums1, nums2);
        System.out.println(medResult);
    }

    /**
     * 求数组和
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i< nums.length; i ++ ){
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int []{0,0};
    }

    /**
     * 找到两个有序数组的中位数
     * @param nums1
     * @param nums2
     * @return
     */
    private  static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 合并两个有序数组
        int[] nums3 = new int[nums1.length + nums2.length];
        int nums1Index = 0;
        int nums2Index = 0;
        int nums3Index = 0;
        while (nums1Index < nums1.length && nums2Index < nums2.length) {
            if (nums1[nums1Index] < nums2[nums2Index]) {
                nums3[nums3Index++] = nums1[nums1Index++];
            } else if (nums1[nums1Index] > nums2[nums2Index]){
                nums3[nums3Index++] = nums2[nums2Index++];
            } else {
                nums3[nums3Index++] = nums1[nums1Index++];
                nums3[nums3Index++] = nums2[nums2Index++];
            }
        }
        if (nums1Index >= nums1.length) {
            System.arraycopy(nums2,nums2Index,nums3,nums3Index, nums3.length - nums3Index);
        } else {
            System.arraycopy(nums1,nums1Index,nums3,nums3Index, nums3.length - nums3Index);
        }
        double result = 0;
        if (nums3.length%2 ==0) {
            result = (double)(nums3[nums3.length/2 -1] + nums3[nums3.length/2])/2;
        } else {
            result = (double)nums3[nums3.length/2];
        }
        return result;
    }

    /**
     * 找到最大的盛水容器
     * O(n^2)O(n​2
     ​​ )
     * @param height
     * @return
     */
    private static int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
}
