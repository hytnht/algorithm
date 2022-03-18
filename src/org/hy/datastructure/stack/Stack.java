package org.hy.datastructure.stack;

import java.util.ArrayList;
import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private final ArrayList<T> data;
    private final int max;
    private int top;

    public Stack(int max) {
        this.top = -1;
        this.max = max;
        data = new ArrayList<>(max);
    }

    public int getSize() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == max - 1;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        } else {
            return data.get(top);
        }
    }

    public void push(T data) {
        if (isFull()) {
            System.out.println("Stack overflow.");
        } else {
            this.top++;
            this.data.add(top, data);
        }
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        } else {
            top--;
            return data.get(top + 1);
        }
    }

    public int search(T data) {
        for (int i = 0; i <= top; i++) {
            if (this.data.get(i) == data) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "Empty";
        }
        StringBuilder sb = new StringBuilder();
        for (T data : this) {
            sb.append(data).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length() - 1, ".");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current <= top;
            }

            @Override
            public T next() {
                T ret = data.get(current);
                current++;
                return ret;
            }
        };
    }

    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>(3);
        for (int i = 0; i <= 3; i++) {
            System.out.println("Push " + i);
            s1.push(i);
        }
        System.out.println("Stack: " + s1);
        System.out.println("Size: " + s1.getSize());
        System.out.println("Empty: " + s1.isEmpty());
        System.out.println("Full: " + s1.isFull());
        System.out.println("Peek: " + s1.peek());

        s1.search(0);
        s1.search(3);
        s1.search(5);
        for (int i = 0; i <= 3; i++) {
            System.out.println("Pop " + s1.pop());
        }
        System.out.println("Stack: " + s1);
        System.out.println("Size: " + s1.getSize());
        System.out.println("Empty: " + s1.isEmpty());
        System.out.println("Full: " + s1.isFull());
        System.out.println("Peek: " + s1.peek());
    }
}

