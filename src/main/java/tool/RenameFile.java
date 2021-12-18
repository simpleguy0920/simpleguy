package tool;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class RenameFile {
    public static void main(String[] args) {
        RenameFile renameFile = new RenameFile();
        renameFile.renameFile("D:\\4\\apollo_config_export_2021_0926_10_13_06\\apollo\\shengyub\\DEV", "D:\\4\\config\\shengyub");
    }


    public void renameFile(String path, String destPath) {
        File pathFile = new File(path);
        File[] files = pathFile.listFiles();
        for (File file : files) {
            String name = file.getName();
            if (name.contains("+application+")) {
                int start = StringUtils.lastIndexOf(name, "+") + 1;
                String shortName = name.substring(start);
                System.out.println(shortName);
                file.renameTo(new File(destPath + "\\" + shortName));
            }
        }
    }
}
