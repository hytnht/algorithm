package main.java.org.hy.algorithm.dynamicprograming;

public class UglyNumbers {
    public static int ugly(int n) {
        int[] u = new int[n];
        u[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        int x2, x3, x5;
        int next;
        for (int i = 1; i < n; i++) {
            x2 = u[i2] * 2;
            x3 = u[i3] * 3;
            x5 = u[i5] * 5;
            next = Math.min(Math.min(x2,x3),x5);
            if (next == x2) i2++;
            if (next == x3) i3++;
            if (next == x5) i5++;
            u[i] = next;
        }
        return u[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(ugly(7));
        System.out.println(ugly(15));
        System.out.println(ugly(150));
    }
}
