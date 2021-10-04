package str;


/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/4 12:05 上午
 */
public class FindMinBiggerThanOrigin {
    /**
     * 给定一个由数字字符组成的字符串，比如 1948752775854321，可以对每个数字字符做无限次调整，寻找调整后比当前值大的最小值
     * 1948752775854321 调整后比当前值大的最小值为1948752778123455
     * 思路：从末位开始往前找，找到一个字符比前一个小的，然后两者调换，其他的按照从小到大的顺序排列
     * 实现：用一个长度为10的数组来存储查找过程中遇到的字符出现的次数
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static String findMinBiggerThanOrigin(String str) {
        int[] counts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (str == null || str.trim().equals("")) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = str.length() - 2; i >= 0; i--) {
            if (str.charAt(i) < str.charAt(i + 1)) {
                builder.append(str, 0, i);
                builder.append(str.charAt(i + 1));
                int value = Integer.parseInt(str.charAt(i) + "");
                counts[value] += 1;
                break;
            } else {
                int value = Integer.parseInt(str.charAt(i + 1) + "");
                counts[value] += 1;
            }
        }
        if (builder.length() == 0) {  //说明当前字符串本身就是最大值，不存在比它还大的值。
            return null;
        }
        for (int i = 0; i < 10; i++) {
            int count = counts[i];
            while (count > 0) {
                builder.append(i);
                count--;
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
//        System.out.println(findMinBiggerThanOrigin("1948752775854321"));
//        System.out.println(findMinBiggerThanOrigin("19"));
        System.out.println(findMinBiggerThanOrigin("91"));
        System.out.println(findMinBiggerThanOrigin("911"));
        System.out.println(findMinBiggerThanOrigin("912"));
    }
}
