package sql;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class BuildDTO2 {
    public static void main(String[] args) throws IOException {
        File file = new File("d:/dto.txt");
        List<String> list = FileUtils.readLines(file);
        for (String str : list) {
            String[] array = StringUtils.split(str, "|");
            if (array.length < 2) {
                continue;
            }
            System.out.println("/**");
            System.out.println("*" + array[0].trim());
            System.out.println("*/");
            System.out.println("private String " + array[1].trim() + ";");
            System.out.println();
        }
    }
}
