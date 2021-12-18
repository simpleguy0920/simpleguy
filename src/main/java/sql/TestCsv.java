package sql;

import com.alibaba.fastjson.JSONObject;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.google.common.collect.Sets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestCsv {
    public static void main(String[] args) throws Exception {
        TestCsv testCsv = new TestCsv();
        testCsv.setPayCode();
    }

    public void writeExcel(String[] head, List<String[]> list, File file) throws IOException {
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row header = sheet.createRow(0);
        Cell cell = null;
        if (head != null) {
            for (int i = 0; i < head.length; i++) {
                cell = header.createCell(i);
                cell.setCellValue(head[i]);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String[] strings = list.get(i);
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < strings.length; j++) {
                cell = row.createCell(j);
                cell.setCellValue(strings[j]);
            }
        }
        FileOutputStream stream = FileUtils.openOutputStream(file);
        workbook.write(stream);
        stream.close();
    }

    public void mergeCsvRe() throws Exception {
        //所有csv文件目录
        Path path = Paths.get("D:\\3\\1\\o2opoas整合数据");
        File filePath = path.toFile();
        File[] files = filePath.listFiles();
        String[] head = null;
        List<String[]> list = new ArrayList<>();
        //遍历csv
        for (File file : files) {
            for (File listFile : file.listFiles()) {
                if (listFile.getName().contains("po_cf_order_pay_")) {
                    System.out.println("do file=" + listFile);

                    CsvReader csvReader = new CsvReader(new FileInputStream(listFile), StandardCharsets.UTF_8);
                    // 读表头
                    csvReader.readHeaders();
                    head = csvReader.getHeaders();
                    //遍历行
                    while (csvReader.readRecord()) {
                        String[] values = csvReader.getValues();
                        list.add(values);
                    }
                }
            }
        }
        writeExcel(head, list, new File("D:\\3\\1\\all\\" + "订单行支付" + ".xlsx"));
    }

    public void mergeCsv() throws Exception {
        //所有csv文件目录
        Path path = Paths.get("D:\\3");
        File filePath = path.toFile();
        File[] files = filePath.listFiles();
        String[] head = null;
        List<String[]> list = new ArrayList<>();
        //遍历csv
        for (File file : files) {
            System.out.println("file=" + file);
            CsvReader csvReader = new CsvReader(file.getAbsolutePath());
            // 读表头
            csvReader.readHeaders();
            head = csvReader.getHeaders();
            //遍历行
            while (csvReader.readRecord()) {
                String[] values = csvReader.getValues();
                list.add(values);
            }
        }
        writeExcel(head, list, new File("d:\\1\\1.xls"));
    }

    public void mergeAllCSV() throws Exception {
        //所有csv文件目录
        Path path = Paths.get("D:\\1\\1");
        File filePath = path.toFile();
        File[] files = filePath.listFiles();
        String[] head = null;
        List<String[]> list = new ArrayList<>();
        for (File file : files) {
            System.out.println("file=" + file);
            CsvReader csvReader = new CsvReader(file.getAbsolutePath());
            // 读表头
            csvReader.readHeaders();
            head = csvReader.getHeaders();
            //遍历行
            while (csvReader.readRecord()) {
                String[] values = csvReader.getValues();
                list.add(values);
            }
        }
        //写入excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow header = sheet.createRow(0);
        HSSFCell cell = null;
        if (head != null) {
            for (int i = 0; i < head.length; i++) {
                cell = header.createCell(i);
                cell.setCellValue(head[i]);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String[] strings = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            for (int j = 0; j < strings.length; j++) {
                cell = row.createCell(j);
                cell.setCellValue(strings[j]);
            }
        }
        //合并后excel路径
        FileOutputStream stream = FileUtils.openOutputStream(new File("d:/1/2.xls"));
        workbook.write(stream);
        stream.close();
    }


    public void readCSV1() throws Exception {
        CsvWriter csvWriter = new CsvWriter("D:\\1\\1\\all.csv");

        List<String> mtList = new ArrayList<>();
        List<String> eleList = new ArrayList<>();
        Path path = Paths.get("D:\\1\\1");
        File filePath = path.toFile();
        File[] files = filePath.listFiles();
        for (File file : files) {
            System.out.println("file=" + file);
            CsvReader csvReader = new CsvReader(file.getAbsolutePath());
            // 读表头
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                String ordeId = csvReader.get("CF_ORDER_ID");
                String platform = csvReader.get("SALE_PLATFORM");
                String CF_ORDER_ITEM_ID = csvReader.get("CF_ORDER_ITEM_ID");
                String OMS_ID = csvReader.get("OMS_ID");
                String OMS_ITEM_ID = csvReader.get("OMS_ITEM_ID");
                String str = ordeId + "," + CF_ORDER_ITEM_ID + "," + OMS_ID + "," + OMS_ITEM_ID;
                if ("MT".equals(platform)) {
                    mtList.add(str);

                } else if ("ELE".equals(platform)) {
                    eleList.add(str);
                }
            }
            csvReader.close();
        }
        csvWriter.close();
        FileUtils.writeLines(new File("d:\\1\\1\\mt.txt"), mtList);
        FileUtils.writeLines(new File("d:\\1\\1\\ele.txt"), eleList);
    }


    public void readCSV() throws Exception {
        String[] header = null;
        List<String[]> list = new ArrayList<>();
        List<String> mtList = new ArrayList<>();
        List<String> eleList = new ArrayList<>();
        Path path = Paths.get("D:\\2\\origin");
        File filePath = path.toFile();
        File[] files = filePath.listFiles();
        for (File file : files) {
            System.out.println("file=" + file);
            CsvReader csvReader = new CsvReader(file.getAbsolutePath(), ',', StandardCharsets.UTF_8);
            // 读表头
            csvReader.readHeaders();
            header = csvReader.getHeaders();
            while (csvReader.readRecord()) {
                String ordeId = csvReader.get("CF_ORDER_ID");
                String platform = csvReader.get("SALE_PLATFORM");
                String CF_ORDER_ITEM_ID = csvReader.get("CF_ORDER_ITEM_ID");
                String OMS_ID = csvReader.get("OMS_ID");
                String OMS_ITEM_ID = csvReader.get("OMS_ITEM_ID");
                String str = ordeId + "," + CF_ORDER_ITEM_ID + "," + OMS_ID + "," + OMS_ITEM_ID;
                if ("MT".equals(platform)) {
                    mtList.add(str);
                    list.add(csvReader.getValues());
                } else if ("ELE".equals(platform)) {
                    eleList.add(str);
                    list.add(csvReader.getValues());
                }
            }
            csvReader.close();
        }

        writeExcel(header, list, new File("d:/1/1/1xls"));
    }

    public void filter1() throws Exception {
        Set<String> set = Sets.newHashSet(StringUtils.split("210100019145774015,210100024487932599,210100016644211169,210100015521511024,210100015194450267,210100017767630130,210100014871184090", ","));
        Workbook workbook = null;
        workbook = new WorkbookFactory().create(new FileInputStream("D:\\3\\row\\订单行支付信息_先删后插.xls"));
        Sheet sheet2 = workbook.getSheetAt(0);
        int j = 0;
        for (int i = 0; i <= sheet2.getLastRowNum(); i++) {
            Row row = sheet2.getRow(i);
            if (row.getCell(0) == null || row.getCell(1) == null) {
                continue;
            }
            String omsid = row.getCell(1).getStringCellValue().trim();

            Cell cell = row.createCell(11);
            if (set.contains(omsid)) {
                cell.setCellValue("1");
            } else {
                cell.setCellValue("2");
            }
        }
        FileOutputStream fos = new FileOutputStream(new File("D:\\3\\row\\订单行支付信息_先删后插.xls"));
        workbook.write(fos);
    }

    public void filter() throws Exception {
        Set<String> set = new HashSet<>();
        Workbook workbook = null;
        workbook = new WorkbookFactory().create(new FileInputStream("D:\\3\\row\\订单行支付信息_先删后插.xls"));
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row.getCell(0) == null || row.getCell(1) == null) {
                continue;
            }
            String omsid = row.getCell(0).getStringCellValue().trim();
            String potsitemid = row.getCell(1).getStringCellValue().trim();
            String potssubitemid = row.getCell(2).getStringCellValue().trim();

            set.add(omsid + "," + potsitemid + "," + potssubitemid);
        }
        workbook = new WorkbookFactory().create(new FileInputStream("D:\\3\\1\\payrow\\订单行优惠分摊.xlsx"));
        Sheet sheet2 = workbook.getSheetAt(0);
        int j = 0;
        for (int i = 0; i <= sheet2.getLastRowNum(); i++) {
            Row row = sheet2.getRow(i);
            if (row.getCell(0) == null || row.getCell(1) == null) {
                continue;
            }
            String omsid = row.getCell(1).getStringCellValue().trim();
            String potsitemid = row.getCell(4).getStringCellValue().trim();
            String potssubitemid = row.getCell(6).getStringCellValue().trim();
            Cell cell = row.createCell(11);
            if (set.contains(omsid + "," + potsitemid + "," + potssubitemid)) {
                cell.setCellValue("1");
            } else {
                cell.setCellValue("2");
            }
        }
        FileOutputStream fos = new FileOutputStream(new File("D:\\3\\1\\payrow\\订单行优惠分摊1.xlsx"));
        workbook.write(fos);
    }


    public void setMemberNo() throws Exception {
        Map<String, String> map = new HashMap<>();
        Workbook workbook = new WorkbookFactory().create(new FileInputStream("D:\\3\\1\\3\\订单头信息.xlsx"));
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row.getCell(0) == null || row.getCell(1) == null) {
                continue;
            }
            String omsId = row.getCell(0).getStringCellValue();
            if ("OMS_ID".equals(omsId.trim()) || omsId.length() > 20 || omsId.length() < 17) {
                continue;
            }
            String member = row.getCell(1).getStringCellValue();
            map.put(omsId, member);
        }
        File[] fileArray = Paths.get("D:\\3\\1\\new").toFile().listFiles();
        for (File file : fileArray) {
            System.out.println(file.getName());
            workbook = new WorkbookFactory().create(new FileInputStream(file));
            Sheet sheetAt = workbook.getSheetAt(0);
            for (int i = 1; i <= sheetAt.getLastRowNum(); i++) {
                Row row = sheetAt.getRow(i);
                if (row == null || row.getCell(1) == null) {
                    System.out.println(row + " |" + i);
                    continue;
                }
                String mem = map.get(row.getCell(1).getStringCellValue());
                if (mem == null) {
                    System.out.println(row.getCell(1));
                }
                row.createCell(0).setCellValue(mem);
            }
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
        }
    }

    public String getRepaymentCode(String channelType, String payCode) {
        String repaymentCode = null;
        if ("MT".equals(channelType) && ("7207".equals(payCode) || "7209".equals(payCode))) {
            repaymentCode = "70639458";
        } else if ("ELE".equals(channelType) && ("7223".equals(payCode) || "7224".equals(payCode))) {
            repaymentCode = "30320272";
        }
        return repaymentCode;
    }

    public void setPayCode() throws Exception {
        TestHttp testHttp = new TestHttp();

        File file = new File("D:\\3\\1\\new\\订单行支付信息_先删后插 - 副本.xls");
        Workbook workbook = new WorkbookFactory().create(new FileInputStream(file));
        Sheet sheetAt = workbook.getSheetAt(0);
        for (int i = 1; i <= sheetAt.getLastRowNum(); i++) {
            Row row = sheetAt.getRow(i);
            if (row == null || row.getCell(1) == null) {
                continue;
            }
            String omsId = row.getCell(1).getStringCellValue();
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("omsOrderId", omsId));
            String itemJSON = testHttp.post("http://www.baidu.com", new UrlEncodedFormEntity(nvps), testHttp.cookie);
            JSONObject jsonObject = JSONObject.parseObject(itemJSON);
            String itemId = jsonObject.getJSONArray("data").getJSONObject(0).getString("orderItemId");
            String platForm = "";

            if (itemId.startsWith("MT")) {
                platForm = "MT";
            } else if (itemId.startsWith("ELE")) {
                platForm = "ELE";
            } else {
                throw new RuntimeException(omsId);
            }
            String paycode = row.getCell(6).getStringCellValue();
            String repaymentCode = getRepaymentCode(platForm, paycode);
            row.createCell(13).setCellValue(repaymentCode);

        }
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
    }


    public void compare() throws Exception {

        Map<String, Integer> map1 = buildMap("D:\\3\\orderdata\\订单行支付信息新.xls");
        Map<String, Integer> map2 = buildMap("D:\\3\\orderdata\\订单行支付信息_先删后插.xls");
        for (Map.Entry<String, Integer> stringIntegerEntry : map1.entrySet()) {
            if (!stringIntegerEntry.getValue().equals(map2.get(stringIntegerEntry.getKey()))) {
                System.out.println(stringIntegerEntry);
            }
        }

    }

    private Map<String, Integer> buildMap(String fileName) throws IOException, InvalidFormatException {
        Map<String, Integer> map1 = new HashMap<>();

        Workbook workbook = new WorkbookFactory().create(new FileInputStream(fileName));
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String omsId = row.getCell(1).getStringCellValue();
            if ("OMS_ID".equals(omsId.trim())) {
                continue;
            }
            map1.merge(omsId, 1, Integer::sum);
        }
        return map1;
    }


}
