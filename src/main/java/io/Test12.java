package io;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test12 {

    public static void main(String[] args){
        // 遍历目录：D:\Music\纯音乐系列 ,
        // 1、 去除文件名前的数字，
        // 例如：文件名"05 秋水伊人.wav"改名为"秋水伊人.wav" 、 "05．独角戏.WAV"改名为"独角戏.WAV"
        // 2、 去除文件名后面的数字，例如"《时代乐难忘的旋律6》004(1).jpg"改名为"《时代乐难忘的旋律6》004.jpg"
        
        String directoryPath = "D:\\Music\\纯音乐系列";
        File directory = new File(directoryPath);
        
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("目录不存在或不是有效目录: " + directoryPath);
            return;
        }
        
        processDirectory(directory);
    }
    
    /**
     * 处理目录中的所有文件
     */
    private static void processDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        
        for (File file : files) {
            if (file.isFile()) {
                renameFile(file);
            } else if (file.isDirectory()) {
                // 递归处理子目录
                processDirectory(file);
            }
        }
    }
    
    /**
     * 重命名文件
     */
    private static void renameFile(File file) {
        String originalName = file.getName();
        String newName = cleanFileName(originalName);
        
        if (!originalName.equals(newName)) {
            File newFile = new File(file.getParent(), newName);
            if (file.renameTo(newFile)) {
                System.out.println("重命名成功: " + originalName + " -> " + newName);
            } else {
                System.out.println("重命名失败: " + originalName);
            }
        }
    }
    
    /**
     * 清理文件名
     */
    private static String cleanFileName(String fileName) {
        // 1. 去除文件名开头的数字（包括数字后的空格或特殊字符）
        String result = fileName.replaceAll("^\\d+[\\s\\.．]*", "");
        
        // 2. 去除文件名末尾括号中的数字，如 (1), (2) 等
        result = result.replaceAll("\\(\\d+\\)(?=\\.[^.]+$)", "");
        
        return result;
    }
}
