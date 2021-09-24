package bitree;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/4/18 11:01 下午
 */
public class BiTreeDistance {


    /**
     * 问题：求二叉树的最大距离(即相距最远的两个叶子节点的路径长度)
     * 分析：求一棵二叉树的最大距离可以分成以下几种情况：
     * 1、当一个二叉树的根节点没有左右子树，那么直接返回0.
     * 2、当一个二叉树的根节点只有左子树或者只有右子树的时， 最大距离只能是在根节点的左子树或右子树中产生，所以可以将根节点指向其左子树或右子树,直到回到情况1或者情况3。
     * 3、当一棵二叉树的根结点的左右孩子都存在时，有以下两种情况：
     * a)、路径经过左子树的最深结点，通过根节点，再到右子树的最深结点；
     * b)、路径不穿过根结点，而是左子树或右子树的最大路径距离，取其大者。
     * 这里需要用到求二叉树的高度的代码。
     * 实现：如果根节点为空，则返回0；如果左孩子为空，则递归调用右孩子，如果右孩子为空，则递归调用左孩子；
     * 如果左右孩子都存在，则分别求左右子树的高度并相加再加1，得到一个临时值temp1，
     * 递归调用其左子树并得到左子树的最大距离temp2，递归调用其右子树并得到右子树的最大距离temp3，返回temp1、temp2和temp3中的最大值。
     * 时间复杂度：O(n)
     */
    public static int calcLongestDistance(BiTree biTree) {
        if (biTree == null) {
            return 0;
        }
        if (biTree.getLeft() == null) {
            return calcLongestDistance(biTree.getRight());
        }
        if (biTree.getRight() == null) {
            return calcLongestDistance(biTree.getLeft());
        }
        int distance = Math.max(calcLongestDistance(biTree.getRight()), calcLongestDistance(biTree.getLeft()));
        return Math.max(distance,
                BiTreeHeight.calcBiTreeHeight(biTree.getLeft()) + BiTreeHeight.calcBiTreeHeight(biTree.getRight()) +
                        1);
    }
}
