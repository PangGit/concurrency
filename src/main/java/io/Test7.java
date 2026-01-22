package io;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test7 {
  public static void main(String[] args) {
    // 读取目录 E:\Web\Vue2+Vue3 珠峰，重命名文件，
    // 28_课时145：2·data为什么必须是一个函数.mp4.mp4  重命名为  28_data为什么必须是一个函数.mp4
    // 29_课时146：4·watch和computed原理.mp4.mp4  重命名为  29_watch和computed原理.mp4
    // 57_课时174：2·路由的钩子实现.mp4  重命名为  57_路由的钩子实现.mp4

    // 指定要处理的目录路径
    String directoryPath = "E:\\Web\\Vue2+Vue3 珠峰";
    File directory = new File(directoryPath);

    // 检查目录是否存在
    if (!directory.exists()) {
      System.out.println("目录不存在: " + directoryPath);
      return;
    }

    // 获取目录下的所有文件
    File[] files = directory.listFiles();

    if (files == null || files.length == 0) {
      System.out.println("目录为空或无法访问");
      return;
    }

    // 编译正则表达式模式，用于匹配重复的.mp4后缀
    Pattern pattern = Pattern.compile("(.+\\.mp4)\\.mp4$");

    // 遍历所有文件并进行重命名操作
    for (File file : files) {
      if (file.isFile()) { // 只处理文件，不处理子目录
        String fileName = file.getName();
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.matches()) {
          // 提取正确的文件名（去除重复的.mp4后缀）
          String newFileName = matcher.group(1);
          String newName = fileName.replaceAll("：", ":").replaceAll("·", "");
          // 进一步清理文件名，确保格式正确
          newName = cleanFileName(newName);
          
          File newFile = new File(directory, newName);
          
          // 执行重命名操作
          if (file.renameTo(newFile)) {
            System.out.println("已重命名: " + fileName + " -> " + newName);
          } else {
            System.out.println("重命名失败: " + fileName);
          }
        } else {
          // 对于没有重复后缀的文件，也进行格式清理
          String cleanedFileName = cleanFileName(fileName);
          if (!cleanedFileName.equals(fileName)) {
            File newFile = new File(directory, cleanedFileName);
            if (file.renameTo(newFile)) {
              System.out.println("已重命名: " + fileName + " -> " + cleanedFileName);
            } else {
              System.out.println("重命名失败: " + fileName);
            }
          }
        }
      }
    }
  }

  /**
   * 清理文件名，移除特殊字符如冒号和点号等，并处理重复的.mp4后缀
   */
  private static String cleanFileName(String fileName) {
    // 处理重复的.mp4后缀
    String result = fileName.replaceAll("\\.mp4\\.mp4$", ".mp4");
    
    // 替换中文冒号为英文冒号
    result = result.replaceAll("：", ":");
    
    // 移除中间的点号或中间符号（如·），但保留文件扩展名前的点
    result = result.replaceAll("·", "");
    
    // 处理其他可能的格式问题
    // 如果有冒号后紧跟数字和点号的情况，规范化格式
    result = result.replaceAll(":\\s*\\d+[·]", "");
    
    return result;
  }
}
