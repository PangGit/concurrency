package huawei.odc100;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * API集群负载统计
 * <p>
 * 题目描述
 * 某个产品的RESTful API集合部署在服务器集群的多个节点上，近期对客户端访问日志进行了采集，需要统计各个API的访问频次，
 * 根据热点信息在服务器节点之间做负载均衡，现在需要实现热点信息统计查询功能。
 * RESTful API是由多个层级构成，层级之间使用 / 连接，如 /A/B/C/D 这个地址，A属于第一级，B属于第二级，C属于第三级，D属于第四级。
 * 现在负载均衡模块需要知道给定层级上某个名字出现的频次，未出现过用0表示，实现这个功能。
 * <p>
 * 输入描述
 * 第一行为N，表示访问历史日志的条数，0 ＜ N ≤ 100。
 * 接下来N行，每一行为一个RESTful API的URL地址，约束地址中仅包含英文字母和连接符 / ，最大层级为10，每层级字符串最大长度为10。
 * 最后一行为层级L和要查询的关键字
 * <p>
 * 输出描述  输出给定层级上，关键字出现的频次，使用完全匹配方式（大小写敏感）。
 * <p>
 * 用例
 * 输入
 * 5
 * /huawei/computing/no/one
 * /huawei/computing
 * /huawei
 * /huawei/cloud/no/one
 * /huawei/wireless/no/one
 * 2 computing
 * 输出
 * 2
 * 1
 * 说明  在第二层级上，computing出现了2次，因此输出2
 * <p>
 * 用例2
 * 输入
 * 5
 * /huawei/computing/no/one
 * /huawei/computing
 * /huawei
 * /huawei/cloud/no/one
 * /huawei/wireless/no/one
 * 4 two
 * 输出
 * 0
 * 说明  存在第四层级的URL上，没有出现two，因此频次是0
 */
public class T49_3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // 读取访问历史日志的条数
        int N = scanner.nextInt();
        scanner.nextLine();

        // 创建一个HashMap，键为层级和关键字，值为频次
        Map<String, Integer> map = new HashMap<>();

        // 遍历每一条访问历史日志
        for (int i = 0; i < N; i++) {
            // 将URL地址按照"/"分割成多个部分
            String[] parts = scanner.nextLine().split("/");
            // 检查每个层级的字符串
            for (int j = 1; j < parts.length; j++) {
                // 将层级和关键字作为键
                String key = j + "-" + parts[j];
                // 如果键在HashMap中存在，就将频次加1，否则将频次设为1
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        // 读取要查询的层级和关键字
        int L = scanner.nextInt();
        String keyword = scanner.next();

        // 输出给定层级上，关键字出现的频次
        System.out.println(map.getOrDefault(L + "-" + keyword, 0));
    }
}
