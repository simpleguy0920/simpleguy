package sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class SearchFile {
    public static void main(String[] args) throws Exception {
        //所有csv文件目录
        Path path = Paths.get("D:\\2\\mtorigin");
        File filePath = path.toFile();
        File[] files = filePath.listFiles();

        //遍历csv
        for (File file : files) {
            String fileStr = FileUtils.readFileToString(file);
            JSONObject jsonObject = JSON.parseObject(fileStr);
            System.out.println(jsonObject.getString("package_bag_money_yuan") + "-" + file.getName());


        }
    }
}
