package chapter_my_train;

import java.util.HashMap;

class SolutionSlideWindow {
    /**
     * 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
     *
     * 示例 1:
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character,Integer> map = new HashMap<Character, Integer>();
        int maxLen = 0;
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            char curChar = s.charAt(right);
            if (map.containsKey(curChar)) {
                // 取出当前 map 重复字符对应的下标
                int index = map.get(curChar);
                // 将 left 更新到重复字符对应的下一个位置
                left = Math.max(index + 1, left);
            }
            map.put(curChar, right);
            maxLen = Math.max(maxLen, right-left+1);
            right++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        SolutionSlideWindow solution = new SolutionSlideWindow();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb")); // 输出：3
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));    // 输出：1
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));   // 输出：3
    }
}
