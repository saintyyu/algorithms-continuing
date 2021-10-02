package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/1 3:49 下午
 */
public class TreeAlikeNonDirectedMapMinTree {

    /**
     * 来源：力扣（LeetCode）链接：https://leetcode-cn.com/problems/minimum-height-trees
     * 问题：树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
     *
     * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai
     * 和 bi 之间存在一条无向边。
     *
     * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
     *
     * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
     * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
     *
     *  * <p>
     *  * 示例 1:
     *  * <p>
     *  * 输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
     *  * <p>
     *  * 0
     *  * |
     *  * 1
     *  * / \
     *  * 2   3
     *  * <p>
     *  * 输出: [1]
     *  * <p>
     *  *
     *
     *  分析：这个题目实际上是考察图的出度问题。使用一个map，用来记录每个点连通的点，然后采用裁剪法，每次裁剪掉出度为1的点，直到剩下1个点或2个点。
     *  时间复杂度：O(3n)
     *  空间复杂度：O(2n)
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        if (n <= 2) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(i);
            }
            return list;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            Integer node1 = edges[i][0];
            Integer node2 = edges[i][1];
            if (map.containsKey(node1)) {
                map.get(node1).add(node2);
            } else {
                Set<Integer> node1Set = new HashSet<>();
                node1Set.add(node2);
                map.put(node1, node1Set);
            }

            if (map.containsKey(node2)) {
                map.get(node2).add(node1);
            } else {
                Set<Integer> node2Set = new HashSet<>();
                node2Set.add(node1);
                map.put(node2, node2Set);
            }
        }

        while (map.size() > 2) {
            Set<Integer> keySet = map.keySet();
            List<Integer> oneDegreeList = new ArrayList<>();
            for (Integer key : keySet) {
                if (map.get(key).size() == 1) {
                    oneDegreeList.add(key);
                }
            }
            for (Integer key : oneDegreeList) {
                //这里只有一度，map.get(key)得到的set实际上只会有一个值
                map.get(map.get(key).toArray()[0]).remove(key);
                map.remove(key);
            }
        }
        return new ArrayList<>(map.keySet());
    }
}
