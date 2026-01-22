package io;

import java.io.File;
import java.util.regex.Pattern;

public class Test8 {

    public static void main(String[] args){

        // 遍历目录及其子目录：E:\Web\Vue3 新标准打造后台综合解决课 ， 重命名文件，去掉文件名中的"~【更多资源小刀娱乐网www.x6g.com】"或"【更多资源小刀娱乐网www.x6g.com】"
        
        String dirPath = "E:\\Web\\Vue3 新标准打造后台综合解决课";
        File directory = new File(dirPath);
        
        if (!directory.exists()) {
            System.out.println("目录不存在: " + dirPath);
            return;
        }
        
        renameFiles(directory);
    }
    
    /**
     * 递归遍历目录并重命名符合条件的文件
     */
    public static void renameFiles(File directory) {
        // 定义要替换的模式
        Pattern pattern = Pattern.compile("~?【更多资源小刀娱乐网www\\.x6g\\.com】");
        
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        
        for (File file : files) {
            if (file.isDirectory()) {
                // 如果是目录，递归处理
                renameFiles(file);
            } else if (file.isFile()) {
                // 如果是文件，检查是否需要重命名
                String fileName = file.getName();
                
                if (pattern.matcher(fileName).find()) {
                    // 创建新的文件名（去掉匹配的部分）
                    String newFileName = pattern.matcher(fileName).replaceAll("");
                    File newFile = new File(file.getParent(), newFileName);
                    
                    // 重命名文件
                    if (file.renameTo(newFile)) {
                        System.out.println("重命名成功: " + fileName + " -> " + newFileName);
                    } else {
                        System.out.println("重命名失败: " + fileName);
                    }
                }
            }
        }
    }


}
