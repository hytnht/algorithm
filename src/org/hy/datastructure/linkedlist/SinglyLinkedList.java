package org.hy.datastructure.linkedlist;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
    //---Attributes---
    class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private int size = 0;
    private Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    //---Methods---

    //Get list's size:
    public int getSize() {
        return size;
    }

    //Get first node:
    public Node getHead() { return head; }

    //Insert data to the end of the list:
    public void insert(T data) {
        Node node = new Node(data);
        if (this.size == 0) {
            this.head = node;
        } else {
            Node i = this.head;
            while (i.next != null) {
                i = i.next;
            }
            i.next = node;
        }
        size++;
    }

    //Insert data to a specified position:
    public void insert(T data, int index) {
        try {
            Node node = new Node(data);
            if (index == 0) {
                node.next = this.head;
                this.head = node;
            } else {
                int count = 1;
                Node i = this.head;
                while (count < index) {
                    count++;
                    i = i.next;
                }
                node.next = i.next;
                i.next = node;
            }
            size++;
        } catch (NullPointerException ex) {
            System.out.println("Index " + index + " is out of the list.");
        }
    }

    //Remove data:
    public void remove(T data) {
        if (this.isEmpty()) {
            throw new Error("The list is empty.");
        } else {
            if (this.head.data == data) {
                this.head = this.head.next;
            } else {
                Node i = this.head;
                Node j;
                while (i.next != null) {
                    j = i;
                    i = i.next;
                    if (i.data == data) {
                        j.next = i.next;
                        i.next = null;
                    }
                }
                if (i.data != data) {
                    throw new Error(data + " isn't in this list.");
                }
            }
            size--;
        }
    }

    //Check if list is empty:
    public boolean isEmpty() {
        return size == 0;
    }

    //Return index of a specified data:
    public int search(T data) {
        if (isEmpty()) {
            throw new Error("The list is empty.");
        } else {
            int count = 0;
            Node i = this.head;
            while (i.next != null) {
                if (i.data == data) {
                    return count;
                }
                i = i.next;
                count++;
            }
            if (i.data == data) {
                return count;
            }
            throw new Error("The list is empty.");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T ret = current.data;
                    current = current.next;
                    return ret;
                }
                return null;
            }
        };
    }


    @Override
    public String toString() {
        if (isEmpty()) {
            return "Nothing to print :<.";
        }

        StringBuilder sb = new StringBuilder("The list is: ");
        for (T data : this) {
            sb.append(data).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length() - 1, ".");
        return sb.toString();
    }

    //--Main--
    public static void main(String[] args) {
        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>();
        l1.insert(1);
        l1.insert(2);
        l1.insert(4);
        System.out.println("Size of list: " + l1.getSize());
        System.out.println(l1);
        l1.insert(0, 0);
        l1.insert(3, 3);
        l1.insert(5, 5);
        l1.insert(7, 7);
        System.out.println(l1);
        System.out.println("Size of list: " + l1.getSize());
        System.out.println("Check if list is empty: " + l1.isEmpty());
        System.out.println("Search result of 0: " + l1.search(0));
        System.out.println("Search result of 4: " + l1.search(4));
        System.out.println("Search result of 5: " + l1.search(5));
        l1.remove(3);
        System.out.println("List after remove 3: " + l1);
        l1.remove(0);
        System.out.println("List after remove 0: " + l1);
        l1.remove(5);
        System.out.println("List after remove 5: " + l1);
        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<>();
        System.out.println("Size of second list: " + l2.getSize());
        System.out.println("Check if second list is empty: " + l2.isEmpty());
    }
}
