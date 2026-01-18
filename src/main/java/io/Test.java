package io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/** 文件移动程序 - 将指定目录中的所有文件递归移动到目标目录 包含防止文件覆盖的逻辑，当目标目录中存在同名文件时会自动重命名 */
public class Test {

  /**
   * 主函数 - 执行文件移动操作
   *
   * @param args 命令行参数数组
   */
  public static void main(String[] args) {

    // 移动这个文件夹及其子文件夹中所有文件到当前文件夹。
    Path sourceDir = Paths.get("D:\\Documents\\Book");

    // 当前文件夹
    Path targetDir = Paths.get("D:\\Documents\\Book2").toAbsolutePath();

    try {
      if (!Files.exists(sourceDir)) {
        System.err.println("源文件夹不存在: " + sourceDir);
        return;
      }

      if (!Files.isDirectory(sourceDir)) {
        System.err.println("源路径不是文件夹: " + sourceDir);
        return;
      }

      // 遍历源目录树并移动每个文件到目标目录
      Files.walkFileTree(
          sourceDir,
          new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException {
              Path targetFile = targetDir.resolve(file.getFileName().toString());

              // 如果目标文件已存在，则添加数字后缀避免覆盖
              if (Files.exists(targetFile)) {
                String fileName = file.getFileName().toString();
                String newName = generateUniqueName(targetDir, fileName);
                targetFile = targetDir.resolve(newName);
              }

              // 移动文件
              Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
              return FileVisitResult.CONTINUE;
            }
          });

      System.out.println("文件移动完成");
    } catch (IOException e) {
      System.err.println("移动文件时发生错误: " + e.getMessage());
    }
  }

  /**
   * 生成唯一的文件名以避免覆盖现有文件
   *
   * @param directory 目标目录路径
   * @param originalName 原始文件名
   * @return 保证在目标目录中唯一的文件名
   */
  private static String generateUniqueName(Path directory, String originalName) {
    String newName = originalName;
    int counter = 1;
    while (Files.exists(directory.resolve(newName))) {
      int dotIndex = originalName.lastIndexOf('.');
      if (dotIndex != -1) {
        newName =
            originalName.substring(0, dotIndex) + "_" + counter + originalName.substring(dotIndex);
      } else {
        newName = originalName + "_" + counter;
      }
      counter++;
    }
    return newName;
  }
}
