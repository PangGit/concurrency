package huawei.odc100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 开源项目热度榜单 排序比较
 * <p>
 * 题目描述
 * 某个开源社区希望将最近热度比较高的开源项目出一个榜单，推荐给社区里面的开发者。
 * 对于每个开源项目，开发者可以进行关注(watch)、收藏(star)、fork、提issue、提交合并请求(MR)等。
 * 数据库里面统计了每个开源项目关注、收藏、fork、issue、MR的数量，开源项目的热度根据这5个维度的加权求和进行排序。
 * H = (Wwatch * #watch) + (Wstar * #star) + (Wfork * #fork) +  (Wissue * #issue) + (Wmr * #mr)
 * H表示热度值
 * Wwatch、Wstar、Wfork、Wissue、Wmr分别表示5个统计维度的权重。
 * #watch、#star、#fork、#issue、#mr分别表示5个统计维度的统计值。
 * 榜单按照热度值降序排序，对于热度值相等的，按照项目名字转换为全小写字母后的字典序排序（‘a’,‘b’,‘c’,…,‘x’,‘y’,‘z’)。
 * <p>
 * 输入描述
 * 第一行输入为N，表示开源项目的个数，0 ＜ N ＜100。
 * 第二行输入为权重值列表，一共 5 个整型值，分别对应关注、收藏、fork、issue、MR的权重，权重取值 0 < W ≤ 50。
 * 第三行开始接下来的 N行 为开源项目的统计维度，每一行的格式为：
 * name nr_watch nr_start nr_fork nr_issue nr_mr
 * 其中 name 为开源项目的名字，由英文字母组成，长度 ≤ 50，其余 5 个整型值分别为该开源项目关注、收藏、fork、issue、MR的数量，数量取值 0 < nr ≤ 1000。
 * <p>
 * 输出描述
 * 按照热度降序，输出开源项目的名字，对于热度值相等的，按照项目名字转换为全小写后的字典序排序（‘a’ > ‘b’ > ‘c’ > … > ‘x’ > ‘y’ > ‘z’）。
 * <p>
 * 用例1
 * 输入
 * 4
 * 8 6 2 8 6
 * camila 66 70 46 158 80
 * victoria 94 76 86 189 211
 * anthony 29 17 83 21 48
 * emily 53 97 1 19 218
 * 输出
 * victoria
 * camila
 * emily
 * anthony
 * 说明
 * 排序热度值计算：
 * camila: 66*8 + 70*6 + 46*2 + 158*8 + 80*6 = 2784
 * victoria: 94*8 + 76*6 + 86*2 + 189*8 + 211*6 = 4158
 * anthony: 29*8 + 17*6 + 83*2 + 21*8 + 48*6 = 956
 * emily: 53*8 + 97*6 + 1*2 + 19*8 + 218*6 = 2468
 * <p>
 * 用例2
 * 输入
 * 5
 * 5 6 6 1 2
 * camila 13 88 46 26 169
 * grace 64 38 87 23 103
 * lucas 91 79 98 154 79
 * leo 29 27 36 43 178
 * ava 29 27 36 43 178
 * 输出
 * lucas
 * grace
 * camila
 * ava
 * leo
 * 说明
 * 排序热度值计算：
 * camila: 13*5 + 88*6 + 46*6 + 26*1 + 169*2 = 1233
 * grace: 64*5 + 38*6 + 87*6 + 23*1 + 103*2 = 1299
 * lucas: 91*5 + 79*6 + 98*6 + 154*1 + 79*2 = 1829
 * leo: 29*5 + 27*6 + 36*6 + 43*1 + 178*2 = 922
 * ava: 29*5 + 27*6 + 36*6 + 43*1 + 178*2 = 922
 * <p>
 * 解题思路
 * 读取其名称和评分。然后，我们使用权重和评分来计算项目的"热度"。
 * 这是通过将每个评分与其对应的权重相乘，然后将所有的乘积相加来完成的。
 * 排序首先是根据热度进行的，热度高的项目排在前面。
 * 如果两个项目的热度相同，那么我们就根据项目名称进行排序，名称按字母顺序排列。
 */
public class T22_5 {

    public static void main(String[] args) {
        // 创建Scanner对象用于获取用户输入
        Scanner sc = new Scanner(System.in);

        // 读取项目数量n
        int n = sc.nextInt();

        // 创建并填充权重数组weights
        int[] weights = new int[5];
        for (int i = 0; i < 5; i++) {
            weights[i] = sc.nextInt(); // 读取每个权重并存储到数组中
        }

        // 创建一个二维数组projects来存储项目的名称和热度值
        // 其中projects[i][0]存储项目名称，projects[i][1]存储项目热度
        String[][] projects = new String[n][2];
        // 读取项目信息并计算每个项目的热度
        for (int i = 0; i < n; i++) {
            // 存储项目名称
            projects[i][0] = sc.next();
            // 初始化项目热度为0
            int hot = 0;
            // 读取项目的5个评分并计算热度
            for (int j = 0; j < 5; j++) {
                // 计算热度
                hot += sc.nextInt() * weights[j];
            }
            // 将热度值转换为字符串并存储
            projects[i][1] = String.valueOf(hot);
        }
        sc.close();

        // 使用自定义比较器对项目数组进行排序
        Arrays.sort(projects, (a, b) -> {
            // 解析字符串热度为整数
            int hotA = Integer.parseInt(a[1]);
            int hotB = Integer.parseInt(b[1]);
            // 首先根据热度值降序排序
            if (hotA != hotB) {
                return hotB - hotA; // 热度高的项目排在前面 倒序
            } else {
                // 如果热度相同，则根据项目名称字母顺序升序排序
                // 名称相同的项目按字母顺序排列
                return a[0].toLowerCase().compareTo(b[0].toLowerCase());
            }
        });

        // 遍历排序后的项目数组并打印项目名称
        for (String[] project : projects) {
            System.out.println(project[0]); // 打印项目名称
        }
    }
}
