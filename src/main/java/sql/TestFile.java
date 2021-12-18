package sql;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestFile {
    public static void main(String[] args) throws IOException {
        String prefix = "E:\\svndoc\\��������̨��ĿȺ\\saas���̳�\\��Ŀ��\\7.���߷���\\SHENGYU\\DB������\\DDL\\PRD\\";
        List<String> pathList = new ArrayList<>();
        pathList.add("10.230.20.22_PublicDS");
        pathList.add("10.230.20.27_Shard2DS");
        pathList.add("10.230.20.32_Shard3DS");
        pathList.add("10.230.20.37_Shard4DS");
        for (String s : pathList) {
            String path = prefix + s;
            String file1 = path + "\\ִ��\\D01.sql";
            String file2 = path + "\\ִ��\\D01_NJ.sql";
            Path f1 = Paths.get(file1);
            File f2 = new File(file2);
            List<String> list1 = Files.readAllLines(f1);
            List<String> list2 = FileUtils.readLines(f2);
            list1.addAll(list2);
            Files.write(f1, list1);
            f2.delete();
            String file3 = path + "\\�ع�\\R01.sql";
            String file4 = path + "\\�ع�\\R01_NJ.sql";
            File f3 = new File(file3);
            File f4 = new File(file4);
            List<String> list3 = FileUtils.readLines(f3);
            List<String> list4 = FileUtils.readLines(f4);
            list3.addAll(list4);
            FileUtils.writeLines(f3, list3);
            f4.delete();
        }


    }


}
