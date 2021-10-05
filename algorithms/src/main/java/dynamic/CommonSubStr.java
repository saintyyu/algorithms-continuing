package dynamic;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/4 4:48 下午
 */
public class CommonSubStr {

    /**
     * Create by yujing10 on 2020/9/30.
     * <p>
     * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，
     * 每步可以删除任意一个字符串中的一个字符。
     * 示例：
     * <p>
     * 输入: "sea", "eat"
     * 输出: 2
     * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
     * <p>
     * 输入："abcd", "abd"
     * 输出：1
     * 解释：第一步将c删除即可。
     * https://leetcode-cn.com/problems/delete-operation-for-two-strings/
     * <p>
     * 分析：像 abc和cba，尽管字符相同，但顺序不同，所以两者的步数为6，像a(bcd)e(fgh)i和(xyz)a(jjk)e(pqq)i
     * 两者相同的是aei三个字符（字符和顺序都同），在其间插入的其他字符不影响。所以我们可以采用递归法，
     * 寻找两个字符串共同的字符串长度，用两者的长度和减去2倍的共同字符串长度即可得到最终结果。
     */
    public int minDistance(String s1, String s2) {
        return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length());
    }
    public int lcs(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            return 1 + lcs(s1, s2, m - 1, n - 1);
        else
            return Math.max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n));
    }

    /**
     * dp[i][j]表示s1的前i个字符和s2的前j个字符中具有相同顺序的字符数
     * @param s1
     * @param s2
     * @return
     */
    public static int minDistance_2(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return s1.length() + s2.length() - 2 * dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        System.out.println(minDistance_2("", ""));
    }
}
