package chapter_my_train;

import java.util.HashSet;

public class SolutionHashSet {

    /**
     * 题目描述：最长连续序列
     * https://leetcode.cn/problems/longest-consecutive-sequence/description/
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     *
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     *
     * 示例 1：
     *
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * 示例 2：
     *
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     *
     *
     * 提示：
     *
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     *
     * 解题思路：我们可以使用哈希表（HashSet）来解决这个问题。以下是详细的解题步骤：
     *
     * 遍历整个数组，将所有元素添加到哈希表中。这样我们可以在O(1)的时间内判断一个元素是否在数组中。
     *
     * 再次遍历数组，对于每个元素，检查其前一个元素是否在哈希表中。如果前一个元素不在哈希表中，说明当前元素是一个连续序列的起始点。然后从当前元素开始向后寻找连续的元素，记录连续序列的长度。
     *
     * 在遍历过程中，维护一个变量maxLen来记录最长连续序列的长度。最后返回maxLen作为结果。
     *
     * 这种方法的时间复杂度为O(n)，因为我们只需要遍历数组两次。空间复杂度为O(n)，因为我们需要使用哈希表来存储数组中的元素。
     *
     * 这个Java实现首先遍历数组，将所有元素添加到HashSet中。
     * 然后再次遍历数组，寻找最长连续序列。
     * 在遍历过程中，维护一个变量maxLen来记录最长连续序列的长度。最后返回maxLen作为结果。
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> numSet = new HashSet<>();g

        // 将所有元素添加到哈希表中
        for (int num : nums) {
            numSet.add(num);
        }

        int maxLen = 0;

        // 遍历数组，寻找最长连续序列
        for (int num : nums) {
            if (!numSet.contains(num - 1)) { // 当前元素是连续序列的起始点
                int currentNum = num;
                int currentLen = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentLen += 1;
                }

                maxLen = Math.max(maxLen, currentLen);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        SolutionHashSet solution = new SolutionHashSet();

        int a [] = {100,4,200,1,3,2};
        int b [] = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(solution.longestConsecutive(a)); // 输出：4
        System.out.println(solution.longestConsecutive(b));   // 输出：9
    }

}
