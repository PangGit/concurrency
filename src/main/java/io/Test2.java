package io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Test2 {

  public static void main(String[] args) {
    // ... existing code ...
    String path = "D:\\Documents\\Book";
    // 移动这个文件夹及其子文件夹中所有文件到当前文件夹。
    try {
      Path sourceDir = Paths.get(path);

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
              String fileName = file.getFileName().toString();
              // 使用正则表达式匹配并移除文件名开头的数字前缀（如 0001. 、0002. 等）
              String newFileName = fileName.replaceFirst("^\\d+\\.\\s*", "");

              // 只有当文件名发生变化时才重命名文件
              if (!fileName.equals(newFileName)) {
                Path targetFile = file.getParent().resolve(newFileName);
                Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("重命名: " + fileName + " -> " + newFileName);
              }

              return FileVisitResult.CONTINUE;
            }
          });

      System.out.println("文件名处理完成");
    } catch (IOException e) {
      System.err.println("处理文件名时发生错误: " + e.getMessage());
    }
  }

}
