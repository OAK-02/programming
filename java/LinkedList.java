import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }

}

class SinglyLinkedList {
    public SinglyLinkedListNode head;
    public SinglyLinkedListNode tail;

    public SinglyLinkedList() {
        this.head = this.tail = null;
    }

    public void insertNode(int data) {
        SinglyLinkedListNode n = new SinglyLinkedListNode(data);
        
        if (this.head == null) {
            this.head = n;
        } else {
            this.tail.next = n;
        }
        
        this.tail = n;
    }

    public void printList() {
        SinglyLinkedListNode curr = head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

}

class SinglyLinkedListPrintHelper {
    public static void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }
}

public class LinkedList {
    public static SinglyLinkedListNode deleteNode(SinglyLinkedListNode llist, int position) {
        if (llist == null || position < 0)
            return llist;

        if (position == 0) {
            llist = llist.next;
        } else {
            int currIdx = 0;
            // Write your code here
            for (SinglyLinkedListNode curr = llist; curr.next != null; curr = curr.next) {
                if(currIdx == position - 1)
                    curr.next = curr.next.next;
                currIdx++;
            }
        }
        return llist;
    }

    public static SinglyLinkedListNode reverse(SinglyLinkedListNode llist) {
        // Write your code here
        SinglyLinkedListNode first = null;
        SinglyLinkedListNode second;
        SinglyLinkedListNode third;
        if (llist == null || llist.next == null)
            return llist;
        else {
            second = llist;
            third = llist.next;
        } 
        while(third != null) {
            second.next = first;
            first = second;
            second = third;
            third = third.next;
        }
        second.next = first;
        return second;
    }

    public static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode prevNode = null;
        SinglyLinkedListNode currNode1 = head1;
        SinglyLinkedListNode currNode2 = head2;

        if (currNode1 == null)
            return head2;
        if (currNode2 == null)
            return head1;

        while (currNode1 != null && currNode2 != null) {
            if (currNode1.data > currNode2.data) {
                SinglyLinkedListNode temp = currNode2;
                currNode2 = currNode2.next;
                if (prevNode == null) {
                    temp.next = currNode1;
                    head1 = temp;
                } else {
                    prevNode.next = temp;
                    temp.next = currNode1;
                }
                prevNode = temp;
            } else {
                prevNode = currNode1;
                currNode1 = currNode1.next;
            }
        }
        if (currNode1 == null) {
            prevNode.next = currNode2;
        } 
        return head1;
    }

    public static SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode llist) {
        // Write your code here
        if (llist == null)
            return llist;
        SinglyLinkedListNode curr = llist.next;
        SinglyLinkedListNode repeated = llist;
        while (curr != null) {
            if(curr.data == repeated.data)
                repeated.next = curr.next;
            else
                repeated = curr;
            curr = curr.next;
        }
        return llist;
    }

    public static void main(String args[]) throws IOException {
        SinglyLinkedList ll = new SinglyLinkedList();
        ll.insertNode(22);
        ll.insertNode(22);
        ll.insertNode(23);
        ll.insertNode(23);
        ll.insertNode(23);
        ll.insertNode(24);
        ll.insertNode(25);
        ll.insertNode(26);
        ll.insertNode(26);

        ll.head = LinkedList.removeDuplicates(ll.head);
        ll.printList();
    }
}
