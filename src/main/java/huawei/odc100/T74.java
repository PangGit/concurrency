package huawei.odc100;

import java.util.Scanner;

/**
 * 螺旋数字矩阵 模拟计算
 * <p>
 * 题目描述
 * 疫情期间，小明隔离在家，百无聊赖，在纸上写数字玩。他发明了一种写法：
 * 给出数字个数n和行数m（0 < n ≤ 999，0 < m ≤ 999），从左上角的1开始，按照顺时针螺旋向内写方式，依次写出2,3…n，最终形成一个m行矩阵。
 * 小明对这个矩阵有些要求：
 * 每行数字的个数一样多
 * 列的数量尽可能少
 * 填充数字时优先填充外部
 * 数字不够时，使用单个*号占位
 * 输入描述
 * 输入一行，两个整数，空格隔开，依次表示n、m
 * <p>
 * 输出描述
 * 符合要求的唯一矩阵
 * <p>
 * 用例1
 * 输入：
 * 9 4
 * 输出：
 * 1 2 3
 * * * 4
 * 9 * 5
 * 8 7 6
 * 说明：
 * 9个数字写成4行，最少需要3列
 */
public class T74 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取要填充的数字个数n
        int m = scanner.nextInt(); // 读取矩阵的行数m

        scanner.close(); // 输入完毕后关闭scanner

        int cols = (int) Math.ceil(n / (double) m); // 计算矩阵的列数
        int[][] matrix = new int[m][cols]; // 创建一个整型矩阵，默认初始化为0

        int num = 1; // 用于填充的数字从1开始
        int top = 0, bottom = m - 1, left = 0, right = cols - 1;
        while (num <= n) {
            for (int i = left; i <= right && num <= n; i++) { // 从左到右填充上边界
                matrix[top][i] = num++;
            }
            top++; // 上边界下移
            for (int i = top; i <= bottom && num <= n; i++) { // 从上到下填充右边界
                matrix[i][right] = num++;
            }
            right--; // 右边界左移
            for (int i = right; i >= left && num <= n; i--) { // 从右到左填充下边界
                matrix[bottom][i] = num++;
            }
            bottom--; // 下边界上移
            for (int i = bottom; i >= top && num <= n; i--) { // 从下到上填充左边界
                matrix[i][left] = num++;
            }
            left++; // 左边界右移
        }

        for (int i = 0; i < m; i++) { // 遍历矩阵的每一行
            for (int j = 0; j < cols; j++) { // 遍历矩阵的每一列
                if (matrix[i][j] == 0) { // 如果当前位置是0，则输出'*'
                    System.out.print('*');
                } else { // 否则输出当前位置的数字
                    System.out.print(matrix[i][j]);
                }
                if (j < cols - 1) { // 在同一行的数字之间打印空格
                    System.out.print(" ");
                }
            }
            System.out.println(); // 每打印完一行后换行
        }
    }
}
