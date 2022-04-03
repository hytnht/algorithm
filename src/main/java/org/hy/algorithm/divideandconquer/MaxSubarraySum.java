package main.java.org.hy.algorithm.divideandconquer;

public class MaxSubarraySum {
    public static int maxSum(int[] array, int left, int right) {
        if (right > left) {
            int mid = left + (right - left) / 2;
            int lsum = maxSum(array, left, mid);
            int rsum = maxSum(array, mid + 1, right);
            int msum = maxCrossMidSum(array, left, mid, right);
            return Math.max(Math.max(lsum, rsum), msum);
        }
        return array[left];
    }

    private static int maxCrossMidSum(int[] array, int left, int mid, int right) {
        int lsum = 0;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += array[i];
            if (sum > lsum) lsum = sum;
        }
        int rsum = 0;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += array[i];
            if (sum > rsum) rsum = sum;
        }
        return lsum + rsum;
    }

    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{-2, -5, 6, -2, -3, 1, 5, -6}, 0, 7));
        System.out.println(maxSum(new int[]{12, -2, -23, 18, -1, -14, -21, 16, 19, -5, 10, -3, -20, 13, -4, -7}, 0, 15));
    }
}
