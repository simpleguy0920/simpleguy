package sql;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BuildEnum {
    public static void main(String[] args) throws IOException {
        File f1 = new File("d:/6.txt");
        List<String> list1 = FileUtils.readLines(f1);
        File f2 = new File("d:/7.txt");
        List<String> list2 = FileUtils.readLines(f2);

        for (int i = 0; i < list1.size(); i++) {
            System.out.println("POS_" + list1.get(i).replace("-", "_") + "(\"" + list1.get(i) + "\", \"" + list2.get(i) + "\"),");

        }
    }
}
