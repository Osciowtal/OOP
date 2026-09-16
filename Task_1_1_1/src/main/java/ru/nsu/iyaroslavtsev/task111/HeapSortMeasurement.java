package ru.nsu.iyaroslavtsev.task111;

import java.util.Random;

public class HeapSortMeasurement {
    public static void main(String[] args) {
        int[] sizes = {100, 10_000, 20_000, 100_000, 200_000, 1_000_000};
        System.out.println("Size (N) | Time (nanosec) | N log N        | Constant");

        for (int n : sizes) {
            int[] array = randArr(n);
            long start = System.nanoTime();
            HeapSort.sort(array);
            long end = System.nanoTime();

            long durat = end - start;
            double nlogn = n * (Math.log(n) / Math.log(2));
            double consta = durat / nlogn;

            System.out.printf("%-8d | %-14d | %-14.3f | %-10.3f%n", n, durat, nlogn, consta);
        }
    }

    private static int[] randArr(int size) {
        Random randomer = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = randomer.nextInt();
        return arr;
    }
}