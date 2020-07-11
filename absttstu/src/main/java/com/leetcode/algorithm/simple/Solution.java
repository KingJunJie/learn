package com.leetcode.algorithm.simple;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author King
 * @since 2020/7/9 19:01
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 8, 10};
        int target = 10;
        new Solution().twoSum(nums, target);
    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        System.out.println("返回数组长度" + result.length);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    if (i == j) {
                        continue;
                    }
                    result[0] = i;
                    result[1] = j;
                    System.out.println(result[0] + "和" + result[1]);
                    return result;
                }
            }
        }
        return result;
    }
}
