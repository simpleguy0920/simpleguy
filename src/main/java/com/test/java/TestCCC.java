package com.test.java;

import java.io.IOException;
import java.util.stream.Stream;

public class TestCCC {
    public static void main(String[] args) throws IOException {
//        File f=new File("d://33.txt");
//        List<String> list= FileUtils.readLines(f);
//        Multiset<String> multiset= HashMultiset.create();
//        for (String str : list) {
//            if(str.contains("10.104.134.129:60000")){
//                String[] array= StringUtils.split(str," ");
//                String[] array1=StringUtils.split(array[4],":");
//                multiset.add(array1[0]);
//            }
//        }
//        for (Multiset.Entry<String> entry : multiset.entrySet()) {
//            System.out.println(entry.getElement()+" "+entry.getCount());
//        }
        Stream<Integer> streamInt = Stream.iterate(0, (x) -> {
            return x + 1;
        }).limit(100);
        streamInt.forEach(System.out::println);
    }
}
