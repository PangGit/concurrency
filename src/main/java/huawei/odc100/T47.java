package huawei.odc100;

import java.util.Scanner;

/**
 * 题目描述
 * 给定一个url前缀和url后缀,通过,分割 需要将其连接为一个完整的url
 * <p>
 * 如果前缀结尾和后缀开头都没有/，需要自动补上/连接符
 * 如果前缀结尾和后缀开头都为/，需要自动去重
 * <p>
 * 约束：不用考虑前后缀URL不合法情况
 * <p>
 * 输入描述  url前缀(一个长度小于100的字符串) url后缀(一个长度小于100的字符串)
 * 输出描述  拼接后的url
 * <p>
 * 用例
 * 输入	/abc/,/bcd
 * 输出	/abc/bcd
 * 说明	无
 */
public class T47 {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            // 读取输入的url前缀和url后缀
            String line = scanner.nextLine();
            String[] split = line.split(",");

            // 如果没有输入前缀和后缀，则输出"/"
            if (split.length == 0) {
                System.out.println("/");
                return;
            }

            // 获取前缀和后缀
            StringBuilder urlBuilder = getStringBuilder(split);

            // 去重"/"
            String url = urlBuilder.toString().replaceAll("/+", "/");

            System.out.println(url);
        }
    }

    private static StringBuilder getStringBuilder(String[] split) {
        String prefix = split[0];
        String suffix = split[1];

        // 检查前缀结尾和后缀开头是否有"/"
        boolean prefixHasSlash = prefix.endsWith("/");
        boolean suffixHasSlash = suffix.startsWith("/");

        // 拼接url
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(prefix);

        // 如果前缀结尾和后缀开头都没有"/"，则补上"/"
        if (!prefixHasSlash && !suffixHasSlash) {
            urlBuilder.append("/");
        }

        urlBuilder.append(suffix);
        return urlBuilder;
    }
}
