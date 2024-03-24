package huawei.odc100;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 虚拟游戏理 栈
 * <p>
 * 题目描述
 * 在一款虚拟游戏中生活，你必须进行投资以增强在虚拟游戏中的资产以免被淘汰出局。
 * 现有一家Bank，它提供有若干理财产品 m 个，风险及投资回报不同，你有 N（元）进行投资，能接收的总风险值为X。
 * 你要在可接受范围内选择最优的投资方式获得最大回报。
 * 备注：
 * 在虚拟游戏中，每项投资风险值相加为总风险值；
 * 在虚拟游戏中，最多只能投资2个理财产品；
 * 在虚拟游戏中，最小单位为整数，不能拆分为小数；
 * 投资额*回报率=投资回
 * <p>
 * 输入描述
 * 第一行：
 * 产品数（取值范围[1,20]）
 * 总投资额（整数，取值范围[1, 10000]）
 * 可接受的总风险（整数，取值范围[1,200]）
 * 第二行：产品投资回报率序列，输入为整数，取值范围[1,60]
 * 第三行：产品风险值序列，输入为整数，取值范围[1, 100]
 * 第四行：最大投资额度序列，输入为整数，取值范围[1, 10000]
 * <p>
 * 输出描述
 * 每个产品的投资额序列
 * <p>
 * 用例
 * 输入
 * 5 100 10
 * 10 20 30 40 50
 * 3 4 5 6 10
 * 20 30 20 40 30
 * 输出
 * 0 30 0 40 0
 * 说明	投资第二项30个单位，第四项40个单位，总的投资风险为两项相加为4+6=10
 */
public class T55_6 {

    public static void main(String[] args) {
        // 创建Scanner对象用于获取用户输入
        Scanner sc = new Scanner(System.in);
        // 读取一行输入并将其分割为字符串数组，然后转换为整数数组
        int[] tmp = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 获取投资项目数量m、总投资额N和风险容忍度X
        int m = tmp[0];
        int N = tmp[1];
        int X = tmp[2];
        // 读取每个项目的预期回报率
        int[] returns = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 读取每个项目的风险值
        int[] risks = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 读取每个项目的最大投资额
        int[] maxInvestments = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 初始化最大回报为0
        int maxReturn = 0;
        // 初始化最大回报对应的投资方案数组
        int[] bestInvestments = new int[m];

        // 遍历所有项目
        for (int i = 0; i < m; i++) {
            // 如果单个项目的风险在容忍度范围内
            if (risks[i] <= X) {
                // 计算对项目i的投资额，不超过总投资额N和项目i的最大投资额
                int investmentForI = Math.min(N, maxInvestments[i]);
                // 计算当前回报
                int currentReturn = investmentForI * returns[i];
                // 如果当前回报大于已知的最大回报
                if (currentReturn > maxReturn) {
                    // 更新最大回报
                    maxReturn = currentReturn;
                    // 重置最佳投资方案数组，并为项目i分配投资额
                    bestInvestments = new int[m];
                    bestInvestments[i] = investmentForI;
                }
            }

            // 遍历项目i之后的项目，寻找两个项目的组合投资方案
            for (int j = i + 1; j < m; j++) {
                // 如果两个项目的风险总和在容忍度范围内
                if (risks[i] + risks[j] <= X) {
                    int investmentForI, investmentForJ;
                    // 根据预期回报率决定投资额分配
                    if (returns[i] > returns[j]) {
                        // 如果项目i的回报率高于项目j，优先投资项目i
                        investmentForI = Math.min(N, maxInvestments[i]);
                        investmentForJ = Math.min(N - investmentForI, maxInvestments[j]);
                    } else {
                        // 如果项目j的回报率高于项目i，优先投资项目j
                        investmentForJ = Math.min(N, maxInvestments[j]);
                        investmentForI = Math.min(N - investmentForJ, maxInvestments[i]);
                    }
                    // 计算当前两个项目组合的回报
                    int currentReturn = investmentForI * returns[i] + investmentForJ * returns[j];
                    // 如果当前回报大于已知的最大回报
                    if (currentReturn > maxReturn) {
                        // 更新最大回报
                        maxReturn = currentReturn;
                        // 重置最佳投资方案数组，并为两个项目分配投资额
                        bestInvestments = new int[m];
                        bestInvestments[i] = investmentForI;
                        bestInvestments[j] = investmentForJ;
                    }
                }
            }
        }

        // 创建StringJoiner对象，用于构建输出格式
        StringJoiner sj = new StringJoiner(" ");
        // 遍历最佳投资方案数组，将投资额添加到StringJoiner中
        for (int investment : bestInvestments) {
            sj.add(String.valueOf(investment));
        }

        // 输出最佳投资方案
        System.out.println(sj);
        // 关闭Scanner对象
        sc.close();
    }
}
