package org.hy.datastructure.heap;

import java.util.ArrayList;

public abstract class Heap<T> {
    ArrayList<T> data;
    int cap;
    int size;

    public Heap(int cap) {
        this.cap = cap;
        data = new ArrayList<T>(cap);
        size = 0;
    }

    public int parent(int key) {
        return (key - 1) / 2;
    }

    public int left(int key) {
        return key * 2 + 1;
    }

    public int right(int key) {
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
        for (int i = size / 2 - 1; i > 0; i--) {
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
        data.set(key, data.get(size));
        data.set(size, null);
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
//        int maxlevel = (int) (Math.log(size + 1) / Math.log(2) - 1);
        while (i < (size - 1) /*&& level < maxlevel*/) {
            sb.append(data.get(i));
            if (i == (int) (Math.pow(2, level + 1) - 1)) {
                sb.append("/n");
                level++;
            } else {
                sb.append("-");
            }
            i++;
        }
        return sb.toString();
    }
}
