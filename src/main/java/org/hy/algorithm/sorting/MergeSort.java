package main.java.org.hy.algorithm.sorting;

import java.util.ArrayList;
import java.util.Arrays;

import static main.java.org.hy.algorithm.sorting.SortUtils.*;

public class MergeSort {

     public static <T extends Comparable<T>> void sort(T[] array, int l, int r, boolean desc) {
        if (r > l) {
            int m = (l + (r - 1)) / 2;
            sort(array, l, m, desc);
            sort(array, m + 1, r, desc);
            merge(array, l, m, r, desc);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] array, int l, int m, int r, boolean desc) {
        int lenL = m - l + 1;
        int headL = 0;
        ArrayList<T> left = new ArrayList<>(lenL);
        left.addAll(Arrays.asList(array).subList(l, m + 1));

        int lenR = r - m;
        int headR = 0;
        ArrayList<T> right = new ArrayList<>(lenR);
        right.addAll(Arrays.asList(array).subList(m + 1, r + 1));

        for (int i = l; i <= r; i++) {
            if (headL < lenL && headR < lenR) {
                if(!desc) {
                    if (isGreater(left.get(headL),right.get(headR))) {
                        array[i] = right.get(headR);
                        headR++;
                    } else {
                        array[i] = left.get(headL);
                        headL++;
                    }
                } else {
                    if (isLess(left.get(headL),right.get(headR))) {
                        array[i] = right.get(headR);
                        headR++;
                    } else {
                        array[i] = left.get(headL);
                        headL++;
                    }
                }
            } else if (headL == lenL) {
                array[i] = right.get(headR);
                headR++;
            } else if (headR == lenR) {
                array[i] = left.get(headL);
                headL++;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] i1 = new Integer[]{6, 1, 35, 7, 2, 10, 5, 12, 8, 23, 10, 0, 9, 1};
        System.out.println("Integer random " + Arrays.toString(i1));
        sort(i1, 0, i1.length -1, false);
        System.out.println("--> Merge sort: " + print(i1) + "\n");

        Integer[] i2 = new Integer[]{0, 1, 3, 7, 7, 9, 11, 25};
        System.out.println("Integer sorted " + Arrays.toString(i2));
        sort(i2, 0, i2.length -1, false);
        System.out.println("--> Merge sort: " + print(i2) + "\n");

        Integer[] i3 = new Integer[]{0, 1, 7, 3, 9, 11, 10, 25};
        System.out.println("Integer nearly sorted " + Arrays.toString(i3));
        sort(i3, 0, i3.length -1, true);
        System.out.println("--> Merge sort: " + print(i3) + "\n");

        Integer[] i4 = new Integer[]{6, 11, 0, 11, 10, 7, 1, 9, 11, 10, 9, 6, 9, 10};
        System.out.println("Integer many duplicates " + Arrays.toString(i4));
        sort(i4, 0, i4.length -1, true);
        System.out.println("--> Merge sort: " + print(i4) + "\n");

        String[] s1 = new String[]{"Tokyo", "Paris", "Hanoi", "Washington", "London", "Moscow", "Berlin"};
        System.out.println("String random " + Arrays.toString(s1));
        sort(s1, 0, s1.length -1, true);
        System.out.println("--> Merge sort: " + print(s1) + "\n");

        Double[] d1 = new Double[]{3.1, 9.2, 4.0, -1.2, 7.9, -9.5, 3.0, -6.7, -0.2, 1.9};
        System.out.println("Double random " + Arrays.toString(d1));
        sort(d1,0, d1.length -1,  false);
        System.out.println("--> Merge sort: " + print(d1) + "\n");
    }
}
