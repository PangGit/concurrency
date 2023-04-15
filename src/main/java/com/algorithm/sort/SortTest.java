package com.algorithm.sort;

import java.util.Arrays;

/**
 * 排序
 *
 * @author pang
 * @since 2023/4/15 17:50
 */
public class SortTest {

    /**
     * 无序队列
     */
    public static final int[] queue = {2, 1, 4, 0, 5, 3, 6, 9, 8, 7};

    public static void main(String[] args) {
        System.out.println("1、BubbleSort : " + Arrays.toString(new BubbleSort().sort(queue)));

        System.out.println("2、BucketSort : " + Arrays.toString(new BucketSort().sort(queue)));

        System.out.println("3、CountingSort : " + Arrays.toString(new CountingSort().sort(queue)));

        System.out.println("4、HeapSort : " + Arrays.toString(new HeapSort().sort(queue)));

        System.out.println("5、InsertSort : " + Arrays.toString(new InsertSort().sort(queue)));

        System.out.println("6、MergeSort : " + Arrays.toString(new MergeSort().sort(queue)));

        System.out.println("7、QuickSort : " + Arrays.toString(new QuickSort().sort(queue)));

        System.out.println("8、RadixSort : " + Arrays.toString(new RadixSort().sort(queue)));

        System.out.println("9、SelectionSort : " + Arrays.toString(new SelectionSort().sort(queue)));

        System.out.println("10、ShellSort : " + Arrays.toString(new ShellSort().sort(queue)));
    }
}
