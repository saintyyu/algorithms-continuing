package dynamic;

import java.util.Arrays;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/5 8:57 下午
 */
public class MaxSubSum {

    /**
     * Create by yujing10 on 2020/10/5.
     * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
     * 今天测试组开完会后,他又发话了:在古老的一维模式识别中,
     * 常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
     * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
     * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
     * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
     *
     * https://www.nowcoder.com/practice/459bd355da1549fa8a49e350bf3df484?tpId=13&&tqId=11183&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     *
     * 链接：https://www.nowcoder.com/questionTerminal/459bd355da1549fa8a49e350bf3df484?answerType=1&f=discussion
     * 来源：牛客网
     *
     * 典型的动态规划。dp[n]代表以当前元素为截止点的连续子序列的最大和，
     * 如果dp[n-1]>0，dp[n]=dp[n]+dp[n-1]，因为当前数字加上一个正数一定会变大；
     * 如果dp[n-1]<0，dp[n]不变，因为当前数字加上一个负数一定会变小。
     * 使用一个变量max记录最大的dp值返回即可。
     */
    public static int findGreatestSumOfSubArray(int[] array) {
        if(array == null) {
            return 0;
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            array[i] += Math.max(array[i - 1], 0);
            max = Math.max(max, array[i]);
        }
        return max;
    }


    /**
     * 上面题目只是计算最大子串的和，这个题目需要将最大子串返回
     * @param array
     * @return
     */
    public static int[] findGreatestSubArrayWithBiggestSum(int[] array) {
        if(array == null || array.length == 0) {
            return null;
        }
        int[] tmp = Arrays.copyOfRange(array, 0, array.length);
        int max = tmp[0];
        int maxStart = 0;
        int maxEnd = 0;
        int tmpStart = 0;
        for (int i = 1; i < array.length; i++) {
            tmp[i] += Math.max(tmp[i - 1], 0);
            if (tmp[i - 1] < 0) {
                tmpStart = i;
            }
            if (max < tmp[i]) {
                max = tmp[i];
                maxStart = tmpStart;
                maxEnd = i;
            }
        }
        return Arrays.copyOfRange(array, maxStart, maxEnd+1);
    }

    public static void main(String[] args) {
        int[] array = {6,-3,-2,7,-15,1,2,2};
        System.out.println(findGreatestSumOfSubArray(array));
    }
}
