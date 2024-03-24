package huawei.odc100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述：整数对最小和
 * 给定两个整数数组array1、array2，数组元素按升序排列。
 * 假设从array1、array2中分别取出一个元素可构成一对元素，现在需要取出k对元素，
 * 并对取出的所有元素求和，计算和的最小值。
 * 注意：
 * 两对元素如果对应于array1、array2中的两个下标均相同，则视为同一对元素。
 * <p>
 * 输入描述
 * 输入两行数组array1、array2，每行首个数字为数组大小size(0 < size <= 100);
 * 0 < array1[i] <= 1000
 * 0 < array2[i] <= 1000
 * 接下来一行为正整数k
 * 0 < k <= array1.size() * array2.size()
 * <p>
 * 输出描述
 * 满足要求的最小和
 * <p>
 * 用例
 * 输入
 * 3 1 1 2
 * 3 1 2 3
 * 2
 * <p>
 * 输出	4
 * 说明
 * 用例中，需要取2对元素
 * 取第一个数组第0个元素与第二个数组第0个元素组成1对元素[1,1];
 * 取第一个数组第1个元素与第二个数组第0个元素组成1对元素[1,1];
 * 求和为1+1+1+1=4，为满足要求的最小和。
 */
public class T9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入第一个数组
        int size1 = scanner.nextInt();
        List<Integer> array1 = new ArrayList<>();
        for (int i = 0; i < size1; i++) {
            array1.add(scanner.nextInt());
        }

        // 输入第二个数组
        int size2 = scanner.nextInt();
        List<Integer> array2 = new ArrayList<>();
        for (int i = 0; i < size2; i++) {
            array2.add(scanner.nextInt());
        }

        // 输入需要取出的元素对数
        int k = scanner.nextInt();

        // 存储所有可能的元素对的和
        List<Integer> pairsSum = new ArrayList<>();
        for (int value1 : array1) {
            for (int value2 : array2) {
                pairsSum.add(value1 + value2);
            }
        }

        // 对和进行排序
        Collections.sort(pairsSum);

        // 取前k个元素进行求和
        int minSum = 0;
        for (int i = 0; i < k; i++) {
            minSum += pairsSum.get(i);
        }

        System.out.println(minSum);
    }

}
