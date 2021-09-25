package bitree;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/9/25 10:03 下午
 */
public class MinBiTreeHeight {

    /**
     *问题：求一棵二叉树的的根结点到叶子节点的最短路径，即最短高度
     *分析：这个问题采用递归求解比较简单
     *实现：依次返回其左右子树的高度，然后分别加1，将更大的值返回。
     *说明：求一棵二叉树的最短高度虽然简单，但在其他相关的二叉树操作中却非常有用。
     * 时间复杂度：O(n)  遍历节点
     */
    public int minBiTreeHeight(BiTree tree) {
        if (tree != null) {
            return Math.min(minBiTreeHeight(tree.getLeft()), minBiTreeHeight(tree.getRight())) + 1;
        }
        return 0;
    }
}
