package io;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test6 {

    public static void main(String[] args) {

        // 读取目录： E:\Web\Vue 全家桶 ， 重命名文件，
        // 例如
        // 001_尚硅谷Vue技术_课程简介.mp4 改名 001_Vue2_课程简介.mp4
        // 136_尚硅谷Vue3技术_vue3简介.mp4 改名 136_Vue3_vue3简介.mp4
        
        String directoryPath = "E:\\Web\\Vue 全家桶";
        File directory = new File(directoryPath);
        
        if (!directory.exists()) {
            System.out.println("目录不存在: " + directoryPath);
            return;
        }
        
        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("无法访问目录或目录为空");
            return;
        }
        
        // 定义正则表达式来匹配原文件名模式
        Pattern pattern = Pattern.compile("^(\\d+)_(.*?)_(.*)$");
        
        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                
                // 检查是否是需要重命名的文件
                Matcher matcher = pattern.matcher(fileName);
                if (matcher.matches()) {
                    String numberPart = matcher.group(1);  // 如 "001"
                    String techPart = matcher.group(2);    // 如 "尚硅谷Vue技术"
                    String descPart = matcher.group(3);    // 如 "课程简介.mp4"
                    
                    // 根据techPart判断是Vue2还是Vue3，并替换为相应的名称
                    String vueVersion;
                    if (techPart.contains("Vue3")) {
                        vueVersion = "Vue3";
                    } else {
                        vueVersion = "Vue2"; // 默认为Vue2
                    }
                    
                    // 构建新的文件名
                    String newFileName = numberPart + "_" + vueVersion + "_" + descPart;
                    File newFile = new File(directory, newFileName);
                    
                    // 重命名文件
                    if (file.renameTo(newFile)) {
                        System.out.println("已重命名: " + fileName + " -> " + newFileName);
                    } else {
                        System.out.println("重命名失败: " + fileName);
                    }
                }
            }
        }
    }
}
