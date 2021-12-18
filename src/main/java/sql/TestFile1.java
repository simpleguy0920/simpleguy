package sql;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestFile1 {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\jddj");
        List<String> list = FileUtils.readLines(file);
        for (String str : list) {
            String[] array = StringUtils.split(str, " ");
            System.out.println("STATUS" + array[0].trim() + "(\"" + array[0].trim() + "\", \"" + array[1] + "\"),");
        }

    }

}
