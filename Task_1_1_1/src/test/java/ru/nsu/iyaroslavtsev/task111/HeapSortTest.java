package ru.nsu.iyaroslavtsev.task111;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    @Test
    void testEmptyOne() {
        int[] empty = {};
        assertArrayEquals(new int[]{}, HeapSort.sort(empty));
    }
    @Test
    void testSoloElem() {
        int[] solo = {8};
        assertArrayEquals(new int[]{8}, HeapSort.sort(solo));
    }
    @Test
    void testDuplics() {
        int[] duplics = {5,5,5,5,901,901,901,2,2,77,77,77,77};
        int[] expected = {2,2,5,5,5,5,77,77,77,77,901,901,901};
        assertArrayEquals(expected, HeapSort.sort(duplics));
    }
    @Test
    void testAlreadySorted() {
        int[] prepared = {-1000,-888,-1,0,9,10,11,12};
        int[] expected = {-1000,-888,-1,0,9,10,11,12};
        assertArrayEquals(expected, HeapSort.sort(prepared));
    }
    @Test
    void testReverseSorted() {
        int[] reverse = {10,9,8,7,6,5,4,3,2,1,0,-1};
        int[] expected = {-1,0,1,2,3,4,5,6,7,8,9,10};
        assertArrayEquals(expected, HeapSort.sort(reverse));
    }
    @Test
    void testNull() {
        assertNull(HeapSort.sort(null));
    }

    @Test
    void edgeValsTest() {
        int[] edgeArr = {Integer.MAX_VALUE,56,32,1,1,Integer.MIN_VALUE,0,-20};
        int[] expected = {Integer.MIN_VALUE,-20,0,1,1,32,56,Integer.MAX_VALUE};
        assertArrayEquals(expected, HeapSort.sort(edgeArr));
    }
}