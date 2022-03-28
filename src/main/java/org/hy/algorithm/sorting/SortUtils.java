package main.java.org.hy.algorithm.sorting;

public class SortUtils {
    static <T extends Comparable<T>> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    static <T extends Comparable<T>> void insert(T[] array, int src, int dst) {
        T data = array[src];
        System.arraycopy(array, dst, array, dst + 1, src - dst);
        array[dst] = data;
    }

    static <T extends Comparable<T>> boolean isLess(T a, T b) {
        return a.compareTo(b) < 0;
    }

    static <T extends Comparable<T>> boolean isGreater(T a, T b) {
        return a.compareTo(b) > 0;
    }

    static <T extends Comparable<T>> String print(T[] array) {
        StringBuilder sb = new StringBuilder();
        for (T i : array) {
            sb.append(i).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length() - 1, ".");
        return sb.toString();
    }

}
