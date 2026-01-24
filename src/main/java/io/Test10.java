package io;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test10 {

  public static void main(String[] args) {

    // 读取JSON文件名： file/json-demo.json， 获取文件中的JSON数据，并解析成对象，并输出。
    try {
      // 读取JSON文件内容
      String jsonString =
          new String(
              Files.readAllBytes(Paths.get("D:\\Code\\github\\concurrency\\file\\json-demo.json")));

      // 解析JSON字符串为JSONObject对象
      JSONObject jsonObject = (JSONObject) JSONReader.of(jsonString).readObject();

      // 输出解析后的JSON对象
      System.out.println("解析后的JSON对象:");
      // 格式化输出
      System.out.println(jsonObject.toJSONString(JSONWriter.Feature.PrettyFormat));

    } catch (IOException e) {
      System.err.println("读取文件时发生错误: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("解析JSON时发生错误: " + e.getMessage());
    }
  }
}
