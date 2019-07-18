package com.algorithm.search;

/**
 * @author dab
 * @version 1.0.0
 * @Description :
 * @Date 2018/5/24 17:05
 */
public class Search {

    /**
     * 无序队列
     */
    private static int[] disorderlyQueue = {2, 1, 4, 0, 5, 3, 6, 9, 8, 7};

    /**
     * 有序队列
     */
    private static int[] orderlyQueue = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};


    /**
     * 顺序查找（linear search ）
     *
     * @param a     数组
     * @param value 查询目标值
     * @param n     数组长度
     * @return 目标值位置
     */
    private static int sequenceSearch(int[] a, int value, int n) {
        int i;
        for (i = 0; i < n; i++) {
            if (a[i] == value) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 二分查找（binary search），递归版本
     *
     * @param a     数组
     * @param value 查询目标值
     * @param low   最小值
     * @param high  最大值
     * @return 目标值位置
     */
    private static int binarySearch2(int[] a, int value, int low, int high) {
        int mid = low + (high - low) / 2;
        if (a[0] > value || a[a.length - 1] < value) {
            return -1;
        }
        if (a[mid] > value) {
            return binarySearch2(a, value, low, mid - 1);
        } else if (a[mid] < value) {
            return binarySearch2(a, value, mid + 1, high);
        } else {
            return mid;
        }
    }


    public static void main(String[] args) {

        System.out.println("---linear search---position：" + sequenceSearch(disorderlyQueue, 111, disorderlyQueue.length));

        System.out.println("---binary search---position：" + binarySearch2(orderlyQueue, 18, 0, 9));

    }


}
