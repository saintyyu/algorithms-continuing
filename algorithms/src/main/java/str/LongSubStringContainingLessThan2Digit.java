package str;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/3 10:39 下午
 */
public class LongSubStringContainingLessThan2Digit {
    /**
     * https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters/
     *
     * // Given a string, find out the length of the longest substring which contains at most two distinct characters
     *     //示例：
     * // abbccc
     * // 5：bbccc
     * // aaaaa
     * // 5：aaaaa
     * //ababaacddc
     * // 6：ababaa
     *
     * 思路：遍历字符串各字符，依次统计以某个字符开始满足要求的字串长度，取最长者返回。用一个Set来记录被依次扫描到的字符，当Set的size为3
     * 时，表示从起始扫描位置到当前位置（当前字符不算）即为以某个字符开始的子串。这里有个很关键的点是，以某个字符串开始统计之后，下一次统计的位置该如何确定：
     * 以 ababccccc为例，以a开头的最长为abab，那么下一个需要统计的一定是bccccc；再以abbbbbccccc为例，以a开头的最长是abbbbb，那么下一个需要统计的是bbbbbccccc。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int findLongestSubStringSize(String str) {
        if (str == null) {
            return 0;
        }
        if (str.length() <= 2) {
            return str.length();
        }
        int max = 0;
        Set<Character> characters = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (max > str.length() - i) {
                return max;
            }
            characters.clear();
            characters.add(str.charAt(i));
            for (int j = i+1; j < str.length(); j++) {
                characters.add(str.charAt(j));
                if (characters.size() == 3) {
                    max = Math.max(max, j - i);
                    i = j - 1;  //这里对起始点i的调整可以让两个for循环的时间复杂度降低到O(n)
                    char ch = str.charAt(i);
                    while (str.charAt(i) == ch) {
                        i--;
                    }
                    break;
                } else {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(findLongestSubStringSize("abbbbbccccc"));
    }
}
