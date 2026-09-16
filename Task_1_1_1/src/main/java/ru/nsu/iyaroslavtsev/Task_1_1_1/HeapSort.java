package ru.nsu.iyaroslavtsev.Task_1_1_1;

import java.util.Random;

/**
 * Class that implements Heapsort - in place, no mem allocation, O(n * log n)
 */
public class HeapSort {
    /**
     * Receives an array and returns sorted one
     * <p>
     * 2 work stages:<br>
     * 1. Build a max-heap (root is max, each parent is greater than or equal to their child)
     * 2. Take out all root elems one-by-one (swap them with last elem of processing arr).
     * </p>
     * <p>
     * Then get shrinked array sorted again with small_to_bottom (sift_down) function call after each retrieve.
     * (need to call small_to_bottom for the root because it's biggest elem that stays in unsorted part of array).
     * </p>
     *
     *
     * @param array of integers to be sorted
     * @return the same array sorted in ascending order
     */
    public static int[] sort(int[] array) {
        if (array == null) return null;

        int num = array.length;
        for (int parent = num / 2 - 1; parent >= 0; parent--) {
            small_to_bottom(array, num, parent);
        }

        for (int end = num - 1; end > 0; end--) {
            swap(array, 0, end);
            small_to_bottom(array, end, 0);
        }
        return array;
    }

    private static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    private static void small_to_bottom(int[] arr, int num, int i) {
        while (true) {
            int max = i;
            int left_child = i * 2 + 1;
            int right_child = i * 2 + 2;

            if ((left_child < num) && (arr[left_child] > arr[max])) max = left_child;
            if ((right_child) < num && (arr[right_child] > arr[max])) max = right_child;
            if (max == i) return;

            swap(arr, max, i);
            i = max;
        }
    }

    public static void main(String[] args) {
        int[] sizes = {100,10_000,20_000,100_000,200_000,1_000_000,10_000_000};
        System.out.println("Size (N) | Time (nanosec) | N log N        | Constant");

        for (int n : sizes) {
            int[] array = rand_arr(n);
            long start, end;

            start = System.nanoTime();
            HeapSort.sort(array);
            end = System.nanoTime();

            long durat;
            double nlogn, consta;
            durat = end - start;
            nlogn = n * (Math.log(n) / Math.log(2));
            consta = durat / nlogn;

            System.out.printf("%-8d | %-14d | %-14.3f | %-10.3f%n", n, durat, nlogn, consta);
        }
    }

    private static int[] rand_arr(int size) {
        Random randomer = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = randomer.nextInt();
        return arr;
    }
}