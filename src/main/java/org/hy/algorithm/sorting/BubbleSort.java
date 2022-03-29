package main.java.org.hy.algorithm.sorting;

import java.util.Arrays;

import static main.java.org.hy.algorithm.sorting.SortUtils.*;

public class BubbleSort {
    public static <T extends Comparable<T>> void sort(T[] array, boolean desc) {
        int n = array.length;
        while (n > 0) {
            for (int i = 0; i < n - 1; i++) {
                if (!desc) {
                    if (isGreater(array[i],array[i + 1])) {
                        swap(array, i, i + 1);
                    }
                } else {
                    if (isLess(array[i],array[i + 1])) {
                        swap(array, i, i + 1);
                    }
                }
            }
            n--;
        }
    }

    public static void main(String[] args) {
        Integer[] i1 = new Integer[]{6, 1, 35, 7, 2, 10, 5, 12, 8, 23, 10, 0, 9, 1};
        System.out.println("Integer random " + Arrays.toString(i1));
        sort(i1, false);
        System.out.println("--> Bubble sort: " + print(i1) + "\n");

        Integer[] i2 = new Integer[]{0, 1, 3, 7, 7, 9, 11, 25};
        System.out.println("Integer sorted " + Arrays.toString(i2));
        sort(i2, false);
        System.out.println("--> Bubble sort: " + print(i2) + "\n");

        Integer[] i3 = new Integer[]{0, 1, 7, 3, 9, 11, 10, 25};
        System.out.println("Integer nearly sorted " + Arrays.toString(i3));
        sort(i3, false);
        System.out.println("--> Bubble sort: " + print(i3) + "\n");

        Integer[] i4 = new Integer[]{6, 11, 0, 11, 10, 7, 1, 9, 11, 10, 9, 6, 9, 10};
        System.out.println("Integer many duplicates " + Arrays.toString(i4));
        sort(i4, true);
        System.out.println("--> Bubble sort: " + print(i4) + "\n");

        String[] s1 = new String[]{"Tokyo", "Paris", "Hanoi", "Washington", "London", "Moscow", "Berlin"};
        System.out.println("String random " + Arrays.toString(s1));
        sort(s1, true);
        System.out.println("--> Bubble sort: " + print(s1) + "\n");

        Double[] d1 = new Double[]{3.1, 9.2, 4.0, -1.2, 7.9, -9.5, 3.0, -6.7, -0.2, 1.9};
        System.out.println("Double random " + Arrays.toString(d1));
        sort(d1, false);
        System.out.println("--> Bubble sort: " + print(d1) + "\n");
    }
}
