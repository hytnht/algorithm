package main.java.org.hy.algorithm.dynamicprograming;

public class Knapsack01 {
    public static int knapsack(int[] weight, int[] value, int cap) {
        int len = weight.length;
        int[][] k = new int[len + 1][cap + 1];
        for (int row = 1; row <= len; row++) {
            for (int col = 1; col <= cap; col++) {
                if (col >= weight[row - 1]) {
                    k[row][col] = Math.max(k[row - 1][col], value[row - 1] + k[row -1][col - weight[row - 1]]);
                } else {
                    k[row][col] = k[row - 1][col];
                }
            }
        }
        return k[len][cap];
    }

    public static void main(String[] args) {
        int[] w1 = new int[]{3, 4, 6, 5};
        int[] v1 = new int[]{2, 3, 1, 4};
        knapsack(w1, v1, 8);
        System.out.println(knapsack(w1, v1, 8));

        int[] w2 = new int[]{1, 2, 3, 5};
        int[] v2 = new int[]{1, 6, 10, 16};
        System.out.println(knapsack(w2, v2, 7));
    }
}
