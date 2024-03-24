package huawei.odc100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 堆内存申请 逻辑题
 * <p>
 * 题目描述
 * 有一个总空间为100字节的堆，现要从中新申请一块内存，内存分配原则为：优先紧接着前一块已使用内存，分配空间足够且最接近申请大小的空闲内存。
 * <p>
 * 输入描述
 * 第1行是1个整数，表示期望申请的内存字节数
 * 第2到第N行是用空格分割的两个整数，表示当前已分配的内存的情况，每一行表示一块已分配的连续内存空间，每行的第1和第2个整数分别表示偏移地址和内存块大小，
 * 如：
 * 0 1
 * 3 2
 * 表示 0 偏移地址开始的 1 个字节和 3 偏移地址开始的 2 个字节已被分配，其余内存空闲。
 * <p>
 * 输出描述
 * 若申请成功，输出申请到内存的偏移；
 * 若申请失败，输出 -1
 * <p>
 * 备注：
 * 若输入信息不合法或无效，则申请失败
 * 若没有足够的空间供分配，则申请失败
 * 堆内存信息有区域重叠或有非法值等都是无效输入
 * <p>
 * 用例
 * 输入
 * 1
 * 0 1
 * 3 2
 * <p>
 * 输出
 * 1
 * <p>
 * 说明	堆中已使用的两块内存是偏移从0开始的1字节和偏移从3开始的2字节，空闲的两块内存是偏移从1开始2个字节和偏移从5开始95字节，根据分配原
 */
public class T75 {

    public static void main(String[] args) {
        // 创建一个扫描器来读取用户输入
        Scanner sc = new Scanner(System.in);

        // 读取第一行输入，这是我们要分配的内存大小
        int mallocSize = Integer.parseInt(sc.nextLine());
        // 创建一个列表来存储已使用的内存块
        List<int[]> usedMemory = new ArrayList<>();

        // 循环读取后续的输入行，每行代表一个已使用的内存块
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // 如果读取到空行，结束输入
            if (line.isEmpty()) {
                break;
            }
            // 将输入行分割成字符串数组，然后转换成整数数组
            int[] memoryBlock = Arrays.stream(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            // 将这个内存块添加到已使用的内存列表中
            usedMemory.add(memoryBlock);
        }

        // 如果要分配的内存大小不在合法范围内，输出-1并结束程序
        if (mallocSize <= 0 || mallocSize > 100) {
            System.out.println(-1);
            return;
        }

        // 按照内存块的起始地址对已使用的内存列表进行排序
        usedMemory.sort((a, b) -> a[0] - b[0]);

        // 初始化起始地址为0
        int start = 0;
        // 初始化最佳适配的起始地址为-1
        int bestFitStart = -1;
        // 初始化最小大小差为最大整数
        int minSizeDiff = Integer.MAX_VALUE;

        // 遍历已使用的内存列表
        for (int[] block : usedMemory) {
            // 获取内存块的起始地址和大小
            int blockStart = block[0];
            int blockSize = block[1];

            // 如果内存块的起始地址小于当前的起始地址，或者内存块的大小小于等于0，或者内存块的结束地址大于100，输出-1并结束程序
            if (blockStart < start || blockSize <= 0 || blockStart + blockSize > 100) {
                System.out.println(-1);
                return;
            }

            // 计算当前的起始地址和内存块的起始地址之间的空闲空间
            int freeSpace = blockStart - start;
            // 如果空闲空间大于等于要分配的内存大小，并且空闲空间和要分配的内存大小的差小于当前的最小大小差，更新最佳适配的起始地址和最小大小差
            if (mallocSize <= freeSpace && (freeSpace - mallocSize) < minSizeDiff) {
                bestFitStart = start;
                minSizeDiff = freeSpace - mallocSize;
            }

            // 更新当前的起始地址为内存块的结束地址
            start = blockStart + blockSize;
        }

        // 检查最后一个内存块之后的空闲空间，如果空闲空间大于等于要分配的内存大小，并且空闲空间和要分配的内存大小的差小于当前的最小大小差，更新最佳适配的起始地址
        if (100 - start >= mallocSize && (100 - start - mallocSize) < minSizeDiff) {
            bestFitStart = start;
        }

        // 输出最佳适配的起始地址
        System.out.println(bestFitStart);
    }
}
