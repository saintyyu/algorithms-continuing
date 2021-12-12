package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/12/12 9:56 下午
 */
public class Permute {

    /**
     * https://leetcode-cn.com/problems/permutations/
     * 问题：给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * 示例：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * 解决思路：
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> listList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        listList.add(list);
        permute(nums, 1, listList);
        return listList;
    }

    public void permute(int[] nums, int index, List<List<Integer>> listList) {
        if (index == nums.length) {
            return;
        }
        int size = listList.size();
        while (size > 0) {
            size--;
            List<Integer> list = listList.remove(0);
            int i = 0;
            while (i <= index) {
                List<Integer> tmpList = new ArrayList<>(list);
                tmpList.add(i, nums[index]);
                listList.add(tmpList);
                i++;
            }
        }

        permute(nums, index + 1, listList);
    }
}
