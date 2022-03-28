package main.java.org.hy.algorithm.sorting;

import java.util.Arrays;

import static main.java.org.hy.algorithm.sorting.SortUtils.*;

public class QuickSort {
    public static <T extends Comparable<T>> void sort(T[] array, int low, int high, boolean desc) {
        if (high > low) {
            int pi = partition(array, low, high, desc);
            sort(array, low, pi - 1, desc);
            sort(array, pi + 1, high, desc);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int low, int high, boolean desc) {
        T pivot = array[low];
        int mid = low + 1;
        if (!desc) {
            for (int i = low + 1; i <= high; i++) {
                if (isLess(array[i], pivot)) {
                    swap(array, i, mid);
                    mid++;
                }
            }
        } else {
            for (int i = low + 1; i <= high; i++) {
                if (isGreater(array[i], pivot)) {
                    swap(array, i, mid);
                    mid++;
                }
            }
        }
        swap(array, low, mid - 1);
        return mid - 1;
    }

    public static void main(String[] args) {

        Integer[] i1 = new Integer[]{6, 1, 35, 7, 2, 10, 5, 12, 8, 23, 10, 0, 9, 1};
        System.out.println("Integer random " + Arrays.toString(i1));
        sort(i1, 0, i1.length - 1, false);
        System.out.println("--> Quick sort: " + print(i1) + "\n");

        Integer[] i2 = new Integer[]{0, 1, 3, 7, 7, 9, 11, 25};
        System.out.println("Integer sorted " + Arrays.toString(i2));
        sort(i2, 0, i2.length - 1, false);
        System.out.println("--> Quick sort: " + print(i2) + "\n");

        Integer[] i3 = new Integer[]{0, 1, 7, 3, 9, 11, 10, 25};
        System.out.println("Integer nearly sorted " + Arrays.toString(i3));
        sort(i3, 0, i3.length - 1, true);
        System.out.println("--> Quick sort: " + print(i3) + "\n");

        Integer[] i4 = new Integer[]{6, 11, 0, 11, 10, 7, 1, 9, 11, 10, 9, 6, 9, 10};
        System.out.println("Integer many duplicates " + Arrays.toString(i4));
        sort(i4, 0, i4.length - 1, true);
        System.out.println("--> Quick sort: " + print(i4) + "\n");

        String[] s1 = new String[]{"Tokyo", "Paris", "Hanoi", "Washington", "London", "Moscow", "Berlin"};
        System.out.println("String random " + Arrays.toString(s1));
        sort(s1, 0, s1.length - 1, true);
        System.out.println("--> Quick sort: " + print(s1) + "\n");

        Double[] d1 = new Double[]{3.1, 9.2, 4.0, -1.2, 7.9, -9.5, 3.0, -6.7, -0.2, 1.9};
        System.out.println("Double random " + Arrays.toString(d1));
        sort(d1, 0, d1.length - 1, false);
        System.out.println("--> Quick sort: " + print(d1) + "\n");
    }
}
