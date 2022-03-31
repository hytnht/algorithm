package main.java.org.hy.algorithm.mathematics;

public class Factorial {
    public static int factorial(int n) {
        if (n < 0) throw new Error("Negative number is not allowed.");
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println(factorial(i));
        }
    }
}
