package sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class TestFileVisit {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("d://1");
        JSONObject json = JSON.parseObject(FileUtils.readFileToString(path.toFile()));
        JSONObject order = json.getJSONObject("data").getJSONArray("list").getJSONObject(0);
        for (Map.Entry<String, Object> entry : order.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
