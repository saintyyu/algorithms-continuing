package linkedList;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/16 8:53 下午
 */
public class TwoNumAdd {

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int addition = 0;
        ListNode result = null;
        ListNode lastNode = null;
        while (l1 != null && l2 != null) { //两个都不为null
            ListNode node = new ListNode();
            if (result == null) {
                result = node;
            } else {
                lastNode.next = node;
            }
            lastNode = node;
            int sum = l1.val + l2.val + addition;
            node.val = sum % 10;
            addition = sum > 9 ? 1 : 0;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            ListNode node = new ListNode();
            lastNode.next = node;
            lastNode = node;
            int sum = l1.val + addition;
            node.val = sum % 10;
            addition = sum > 9 ? 1 : 0;
            l1 = l1.next;
        }
        while (l2 != null) {
            ListNode node = new ListNode();
            lastNode.next = node;
            lastNode = node;
            int sum = l2.val + addition;
            node.val = sum % 10;
            addition = sum > 9 ? 1 : 0;
            l2 = l2.next;
        }
        if (addition == 1) {
            lastNode.next = new ListNode(1);
        }
        return result;
    }

}
