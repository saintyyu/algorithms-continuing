package bitree;

import java.util.LinkedList;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/1 5:08 下午
 */
public class SortBinaryTreeToLinkedList {

    /**
     * 问题：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * https://www.nowcoder.com/practice/947f6eb80d944a84850b0538bf0ec3a5?tpId=13&&tqId=11179&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     *
     * 分析：根据二叉搜索树的特性（左孩小于父亲，右孩大于父亲），采用中序遍历即可得到排序列表
     * 实现：先中序遍历二叉树（得到排序结果），然后再修改指针得到最终结果
     * 时间复杂度：O(2n)
     * 空间复杂度：O(n)
     */
    public BiTree convertOne(BiTree pRootOfTree) {
        if(pRootOfTree == null){
            return null;
        }
        LinkedList<BiTree> list = new LinkedList<>();
        middleTraversal(pRootOfTree, list);
        BiTree first = list.pollFirst();
        BiTree tmp = first;
        for (BiTree node : list) {
            tmp.right = node;
            node.left = tmp;
            tmp = node;
        }
        return first;
    }

    public void middleTraversal(BiTree pRootOfTree, LinkedList<BiTree> list) {
        if(pRootOfTree == null){
            return;
        }
        middleTraversal(pRootOfTree.left, list);
        list.add(pRootOfTree);
        middleTraversal(pRootOfTree.right, list);
    }

    public BiTree pre;

    /**
     * 问题：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * https://www.nowcoder.com/practice/947f6eb80d944a84850b0538bf0ec3a5?tpId=13&&tqId=11179&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     *
     * 分析：用一个全局变量保存中序遍历的前一个节点值
     * 实现：
     * 时间复杂度: O(N)
     * 空间复杂度：O(1)
     */
    public BiTree convertTwo(BiTree pRootOfTree) {
        if (pRootOfTree==null)
            return null;
        convertTwo(pRootOfTree.right);
        if (pre!= null){
            pRootOfTree.right=pre;
            pre.left=pRootOfTree;
        }
        pre=pRootOfTree;
        convertTwo(pRootOfTree.left);
        return pre;
    }

}
