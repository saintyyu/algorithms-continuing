package backTrace;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/12/12 9:56 下午
 *
 * https://leetcode-cn.com/problems/permutations/
 *      * 问题：给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *      * 示例：
 *      * 输入：nums = [1,2,3]
 *      * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Permute {

    /**
     *
     * 解决思路一：广度优先遍历：以数组中第一个元素开始，逐步往后排，每新到一个元素，就让这个元素依次插入到已有数组的全排列中，得到一个新的全排列，直到所有元素被穷举完
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

    /**
     * 解决思路二：深度优先遍历：回溯法
     */
    public List<List<Integer>> permute_2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;

        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth++, path, used, res);
            path.removeLast();
            used[i] = false;
        }
    }
}
