package bitree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/4/29 11:26 下午
 */
public class BiTreeWidth {


    /**
     * 问题：求一棵二叉树的宽度（二叉树的宽度是指二叉树各层结点个数的最大值）
     *
     * 分析：我们可以从根结点开始，先将每一层的结点存入一个链表中，存完之后统计这一层的结点数，
     * 然后将这些结点依次取出， 取出后将这个结点的左右孩子存入这个链表中，
     * 当该层所有的结点都被取出时，下一层的所有结点刚好都被存入链表中，通过迭代的方式记录最最宽的结点数。
     * 思路：先将根结点存入，然后第一层的宽度为1，然后将根结点取出，并将左右孩子存入链表，则第二层的宽度为链表的元素总数，
     * 依次这样操作，直到链表中的元素为空，然后记录最大的元数值。
     * 时间复杂度O(n)。
     */

    public static int getWidth(BiTree btree) {
        if (btree == null)
            return 0;

        int nWidth = 1; //记录二叉树最长的宽度
        int nLastLevelWidth = 1;//上一层的宽度

        Queue<BiTree> myQueue = new LinkedList<>();
        myQueue.add(btree);

        while (!myQueue.isEmpty())//链表不为空
        {
            BiTree tmp;
            while (nLastLevelWidth != 0) {
                tmp = myQueue.poll();

                if (tmp.getLeft() != null) {
                    myQueue.add(tmp.getLeft());
                }
                if (tmp.getRight() != null) {
                    myQueue.add(tmp.getRight());
                }
                nLastLevelWidth--;
            }
            nLastLevelWidth = myQueue.size();
            nWidth = Math.max(nLastLevelWidth, nWidth);
        }
        return nWidth;
    }
}
