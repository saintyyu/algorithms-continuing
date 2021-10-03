package list;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/3 10:30 下午
 *
 * 输入一个链表，输出该链表中倒数第k个节点开始的后续节点。
 *  * https://www.nowcoder.com/practice/529d3ae5a407492994ad2a246518148a?tpId=13&&tqId=11167&rp=1&ru=/ta/coding
 *  -interviews&qru=/ta/coding-interviews/question-ranking
 */
public class NumKthInLinkedList {

    /**
     * 思路一：两次反转
     * @param head
     * @param k
     * @return
     */
    public static ListNode FindKthToTail(ListNode head,int k) {
        if(head == null || k <= 0) {
            return null;
        }
        ListNode newHead = reverseList(head);
        ListNode tmp = newHead;
        int count = 1;
        while(count < k && tmp != null) {
            count++;
            tmp = tmp.next;
        }
        if(tmp == null) {
            return null;
        } else {
            tmp.next = null;
            return reverseList(newHead);
        }
    }

    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 思路二：先第一轮循环计数，第二轮循环到指定节点返回
     * @param head
     * @param k
     * @return
     */
    public ListNode findKthToTail_2(ListNode head, int k) {
        if(head == null || k <= 0) {
            return null;
        }
        int n = 0;
        ListNode tmp = head;
        while(tmp != null) {
            n++;
            tmp = tmp.next;
        }
        if(n<k) {
            return null;
        }
        n -= k;
        while(n>0){
            head = head.next;
            n--;
        }
        return head;
    }
}
