package org.hy.datastructure.linkedlist;

public class CircularlyLinkedList<T> {
    //---Attributes---
    class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    int size = 0;
    private Node head, end;

    public CircularlyLinkedList() {
        this.head = null;
        this.end = null;
    }

    //---Methods---
    //Insert data to the end of the list:
    void insert(T data) {
        Node node = new Node(data);
        if (this.size == 0) {
            this.head = node;
            this.head.next = this.head;
            this.end = this.head;
        } else {
            Node i = this.head;
            while (i.next != this.head) {
                i = i.next;
            }
            i.next = node;
            node.next = this.head;
            this.end = node;
        }
        size++;
        System.out.println("Inserted " + data + " to the end.");
    }

    //Insert data to a specified position:
    void insert(T data, int index) {
        if (index > size) {
            System.out.println("Index " + index + " is out of the list.");
        } else {
            Node node = new Node(data);
            if (index == 0) {
                node.next = this.head;
                this.head = node;
                this.end.next = this.head;
            } else {
                int count = 1;
                Node i = this.head;
                while (count < index) {
                    count++;
                    i = i.next;
                }
                node.next = i.next;
                i.next = node;
                if (index == size) {
                    this.end = node;
                }
            }
            size++;
            System.out.println("Inserted " + data + " to the index " + index + ".");
        }
    }

    //Remove data:
    void remove(T data) {
        if (this.isEmpty() || !this.contain(data)) {
            System.out.println(data + " isn't in this list.");
        } else {
            if (this.head.data == data) {
                this.end.next = this.head.next;
                this.head = this.head.next;
            } else {
                Node node = new Node(data);
                Node i = this.head;
                Node j = null;
                while (i.data != node.data) {
                    j = i;
                    i = i.next;
                }
                if (i.next == this.head) {
                    this.end = j;
                }
                j.next = i.next;
                i.next = null;
            }
            size--;
            System.out.println("Removed " + data + ".");
        }
    }

    //Check if list is empty:
    boolean isEmpty() {
        return size == 0;
    }

    //Check if list contains a specified data:
    boolean contain(T data) {
        Node i = this.head;
        while (i.next != this.head) {
            if (i.data == data) {
                return true;
            }
            i = i.next;
        }
        return i.data == data;
    }

    //Return index of a specified data:
    int search(T data) {
        if (this.contain(data)) {
            int count = 0;
            Node i = this.head;
            while (i.next != this.head) {
                if (i.data == data) {
                    return count;
                }
                i = i.next;
                count++;
            }
            if (i.data == data) {
                return count++;
            }
        }
        System.out.println(data + " isn't in the list.");
        return -1;
    }

    //Print the list:
    void print() {
        System.out.print("The list is: ");
        try {
            Node i = this.head;
            while (i.next != this.head) {
                System.out.print(i.data + ", ");
                i = i.next;
            }
            System.out.println(i.data + ".");
        } catch (NullPointerException ex) {
            System.out.print("empty. Nothing to print :<.");
        }
    }

    //--Main--
    public static void main(String[] args) {
        CircularlyLinkedList<Integer> l1 = new CircularlyLinkedList<>();
        l1.insert(1);
        l1.insert(2);
        l1.insert(4);
        System.out.println("Size of list: " + l1.size);
        l1.print();
        l1.insert(0, 0);
        l1.insert(3, 3);
        l1.insert(5, 5);
        l1.insert(7, 7);
        l1.print();
        System.out.println("Size of list: " + l1.size);
        System.out.println("Check if list is empty: " + l1.isEmpty());
        System.out.println("Search result of 0: " + l1.search(0));
        System.out.println("Search result of 4: " + l1.search(4));
        System.out.println("Search result of 5: " + l1.search(5));
        System.out.println("Search result of null: " + l1.search(null));
        l1.remove(3);
        l1.print();
        l1.remove(0);
        l1.print();
        l1.remove(5);
        l1.print();
        l1.remove(null);
        l1.print();
        CircularlyLinkedList<Integer> l2 = new CircularlyLinkedList<>();
        System.out.println("Size of second list: " + l2.size);
        System.out.println("Check if second list is empty: " + l2.isEmpty());
        l2.remove(3);
        l2.print();
    }
}
