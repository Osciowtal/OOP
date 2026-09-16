package ru.nsu.iyaroslavtsev.task111;

import java.util.Random;

/**
 * Class that implements Heapsort - in place, no mem allocation, O(n * log n).
 */
public class HeapSort {
    /**
     * Receives an array and returns sorted one.
     * "Layers" in binary heap - log(n), so one call of siftDown works for log(n) at worst case.
     * 2 work stages:
     * 1. Build a max-heap (root is max, each parent is greater than or equal to their child).
     * siftDown for each parent node - it takes O(n).
     * 2. Take out all root elems one-by-one (swap them with last elem of processing arr).
     * siftDown after each retrieve to get a heap sorted again - it takes O(n log n).
     * Final asymptotics = O(n + n log n) = O(n log n).
     * @param array of integers to be sorted
     * @return the same array sorted in ascending order
     */
    public static int[] sort(int[] array) {
        if (array == null) return null;

        int num = array.length;
        for (int parent = num / 2 - 1; parent >= 0; parent--) {
            siftDown(array, num, parent);
        }

        for (int end = num - 1; end > 0; end--) {
            swap(array, 0, end);
            siftDown(array, end, 0);
        }
        return array;
    }

    private static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    private static void siftDown(int[] arr, int num, int i) {
        while (true) {
            int max = i;
            int leftChild = i * 2 + 1;
            int rightChild = i * 2 + 2;

            if ((leftChild < num) && (arr[leftChild] > arr[max])) max = leftChild;
            if ((rightChild) < num && (arr[rightChild] > arr[max])) max = rightChild;
            if (max == i) {return;}

            swap(arr, max, i);
            i = max;
        }
    }
}