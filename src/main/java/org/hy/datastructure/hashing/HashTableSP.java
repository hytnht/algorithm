package main.java.org.hy.datastructure.hashing;

import main.java.org.hy.datastructure.linkedlist.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Iterator;

public class HashTableSP<K, V> implements Iterable<HashTableSP<K, V>.Node> {
    private final int bucket;
    private int size;
    private final ArrayList<Node> table;
    private final SinglyLinkedList<K> keylist;

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

    public HashTableSP(int bucket) {
        this.bucket = bucket;
        table = new ArrayList<>(bucket);
        for (int i = 0; i < bucket; i++) {
            table.add(null);
        }
        keylist = new SinglyLinkedList<>();
    }

    public int hashing(K key) {
        int hashKey = (int) key % bucket;
        if (hashKey < 0) {
            return hashKey + bucket;
        } else return hashKey;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(K key, V value) {
        int hashKey = hashing(key);
        Node node = new Node(key, value);
        Node current = table.get(hashKey);
        if (current == null) {
            table.set(hashKey, node);
        } else {
            while (current.next != null) {
                if (current.key == key) {
                    throw new Error("Key is existed.");
                }
                current = current.next;
            }
            current.next = node;
        }
        keylist.insert(key);
        size++;
        System.out.println("Inserted " + key + " - " + value);
    }

    public V remove(K key) {
        int hashKey = hashing(key);
        Node current = table.get(hashKey);
        if (current.key == key) {
            table.set(hashKey, current.next);
            current.next = null;
        }
        while (current.next != null) {
            Node prev = current;
            current = current.next;
            if (current.key == key) {
                prev.next = current.next;
                current.next = null;
            }
        }
        if (current.key != key) {
            throw new Error(key + " isn't in the table.");
        }
        size--;
        keylist.remove(key);
        return current.value;
    }

    public V get(K key) {
        int hashKey = hashing(key);
        Node current = table.get(hashKey);
        while (current != null && current.key != key) {
            current = current.next;
        }
        if (current == null) {
            throw new Error(key + " isn't in the table.");
        }
        return current.value;
    }

    public Iterator<Node> iterator() {
        return new Iterator<>() {
            final Iterator<K> i = keylist.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public Node next() {
                K key = i.next();
                return new Node(key, get(key));
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node i : this) {
            sb.append("[").append(i.key).append("]:[").append(i.value).append("] - ");
        }
        sb.replace(sb.length()-3, sb.length()-1,".");
        return sb.toString();
    }

    public static void main(String[] args) {
        HashTableSP<Integer, String> h1 = new HashTableSP<>(10);
        System.out.println("Table size: " + h1.table.size());
        System.out.println("Size: " + h1.getSize());
        System.out.println("Empty: " + h1.isEmpty());
        h1.insert(31, "A");
        h1.insert(12, "B");
        h1.insert(67, "C");
        h1.insert(50, "D");
        h1.insert(22, "E");
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
