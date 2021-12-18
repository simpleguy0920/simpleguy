package sql;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Testhash {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        long count = Files.lines(Paths.get("E:\\fileRec\\out_20200212\\out_20200212.txt"), Charset.defaultCharset()).count();
        System.out.println(count);
        LineIterator lineIterator = FileUtils.lineIterator(Paths.get("E:\\fileRec\\out_20200212\\out_20200212.txt").toFile());

//        LineIterator lineIterator = IOUtils.lineIterator(multipartFile.getInputStream())
        int i = 0;
        List<String> list = new ArrayList<>(5000);
        while (lineIterator.hasNext()) {
            i++;
            list.add(lineIterator.nextLine());
            if (i % 5000 == 0) {
                final List<String> list1 = list;
                //入数据库

                list = new ArrayList<>(5000);
            }
        }
        System.out.println(list.size());
//        File file=Paths.get("E:\\fileRec\\out_20200212\\out_20200212.txt").toFile();
//        Files.lines(Paths.get("E:\\fileRec\\out_20200212\\out_20200212.txt"), Charset.defaultCharset()).forEach(s -> Arrays.stream(StringUtils.split(s,"^")).forEach(System.out::println)) ;
//        LineIterator it = FileUtils.lineIterator(Paths.get("E:\\fileRec\\out_20200212\\out_20200212.txt").toFile(), "UTF-8");
//       while(it.hasNext()){
//           System.out.println(it.nextLine());

    }
}
