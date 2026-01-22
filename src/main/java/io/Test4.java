package io;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test4 {

    public static void main(String[] args){
        // E:\Web\JavaScript 基础+高级
        String directoryPath = "E:\\Web\\JavaScript 基础+高级";
        
        File directory = new File(directoryPath);
        
        if (!directory.exists()) {
            System.out.println("目录不存在: " + directoryPath);
            return;
        }
        
        if (!directory.isDirectory()) {
            System.out.println("路径不是一个目录: " + directoryPath);
            return;
        }
        
        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("无法访问目录或目录为空: " + directoryPath);
            return;
        }
        
        // 正则表达式匹配模式：匹配如 001-01.尚硅谷_JS基础_JS简介.mp4 这样的文件名
        Pattern pattern = Pattern.compile("^(\\d{3})-(\\d+)\\.([^_]+)_([^.]+)\\.mp4$");
        
        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                
                Matcher matcher = pattern.matcher(fileName);
                if (matcher.matches()) {
                    String prefixNumber = matcher.group(1); // 001
                    String coursePrefix = matcher.group(3); // 尚硅谷
                    String suffixPart = matcher.group(4);   // JS基础_JS简介
                    
                    // 构建新文件名，去掉中间的数字和点以及"尚硅谷_"前缀
                    String newFileName = prefixNumber + "-" + suffixPart + ".mp4";
                    File newFile = new File(directory, newFileName);
                    
                    if (file.renameTo(newFile)) {
                        System.out.println("重命名成功: " + fileName + " -> " + newFileName);
                    } else {
                        System.out.println("重命名失败: " + fileName);
                    }
                } else {
                    System.out.println("文件名不符合预期格式，跳过: " + fileName);
                }
            }
        }
    }
}
