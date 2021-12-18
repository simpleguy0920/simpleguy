package com.test.java;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import sun.net.util.IPAddressUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestXml {
    public static void main(String[] args) throws DocumentException, IOException {
        String str = FileUtils.readFileToString(new File("d:/aa.xml"));
        Document document = DocumentHelper.parseText(str);
        List<Node> list = document.selectNodes("//connection-url");
        List<String> strList = new ArrayList<>();
        for (Node node : list) {
            Element element = (Element) node;
            String url = element.getText();
            String[] array = StringUtils.split(url, ":");
            for (String a : array) {
                String value = StringUtils.removeStart(a, "//");
                if (IPAddressUtil.isIPv4LiteralAddress(value)) {
                    strList.add(value);
                }
            }
        }
        System.out.println(strList.size());
        List<String> excelList = FileUtils.readLines(new File("d:/aa.txt"));
        Collection sub = CollectionUtils.subtract(excelList, strList);
        System.out.println(StringUtils.join(sub));

    }
}
