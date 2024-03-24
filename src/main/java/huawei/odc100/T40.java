package huawei.odc100;

import java.util.Scanner;

/**
 * 题目描述
 * 有一个字符串数组 words 和一个字符串 chars。
 * 假如可以用 chars 中的字母拼写出 words 中的某个“单词”（字符串），那么我们就认为你掌握了这个单词。
 * words 的字符仅由 a-z 英文小写字母组成，例如 “abc”
 * chars 由 a-z 英文小写字母和 “?” 组成。其中英文 “?” 表示万能字符，能够在拼写时当作任意一个英文字母。
 * 例如：“?” 可以当作 “a” 等字母。
 * 注意：每次拼写时，chars 中的每个字母和万能字符都只能使用一次。
 * 输出词汇表 words 中你掌握的所有单词的个数。没有掌握任何单词，则输出0。
 * <p>
 * 输入描述
 * 第一行：输入数组 words 的个数，记作N。
 * 第二行 ~ 第N+1行：依次输入数组words的每个字符串元素
 * 第N+2行：输入字符串chars
 * <p>
 * 输出描述
 * 输出一个整数，表示词汇表 words 中你掌握的单词个数
 * <p>
 * 备注
 * 1 ≤ words.length ≤ 100
 * 1 ≤ words[i].length, chars.length ≤ 100
 * 所有字符串中都仅包含小写英文字母、英文问号
 * <p>
 * 解题思路
 * 我们需要创建一个数组来计算字符集中每个字母的出现次数，同时计算问号的数量。
 * 接下来，我们遍历所有的单词，对于每个单词，我们也需要计算单词中每个字母的出现次数。
 * 然后，我们需要判断是否可以使用字符集来拼写单词。如果单词中的字母数量大于字符集中的字母数量，
 * 我们可以使用问号来替代。如果问号的数量不足，那么我们就不能拼写这个单词。
 * 如果所有的字母都可以匹配，那么我们就可以拼写这个单词。
 */
public class T40 {

    public static void main(String[] args) {

        // 创建一个扫描器用于读取输入
        Scanner scanner = new Scanner(System.in);
        // 读取输入的第一个整数，即单词的数量
        int N = scanner.nextInt();
        // 消耗掉换行符
        scanner.nextLine();

        // 创建一个字符串数组用于存储所有的单词
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            // 读取每一个单词
            words[i] = scanner.nextLine();
        }

        // 读取包含字母和问号的字符串
        String chars = scanner.nextLine();

        // 创建一个数组用于计数字母的出现次数
        int[] count = new int[26];
        for (char c : chars.toCharArray()) {
            if (c != '?') {
                // 如果字符不是问号，则计数
                count[c - 'a']++;
            }
        }

        // 计算问号的数量
        int wildcards = (int) chars.chars().filter(c -> c == '?').count();

        // 初始化结果为0
        int res = 0;

        // 遍历所有的单词
        for (String word : words) {
            // 创建一个数组用于计数单词中字母的出现次数
            int[] wordCount = new int[26];
            for (char c : word.toCharArray()) {
                wordCount[c - 'a']++; // 计数单词中的字母
            }

            // 如果可以拼写单词，则结果加1
            if (canSpell(wordCount, count, wildcards)) {
                res++;
            }
        }

        // 输出结果
        System.out.println(res);
    }

    // 判断是否可以拼写单词
    private static boolean canSpell(int[] wordCount, int[] count, int wildcards) {
        for (int i = 0; i < 26; i++) {
            if (wordCount[i] > count[i]) {
                // 如果单词中的字母数量大于字符集中的字母数量，则使用问号替代
                wildcards -= wordCount[i] - count[i];
                if (wildcards < 0) {
                    // 如果问号不足，则返回false
                    return false;
                }
            }
        }
        // 如果所有的字母都可以匹配，则返回true
        return true;
    }
}
