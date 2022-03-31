package main.java.org.hy.algorithm.mathematics;

public class Catalan {
    public static int catalan(int n) {
        int result = 0;
        if (n == 0) result = 1;
        for (int i = 0; i < n; i++) {
            result = result + catalan(i) * catalan(n - i - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println(catalan(i));
        }
    }
}
