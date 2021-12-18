package sql;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

public class TestSplitFile {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\1\\1\\all.csv");
        LinkedHashSet<String> set = new LinkedHashSet<>();
        List<String> list = FileUtils.readLines(file);
        for (String s : list) {
            set.add(StringUtils.split(s, ",")[0]);
        }

        LinkedHashSet<String> set2 = new LinkedHashSet<>();

        File file2 = new File("D:\\1\\1\\2.txt");
        List<String> list2 = FileUtils.readLines(file2);
        for (String s : list2) {
            set2.add(s);
        }
        set2.removeAll(set);
        System.out.println(StringUtils.join(set2.size()));

        System.out.println(StringUtils.join(set2));
    }
}
