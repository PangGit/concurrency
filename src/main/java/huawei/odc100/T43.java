package huawei.odc100;

import java.util.Scanner;

/**
 * 题目描述
 * 有位客人来自异国，在该国使用m进制计数。该客人有个幸运数字n(n<m)，
 * 每次购物时，其总是喜欢计算本次支付的花费(折算为异国的价格后)中存在多少幸运数字。
 * 问：当其购买一个在我国价值k的产品时，其中包含多少幸运数字？
 * <p>
 * 输入描述
 * 第一行输入为 k, n, m。
 * 其中：
 * k 表示 该客人购买的物品价值（以十进制计算的价格）
 * n 表示 该客人的幸运数字
 * m 表示 该客人所在国度的采用的进制
 * <p>
 * 输出描述
 * 输出幸运数字的个数，行末无空格。当输入非法内容时，输出0
 * <p>
 * 用例1
 * 输入：10 2 4
 * 输出：2
 * 说明：10用4进制表示时为22，同时，异国客人的幸运数字是2，故而此处输出为2，表示有2个幸运数字。
 * <p>
 * 用例2
 * 输入：10 4 4
 * 输出：0
 * 说明：此时客人的幸运数字为4，但是由于该国最大为4进制，故而在该国的进制下不可能出现幸运数字，故而返回0
 * <p>
 * 解题思路
 * 将十进制数转换为其他进制数的基本方法是除法-取余数。
 * 以下是将十进制数转换为其他进制数的步骤：
 * 将十进制数除以目标进制数，得到商和余数。
 * 记录余数。
 * 将商再除以目标进制数。
 * 重复步骤2和3，直到商为0。
 * 将记录的余数倒序排列，得到的就是转换后的数。
 */
public class T43 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // 该客人购买的物品价值（以十进制计算的价格）
        int k = Integer.parseInt(input.split(" ")[0]);
        // 该客人的幸运数字  幸运数字n(n<m)
        int n = Integer.parseInt(input.split(" ")[1]);
        // 该客人所在国度的采用的进制
        int m = Integer.parseInt(input.split(" ")[2]);

        // 检查输入是否合法
        if (k < 0 || n < 0 || m <= 1 || n >= m) {
            System.out.println(0);
            return;
        }

        // 幸运数字计数器
        int count = 0;

        while (k > 0) {
            // 如果当前位是幸运数字，计数器加1
            if (k % m == n) {
                count++;
            }
            // 将k转换为m进制
            k /= m;
        }

        System.out.println(count);
    }
}
