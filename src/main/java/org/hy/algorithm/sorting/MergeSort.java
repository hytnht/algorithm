package main.java.org.hy.algorithm.sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> {
    private final ArrayList<T> array;
    boolean desc;

    private void sort(ArrayList<T> array, int l, int r) {
        if (r > l) {
            int m = (l + (r - 1)) / 2;
            sort(array, l, m);
            sort(array, m + 1, r);
            merge(array, l, m, r);
        }
    }

    private void merge(ArrayList<T> array, int l, int m, int r) {
        int lenL = m - l + 1;
        int headL = 0;
        ArrayList<T> left = new ArrayList<>(lenL);
        for (int i = l; i <= m; i++) {
            left.add(array.get(i));
        }

        int lenR = r - m;
        int headR = 0;
        ArrayList<T> right = new ArrayList<>(lenR);
        for (int i = m + 1; i <= r; i++) {
            right.add(array.get(i));
        }

        for (int i = l; i <= r; i++) {
            if (headL < lenL && headR < lenR) {
                if(!desc) {
                    if (left.get(headL).compareTo(right.get(headR)) <= 0) {
                        array.set(i, left.get(headL));
                        headL++;
                    } else if (left.get(headL).compareTo(right.get(headR)) > 0) {
                        array.set(i, right.get(headR));
                        headR++;
                    }
                } else {
                    if (left.get(headL).compareTo(right.get(headR)) >= 0) {
                        array.set(i, left.get(headL));
                        headL++;
                    } else if (left.get(headL).compareTo(right.get(headR)) < 0) {
                        array.set(i, right.get(headR));
                        headR++;
                    }
                }
            } else if (headL == lenL) {
                array.set(i, right.get(headR));
                headR++;
            } else if (headR == lenR) {
                array.set(i, left.get(headL));
                headL++;
            }
        }
    }

    MergeSort(ArrayList<T> array, boolean desc) {
        this.array = array;
        this.desc = desc;
        sort(this.array, 0, this.array.size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T i : array) {
            sb.append(i).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length() - 1, ".");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayList<Integer> i1 = new ArrayList<>(Arrays.asList(6, 1, 35, 7, 2, 10, 5, 12, 8, 23, 10, 0, 9, 1));
        System.out.println("Integer random " + i1);
        MergeSort<Integer> i1s = new MergeSort<>(i1, false);
        System.out.println("--> Merge sort: " + i1s + "\n");
        ArrayList<Integer> i2 = new ArrayList<>(Arrays.asList(0, 1, 3, 7, 7, 9, 11, 25));
        System.out.println("Integer sorted " + i2);
        MergeSort<Integer> i2s = new MergeSort<>(i2, false);
        System.out.println("--> Merge sort: " + i2s + "\n");
        ArrayList<Integer> i3 = new ArrayList<>(Arrays.asList(0, 1, 7, 3, 9, 11, 10, 25));
        System.out.println("Integer nearly sorted " + i3);
        MergeSort<Integer> i3s = new MergeSort<>(i3, true);
        System.out.println("--> Merge sort: " + i3s + "\n");
        ArrayList<Integer> i4 = new ArrayList<>(Arrays.asList(6, 11, 0, 11, 10, 7, 1, 9, 11, 10, 9, 6, 9, 10));
        System.out.println("Integer many duplicates " + i4);
        MergeSort<Integer> i4s = new MergeSort<>(i4, true);
        System.out.println("--> Merge sort: " + i4s + "\n");

        ArrayList<String> s1 = new ArrayList<>(Arrays.asList("Tokyo", "Paris", "Hanoi", "Washington", "London", "Moscow", "Berlin"));
        System.out.println("String random " + s1);
        MergeSort<String> s1s = new MergeSort<>(s1, false);
        System.out.println("--> Merge sort: " + s1s + "\n");

        ArrayList<Double> f1 = new ArrayList<>(Arrays.asList(3.1, 9.2, 4.0, -1.2, 7.9, -9.5, 3.0, -6.7, -0.2, 1.9));
        System.out.println("Double random " + f1);
        MergeSort<Double> f1s = new MergeSort<>(f1, true);
        System.out.println("--> Merge sort: " + f1s + "\n");

    }
}
