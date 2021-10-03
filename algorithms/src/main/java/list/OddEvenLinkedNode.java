package list;

import bitree.BiTree;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/3 9:42 下午
 */
public class OddEvenLinkedNode {

    /**
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     *  *
     *  * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     *  *
     *  * 示例 1:
     *  *
     *  * 输入: 1->2->3->4->5->NULL
     *  * 输出: 1->3->5->2->4->NULL
     *  *
     *  * 来源：力扣（LeetCode）
     *  * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
     *  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode evenTail = head;
        ListNode oddHead = head.next;
        ListNode oddTail = head.next;
        ListNode tmp = head.next.next;
        oddTail.next = null;
        boolean flag = true;
        while (tmp != null) {
            if (flag) { //偶数
                evenTail.next = tmp;
                evenTail = tmp;
                tmp = tmp.next;
                evenTail.next = null;
            } else {
                oddTail.next = tmp;
                oddTail = tmp;
                tmp = tmp.next;
                oddTail.next = null;
            }
            flag = !flag;

        }
        evenTail.next = oddHead;
        return head;
    }
}
