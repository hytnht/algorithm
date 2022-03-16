package org.hy.datastructure.queue;

import java.util.ArrayList;
import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    private ArrayList<T> data;
    private T front;
    private T rear;
    private int cap;
    private int size;

    public Queue(int cap) {
        this.cap = cap;
        data = new ArrayList<>(cap);
        size = 0;
        front = null;
        rear = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public T next() {
                T ret = data.get(current);
                current++;
                return ret;
            }
        };
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Empty.";
        }
        StringBuilder sb = new StringBuilder();
        for (T data : this) {
            sb.append(data).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), ".");
        return sb.toString();
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == cap;
    }

    public T getFront() {
        if (isEmpty()) {
            throw new Error("Empty.");
        }
        front = data.get(0);
        return front;
    }

    public T getRear() {
        if (isEmpty()) {
            throw new Error("Empty.");
        }
        return rear;
    }

    public void enqueue(T data) {
        if (size == cap) {
            throw new Error("Overflow.");
        }
        size++;
        this.data.set(size, data);
        rear = data;
    }

    public T dequeue() {
        if (size == 0) {
            throw new Error("Underflow.");
        }
        T removed = data.get(0);
        data.remove(0);
        size--;
        return removed;
    }

    public static void main(String[] args) {
        Queue<Integer> q1 = new Queue<>(3);
        for (int i = 0; i < 3; i++) {
            System.out.println("Enqueue " + i);
            q1.enqueue(i);
            System.out.println("Front: " + q1.getFront());
            System.out.println("Rear: " + q1.getRear());
        }
        System.out.println("Queue: " + q1);
        System.out.println("Size: " + q1.getSize());
        System.out.println("Empty: " + q1.isEmpty());
        System.out.println("Full: " + q1.isFull());

        for (int i = 0; i < 3; i++) {
            System.out.println("Dequeue " + q1.dequeue());
        }
        System.out.println("Queue: " + q1);
        System.out.println("Size: " + q1.getSize());
        System.out.println("Empty: " + q1.isEmpty());
        System.out.println("Full: " + q1.isFull());
    }
}
