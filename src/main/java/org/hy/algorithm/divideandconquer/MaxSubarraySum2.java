package main.java.org.hy.algorithm.divideandconquer;

public class MaxSubarraySum2 {
    static class Node {
        int max;
        int sum;
        int prefix;
        int suffix;

        Node(int value) {
            max = value;
            sum = value;
            prefix = value;
            suffix = value;
        }
    }

    public static int maxSum(int[] nums) {
        Node max = max(nums, 0, nums.length - 1);
        return max.max;
    }

    private static Node max(int[] nums, int left, int right) {
        if (right > left) {
            int mid = left + (right - left) / 2;
            Node maxleft = max(nums, left, mid);
            Node maxright = max(nums, mid + 1, right);
            return merge(maxleft, maxright);
        }
        return new Node(nums[left]);
    }

    private static Node merge(Node left, Node right) {
        Node maxsub = new Node(0);
        maxsub.prefix = Math.max(left.prefix, Math.max(left.sum + right.prefix, left.sum + right.sum));
        maxsub.suffix = Math.max(right.suffix, Math.max(right.sum + left.suffix, left.sum + right.sum));
        maxsub.sum = left.sum + right.sum;
        maxsub.max = Math.max(maxsub.prefix, Math.max(maxsub.suffix, Math.max(left.max, Math.max(right.max, right.prefix + left.suffix))));
        return maxsub;
    }

    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
