package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Test11 {

    public static void main(String[] args) {
        // 遍历目录：D:\Music\纯音乐系列，删除重复的文件，重复文件的判断标准：文件哈希值相同，文件大小相同
        
        String directoryPath = "D:\\Music\\纯音乐系列";
        File directory = new File(directoryPath);
        
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("目录不存在或不是有效目录: " + directoryPath);
            return;
        }
        
        // 用于存储文件信息的Map，key为"文件大小_哈希值"，value为文件列表
        Map<String, List<File>> fileMap = new HashMap<>();
        
        // 遍历目录
        traverseDirectory(directory, fileMap);
        
        // 查找并删除重复文件
        removeDuplicateFiles(fileMap);
        
        System.out.println("重复文件清理完成！");
    }
    
    /**
     * 递归遍历目录
     */
    private static void traverseDirectory(File directory, Map<String, List<File>> fileMap) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 递归遍历子目录
                    traverseDirectory(file, fileMap);
                } else if (file.isFile()) {
                    try {
                        // 获取文件大小和哈希值
                        long fileSize = file.length();
                        String hash = calculateFileHash(file);
                        
                        // 使用"文件大小_哈希值"作为唯一标识
                        String key = fileSize + "_" + hash;
                        
                        // 将文件添加到对应的列表中
                        fileMap.computeIfAbsent(key, k -> new ArrayList<>()).add(file);
                        
                        System.out.println("处理文件: " + file.getAbsolutePath() + 
                                         ", 大小: " + fileSize + " bytes, 哈希: " + hash);
                    } catch (Exception e) {
                        System.err.println("处理文件时出错: " + file.getAbsolutePath() + ", 错误: " + e.getMessage());
                    }
                }
            }
        }
    }
    
    /**
     * 计算文件的MD5哈希值
     */
    private static String calculateFileHash(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }
        
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    /**
     * 删除重复文件，保留每个组中的第一个文件
     */
    private static void removeDuplicateFiles(Map<String, List<File>> fileMap) {
        int duplicateCount = 0;
        long savedSpace = 0;
        
        for (Map.Entry<String, List<File>> entry : fileMap.entrySet()) {
            List<File> files = entry.getValue();
            
            // 如果该组只有一个文件，说明没有重复
            if (files.size() <= 1) {
                continue;
            }
            
            // 保留第一个文件，删除其余重复文件
            File file = files.get(0);
            System.out.println("\n找到重复文件组:");
            System.out.println("保留文件: " + file.getAbsolutePath());
            
            for (int i = 1; i < files.size(); i++) {
                File duplicateFile = files.get(i);
                long fileSize = duplicateFile.length();
                
                System.out.println("删除重复文件: " + duplicateFile.getAbsolutePath());
                
                if (duplicateFile.delete()) {
                    duplicateCount++;
                    savedSpace += fileSize;
                    System.out.println("删除成功");
                } else {
                    System.err.println("删除失败");
                }
            }
        }
        
        System.out.println("\n=== 清理统计 ===");
        System.out.println("删除重复文件数量: " + duplicateCount);
        System.out.println("释放磁盘空间: " + formatFileSize(savedSpace));
    }
    
    /**
     * 格式化文件大小显示
     */
    private static String formatFileSize(long size) {
        if (size < 1024) {
            return size + " B";
        } else if (size < 1024 * 1024) {
            return String.format("%.2f KB", size / 1024.0);
        } else if (size < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", size / (1024.0 * 1024));
        } else {
            return String.format("%.2f GB", size / (1024.0 * 1024 * 1024));
        }
    }
}
