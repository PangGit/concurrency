package huawei.odc100;

import java.util.Scanner;

/**
 * 单行道汽车通行时间 逻辑题
 * 题目描述
 * M（1<=M<=20）辆车需要在一条不能超车的单行道到达终点，起点到终点的距离为N（1<=N<=400）。
 * 速度快的车追上前车后，只能以前车的速度继续行驶。求最后一车辆到达目的地花费的时间。
 * 注：每辆车固定间隔一小时出发，比如第一辆车0时出发，第二辆车1时出发，以此类推
 * <p>
 * 输入描述
 * 第一行两个数字：M N分别代表车辆数和到终点的距离，以空格分隔。
 * 接下来M行，每行1个数字S，代表每辆车的速度。0<S<30。
 * <p>
 * 输出描述
 * 最后一辆车到达目的地花费的时间
 * <p>
 * 用例
 * 输入
 * 2 11
 * 3
 * 输出
 * 5.5
 */
public class T76 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] inputLine = scanner.nextLine().split(" ");

        // 获取车辆数M和终点距离N
        int M = Integer.parseInt(inputLine[0]);
        int N = Integer.parseInt(inputLine[1]);

        // 获取每辆车的速度并存储在数组speeds中
        int[] speeds = new int[M];
        for (int index = 0; index < M; index++) {
            speeds[index] = Integer.parseInt(scanner.nextLine());
        }

        // 初始化arrivalTimes数组，其中存储第一辆车到达目的地的时间
        double[] arrivalTimes = new double[M];
        arrivalTimes[0] = (double) N / speeds[0];

        // 对于剩余的车辆，循环计算每辆车到达目的地的时间
        // 如果当前车辆比前一辆车更晚到达或与前一辆车同时到达，则更新时间并添加到arrivalTimes
        for (int index = 1; index < M; index++) {
            // 计算第index辆车单独行驶到目的地的时间，即终点距离N除以车速speeds[index]
            // 由于车辆是依次出发的，所以还需要加上车辆的出发延迟时间index
            double estimatedTime = (double) N / speeds[index] + index;

            // 比较当前车辆计算出的到达时间estimatedTime和前一辆车的到达时间arrivalTimes[index - 1]
            // 使用Math.max函数确保当前车辆的到达时间不会早于前一辆车
            double adjustedTime = Math.max(estimatedTime, arrivalTimes[index - 1]);
            arrivalTimes[index] = adjustedTime;
        }

        // 输出最后一辆车到达目的地的时间，但减去M再加1（这是因为车辆从0开始计数，而时间是从1开始计数）
        System.out.println(arrivalTimes[M - 1] - M + 1);
    }
}
