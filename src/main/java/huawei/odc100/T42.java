package huawei.odc100;

import java.util.Scanner;

/**
 * 题目描述
 * 小明在玩一个游戏，游戏规则如下：
 * 在游戏开始前，小明站在坐标轴原点处（坐标值为0）.
 * 给定一组指令和一个幸运数，每个指令都是一个整数，小明按照指令前进指定步数或者后退指定步数。
 * 前进代表朝坐标轴的正方向走，后退代表朝坐标轴的负方向走。
 * 幸运数为一个整数，如果某个指令正好和幸运数相等，则小明行进步数+1。
 * <p>
 * 例如：
 * 幸运数为3，指令为[2,3,0,-5]
 * 指令为2，表示前进2步；
 * 指令为3，正好和幸运数相等，前进3+1=4步；
 * 指令为0，表示原地不动，既不前进，也不后退。
 * 指令为-5，表示后退5步。
 * 请你计算小明在整个游戏过程中，小明所处的最大坐标值。
 * <p>
 * 输入描述
 * 第一行输入1个数字，代表指令的总个数 n（1 ≤ n ≤ 100）
 * 第二行输入1个数字，代表幸运数m（-100 ≤ m ≤ 100）
 * 第三行输入n个指令，每个指令的取值范围为：-100 ≤ 指令值 ≤ 100
 * <p>
 * 输出描述  输出在整个游戏过程中，小明所处的最大坐标值。异常情况下输出：12345
 * <p>
 * 用例1
 * 输入
 * 2
 * 1
 * -5 1
 * 输出
 * 0
 * <p>
 * 解题思路
 * 本题就模拟计算,主要注意幸运数字是0的情况.
 */
public class T42 {

    public static void main(String[] args) {

        // 创建一个新的扫描器实例
        Scanner sc = new Scanner(System.in);

        // 读取用户输入的整数n和l
        int count = Integer.parseInt(sc.nextLine());
        int lucky = Integer.parseInt(sc.nextLine());

        // 检查输入的整数n和l是否在指定的范围内
        if (count < 1 || count > 100 || lucky < -100 || lucky > 100) {
            System.out.println("12345");
            return;
        }

        // 初始化位置变量p和最大位置变量mp
        int p = 0;
        int mp = 0;

        // 读取用户输入的整数cmd
        String[] cmdArray = sc.nextLine().split(" ");
        if (cmdArray.length != count) {
            System.out.println("12345");
            return;
        }

        // 对于每一个输入的整数
        for (int i = 0; i < count; i++) {
            int cmd = Integer.parseInt(cmdArray[i]);

            // 检查输入的整数cmd是否在指定的范围内
            if (cmd < -100 || cmd > 100) {
                System.out.println("12345");
                return;
            }

            // 如果输入的整数cmd等于l
            if (cmd == lucky) {
                // 如果cmd大于0，那么位置p增加cmd+1
                if (cmd > 0) {
                    p += cmd + 1;
                } else if (cmd < 0) {
                    // 否则，位置p减少cmd-1
                    p += cmd - 1;
                }
            } else {
                // 如果输入的整数cmd不等于l，那么位置p增加cmd
                p += cmd;
            }
            // 更新最大位置变量mp
            mp = Math.max(mp, p);
        }

        // 打印最大位置变量mp
        System.out.println(mp);
    }
}
