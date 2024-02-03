package dos;


import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <a href="https://developer.aliyun.com/article/92194">一种高级的DoS攻击-Hash碰撞攻击</a>
 *
 * @author pang
 * @since 2023/4/10 下午 6:57
 */
public class DosTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newWorkStealingPool(24);

        DosTest test = new DosTest();
        for (int i = 0; i < 100; i++) {
            pool.execute(test::index);
        }
    }

    public void index() {
        String jsonStr = "";
        try (FileReader fr = new FileReader("file/javaHash.log"); BufferedReader br = new BufferedReader(fr)) {
            jsonStr = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> map;
        map = JSONObject.parseObject(jsonStr);
        System.out.println("map : " + map);
    }
}
