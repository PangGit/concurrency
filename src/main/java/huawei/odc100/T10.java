package huawei.odc100;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 * 公司组织了一次考试,现在考试结果出来了，想看一下有没人存在作弊行为,
 * 但是员工太多了,需要先对员工进行一次过滤,再进一步确定是否存在作弊行为。
 * 过滤的规则为:找到分差最小的员工ID对(p1,p2)列表,要求p1<p2
 * 员工个数取值范国:O<n<100000
 * 员工ID为整数,取值范围:0<=n<=100000
 * 考试成绩为整数,取值范围:0<=score<=300
 * <p>
 * 输入描述
 * 员工的ID及考试分数
 * <p>
 * 输出描述
 * 分差最小的员工ID对(p1,p2)列表,要求p1<p2。
 * 每一行代表一个集合,每个集合内的员工ID按顺序排列,多行结果也以员工对中p1值大小升序排列(如果p1相同则p2升序)。
 */
public class T10 {

    public static void main(String[] args) {
        // 创建一个Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);
        // 读取员工的数量
        int n = scanner.nextInt();
        // 创建一个List用于存储员工的ID和分数
        List<int[]> employees = new ArrayList<>();

        // 读取每个员工的ID和分数，并将其添加到List中
        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            int score = scanner.nextInt();
            employees.add(new int[]{id, score});
        }

        // 关闭Scanner对象
        scanner.close();

        // 对员工List按照分数进行排序
        employees.sort(Comparator.comparingInt(a -> a[1]));

        // 初始化最小分差为Integer的最大值
        int minDiff = Integer.MAX_VALUE;
        // 创建一个List用于存储分差最小的员工ID对
        List<int[]> result = new ArrayList<>();

        // 遍历排序后的员工List，计算相邻员工的分差
        for (int i = 1; i < n; i++) {
            int diff = employees.get(i)[1] - employees.get(i - 1)[1];
            // 如果当前分差小于最小分差，则更新最小分差，并清空结果List，将当前员工ID对添加到结果List中
            if (diff < minDiff) {
                minDiff = diff;
                result.clear();
                result.add(new int[]{employees.get(i - 1)[0], employees.get(i)[0]});
            }
            // 如果当前分差等于最小分差，则将当前员工ID对添加到结果List中
            else if (diff == minDiff) {
                result.add(new int[]{employees.get(i - 1)[0], employees.get(i)[0]});
            }
        }

        // 对结果List按照员工ID进行排序
        result.sort(Comparator.comparingInt(a -> a[0]));

        // 打印出分差最小的员工ID对
        for (int[] pair : result) {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }
}
