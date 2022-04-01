package main.java.org.hy.algorithm.sorting;

import java.util.ArrayList;
import java.util.Arrays;

import static main.java.org.hy.algorithm.sorting.SortUtils.print;

public class BucketSort {
    @SuppressWarnings({"rawtype","unchecked"})
    public static void sort(Float[] array, boolean desc) {
        int len = array.length;
        ArrayList<Float>[] bucketList = new ArrayList[len];

        for (int i = 0; i < len; i++) {
            bucketList[i] = new ArrayList<>();
        }

        if (!desc) {
            for (float data : array) {
                int index = (int) (data * len);
                bucketList[index].add(data);
            }
        } else {
            for (float data : array) {
                int index = len - (int) (data * len) - 1;
                bucketList[index].add(data);
            }
        }

        for (ArrayList<Float> bucket : bucketList) {
            int current = 1;
            while (current < bucket.size()) {
                for (int i = 0; i < current; i++) {
                    float data = bucket.get(current);
                    if (!desc) {
                        if (bucket.get(i) > data) {
                            for (int j = current; j > i; j--) {
                                bucket.set(j, bucket.get(j - 1));
                            }
                            bucket.set(i, data);
                            break;
                        }
                    } else {
                        if (bucket.get(i) < data) {
                            for (int j = current; j > i; j--) {
                                bucket.set(j, bucket.get(j - 1));
                            }
                            bucket.set(i, data);
                            break;
                        }
                    }
                }
                current++;
            }
        }

        int i = 0;
        for (ArrayList<Float> bucket : bucketList) {
            for (float data : bucket) {
                array[i] = data;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Float[] f1 = new Float[]{0.43f, 0.41f, 0.11f, 0.67f, 0.34f, 0.96f, 0.02f, 0.001f, 0.6f};
        System.out.println("Float in range [0, 1) " + Arrays.toString(f1));
        sort(f1, false);
        System.out.println("--> Bucket sort: " + print(f1) + "\n");

        Float[] f2 = new Float[]{0.12f, 0.51f, 0.12f, 0.12f, 0.51f, 0.67f, 0.67f, 0.44f};
        System.out.println("Float in range [0, 1), many duplicates " + Arrays.toString(f1));
        sort(f2, true);
        System.out.println("--> Bucket sort: " + print(f2) + "\n");
    }
}
