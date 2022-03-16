package org.hy.datastructure.linkedlist;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
    //---Attributes---
    class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null; // NOTE: set default value for next
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
    // NOTE: missing getSize method
    int getSize() {
        return size;
    }

    //Insert data to the end of the list:
    void insert(T data) {
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
        System.out.println("Inserted " + data + " to the end.");
    }

    //Insert data to a specified position:
    void insert(T data, int index) {
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
            System.out.println("Inserted " + data + " to the index " + index + ".");
        } catch (NullPointerException ex) {
            System.out.println("Index " + index + " is out of the list.");
        }
    }

    //Remove data:
    void remove(T data) {
        /* NOTE: Những chỗ như thế này hạn chế xài hàm contain. Vì phải mất O(n) để check xem data có tồn tại hay không.
        Thay vào đó sửa lại sao cho mình vừa lướt qua 1 lượt, có thì mình xoá không thì mình nói không có thôi.
        Thích thì sửa không cũng không sao */
        if (this.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            boolean available = false;
            if (this.head.data == data) {
                this.head = this.head.next;
                available = true;
            } else {
                Node node = new Node(data);
                Node i = this.head;
                Node j = null;
                while (i.next != null) {
                    j = i;
                    if (i.data == node.data) {
                        j.next = i.next;
                        available = true;
                    } else {
                        i = i.next;
                    }
                }
            }
            if (available) {
                size--;
                System.out.println("Removed " + data + ".");
            } else {
                System.out.println(data + " isn't in this list.");
            }
        }
    }

    //Check if list is empty:
    boolean isEmpty() {
        return size == 0;
    }

    // NOTE: hàm này trùng với hàm search, hàm này trả về booleen thì hàm search trả về index, mình chỉ cần check index != -1 là được
    //Check if list contains a specified data:
    boolean contain(T data) {
        return this.search(data) != -1;
    }

    //Return index of a specified data:
    int search(T data) {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return -1;
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
                return count; // NOTE: count được return trước khi được tăng lên 1, nếu muốn thì xài ++count -> tăng count trước khi return
            }
            System.out.println(data + " isn't in the list.");
            return -1;
        }
    }

    // NOTE: Implement iterator nếu muốn xài java for each như thế này for (T d : list)
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
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

    // NOTE: Override hàm toString(), chỉ cần System.out.println(l1) là in như 1 Object bình thường
    // tham khảo link sau https://www.javatpoint.com/understanding-toString()-method
    @Override // nhớ thêm annotation @Override để biết method đã bị override
    public String toString() {
        if (isEmpty()) {
            return "empty. Nothing to print :<.";
        }

        StringBuilder sb = new StringBuilder("The list is: ");
        for (T data : this) {
            sb.append(data).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length() - 1, ".");
        return sb.toString();
    }


    // NOTE: remove this method
    //Print the list:
    void print() {
        System.out.print("The list is: ");
        try {
            Node i = this.head;
            while (i.next != null) {
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
        System.out.println("Search result of null: " + l1.search(null));
        l1.remove(3);
        l1.print();
        l1.remove(0);
        l1.print();
        l1.remove(5);
        l1.print();
        l1.remove(null);
        l1.print();
        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<>();
        System.out.println("Size of second list: " + l2.getSize());
        System.out.println("Check if second list is empty: " + l2.isEmpty());
        l2.remove(3);
        l2.print();
    }
}
