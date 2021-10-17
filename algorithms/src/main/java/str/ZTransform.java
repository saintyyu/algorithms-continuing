package str;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/17 3:48 下午
 *
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 * 示例 1：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ZTransform {

    /**
     * 分析：这里题目说是Z变换，个人觉得还不如说是倒N变换，因为实际上题目是说的将字符串按如下顺序排列：
     * 0     6        12
     * 1   5 7     11 13
     * 2 4   8  10    14
     * 3     9
     * 这更像是将N上下颠倒过来。其实从上面的字符序列号的排列来看，变换是有规律的。为了找到规律，我们再排一个行数为5的：
     * 0       8           16
     * 1     7 9        15 17
     * 2   6   10    14    18
     * 3 5     11 13
     * 4       12
     * 由此你会发现：如果说定义总行数为r，则第1行和第r行的两列之间的间隔为2*r-2，剩下的第i行的两列之间的间隔循环地为2*r-2-2*i以及2*i。
     * 实现：根据规律，以行号i循环，将对应列的字符添加到StringBuilder，每一行以字符串的长度为界。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(s.length() <= numRows || numRows == 1) {
            return s;
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i<numRows;i++) {
            int next = i;
            if(i == 0 || i == numRows - 1) {
                while(s.length() > next) {
                    builder.append(s.charAt(next));
                    next += 2*numRows - 2;
                }
            } else {
                boolean flag = true;
                while(s.length() > next) {
                    builder.append(s.charAt(next));
                    if(flag) {
                        next += 2*numRows - 2 - 2*i;
                        flag = false;
                    } else {
                        next += 2*i;
                        flag = true;
                    }
                }
            }
        }
        return builder.toString();
    }
}
