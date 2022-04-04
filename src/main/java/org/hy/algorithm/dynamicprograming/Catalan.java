package main.java.org.hy.algorithm.dynamicprograming;

public class Catalan {
    public static int catalan(int n) {
        int[] c = new int[n + 1];
        c[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                c[i + 1] += c[j] * c[i - j];
            }
        }
        return c[n];
    }

    public static void main(String[] args) {
        System.out.println(catalan(3));
        System.out.println(catalan(5));
        System.out.println(catalan(15));
    }
}
