package dynamic;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/4 4:29 下午
 */
public class ClimbStairs {

    /**
     * n阶楼梯，可以一次走一步，也可以一次走两步，问一共有多少总走法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int climbStairs_1(int n) {
        if (n <= 2) {
            return n;
        }
        int[] ways = new int[n+1];
        ways[0] = 1;
        ways[1] = 2;
        for (int i=3;i<=n;i++) {
            ways[i] = ways[i - 2] + ways[i - 1];
        }
        return ways[n];
    }

    /**
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int climbStairs_2(int n) {
        if (n <= 2) {
            return n;
        }
        int a1 = 1;
        int a2 = 2;
        for (int i=3;i<=n;i++) {
            int tmp = a1 + a2;
            a1 = a2;
            a2 = tmp;
        }
        return a2;
    }

}
