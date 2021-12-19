package backTrace;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/12/16 11:18 下午
 *
 * https://leetcode-cn.com/problems/permutations-ii/
 *      * 问题：给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *      * 示例：
 *      * 输入：nums = [1,1,2]
 *      * 输出：[[1,1,2],[1,2,1],[2,1,1]]
 */
public class PermuteUnique {

    /**
     * 解决思路二：先对原数组进行排序，然后在深度优先
     */

    public List<List<Integer>> permute_2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);
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
            if (used[i] || (i>0 && nums[i] == nums[i-1] && !used[i-1])) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth++, path, used, res);
            path.removeLast();
            used[i] = false;
        }
    }

    /**
     *
     * 解决思路一：以数组中第一个元素开始，逐步往后排，每新到一个元素，就让这个元素一次插入到已有数组的全排列中，得到一个新的全排列，直到所有元素被穷举完，得到的结果一定是会有重复的，那么再用Set
     * 集合进行去重即可，这个方法算是暴力算法，性能比较差。
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {

        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> listList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        listList.add(list);
        permuteUnique(nums, 1, listList);
        List<List<Integer>> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < listList.size(); i++) {
            List<Integer> tmpList = listList.get(i);
            StringBuilder builder  = new StringBuilder();
            for (int j = 0; j < tmpList.size(); j++) {
                builder.append(tmpList.get(j));
            }
            if (!set.contains(builder.toString())) {
                result.add(tmpList);
                set.add(builder.toString());
            }
        }
        return result;

    }

    public void permuteUnique(int[] nums, int index, List<List<Integer>> listList) {
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

        permuteUnique(nums, index + 1, listList);
    }
}
