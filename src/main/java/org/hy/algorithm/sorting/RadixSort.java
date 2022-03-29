package main.java.org.hy.algorithm.sorting;

import java.util.Arrays;

import static main.java.org.hy.algorithm.sorting.SortUtils.print;

public class RadixSort {
    public static void sort(Integer[] array, boolean desc) {
        int max = 0;
        for (int data : array) {
            if (data < 0) throw new Error("Positive only.");
            if (max < data) {
                max = data;
            }
        }
        int place = (int) Math.log10(max) + 1;
        for (int i = 1; i <= place; i++) {
            sortDigit(array, i, desc);
        }
    }

    private static void sortDigit(Integer[] array, int place, boolean desc) {
        int[] count = new int[10];
        Integer[] unsorted = array.clone();
        for (int data : array) {
            int digit = (int) (data % Math.pow(10, place) / Math.pow(10, place - 1));
            count[digit]++;
        }
        if (!desc) {
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
        } else {
            for (int i = 8; i >= 0; i--) {
                count[i] += count[i + 1];
            }
        }
        for (int i = array.length - 1; i >= 0; i--) {
            int digit = (int) (unsorted[i] % Math.pow(10, place) / Math.pow(10, place - 1));
            array[count[digit] - 1] = unsorted[i];
            count[digit]--;
        }
    }

    public static void main(String[] args) {
        Integer[] i1 = new Integer[]{6, 1, 35, 7, 2, 10, 5, 12, 8, 23, 10, 0, 9, 1};
        System.out.println("Integer random " + Arrays.toString(i1));
        sort(i1, false);
        System.out.println("--> Radix sort: " + print(i1) + "\n");

        Integer[] i2 = new Integer[]{0, 1, 3, 7, 7, 9, 11, 25};
        System.out.println("Integer sorted " + Arrays.toString(i2));
        sort(i2, false);
        System.out.println("--> Radix sort: " + print(i2) + "\n");

        Integer[] i3 = new Integer[]{0, 1, 7, 3, 9, 11, 10, 25};
        System.out.println("Integer nearly sorted " + Arrays.toString(i3));
        sort(i3, false);
        System.out.println("--> Radix sort: " + print(i3) + "\n");

        Integer[] i4 = new Integer[]{6, 11, 0, 11, 10, 7, 1, 9, 11, 10, 9, 6, 9, 10};
        System.out.println("Integer many duplicates " + Arrays.toString(i4));
        sort(i4, true);
        System.out.println("--> Radix sort: " + print(i4) + "\n");
    }
}
