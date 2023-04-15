package com.algorithm.search;

/**
 * 二分查找
 *
 * @author pang
 * @since 2023/4/15 17:38
 */
public class BinarySearch {

    /**
     * 二分查找（binary search），递归版本
     *
     * @param a     数组
     * @param value 查询目标值
     * @param low   最小值
     * @param high  最大值
     * @return 目标值位置
     */
    public static int search(int[] a, int value, int low, int high) {
        int mid = low + (high - low) / 2;
        if (a[0] > value || a[a.length - 1] < value) {
            return -1;
        }
        if (a[mid] > value) {
            return search(a, value, low, mid - 1);
        } else if (a[mid] < value) {
            return search(a, value, mid + 1, high);
        } else {
            return mid;
        }
    }
}
