package huawei.odc100;

import java.util.*;

/**
 * 题目描述
 * 开头和结尾都是元音字母（aeiouAEIOU）的字符串为元音字符串，其中混杂的非元音字母数量为其瑕疵度。
 * 比如:
 * “a”、“aa” 是元音字符串，其瑕疵度都为0
 * “aiur”  不是元音字符串（结尾不是元音字符）
 * “abira”  是元音字符串，其瑕疵度为2
 * 给定一个字符串，请找出指定瑕疵度的最长元音字符子串，并输出其长度，如果找不到满足条件的元音字符子串，输出0。
 * 子串： 字符串中任意个连续的字符组成的子序列称为该字符串的子串。
 * <p>
 * 输入描述
 * 首行输入是一个整数，表示预期的瑕疵度flaw，取值范围[0, 65535]。
 * 接下来一行是一个仅由字符a-z和A-Z组成的字符串，字符串长度(0, 65535]。
 * <p>
 * 输出描述
 * 输出为一个整数，代表满足条件的元音字符子串的长度。
 */
public class T8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入瑕疵度
        int flaw = scanner.nextInt();
        scanner.nextLine(); // 读取换行符
        // 输入字符串
        String s = scanner.nextLine();
        // 定义元音字母集合
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        // 记录字符串中所有元音字母的下标
        List<Integer> lengths = getIntegers(s, vowels, flaw);

        // 如果没有满足瑕疵度的元音子串，输出 0
        if (lengths.isEmpty()) {
            System.out.println(0);
            return;
        }
        // 输出最长的元音子串的长度
        lengths.sort(Collections.reverseOrder());
        System.out.println(lengths.get(0));
    }

    private static List<Integer> getIntegers(String s, Set<Character> vowels, int flaw) {
        List<Integer> vowelIdxs = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                vowelIdxs.add(i);
            }
        }
        // 初始化双指针
        int left = 0, right = 0;
        // 记录所有满足瑕疵度的元音子串的长度
        List<Integer> lengths = new ArrayList<>();
        while (right < vowelIdxs.size()) {
            // 计算当前子串的瑕疵度
            int lengthDiff = vowelIdxs.get(right) - vowelIdxs.get(left) - (right - left);
            if (lengthDiff > flaw) {
                // 如果瑕疵度超过了预期，左指针右移
                left++;
            } else {
                // 如果瑕疵度不超过预期，记录子串长度
                if (lengthDiff == flaw) {
                    lengths.add(vowelIdxs.get(right) - vowelIdxs.get(left) + 1);
                }
                // 右指针右移
                right++;
            }
        }
        return lengths;
    }
}
