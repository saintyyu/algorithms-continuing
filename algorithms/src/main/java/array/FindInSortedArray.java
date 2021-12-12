package array;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/5/27 11:02 下午
 */
public class FindInSortedArray {

    /**
     * 问题：从一个整数数组中快速查找是否存在某个元素，数组每行、每列都是有序的（递增）；
     * 示例：
     * {1, 4, 7, 10},
     * {2, 5, 8, 11},
     * {3, 6, 9, 12}
     * 5在数组中，13则不在数组中。
     * <p>
     * 分析：对于数组中的任意一个元素，其左边和上边两个元素都比它小，而下边和右边都比他大，因而可以从数组的最左下角开始进行查询，
     * 如果当前元素和目标元素相等则返回true，如果当前元素比目标元素小，则往右移动；否则往上移动，直到返回true或到达边界返回false。
     * 其实从左下角或者右上角都可以。
     *
     * 从左下角开始
     * 时间复杂度（最大值）：O(r+c)，其中r为行数，c为列数
     */
    public static boolean exist(int[][] array, int target) {
        if (array == null) {
            return false;
        }
        int row = array.length - 1;
        int column = 0;
        while (row >= 0 && column < array[0].length) {
            if (array[row][column] == target) {
                return true;
            } else if (array[row][column] > target) {
                row--;
            } else {
                column++;
            }
        }
        return false;
    }

    /**
     * 从右上角开始
     * @param array
     * @param target
     * @return
     */
    public static boolean exists(int[][] array, int target) {
        if (array == null) {
            return false;
        }
        int column = array[0].length - 1;
        int row = 0;
        while (column >= 0 && row < array.length) {
            if (array[row][column] == target) {
                return true;
            } else if (array[row][column] < target) {
                row++;
            } else {
                column--;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] array = {{1, 4, 7, 10}, {2, 5, 8, 11}, {3, 6, 9, 12}};
        System.out.println(exist(array, 5));
        System.out.println(exist(array, 13));
        System.out.println(exists(array, 5));
        System.out.println(exists(array, 13));
    }
}
