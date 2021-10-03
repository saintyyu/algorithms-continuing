package array;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/3 3:35 下午
 */
public class QuickSearch {

    /**
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
     * 已知存在一个按非降序(num[k+1]>=num[k]）排列的整数数组 nums ，数组中的值不必互不相同。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0],
     * nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 该index ，否则返回 -1
     * 分析：利用二分法，每次二分之后，一定至少有一半是有序的，利用有序进行比较，另一半为else即可快速二分。
     * 4,4,4,2,4
     */
    public static int quickSearch_pair_asc(int[] array, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right)/2;
        if (array[middle] == target) {
            return middle;
        }
        if (array[left] <= array[middle]) {   //左边有序
            if (array[left] <= target && target <= array[middle]) {  //左边查找
                return quickSearch_pair_asc(array, left, middle-1 , target);
            } else {  //右边查找
                return quickSearch_pair_asc(array, middle+1 , right, target);
            }
        } else {  //右边有序
            if (array[middle] <= target && target <= array[right]) {  //右边查找
                return quickSearch_pair_asc(array, middle+1, right, target);
            } else {  //左边查找
                return quickSearch_pair_asc(array, left, middle-1, target);
            }
        }
    }
}
