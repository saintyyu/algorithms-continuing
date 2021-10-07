package backTrace;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/7 6:04 下午
 */
public class EightQueen {
    /**
     * 问题：在8×8格的国际象棋上摆放8个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线（对角）上，问有多少种摆法。
     * 分析：每种摆法都是每行每列摆一个皇后。
     * 时间复杂度：O(n^n)
     * 空间复杂度：O(n)
     */
    public static int count = 0;
    public static void main(String[] args) {
        eightQueen(8);
        System.out.println(count);
    }
    public static void eightQueen(int n) {
        check(0, 8, new int[n]);
    }

    //array存储的index表示行号，value为列号
    public static void check(int m, int max, int[] array) {
        if (m == max) {
            count++;
            return;
        }
        for (int i = 0; i < max; i++) {
            array[m] = i;
            if (judge(m, array)) {
                check(m + 1, max, array);
            }
        }
    }

    public static boolean judge(int n, int[] array) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || (n - i) == Math.abs(array[i] - array[n])) {
                return false;
            }
        }
        return true;
    }
}
