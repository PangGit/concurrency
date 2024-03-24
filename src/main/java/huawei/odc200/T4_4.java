package huawei.odc200;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述：最长合法表达式  提取字符串中的最长数学表达式并计算  双指针
 * <p>
 * 提取字符串中的最长合法简单数学表达式字符串长度最长的，并计算表达式的值。如果没有返回 0。
 * <p>
 * 简单数学表达式只能包含以下内容
 * 0-9 数字，符号 ±*
 * 说明:
 * 1.所有数字，计算结果都不超过 long
 * 2.如果有多个长度一样的，请返回第一个表达式的结果
 * 3.数学表达式，必须是最长的，合法的
 * 4.操作符不能连续出现，如 ±-+1 是不合法的
 * <p>
 * 示例
 * 输入
 * 1-2abcd
 * 输出
 * -1
 */
public class T4_4 {

    public static void main(String[] args) {
        // 创建一个Scanner对象来处理输入
        Scanner in = new Scanner(System.in);
        // 读取一行输入
        String line = in.nextLine();
        // 关闭Scanner对象
        in.close();

        // 调用extractExpressions方法提取输入中的合法表达式
        List<String> expressions = extractExpressions(line);

        // 使用Java 8的Lambda表达式对表达式按长度进行排序
        expressions.sort((s1, s2) -> Integer.compare(s2.length(), s1.length()));

        // 如果表达式列表不为空，则计算并打印最长表达式的结果
        // 否则，打印0
        if (!expressions.isEmpty()) {
            System.out.println(calc(expressions.get(0)));
        } else {
            System.out.println(0);
        }
    }

    // 提取合法表达式的方法
    public static List<String> extractExpressions(String line) {
        // 创建一个列表来存储提取的表达式
        List<String> expressions = new ArrayList<>();
        // 初始化开始索引为-1
        int start = -1;
        // 有表达式
        boolean have = false;

        // 遍历输入字符串的每个字符
        for (int i = 0; i < line.length(); i++) {
            char cur = line.charAt(i);

            // 如果当前字符是数字
            if (Character.isDigit(cur)) {
                // 如果开始索引为-1，设置开始索引为当前索引
                if (start == -1) {
                    start = i;
                }
            } else if (start != -1 && "+-*".contains(String.valueOf(cur))) {
                // 如果当前字符是操作符，并且前后一个字符是数字
                // 则将当前索引加1
                if (Character.isDigit(line.charAt(i - 1)) && (i + 2) <= line.length() && Character.isDigit(line.charAt(i + 1))) {
                    i++;
                    have = true;
                } else {
                    start = -1;
                }
            } else {
                // 有表达式
                if (have) {
                    expressions.add(line.substring(start, i));
                    start = -1;
                    have = false;
                }
            }
        }
        // 有表达式
        if (have) {
            expressions.add(line.substring(start));
        }
        // 返回提取的表达式列表
        return expressions;
    }

    // 计算表达式结果的方法
    public static int calc(String str) {
        // 创建一个列表来存储表达式的每个元素（数字或操作符）
        List<String> tokens = new ArrayList<>();
        // 创建一个StringBuilder对象来构建数字
        StringBuilder sb = new StringBuilder();

        // 遍历表达式的每个字符
        for (char ch : str.toCharArray()) {
            // 如果当前字符是数字，则将其添加到当前数字的末尾
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else {
                // 否则，将当前数字和当前操作符添加到元素列表中
                // 并开始构建新的数字
                tokens.add(sb.toString());
                tokens.add(String.valueOf(ch));
                sb.setLength(0);
            }
        }
        // 将最后一个数字添加到元素列表中
        tokens.add(sb.toString());

        // 遍历元素列表，先进行所有的乘法操作
        for (int i = 0; i < tokens.size(); i++) {
            if ("*".equals(tokens.get(i))) {
                int result = Integer.parseInt(tokens.get(i - 1)) * Integer.parseInt(tokens.get(i + 1));
                tokens.set(i - 1, String.valueOf(result));
                tokens.remove(i);
                tokens.remove(i);
                i--;
            }
        }

        // 初始化结果为元素列表的第一个数字
        int result = Integer.parseInt(tokens.get(0));
        // 遍历元素列表，进行所有的加法和减法操作
        for (int i = 1; i < tokens.size(); i += 2) {
            if ("+".equals(tokens.get(i))) {
                result += Integer.parseInt(tokens.get(i + 1));
            } else {
                result -= Integer.parseInt(tokens.get(i + 1));
            }
        }

        // 返回计算结果
        return result;
    }

}
