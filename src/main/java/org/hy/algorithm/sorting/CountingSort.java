package main.java.org.hy.algorithm.sorting;

import java.util.Arrays;

import static main.java.org.hy.algorithm.sorting.SortUtils.print;

public class CountingSort {
    public static void sort(Integer[] array, boolean desc) {
        Integer[] unsorted = array.clone();
        int len = array.length;
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < len; i++) {
            if (max < array[i]) {
                max = array[i];
            }
            if (min > array[i]) {
                min = array[i];
            }
        }
        int range = max + 1 - min;
        int[] count = new int[range];
        for (int i : array) {
            count[i - min]++;
        }
        if (!desc) {
            for (int i = 1; i < range; i++) {
                count[i] += count[i - 1];
            }
        } else {
            for (int i = range - 2; i >= 0; i--) {
                count[i] += count[i + 1];
            }
        }
        for (int i : unsorted) {
            array[count[i - min] - 1] = i;
            count[i - min]--;
        }
    }

    public static void main(String[] args) {
        Integer[] i1 = new Integer[]{6, 1, 35, -7, -2, 10, 5, 12, -8, 23, 10, 0, 9, 1};
        System.out.println("Integer random " + Arrays.toString(i1));
        sort(i1, false);
        System.out.println("--> Counting sort: " + print(i1) + "\n");

        Integer[] i2 = new Integer[]{0, 1, 3, 7, 7, 9, 11, 25};
        System.out.println("Integer sorted " + Arrays.toString(i2));
        sort(i2, false);
        System.out.println("--> Counting sort: " + print(i2) + "\n");

        Integer[] i3 = new Integer[]{0, 1, 7, 3, 9, 11, 10, 25};
        System.out.println("Integer nearly sorted " + Arrays.toString(i3));
        sort(i3, false);
        System.out.println("--> Counting sort: " + print(i3) + "\n");

        Integer[] i4 = new Integer[]{6, 11, 0, 11, 10, -7, 1, 9, 11, 10, 9, 6, 9, 10};
        System.out.println("Integer many duplicates " + Arrays.toString(i4));
        sort(i4, true);
        System.out.println("--> Counting sort: " + print(i4) + "\n");
    }
}
