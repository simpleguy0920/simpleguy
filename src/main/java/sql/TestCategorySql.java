package sql;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestCategorySql {
    public static void main(String[] args) throws IOException {
        List<String> list = Files.readAllLines(Paths.get("d:/2.txt"));
        File file = new File("d:/2.sql");
        for (String s : list) {
            List<String> list1 = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                list1.add("DELETE  FROM md_commodity_category_carrefour_" + String.format("02d", i) + " WHERE COMMODITY_CODE= '" + s + "' ;");
            }
            FileUtils.writeLines(file, list1, true);
        }

    }
}
