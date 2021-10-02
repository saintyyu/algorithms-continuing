package str;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/2 10:28 下午
 */
public class ReverseCharsInParentheses {

    /**
     * 1190. 反转每对括号间的子串
     * https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/
     *
     * 给一个字符串，遇到括号将里边的字符串则将括号里的字符串翻转：
     *  Examples : Input:
     *  * s = "(abcd)"
     *  * Output: "dcba"
     *  * Input: s = "(e(hope)s)"
     *  * Output: "shopee"
     *  * Input: s = "(ee(sh(po))og)"
     *  * Output: "goshopee"
     *  Input: s = " (og)to(e(hope)s)"
     *  Output: "gotoshopee"
     *  思路：先寻找到括号对，然后每次遇到括号就跳转去对应的括号位置并按照相反方向行走即可。
     *  时间复杂度：O(2n)
     *  空间复杂度：O(n)
     */
    public String reverseParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int[] pair = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                Integer j = stack.pop();
                pair[j] = i;
                pair[i] = j;
            }
        }
        StringBuilder builder = new StringBuilder();
        // i是当前位置 | d是方向,1就是向右
        for (int i = 0, d = 1; i < s.length(); i += d) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                // 如果碰到括号，那么去他对应的括号，并且将方向置反
                i = pair[i];
                d = -d;
            } else {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
