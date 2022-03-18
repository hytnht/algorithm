package org.hy.datastructure.heap;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Heap<T> implements Iterable {
    protected ArrayList<T> data;
    protected int cap;
    protected int size;

    public Heap(int cap) {
        this.cap = cap;
        data = new ArrayList<T>(cap);
        size = 0;
    }

    public static int parent(int key) {
        return (key - 1) / 2;
    }

    public static int left(int key) {
        return key * 2 + 1;
    }

    public static int right(int key) {
        return key * 2 + 2;
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

    public void swap(int i, int j) {
        T tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }

    public void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    public T remove(int key) {
        if (isEmpty()) {
            throw new Error("Empty.");
        }
        if (key > size) {
            throw new Error(key + "is out of the heap");
        }
        T removed = data.get(key);
        data.set(key, data.get(size - 1));
        data.set(size - 1, null);
        size--;
        heapify(key);
        return removed;
    }

    abstract public void insert(T data);

    abstract public void heapify(int key);

    @Override
    public String toString() {
        if (isEmpty()) {
            throw new Error("Empty");
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        int level = 0;
        while (i < size) {
            sb.append(data.get(i));
            if (i != size - 1) {
                int br = (int) Math.pow(2, level + 1) - 2;
                if (i == br) {
                    sb.append("\n");
                    level++;
                } else {
                    sb.append("-");
                }
            }
            i++;
        }
        return sb.toString();
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
}
