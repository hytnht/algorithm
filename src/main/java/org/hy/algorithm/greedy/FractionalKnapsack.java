package main.java.org.hy.algorithm.greedy;

public class FractionalKnapsack {
    public static double knapsack(double[] value, double[] weight, double cap) {
        double[] ratio = getRatio(value, weight);
        int max = getMax(ratio);
        double size = 0;
        double total = 0;
        while (size + weight[max] <= cap) {
            total += value[max];
            size += weight[max];
            ratio[max] = 0;
            max = getMax(ratio);
        }
        if (size < cap) {
            total += value[max] * (cap - size) / weight[max];
        }
        return total;
    }

    private static double[] getRatio(double[] value, double[] weight) {
        int len = value.length;
        double[] ratio = new double[len];
        for (int i = 0; i < len; i++) {
            ratio[i] = value[i] / weight[i];
        }
        return ratio;
    }

    private static int getMax(double[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[max]) {
                max = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        double[] value = new double[]{5, 10, 15, 7, 8, 9, 4};
        double[] weight = new double[]{1, 3, 5, 4, 1, 3, 2};
        System.out.println(knapsack(value, weight, 15));

        double[] price = new double[]{2.6, 7.15, 8.1, 1, 22.1, 9.63, 4.99};
        double[] loading = new double[]{1.1, 3.55, 4.9, 0.25, 15, 7, 2.01};
        System.out.println(knapsack(price, loading, 20));
    }
}
