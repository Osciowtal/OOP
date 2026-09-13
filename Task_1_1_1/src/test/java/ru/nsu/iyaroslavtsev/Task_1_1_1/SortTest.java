package ru.nsu.iyaroslavtsev.Task_1_1_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void sort() {
        int[] array = new int[]{3,2,1};
        var res = Sort.sort(array);
        assertArrayEquals(new int[]{1,2,3}, res);
    }
}