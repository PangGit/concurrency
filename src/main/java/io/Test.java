package io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Test {

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

  // ... existing code ...

  // 辅助方法：生成唯一文件名
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
