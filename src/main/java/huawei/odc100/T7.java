package huawei.odc100;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目描述：查找众数及中位数（本题分值100）
 * 众数是指一组数据中出现次数量多的那个数，众数可以是多个。
 * 中位数是指把一组数据从小到大排列，最中间的那个数，如果这组数据的个数是奇数，那最中间那个就是中位数，
 * 如果这组数据的个数为偶数，那就把中间的两个数之和除以2，所得的结果就是中位数。
 * 查找整型数组中元素的众数并组成一个新的数组，求新数组的中位数。
 * <p>
 * 输入描述
 * 输入一个一维整型数组，数组大小取值范围 0<N<1000，数组中每个元素取值范围 0<E<1000
 * <p>
 * 输出描述
 * 输出众数组成的新数组的中位数
 * <p>
 * 题目解析
 * 题目要求找到整型数组中的众数，可以使用哈希表来统计每个数出现的次数，然后找到出现次数最多的数，即为众数。
 * 如果有多个众数，都放入一个新数组中。接着，对新数组进行排序，找到其中位数即可。
 * <p>
 * 具体步骤如下：
 * 使用哈希表统计每个数出现的次数，找到出现次数最多的数，即为众数。如果有多个众数，都放入一个新数组中。
 * 对新数组进行排序，找到其中位数。
 */
public class T7 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        List<Integer> numbers = Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        // 统计数字出现次数及出现最大次数
        Map<Integer, Integer> countMap = new HashMap<>();

        int maxCount = 0;
        for (int number : numbers) {
            int count = countMap.getOrDefault(number, 0) + 1;
            countMap.put(number, count);
            maxCount = Math.max(maxCount, count);
        }

        final int finalMaxCount = maxCount;
        List<Integer> maxCountNumbers = countMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == finalMaxCount)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());

        // 计算中位数
        int median;
        if (maxCountNumbers.size() % 2 != 0) {
            int index = (maxCountNumbers.size() + 1) / 2 - 1;
            median = maxCountNumbers.get(index);
        } else {
            int index1 = maxCountNumbers.size() / 2 - 1;
            int index2 = maxCountNumbers.size() / 2;
            median = (maxCountNumbers.get(index1) + maxCountNumbers.get(index2)) / 2;
        }

        System.out.println(median);
    }
}
