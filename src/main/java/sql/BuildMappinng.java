package sql;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BuildMappinng {
    public static void main(String[] args) throws IOException {
        File f1 = new File("d:/1.txt");
        List<String> list1 = FileUtils.readLines(f1);
        JSONObject allObject = new JSONObject(true);
        for (int i = 0; i < list1.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "keyword");

            allObject.put(list1.get(i), jsonObject);
        }
        System.out.println(allObject.toJSONString());
    }
}
