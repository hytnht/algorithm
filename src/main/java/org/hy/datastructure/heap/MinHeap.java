package main.java.org.hy.datastructure.heap;

public class MinHeap<T extends Comparable<T>> extends Heap<T> {
    public MinHeap(T[] data, int cap) {
        super(cap);
        if (data.length > cap) {
            throw new Error("Array length is larger than defined capacity");
        }
        for (int i = 0; i < data.length; i++) {
            this.data.add(i, data[i]);
        }
        size = data.length;
        buildHeap();
    }

    @Override
    public void insert(T data) {
        if (isFull()) {
            throw new Error("Overflow.");
        }
        this.data.add(size, data);
        int i = size;
        while (i > 0 && this.data.get(i).compareTo(this.data.get(parent(i))) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
        size++;
    }

    @Override
    public void heapify(int key) {
        int left = left(key);
        int right = right(key);
        int min = key;
        if (left < size && this.data.get(left).compareTo(this.data.get(key)) < 0) {
            min = left;
        }
        if (right < size && this.data.get(right).compareTo(this.data.get(min)) < 0) {
            min = right;
        }
        if (min != key) {
            swap(min, key);
            heapify(min);
        }
    }

    public static void main(String[] args) {
        MinHeap<Integer> h1 = new MinHeap<>(new Integer[]{3, 5, 8, 2, 1, 7, 6}, 9);
        System.out.println(h1);
        System.out.println("Inserted 4.");
        h1.insert(4);
        System.out.println(h1);
        System.out.println("Inserted 0.");
        h1.insert(0);
        System.out.println(h1);
        System.out.println("Size: " + h1.getSize());
        System.out.println("Empty: " + h1.isEmpty());
        System.out.println("Full: " + h1.isFull());
        System.out.println("Removed 2: "+ h1.remove(2));
        System.out.println(h1);
        System.out.println("Removed 0: "+ h1.remove(0));
        System.out.println(h1);
    }
}

