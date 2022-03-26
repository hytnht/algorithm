package main.java.org.hy.datastructure.hashing;

import java.util.ArrayList;
import java.util.Iterator;

public class HashTableOA<K, V> implements Iterable<HashTableOA<K, V>.Node> {
    private final int cap;
    private int size;
    private final ArrayList<Node> table;

    class Node {
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public HashTableOA(int cap) {
        this.cap = cap;
        table = new ArrayList<>(cap);
        for (int i = 0; i < cap; i++) {
            table.add(null);
        }
    }

    public int hashing(K key) {
        int hashKey = (int) key % cap;
        if (hashKey < 0) {
            return hashKey + cap;
        } else return hashKey;
    }

    public int rehash() {
        for (int i = 0; i < cap; i++) {
            if (table.get(i) == null) {
                return i;
            }
        }
        throw new Error("Table is already full");
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(K key, V value) {
        Node node = new Node(key, value);
        int hashKey = hashing(key);
        if (table.get(hashKey) != null) {
            hashKey = rehash();
        }
        table.set(hashKey, node);
        size++;
        System.out.println("Inserted " + key + " - " + value);
    }

    public V remove(K key) {
        int hashKey = hashing(key);
        Node current = table.get(hashKey);
        if (current.key == key) {
            table.set(hashKey, null);
            return current.value;
        }
        int index = 0;
        for (Node i : this) {
            if (i != null) {
                if (i.key == key) {
                    table.set(index, null);
                    size--;
                    return i.value;
                }
            }
            index++;
        }
        throw new Error(key + " isn't in the table.");
    }

    public V get(K key) {
        int hashKey = hashing(key);
        Node current = table.get(hashKey);
        if (current.key == key) {
            return current.value;
        }
        for (Node i : this) {
            if (i.key == key) {
                return i.value;
            }
        }
        throw new Error(key + " isn't in the table.");
    }

    public Iterator<Node> iterator() {
        return new Iterator<>() {
            int i = -1;

            @Override
            public boolean hasNext() {
                return i < cap - 1;
            }

            @Override
            public Node next() {
                i++;
                return table.get(i);
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node i : this) {
            if (i != null) {
                sb.append("[").append(i.key).append("]:[").append(i.value).append("] - ");
            }
        }
        sb.replace(sb.length() - 3, sb.length() - 1, ".");
        return sb.toString();
    }

    public static void main(String[] args) {
        HashTableOA<Integer, String> h1 = new HashTableOA<>(10);
        System.out.println("Table size: " + h1.table.size());
        System.out.println("Size: " + h1.getSize());
        System.out.println("Empty: " + h1.isEmpty());
        h1.insert(31, "A");
        System.out.println("Hash: " + h1);
        h1.insert(12, "B");
        System.out.println("Hash: " + h1);
        h1.insert(67, "C");
        System.out.println("Hash: " + h1);
        h1.insert(50, "D");
        System.out.println("Hash: " + h1);
        h1.insert(22, "E");
        System.out.println("Hash: " + h1);
        h1.insert(32, "F");
        System.out.println("Hash: " + h1);
        System.out.println("Size: " + h1.getSize());
        System.out.println("Empty: " + h1.isEmpty());
        System.out.println("Get key 50: " + h1.get(50));
        System.out.println("Get key 32: " + h1.get(32));
        System.out.println("Remove 12: " + h1.remove(12));
        System.out.println("Hash: " + h1);
        System.out.println("Remove 31: " + h1.remove(31));
        System.out.println("Hash: " + h1);
    }
}
