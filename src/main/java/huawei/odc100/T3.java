package huawei.odc100;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 题目描述：构成指定长度字符串的个数 (本题分值100)
 * 给定 M（0 < M ≤ 30）个字符（a-z），从中取出任意字符（每个字符只能用一次）拼接成长度为 N（0 < N ≤ 5）的字符串，
 * 要求相同的字符不能相邻，计算出给定的字符列表能拼接出多少种满足条件的字符串，输入非法或者无法拼接出满足条件的字符串则返回0。
 * <p>
 * 输入描述  给定的字符列表和结果字符串长度，中间使用空格(" ")拼接
 * 输出描述  满足条件的字符串个数
 * <p>
 * 解题思路
 * 使用递归和回溯的思想来生成不同的字符串。具体的逻辑如下：
 * <p>
 * 首先，我们定义一个函数generateDistinctStrings，这个函数接收以下参数：可用字符集s，目标字符串长度length，
 * 当前已生成的字符串current，已生成的结果集result，以及一个标记数组used，用来记录每个字符是否已被使用。
 * <p>
 * 在generateDistinctStrings函数中，首先检查当前已生成的字符串current的长度是否等于目标长度length。
 * 如果等于，说明我们已经生成了一个满足长度要求的字符串，将其添加到结果集result中，然后返回。
 * <p>
 * 如果当前字符串current的长度还未达到目标长度length，我们就需要继续添加字符。
 * 此时，我们遍历可用字符集s中的每一个字符。对于每一个字符，我们首先检查它是否已经被使用（通过查看used数组），以及它是否与current的最后一个字符相同。
 * 如果字符已经被使用，或者与current的最后一个字符相同，我们就跳过这个字符，继续检查下一个字符。
 * <p>
 * 如果一个字符未被使用，且与current的最后一个字符不同，我们就将它添加到current的末尾，然后标记这个字符为已使用，
 * 接着递归调用generateDistinctStrings函数，以生成下一个字符。
 * <p>
 * 在递归调用返回后，我们需要取消对当前字符的使用标记，以便在后续的遍历中可以再次使用这个字符。这就是回溯的思想，即撤销之前的选择，尝试其他的选择。
 * <p>
 * 用例1
 * 输入 aab 2
 * 输出 2
 * 说明 只能构成ab,ba。
 */
public class T3 {

    public static void main(String[] args) {
        // 创建一个Scanner对象来读取用户的输入
        Scanner sc = new Scanner(System.in);
        // 读取用户输入的字符串
        String input = sc.nextLine();

        // 将输入的字符串按空格分割为两部分，分别为字符串和长度
        String[] parts = input.split(" ");
        String str = parts[0]; // 获取输入的字符串
        int length = Integer.parseInt(parts[1]); // 将输入的长度部分转换为整数

        // 调用countDistinctStrings方法计算满足条件的不同字符串的数量
        int count = countDistinctStrings(str, length);
        // 输出计算结果
        System.out.println(count);
    }

    // 计算满足条件的不同字符串的数量
    public static int countDistinctStrings(String str, int length) {
        // 创建一个HashSet来存储不同的字符串
        HashSet<String> set = new HashSet<>();
        // 创建一个boolean数组来标记字符串中的字符是否已经被使用
        boolean[] used = new boolean[str.length()];
        // 调用generateDistinctStrings方法生成满足条件的不同字符串
        generateDistinctStrings(str, length, "", set, used);
        // 打印生成的所有不同的字符串
        for (String str1 : set) {
            System.out.println(str1);
        }
        // 返回不同字符串的数量
        return set.size();
    }

    // 递归生成满足条件的不同字符串
    public static void generateDistinctStrings(String str, int length, String current, HashSet<String> set, boolean[] used) {
        // 当生成的字符串长度等于指定长度时，将其加入到HashSet中
        if (current.length() == length) {
            set.add(current);
            return;
        }

        // 遍历字符串中的字符
        for (int i = 0; i < str.length(); i++) {
            // 判断字符是否已经被使用，或者当前字符与前一个字符相同
            if (used[i] || (!current.isEmpty() && current.charAt(current.length() - 1) == str.charAt(i))) {
                // 如果字符已被使用或与前一个字符相同，则跳过当前字符
                continue;
            }
            // 标记当前字符为已使用
            used[i] = true;
            // 递归调用生成下一个字符
            generateDistinctStrings(str, length, current + str.charAt(i), set, used);
            // 取消标记当前字符的使用状态，以便下一次遍历
            used[i] = false;
        }
    }

}
