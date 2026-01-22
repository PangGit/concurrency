package io;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test5 {

    public static void main(String[] args) {
        // 指定要处理的目录
        String directoryPath = "E:\\Web\\JavaScript 基础+高级";
        File directory = new File(directoryPath);

        // 检查目录是否存在
        if (!directory.exists()) {
            System.out.println("目录不存在: " + directoryPath);
            return;
        }

        if (!directory.isDirectory()) {
            System.out.println(directoryPath + " 不是一个目录");
            return;
        }

        // 获取目录中的所有文件
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("目录为空或无法访问");
            return;
        }

        // 定义正则表达式匹配 JavaScript 高级 文件
        Pattern jsPattern = Pattern.compile("(\\d+)-JavaScript(高级|基础)--(\\d+)\\.(.*)");
        Pattern jsBasePattern = Pattern.compile("(\\d+)-JavaScript(基础)--(\\d+)\\.(.*)");

        // 处理每个文件
        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                String newName = renameFile(fileName);
                
                if (newName != null && !newName.equals(fileName)) {
                    File newFile = new File(directory, newName);
                    
                    // 重命名文件
                    if (file.renameTo(newFile)) {
                        System.out.println("已重命名: " + fileName + " -> " + newName);
                    } else {
                        System.out.println("重命名失败: " + fileName);
                    }
                } else if (newName != null) {
                    System.out.println("跳过（无需重命名）: " + fileName);
                } else {
                    System.out.println("不匹配模式，跳过: " + fileName);
                }
            }
        }
    }

    /**
     * 根据规则重命名文件
     * 例如: 141-JavaScript高级--01.准备.mp4 -> 141-JS高级-准备.mp4
     */
    public static String renameFile(String originalName) {
        // 匹配 JavaScript 高级 或 JavaScript 基础 的格式
        Pattern advancedPattern = Pattern.compile("(\\d+)-JavaScript(高级|基础)--(\\d+)\\.(.*)");
        Matcher matcher = advancedPattern.matcher(originalName);

        if (matcher.matches()) {
            String number = matcher.group(1);      // 序号，如 141
            String level = matcher.group(2);      // 高级或基础
            String lessonNum = matcher.group(3);  // 课程编号，如 01
            String rest = matcher.group(4);       // 后面的部分，如 准备.mp4

            // 提取主题名称（从 rest 部分获取）
            String[] parts = rest.split("\\.");
            if (parts.length > 1) {
                String extension = parts[parts.length - 1]; // 文件扩展名
                String topic = "";
                // 从 rest 部分提取主题名称，移除数字部分
                String topicPart = rest.substring(0, rest.lastIndexOf('.'));
                // 假设格式是 "数字.主题名"，我们提取主题名
                int dotIndex = topicPart.indexOf('.');
                if (dotIndex != -1) {
                    topic = topicPart.substring(dotIndex + 1);
                } else {
                    topic = topicPart;
                }

                // 根据是基础还是高级生成对应的前缀
                String prefix = "JS" + level;
                String newName = number + "-" + prefix + "-" + topic + "." + extension;
                return newName;
            }
        }

        // 如果不匹配，则尝试更简单的匹配模式
        Pattern simplePattern = Pattern.compile("(\\d+)-JavaScript(高级|基础)-(\\d+)\\.(.*)");
        Matcher simpleMatcher = simplePattern.matcher(originalName);
        if (simpleMatcher.matches()) {
            String number = simpleMatcher.group(1);
            String level = simpleMatcher.group(2);
            String lessonNum = simpleMatcher.group(3);
            String rest = simpleMatcher.group(4);

            String[] parts = rest.split("\\.");
            if (parts.length > 1) {
                String extension = parts[parts.length - 1];
                String topic = "";
                String topicPart = rest.substring(0, rest.lastIndexOf('.'));
                int dotIndex = topicPart.indexOf('.');
                if (dotIndex != -1) {
                    topic = topicPart.substring(dotIndex + 1);
                } else {
                    topic = topicPart;
                }

                String prefix = "JS" + level;
                String newName = number + "-" + prefix + "-" + topic + "." + extension;
                return newName;
            }
        }

        // 尝试处理包含双破折号的情况
        Pattern doubleDashPattern = Pattern.compile("(\\d+)-JavaScript(高级|基础)--(.*)");
        Matcher doubleDashMatcher = doubleDashPattern.matcher(originalName);
        if (doubleDashMatcher.matches()) {
            String number = doubleDashMatcher.group(1);
            String level = doubleDashMatcher.group(2);
            String rest = doubleDashMatcher.group(3);

            // 解析 rest 部分，格式可能是 "01.主题.mp4"
            String[] parts = rest.split("\\.");
            if (parts.length >= 2) {
                String extension = parts[parts.length - 1]; // 文件扩展名
                String topicWithNumber = "";
                for (int i = 0; i < parts.length - 1; i++) {
                    if (i > 0) topicWithNumber += ".";
                    topicWithNumber += parts[i];
                }

                // 移除前面的数字和点号，保留主题名称
                String topic = topicWithNumber.replaceFirst("^\\d+\\.?", "");
                if (!topic.isEmpty()) {
                    String prefix = "JS" + level;
                    String newName = number + "-" + prefix + "-" + topic + "." + extension;
                    return newName;
                }
            }
        }

        // 如果都不匹配，返回null表示不需要重命名
        return null;
    }
}
