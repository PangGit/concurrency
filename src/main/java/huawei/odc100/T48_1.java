package huawei.odc100;

import java.util.Scanner;

/**
 * 最少停车数/停车场车辆统计
 * <p>
 * 题目描述
 * 特定大小的停车场，数组cars[]表示，其中1表示有车，0表示没车。
 * 车辆大小不一，小车占一个车位（长度1），货车占两个车位（长度2），卡车占三个车位（长度3）。
 * 统计停车场最少可以停多少辆车，返回具体的数目。
 * <p>
 * 输入描述
 * 整型字符串数组cars[]，其中1表示有车，0表示没车，数组长度小于1000。
 * <p>
 * 输出描述
 * 整型数字字符串，表示最少停车数目。
 */
public class T48_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 将输入的字符串转换为停车场数组
        String[] inputArray = scanner.nextLine().split(",");
        String inputString = String.join("", inputArray);
        String[] parking_slots = inputString.split("0");

        // 初始化停车场最少停车数目为0
        int min_cars = 0;

        // 遍历停车场数组，统计每个连续的1的长度
        for (String slot : parking_slots) {
            // 计算当前连续1的长度
            int occupied_length = slot.length();

            // 如果当前连续1的长度为0，不做任何操作
            if (occupied_length == 0) {
            }
            // 如果当前连续1的长度能被3整除，说明可以完全放置卡车
            else if (occupied_length % 3 == 0) {
                // 将当前连续1的长度除以3，得到卡车数量，并累加到最少停车数目
                min_cars += occupied_length / 3;
            }
            // 如果当前连续1的长度不能被3整除，说明需要放置小车或货车
            else {
                // 计算可以放置的卡车数量，并累加到最少停车数目
                // 由于还有剩余的车位，需要放置一个小车或货车，所以最少停车数目加1
                min_cars += occupied_length / 3 + 1;
            }
        }

        // 输出停车场最少停车数目
        System.out.println(min_cars);
    }
}
