package DSA.LinkedList;

public class LList {
    public static void main(String[] args) {
        //Single
        System.out.println("Single Linked List");
        SNode head = null;
        SNode second = null;
        SNode third = null;

        head = new SNode();
        second = new SNode();
        third = new SNode();

        head.data = 1;
        head.next = second;
        second.data = 2;
        second.next = third;
        third.data = 3;
        third.next = null;

        printList(head);
        System.out.println();

        //Double
        System.out.println("\nDouble Linked List");
        head_ref = null;

        push(6);
        push(7);
        push(1);
        push(4);

        System.out.println("Created DLL is: ");
        printList(head_ref);
        System.out.println();

        //Circular
        System.out.println("\nCircular Linked List");
        CNode cirhead = null;

        cirhead = push(cirhead, 12);
        cirhead = push(cirhead, 56);
        cirhead = push(cirhead, 2);
        cirhead = push(cirhead, 11);

        System.out.println("Contents of Circular Linked List:");
        printList(cirhead);
        System.out.println();

        //Circular Doubly
    }

    //Single Linked List
    static class SNode {
        int data;
        SNode next;
    }

    static void printList(SNode n) {
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
    }

    //Double Linked List
    static class DNode {
        int data;
        DNode next;
        DNode prev;
    }

    static DNode head_ref;

    static void push(int new_data) {
        DNode new_node = new DNode();
        new_node.data = new_data;
        new_node.next = head_ref;
        new_node.prev = null;
        if (head_ref != null) {
            head_ref.prev = new_node;
        }
        head_ref = new_node;
    }

    static void printList(DNode node) {
        DNode last = null;
        System.out.println("Traversal in Forward Direction");
        while (node != null) {
            System.out.print(node.data + " ");
            last = node;
            node = node.next;
        }
        System.out.println();
        System.out.println("Traversal in Reverse Direction");
        while (last != null) {
            System.out.print(last.data + " ");
            last = last.prev;
        }
    }

    //Circular Linked List
    static class CNode {
        int data;
        CNode next;
    }

    static CNode push(CNode head_ref, int data) {
        CNode ptr1 = new CNode();
        CNode temp = head_ref;
        ptr1.data = data;
        ptr1.next = head_ref;

        if (head_ref != null) {
            while (temp.next != head_ref) {
                temp = temp.next;
            }
            temp.next = ptr1;
        } else {
            ptr1.next = ptr1;
        }
        head_ref = ptr1;
        return head_ref;
    }

    static void printList(CNode head) {
        CNode temp = head;
        if (head != null) {
            do {
                System.out.print(temp.data + " ");
                temp = temp.next;
            } while (temp != head);
        }
    }

    //Circular Doubly Linked List

}
