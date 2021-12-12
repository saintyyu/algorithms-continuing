package bitree;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/4/18 11:01 下午
 */
public class BiTreeDistance {


    /**
     * https://leetcode-cn.com/problems/diameter-of-binary-tree/
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     */
    public int diameterOfBinaryTree(BiTree root) {

        int diameter = calcLongestBiTreeDistance(root);
        return diameter == 0 ? diameter : diameter - 1;  //这里要减1，是因为是按边来记录长度
    }

    /**
     * 这里是计算任意两个节点之间的数量
     *
     * 时间复杂度：O(n)
     * @param biTree
     * @return
     */
    public static int calcLongestBiTreeDistance(BiTree biTree) {
        if (biTree == null) {
            return 0;
        }
        int distance = Math.max(calcLongestBiTreeDistance(biTree.getRight()), calcLongestBiTreeDistance(biTree.getLeft()));
        return Math.max(distance,
                BiTreeHeight.calcBiTreeHeight(biTree.getLeft()) + BiTreeHeight.calcBiTreeHeight(biTree.getRight())) + 1;
    }
}
