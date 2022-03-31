package main.java.org.hy.algorithm.searching;

public class LinearSearch {
    public static <T> int search(T[] array, T data) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == data) {
                return i;
            }
        }
        return -1;
    } 

    public static void main(String[] args) {
        Integer[] i1 = {-5, 3, 6, 22, 67, 9};
        System.out.println(search(i1, 6));
        System.out.println(search(i1, 0));

        String[] s1 = {"JavaScript", "Java", "Python", "C", "Ruby"};
        System.out.println(search(s1, "Java"));
    }
}
