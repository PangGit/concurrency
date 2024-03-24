package huawei.odc100;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述：用连续自然数之和来表达整数 （本题分值100）
 * 一个整数可以由连续的自然数之和来表示。
 * 给定一个整数，计算该整数有几种连续自然数之和的表达式，且打印出每种表达式
 * <p>
 * 输入描述
 * 一个目标整数T (1 <=T<= 1000)
 * <p>
 * 输出描述
 * 该整数的所有表达式和表达式的个数。
 * 如果有多种表达式，输出要求为：
 * 自然数个数最少的表达式优先输出
 * 每个表达式中按自然数递增的顺序输出，具体的格式参见样例。
 * 在每个测试数据结束时，输出一行”Result:X”，其中X是最终的表达式个数。
 * <p>
 * 解题思路如下：
 * <p>
 * 首先，直接打印出目标整数本身作为一个表达式。
 * 然后，我们需要找出所有可能的连续自然数之和的表达式。我们可以通过枚举起始自然数来实现这一点。
 * 对于每一个起始自然数，我们从这个数开始，依次加上后面的自然数，直到总和超过目标整数。
 * 当总和等于目标整数时，我们就找到了一个有效的表达式。我们需要将这个表达式存储下来，以便稍后打印。
 * 在存储表达式的同时，我们还需要记录表达式中自然数的个数，因为我们需要按照这个数量对表达式进行排序。
 * 一旦我们找到了所有的表达式，我们就可以对它们进行排序。排序的依据是表达式中自然数的个数，自然数个数少的表达式优先输出。
 * 最后，我们按照排序后的顺序打印出所有的表达式，然后打印出表达式的总数。
 * <p>
 * 这种方法的时间复杂度是O(n^2)，其中n是目标整数。因为我们需要枚举所有可能的起始自然数，并且对于每一个起始自然数，
 * 我们可能需要加到目标整数才能确定是否可以形成有效的表达式。在实际应用中，由于目标整数的范围是1到1000，所以这种方法的效率是可以接受的。
 */
public class T4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        // 输出目标整数T
        System.out.println(target + "=" + target);

        // 存储所有表达式的 vector
        List<String> expressions = getStrings(target);
        // 按表达式中自然数的个数排序
        expressions.sort(Comparator.comparingInt(String::length));
        // 输出所有表达式
        for (String expression : expressions) {
            System.out.println(expression);
        }
        // 输出表达式的个数
        System.out.println("Result:" + (expressions.size() + 1));
    }

    private static List<String> getStrings(int target) {
        List<String> expressions = new ArrayList<>();

        // 枚举从 1 开始的连续自然数的个数
        for (int i = 1; i < target; i++) {
            int sum = 0;
            StringBuilder sb = new StringBuilder();
            // 从第 i 个自然数开始累加
            for (int j = i; sum < target; j++) {
                sum += j;
                sb.append(j);
                // 找到了一个表达式
                if (sum == target) {
                    // 将表达式加入 vector
                    expressions.add(target + "=" + sb);
                    break;
                }
                sb.append("+");
            }
        }
        return expressions;
    }
}
