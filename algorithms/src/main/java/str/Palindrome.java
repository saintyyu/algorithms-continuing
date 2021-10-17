package str;

import com.sun.deploy.uitoolkit.impl.fx.AppletStageManager;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/16 9:25 下午
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出："a"
 *
 * 示例 4：
 *
 * 输入：s = "ac"
 * 输出："a"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Palindrome {
    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度O(1)
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s.length() == 1) {
            return s;
        }
        int[] indexes = {0, 0};
        for(int i=0;i<s.length();i++){
            int[] tmp = longestPalindromeWithStartIndex(s, i);
            if(tmp[1] - tmp[0] > indexes[1] - indexes[0]) {
                indexes = tmp;
            }
        }
        return s.substring(indexes[0], indexes[1] + 1);
    }

    public int[] longestPalindromeWithStartIndex(String s, int startIndex) {
        if(startIndex == 0) {
            int[] result = {0,0};
            if(s.charAt(0) == s.charAt(1)) {
                result[1] = 1;
            }
            return result;
        } else if(startIndex == s.length() - 1) {
            int[] result = {s.length() - 1,s.length() - 1};
            if(s.charAt(s.length() - 1) == s.charAt(s.length() - 2)) {
                result[0] = s.length() - 2;
            }
            return result;
        } else {
            int left = startIndex - 1;
            int right = startIndex + 1;
            while(left >= 0 && right <= s.length() - 1) {
                if(s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            int[] result1 = {left + 1, right - 1};
            if(s.charAt(startIndex) != s.charAt(startIndex + 1)) {
                return result1;
            }
            left = startIndex - 1;
            right = startIndex + 2;
            while(left >= 0 && right <= s.length() - 1) {
                if(s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            int[] result2 = {left + 1, right - 1};
            return result1[1] - result1[0] > result2[1] - result2[0] ? result1 : result2;
        }
    }
}
