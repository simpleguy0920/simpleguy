package sql;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateTableSql {
    @Test
    public void testdb3() throws IOException {
        List<String> list = FileUtils.readLines(new File("d:/1.sql"));
        List<String> allText = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            for (String str : list) {
                if ((str.contains("create") || str.contains("drop")) && str.contains("table")) {
                    String line;
                    if (str.contains("create")) {
                        String[] end = StringUtils.substringsBetween(str, "table", "(");
                        String tablename = end[0].trim() + "_" + String.format("%02d", i);
                        line = "create table " + tablename + " (";
                    } else if (str.contains("drop")) {
                        String[] end = StringUtils.substringsBetween(str, "table", ";");
                        String tablename = end[0].trim() + "_" + String.format("%02d", i);
                        line = "drop table " + tablename + ";";
                    } else {
                        line = str;
                    }
                    allText.add(line);
                } else {
                    allText.add(str);
                }

            }
        }
        FileUtils.writeLines(new File("d:/3.sql"), allText);
    }

    @Test
    public void testdb4() throws IOException {
        List<String> list = FileUtils.readLines(new File("d:/1.sql"));
        List<String> allText = new ArrayList<>();
        for (int i = 64; i < 128; i++) {
            for (String str : list) {
                if ((str.contains("create") || str.contains("drop")) && str.contains("table")) {
                    String line;
                    if (str.contains("create")) {
                        String[] end = StringUtils.substringsBetween(str, "table", "(");
                        String tablename = end[0].trim() + "_" + String.format("%03d", i);
                        line = "create table " + tablename + " (";
                    } else if (str.contains("drop")) {
                        String[] end = StringUtils.substringsBetween(str, "table", ";");
                        String tablename = end[0].trim() + "_" + String.format("%03d", i);
                        line = "drop table " + tablename + ";";
                    } else {
                        line = str;
                    }
                    allText.add(line);
                } else {
                    allText.add(str);
                }

            }
        }
        FileUtils.writeLines(new File("d:/4.sql"), allText);
    }


    @Test
    public void testdb3rollback() throws IOException {
        List<String> list = FileUtils.readLines(new File("d:/1.sql"));
        List<String> allText = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            for (String str : list) {
                String line;
                if ((str.contains("create") || str.contains("drop")) && str.contains("table")) {
                    String[] end = StringUtils.substringsBetween(str, "table", "(");
                    String tablename = end[0].trim() + "_" + String.format("%03d", i);
                    line = "drop table " + tablename + " ;";
                    allText.add(line);
                }
            }
        }
        FileUtils.writeLines(new File("d:/3-rollback.sql"), allText);
    }


    @Test
    public void testdb4rollback() throws IOException {
        List<String> list = FileUtils.readLines(new File("d:/1.sql"));
        List<String> allText = new ArrayList<>();
        for (int i = 64; i < 128; i++) {
            for (String str : list) {
                String line;
                if ((str.contains("create") || str.contains("drop")) && str.contains("table")) {
                    String[] end = StringUtils.substringsBetween(str, "table", "(");
                    String tablename = end[0].trim() + "_" + String.format("%03d", i);
                    line = "drop table " + tablename + " ;";
                    allText.add(line);
                }
            }
        }
        FileUtils.writeLines(new File("d:/4-rollback.sql"), allText);
    }


    public void test2() throws IOException {
        List<String> list = FileUtils.readLines(new File("d:/1.sql"));
        List<String> allText = new ArrayList<>();
        for (int i = 0; i <= 63; i++) {
            for (String str : list) {
                if ((str.contains("create") || str.contains("drop")) && str.contains("table")) {
                    String line;
                    if (str.contains("create")) {
                        String[] end = StringUtils.substringsBetween(str, "table", "(");
                        String tablename = end[0].trim() + "_" + String.format("%03d", i);
                        line = " drop table if exists  " + tablename + " ;";
                        allText.add(line);
                    }
                }

            }
        }

        FileUtils.writeLines(new File("d:/3.sql"), allText);
    }

    public void test3() throws IOException {
        List<String> fileNameList = Stream.of("pre0", "pre1", "pre2", "pre3", "pre4").collect(Collectors.toList());
        for (String fileName : fileNameList) {

            List<String> list = FileUtils.readLines(new File("D:\\presql\\" + fileName + ".sql"));
            List<String> allText = new ArrayList<>();
            for (String str : list) {
                if ((str.contains("create") || str.contains("drop")) && str.contains("table")) {
                    String line;
                    if (str.contains("create")) {
                        String[] end = StringUtils.substringsBetween(str, "table", "(");
                        String tablename = end[0].trim();
                        line = " drop table if exists  " + tablename + " ;";
                        allText.add(line);
                    }
                }


            }

            FileUtils.writeLines(new File("D:\\presql\\" + fileName + "-rollback.sql"), allText);
        }
    }
}
