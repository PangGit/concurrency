package io;

import java.io.File;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test3 {

    public static void main(String[] args) {
        // 【T3出行-13.90元-1个行程】高德打车电子行程单.pdf
        // 【鞍马出行-30.90元-2个行程】高德打车电子行程单.pdf

        // 指定要遍历的文件夹路径
        String folderPath = "D:\\Downloads\\行程单";

        // 创建文件对象
        File folder = new File(folderPath);

        // 检查文件夹是否存在
        if (!folder.exists()) {
            System.out.println("文件夹 " + folderPath + " 不存在！");
            return;
        }

        if (!folder.isDirectory()) {
            System.out.println(folderPath + " 不是一个文件夹！");
            return;
        }

        // 定义正则表达式模式来匹配文件名中的金额
        // 匹配格式：【公司名称-金额元-行程数】文件名.pdf
        Pattern pattern = Pattern.compile("【[^-]+-([\\d.]+)元-[^】]+】.*\\.pdf");

        BigDecimal totalAmount = BigDecimal.ZERO;
        int fileCount = 0;

        System.out.println("开始遍历文件夹: " + folderPath);
        System.out.println(getRepeatedString("=", 50));

        // 获取文件夹中的所有文件
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                // 只处理PDF文件
                if (file.isFile() && file.getName().toLowerCase().endsWith(".pdf")) {
                    String fileName = file.getName();
                    System.out.println("处理文件: " + fileName);

                    // 使用正则表达式匹配金额
                    Matcher matcher = pattern.matcher(fileName);
                    if (matcher.find()) {
                        try {
                            BigDecimal amount = new BigDecimal(matcher.group(1));
                            System.out.println("  -> 解析金额: " + amount + " 元");
                            totalAmount = totalAmount.add(amount);
                            fileCount++;

                            System.out.println("  -> 当前总金额: " + totalAmount + " 元");
                        } catch (NumberFormatException e) {
                            System.out.println("  -> 解析金额失败: " + matcher.group(1));
                        }
                    } else {
                        System.out.println("  -> 文件名格式不匹配，跳过");
                    }
                    System.out.println();
                }
            }
        }

        System.out.println(getRepeatedString("=", 50));
        System.out.println("统计结果:");
        System.out.println("处理文件数量: " + fileCount);
        System.out.println("总金额: " + totalAmount + " 元");
    }

    // 辅助方法：重复字符串
    private static String getRepeatedString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}
