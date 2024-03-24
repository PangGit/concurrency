package huawei.odc100;

import java.util.Scanner;

/**
 * 题目描述：字符串序列判定/最后一个有效字符（本题分值100）
 * <p>
 * 输入两个字符串S和L，都只包含英文小写字母。S长度<=100，L长度<=500,000。判定S是否是L的有效子串。
 * <p>
 * 判定规则：
 * <p>
 * S中的每个字符在L中都能找到（可以不连续），
 * <p>
 * 且S在Ｌ中字符的前后顺序与S中顺序要保持一致。
 * <p>
 * （例如，S=”ace”是L=”abcde”的一个子序列且有效字符是a、c、e，而”aec”不是有效子序列，且有效字符只有a、e）
 * <p>
 * 输入描述
 * 输入两个字符串S和L，都只包含英文小写字母。S长度<=100，L长度<=500,000。
 * <p>
 * 先输入S，再输入L，每个字符串占一行。
 * <p>
 * 输出描述
 * 输出S串最后一个有效字符在L中的位置。（首位从0开始计算，无有效字符返回-1）
 */
public class T1 {

    public static void main(String[] args) {
        // 创建一个Scanner对象来读取用户的输入
        Scanner scanner = new Scanner(System.in);

        // 读取第一个字符串S
        String stringS = scanner.nextLine();
        // 读取第二个字符串L
        String stringL = scanner.nextLine();

        // 初始化两个索引，分别用于遍历S和L
        int indexS = 0;
        int indexL = 0;

        // 当S和L都没有遍历完时，继续遍历
        while (indexS < stringS.length() && indexL < stringL.length()) {
            // 如果S中的当前字符与L中的当前字符相同，则S的索引加1
            if (stringS.charAt(indexS) == stringL.charAt(indexL)) {
                indexS++;
            }
            // 无论字符是否相同，L的索引都加1
            indexL++;
        }

        // 如果S的所有字符都在L中找到了（即S已经遍历完了），则打印L中最后一个有效字符的位置（即L的当前索引减1）
        if (indexS == stringS.length()) {
            System.out.println(indexL - 1);
        } else {
            // 如果S还有字符没有在L中找到，则打印-1
            System.out.println(-1);
        }
    }
}
