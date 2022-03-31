package main.java.org.hy.algorithm.searching;

public class BinarySearch {
    public static <T extends Comparable<T>> int search(T[] array, T data, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (array[mid] == data) return mid;
            if (array[mid].compareTo(data) > 0) return search(array, data, left, mid - 1);
            if (array[mid].compareTo(data) < 0) return search(array, data, mid + 1, right);
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] i1 = {-5, 3, 6, 9, 22, 67};
        System.out.println(search(i1, 6, 0, 5));
        System.out.println(search(i1, 0, 0, 5));

        String[] s1 = {"C", "Java", "JavaScript", "Python", "Ruby"};
        System.out.println(search(s1, "Java", 0, 4));
    }
}
