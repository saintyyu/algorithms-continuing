package bitree;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/9/30 10:09 下午
 */
public class ClosestParent {

    /**
     * 问题：求一颗二叉树中任意两个节点最近的一个共同父节点
     * <p>
     * 分析：从跟节点到两个节点的的任意一个的路径都是唯一的，两条路径最后一个相同的节点即为离两个节点最近的共同父节点
     * 实现：用两个链表存储根节点到目标节点的路径，然后依次对比即可。
     *
     */
    public static BiTreeNode closestParent(BiTreeNode node1, BiTreeNode node2) {
        LinkedList<BiTreeNode> linkedList1 = new LinkedList<>();
        LinkedList<BiTreeNode> linkedList2 = new LinkedList<>();
        BiTreeNode tmp = node1;
        while (tmp.getParent() != null) {
            linkedList1.addFirst(tmp.getParent());
            tmp = tmp.getParent();
        }
        tmp = node2;
        while (tmp.getParent() != null) {
            linkedList2.addFirst(tmp.getParent());
            tmp = tmp.getParent();
        }
        Iterator<BiTreeNode> iterator1 = linkedList1.iterator();
        Iterator<BiTreeNode> iterator2 = linkedList2.iterator();
        BiTreeNode result = linkedList1.getFirst();
        while (iterator1.hasNext()) {
            tmp = iterator1.next();
            if (tmp == iterator2.next()) {
                result = tmp;
            } else {
                return result;
            }
        }
        return result;
    }
}
