package sql;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestFile2 {
    public static void main(String[] args) throws IOException {
        List<String> list = Files.readAllLines(Paths.get("D:\\4.sql"));
        StringBuilder builder = new StringBuilder();
        List<String> l = new ArrayList<>();
        for (int j = 0; j < 50; j++) {
            builder = new StringBuilder();
            builder.append(" DELETE FROM  md_commodity_sgdsp_" + String.format("%02d", j) + "  WHERE  STORE_CODE ='74PA'  AND  CHANNEL_TYPE ='ELE'  AND  COMMODITY_CODE IN (");
            for (int k = 0; k < list.size(); k++) {
                if (k == list.size() - 1) {
                    String s = list.get(k);
                    builder.append(" '" + s + "');");
                } else {
                    String s = list.get(k);
                    builder.append(" '" + s + "',");
                }
            }
            l.add(builder.toString());

            if (String.valueOf(j).endsWith("9")) {
                FileUtils.writeLines(new File("D:\\deletecomodity" + j + ".sql"), l);
                l = new ArrayList<>();
            }

        }
    }
}
