package leetcode.topic001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/two-sum/description/">1. 两数之和</a>
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 10, 11, 12, 14, 15};
        int target = 12;
        System.out.println(Arrays.toString(new TwoSum().twoSum(nums, target)));
    }

    /**
     * two sum
     * <p>
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     */
    int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                map.put(nums[i], i);
                continue;
            }
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
