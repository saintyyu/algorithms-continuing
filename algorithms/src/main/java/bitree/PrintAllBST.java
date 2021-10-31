package bitree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/31 4:21 下午
 *
 * 题目：
 *
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintAllBST {

    /**
     * 思路：回溯法
     * 二叉搜索树关键的性质是根节点的值大于左子树所有节点的值，小于右子树所有节点的值，且左子树和右子树也同样为二叉搜索树。
     * 因此在生成所有可行的二叉搜索树的时候，假设当前序列长度为 n，如果我们枚举根节点的值为
     * i，那么根据二叉搜索树的性质我们可以知道左子树的节点值的集合为 [1…i−1]，右子树的节点值的集合为 [i+1…n]。
     * 而左子树和右子树的生成相较于原问题是一个序列长度缩小的子问题，因此我们可以想到用回溯的方法来解决这道题目。
     *
     * 我们定义 generateTrees(start, end) 函数表示当前值的集合为 [start,end]，返回序列 [start, end]
     * 生成的所有可行的二叉搜索树。按照上文的思路，我们考虑枚举
     * [start,end] 中的值 i 为当前二叉搜索树的根，那么序列划分为了 [start,i−1] 和 [i+1,end]两部分。
     * 我们递归调用这两部分，即 generateTrees(start, i - 1) 和 generateTrees(i + 1, end)
     * ，获得所有可行的左子树和可行的右子树，那么最后一步我们只要从可行左子树集合中选一棵，再从可行右子树集合中选一棵拼接到根节点上，并将生成的二叉搜索树放入答案数组即可。
     *
     * 递归的入口即为 generateTrees(1, n)，出口为当 start>end 的时候，当前二叉搜索树为空，返回空节点即可。
     *
     */
    public List<BiTree> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    public List<BiTree> generateTrees(int start, int end) {
        List<BiTree> allList = new ArrayList<>();
        if (start > end) {
            allList.add(null);
            return allList;
        }
        for (int i = start; i <= end; i++) {
            List<BiTree> leftList = generateTrees(start, i - 1);
            List<BiTree> rightList = generateTrees(i + 1, end);

            for (BiTree left : leftList) {
                for (BiTree right : rightList) {
                    BiTree current = new BiTree(i);
                    current.left = left;
                    current.right = right;
                    allList.add(current);
                }
            }
        }
        return allList;
    }

}
