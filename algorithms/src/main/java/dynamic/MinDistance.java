package dynamic;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/5 8:37 下午
 */
public class MinDistance {

    /**
     * a,b两个字符串，可以进行如下操作：
     * 在任意位置上插入一个字符；
     * 替换掉任意字符；
     * 删除任意字符。
     * 最终将两个字符串改成相同，请问最少需要多少次改动
     */
    public static int minDistance(String a, String b) {
        if (a == null && b == null) {
            return 0;
        }
        if (a == null) {
            return b.length();
        }
        if (b == null) {
            return a.length();
        }

        //ds[i][j]表示源串a的i位置到目标串b的j处最少需要操作的次数
        int[][] ds = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length() + 1; i++) {
            ds[i][0] = i;
        }
        for (int j = 0; j < b.length() + 1; j++) {
            ds[0][j] = j;
        }

        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                //第i个字符是字符串下标为i-1的那个
                if (a.charAt(i - 1) != b.charAt(j - 1)) {
                    ds[i][j] = Math.min(Math.min(ds[i - 1][j], ds[i][j - 1]), ds[i - 1][j - 1]) + 1;
                } else {
                    ds[i][j] = ds[i - 1][j - 1];
                }
            }
        }
        return ds[a.length()][b.length()];
    }
}
