package huawei.odc100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 全量和已占用字符集 、字符串统计
 * 给定两个字符集合，一个是全量字符集，一个是已占用字符集，已占用字符集中的字符不能再使用。
 * 要求输出剩余可用字符集。
 * <p>
 * 输入描述
 * 输入一个字符串 一定包含@，@前为全量字符集 @后的为已占用字符集
 * 已占用字符集中的字符一定是全量字符集中的字符
 * 字符集中的字符跟字符之间使用英文逗号隔开
 * 每个字符都表示为字符+数字的形式用英文冒号分隔，比如a:1标识一个a字符
 * 字符只考虑英文字母，区分大小写
 * 数字只考虑正整型 不超过100
 * 如果一个字符都没被占用 @标识仍存在，例如 a:3,b:5,c:2@
 * <p>
 * 输出描述
 * 输出可用字符集
 * 不同的输出字符集之间用回车换行
 * 注意 输出的字符顺序要跟输入的一致，如下面用例不能输出b:3,a:2,c:2
 * 如果某个字符已全部占用 则不需要再输出
 * <p>
 * 用例
 * 输入 a:3,b:5,c:2@a:1,b:2
 * 输出 a:2,b:3,c:2
 * 说明
 * 全量字符集为三个a，5个b，2个c
 * 已占用字符集为1个a，2个b
 * 由于已占用字符不能再使用
 * 因此剩余可用字符为2个a，3个b，2个c
 * 因此输出a:2,b:3,c:2
 */
public class T5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入字符串
        String input = scanner.nextLine();

        // 将输入字符串按照@符号分割为全量字符集和已占用字符集
        String[] splitInput = input.split("@");
        String fullCharacterSet = splitInput[0]; // 全量字符集
        String occupiedCharacterSet = splitInput[1]; // 已占用字符集

        // 创建字符列表，用于存储全量字符集中的字符及其对应的数量
        ArrayList<String[]> characterList = new ArrayList<>();

        // 将全量字符集按照逗号分割为单个字符
        String[] fullCharacterSetSplit = fullCharacterSet.split(",");

        // 遍历全量字符集的每个字符
        for (String character : fullCharacterSetSplit) {
            // 将字符按照冒号分割为字符和数量
            String[] characterSplit = character.split(":");
            characterList.add(characterSplit); // 将字符和数量添加到字符列表中
        }

        // 如果已占用字符集为空，则输出全量字符集
        if (occupiedCharacterSet.isEmpty()) {
            System.out.println(fullCharacterSet + "@");
            System.exit(0);
        }

        // 创建已占用字符集的哈希表，用于存储已占用字符及其对应的数量
        HashMap<String, Integer> occupiedCharacters = getHashMap(occupiedCharacterSet);

        // 遍历字符列表中的每个字符
        for (int i = 0; i < characterList.size(); i++) {
            String[] character = characterList.get(i);
            // 如果已占用字符集中包含当前字符
            if (occupiedCharacters.containsKey(character[0])) {
                int count = Integer.parseInt(character[1]) - occupiedCharacters.get(character[0]); // 计算剩余可用数量
                if (count > 0) {
                    character[1] = Integer.toString(count); // 更新字符列表中的数量为剩余可用数量
                } else {
                    characterList.remove(i); // 如果剩余可用数量为0，则移除当前字符
                    i--; // 由于移除了一个字符，需要将索引减1
                }
            }
        }

        // 构建输出字符串
        StringBuilder result = new StringBuilder();
        for (String[] character : characterList) {
            result.append(character[0]).append(":").append(character[1]).append(","); // 将每个字符及其数量添加到输出字符串中
        }
        result.deleteCharAt(result.length() - 1); // 删除最后一个逗号
        System.out.println(result.toString()); // 输出结果
    }

    private static HashMap<String, Integer> getHashMap(String occupiedCharacterSet) {
        HashMap<String, Integer> occupiedCharacters = new HashMap<>();

        // 将已占用字符集按照逗号分割为单个字符
        String[] occupiedCharacterSetSplit = occupiedCharacterSet.split(",");

        // 遍历已占用字符集的每个字符
        for (String character : occupiedCharacterSetSplit) {
            // 将字符按照冒号分割为字符和数量
            String[] characterSplit = character.split(":");
            occupiedCharacters.put(characterSplit[0], Integer.parseInt(characterSplit[1])); // 将字符和数量添加到已占用字符集的哈希表中
        }
        return occupiedCharacters;
    }
}
