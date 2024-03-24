package huawei.odc100;

import java.util.Scanner;

/**
 * 题目描述：密码输入检测
 * <p>
 * 给定用户密码输入流input，输入流中字符’<'表示退格，可以清除前一个输入的字符，请你编写程序，输出最终得到的密码字符，并判断密码是否满足如下的密码安全要求。
 * 密码安全要求如下：
 * 1.密码长度>=8;
 * 2.密码至少需要包含1个大写字母;
 * 3.密码至少需要包含1个小写字母;
 * 4.密码至少需要包含1个数字;
 * 5.密码至少需要包含1个字母和数字以外的非空白特殊字符
 * 注意空串退格后仍然为空串，且用户输入的字符串不包含‘<’字符和空白字符。
 * <p>
 * 输入描述
 * 用一行字符串表示输入的用户数据，输入的字符串中‘<’字符标识退格，用户输入的字符串不包含空白字符，例如：ABC<c89%000<
 * <p>
 * 输出描述
 * 输出经过程序处理后，输出的实际密码字符串，并输出改密码字符串是否满足密码安全要求。两者间由‘,’分隔， 例如：ABc89%00,true
 * <p>
 * 示例1
 * 输入 ABC<c89%000<
 * 输出 ABc89%00,true
 */
public class T6 {

    public static void main(String[] args) {
        // 创建一个Scanner对象来读取用户的输入
        Scanner scanner = new Scanner(System.in);
        // 读取一行输入
        String input = scanner.nextLine();
        // 创建一个StringBuilder对象来构建结果字符串
        StringBuilder result = new StringBuilder();

        // 创建四个布尔变量来检查输入字符串中是否包含
        // 大写字母
        boolean isBig = false;
        // 小写字母
        boolean isSmall = false;
        // 数字
        boolean isNum = false;
        // 特殊字符
        boolean isSpec = false;

        // 遍历输入字符串中的每一个字符
        for (char c : input.toCharArray()) {
            // 如果字符是'<', 则删除结果字符串的最后一个字符
            if (c == '<') {
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else {
                // 否则，将字符添加到结果字符串中
                result.append(c);
            }
        }

        // 遍历输入字符串中的每一个字符
        for (int i = 0; i < result.length(); i++) {
            char c = result.charAt(i);

            // 检查字符是否是数字
            if (Character.isDigit(c)) {
                isNum = true;
            }
            // 检查字符是否是小写字母
            else if (Character.isLowerCase(c)) {
                isSmall = true;
            }
            // 检查字符是否是大写字母
            else if (Character.isUpperCase(c)) {
                isBig = true;
            }
            // 检查字符是否是特殊字符
            else {
                isSpec = true;
            }
        }

        // 检查结果字符串是否满足长度大于等于8，并且包含大写字母、小写字母、数字和特殊字符
        boolean flagRes = result.length() >= 8 && isNum && isSmall && isBig && isSpec;

        // 输出结果字符串和检查结果
        System.out.println(result + "," + flagRes);
    }
}
