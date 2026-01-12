package org.example;

import java.util.List;

public class ReverseEvenLengthGroups {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Pair {
        ListNode previous;
        ListNode current;

        public Pair(ListNode current, ListNode previous) {
            this.current = current;
            this.previous = previous;
        }
    }

    private static Pair skip(ListNode start, int skip) {
        ListNode current = start;
        ListNode previous = null;
        int i = 0;
        while (i < skip && current != null) {
            previous = current;
            current = current.next;
            ++i;
        }
        return new Pair(current, previous);
    }

    private static Pair reverse(ListNode head, int count) {
        int i = 0;

        //check if there are at least count #of nodes
        ListNode current = head;
        while (current != null && i < count) {
            current = current.next;
            i++;
        }

        if ((current == null && (i % 2) == 0) || (current != null && (count % 2) == 0)) {
            i = 0;
            current = head;
            ListNode previous = null;
            ListNode next = null;
            while (i < count && current != null) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
                i++;
            }
            head.next = current;
            return new Pair(current, previous);
        }
        return null;

    }

    static ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode current = head.next;
        ListNode previous = head;
        int i = 2;
        Pair p;
        while(current != null) {
            p = reverse(current, i);
            if (p != null) {
                previous.next = p.previous;
                previous = current;
                current = p.current;
            }
            if (current == null) {
                break;
            }
            if (i % 2 != 0) {
                p = skip(current, i);
                current = p.current;
                previous = p.previous;
            }
            ++i;
        }
        return head;
    }

    static ListNode buildList(int[] elements) {
        if (elements.length == 0) {
            return null;
        }

        ListNode head = new ListNode(elements[0]);
        ListNode previous = head;

        for (int i = 1; i < elements.length; ++i) {
            ListNode current = new ListNode(elements[i]);
            previous.next = current;
            previous = current;
        }
        return head;
    }

    static void displayList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        displayList(reverseEvenLengthGroups(buildList(new int[] {1, 1, 0, 6, 5})));
        displayList(reverseEvenLengthGroups(buildList(new int[] {5, 2, 6, 3, 9, 1, 7, 3, 8, 4})));
        displayList(reverseEvenLengthGroups(buildList(new int[]{1, 1, 0})));
    }
}
