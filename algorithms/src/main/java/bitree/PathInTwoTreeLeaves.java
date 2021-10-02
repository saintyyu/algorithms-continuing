package bitree;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/4/29 11:40 下午
 */
public class PathInTwoTreeLeaves {

    /**
     * 问题：打印二叉树两个叶子结点间的路径
     * 分析：找到从根结点到两个叶子结点的路径结点并分别用链表进行存储，然后就可以依次进行比较，两个链表中，
     * 从最后一个相同的结点以后的所有结点(包括最后一个结点)构成了两个叶子结点的路径。
     * 实现：
     * 如果两个叶子结点是同一个结点，则将叶子结点封装成List返回；
     * 否则，专门写一个子函数，用来记录从根结点到给定叶子结点的路径；
     * 然后分别对给定的这两个叶子结点调用该子函数，如果任意一个返回false，则说明所给二叉树中不存在该叶子结点，则抛出异常；
     * <p>
     * 否则可以得到两个链表，这两个链表分别存储了叶子结点到根节点的路径，从这两个链表的末端依次取出元素，判断是否是同一个元素，
     * 从最后一个相同的元素开始往前，两个链表中剩下的元素恰好是这两个叶子间的路径。
     * 时间复杂度：O(n)
     */
    public static LinkedList<BiTree> getPathInTwoLeaves(BiTree head, BiTree leaf1, BiTree leaf2) {
        LinkedList<BiTree> list = new LinkedList<>();//存放最终结果
        LinkedList<BiTree> list1 = new LinkedList<>();//存放根结点到第一个结点的路径
        LinkedList<BiTree> list2 = new LinkedList<>();//存放根结点到第二个结点的路径
        if (head == null || leaf1 == null || leaf2 == null) {
            return null;
        }
        if (leaf1.equals(leaf2)) {
            list.add(leaf1);
            return list;
        }
        if (!getPathFromRootToLeaf(head, leaf1, list1) || !getPathFromRootToLeaf(head, leaf2, list2))
            return null;

        BiTree tmpTree = null;
        while (list1.peekLast().equals(list2.peekLast())) {
            tmpTree = list1.pollLast();
            list2.pollLast();
        }
        list.add(tmpTree);
        while (list1.size() > 0)
            list.add(list1.pollLast());
        while (list2.size() > 0)
            list.add(list2.pollLast());

        return list;
    }

    /**
     * 从leaf到root存放，平均时间复杂度 O(n)：递归算法实际上会执行到二叉树的所有叶子节点
     * @param head
     * @param leaf
     * @param list
     * @return
     */
    public static boolean getPathFromRootToLeaf(BiTree head, BiTree leaf, List<BiTree> list) {
        if (head.equals(leaf) || (head.getLeft() != null && getPathFromRootToLeaf(head.getLeft(), leaf, list))
                || (head.getRight() != null && getPathFromRootToLeaf(head.getRight(), leaf, list))) {
            list.add(head);
            return true;
        }

        return false;
    }
}
