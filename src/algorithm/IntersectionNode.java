package algorithm;

public class IntersectionNode {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA;
        while (node1 != null) {
            ListNode node2 = headB;
            while (node2 != null) {
                if (node1 == node2) {
                    return node1;
                }
                node2 = node2.next;
            }
            node1 = node1.next;
        }
        return null;
    }

    // 力扣官方解法：双指针
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != node2) {
            node1 = node1 == null ? headA : node1.next;
            node2 = node2 == null ? headB : node2.next;
        }
        return node1;
    }

}