package org.poison.async.thread;

public class Main {
    public static void main(String args[]) {


        /**
         *
         * @param head ListNode类
         * @param m int整型
         * @param n int整型
         * @return ListNode类
         */


    }

    static class ListNode {
        int val;
        ListNode next = null;
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        // write code here
        if (m == n) {
            return head;
        }

        ListNode pre_head = null;
        int len = n - m + 1;
        ListNode result = head;

        while (head != null && m - 1 > 0) {
            pre_head = head;
            head = head.next;
            --m;
        }
        ListNode end = head;
        ListNode new_head = null;
        while (head != null && len > 0) {
            ListNode next = head.next;
            head.next = new_head;
            new_head = head;
            head = next;
            len--;
        }
        end.next = head;
        if (pre_head != null) {
            pre_head.next = new_head;
        } else {
            result = new_head;
        }
        return result;

    }
}
