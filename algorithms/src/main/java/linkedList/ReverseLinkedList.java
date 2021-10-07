package linkedList;

import java.util.LinkedList;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/2 9:28 下午
 */
public class ReverseLinkedList {

    /**
     * 反转单链表：A->B->C->D : D->C->B->A
     * 递归法
     */
    public ListNode ReverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = ReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 反转单链表：A->B->C->D : D->C->B->A
     * 迭代
     */
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        LinkedList<ListNode> nodeList = new LinkedList<>();
        while(head != null) {
            nodeList.add(head);
            head = head.next;
            nodeList.peekLast().next = null;
        }
        ListNode newHead = nodeList.pollLast();
        ListNode last = newHead;
        ListNode tmp;
        while((tmp=nodeList.pollLast()) != null) {
            last.next = tmp;
            last = tmp;
        }
        return newHead;
    }
}
