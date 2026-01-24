package io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Test9 {

  public static void main(String[] args) {

    // 把一个字符串转换成字节数组，然后再把字节数组使用gzip压缩，输出压缩后的字节数组，解压后的字节数组再转换成字符串并输出。
    try {
      // 读取JSON文件内容
      String originalString =
          new String(
              Files.readAllBytes(Paths.get("D:\\Code\\github\\concurrency\\file\\json-demo.json")));

      // 将字符串转换为字节数组
      byte[] originalBytes = originalString.getBytes(StandardCharsets.UTF_8);

      // 使用 GZIP 压缩字节数组
      byte[] compressedBytes = compress(originalBytes);
      System.out.println("原始字节数组长度: " + originalBytes.length);
      System.out.println("压缩后字节数组长度: " + compressedBytes.length);

      // 解压字节数组
      byte[] decompressedBytes = decompress(compressedBytes);

      // 将解压后的字节数组转换回字符串
      String decompressedString = new String(decompressedBytes, StandardCharsets.UTF_8);

      System.out.println("原始字符串: " + originalString);
      System.out.println("解压后的字符串: " + decompressedString);
      System.out.println("是否相同: " + originalString.equals(decompressedString));

    } catch (Exception e) {
      System.err.println("gzip 压缩 解压 发生错误: " + e.getMessage());
    }
  }

  // GZIP 压缩方法
  public static byte[] compress(byte[] data) throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
    GZIPOutputStream gzipOS = new GZIPOutputStream(bos);
    gzipOS.write(data);
    gzipOS.close();
    return bos.toByteArray();
  }

  // GZIP 解压方法
  public static byte[] decompress(byte[] data) throws IOException {
    ByteArrayInputStream bis = new ByteArrayInputStream(data);
    GZIPInputStream gzipIS = new GZIPInputStream(bis);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    byte[] buffer = new byte[1024];
    int len;
    while ((len = gzipIS.read(buffer)) != -1) {
      bos.write(buffer, 0, len);
    }

    gzipIS.close();
    bos.close();

    return bos.toByteArray();
  }
}
