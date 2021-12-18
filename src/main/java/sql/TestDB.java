package sql;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class TestDB {

    private static final Logger logger = LoggerFactory.getLogger(TestDB.class);

    public static void main(String[] args) throws Exception {
        BasicDataSource dataSource = getShengyuPre();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.queryForObject("select now()", String.class);
        dataSource.setMaxActive(100);
        TestDB testDB = new TestDB();
        testDB.testhuawei();
//        Files.list(Paths.get("D:\\data")).forEach(path -> insertExcelCmmdityInfo(path,jdbcTemplate));
//        insert(jdbcTemplate);
//        insertData1(jdbcTemplate);

    }

    public static void insertMdLogic() {
        Path path = Paths.get("E:\\fileRec\\苏宁和天猫快递公司编码对应关系.xlsx");
        String sql = "INSERT INTO md_logistics_company (  sn_express_cmp_id,  sn_express_cmp_name,  tm_express_cmp_id,  tm_express_cmp_name,  staff_number,  version,  status)VALUES  (  ?, ?,?, ?, 'system','1', '1'  );";
        BasicDataSource dataSource = getO2OPOASSitDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        insertExcelCommon(path, jdbcTemplate, sql, 4);
    }

    public static void setCommodityCode() throws IOException, InvalidFormatException {
        BasicDataSource dataSource = getO2OPOASPreDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        importFromExcel(jdbcTemplate);

    }

    private static void importFromExcel(JdbcTemplate jdbcTemplate) throws IOException, InvalidFormatException {
        Path path = Paths.get("D:\\2\\22.csv");
        FileInputStream fis = new FileInputStream(path.toFile());

        Workbook workbook = new WorkbookFactory().create(fis);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            String snCommodityCode = StringUtils.leftPad(row.getCell(3).getStringCellValue(), 18, '0');
            List<String> list = jdbcTemplate.queryForList("SELECT `SUPPLIER_CMMDTY_CODE` FROM md_cmmdty_info WHERE  CMMDTY_CODE=?", new Object[]{snCommodityCode}, String.class);
            String carrefourCode;
            if (CollectionUtils.isNotEmpty(list)) {
                carrefourCode = list.get(0);
            } else {
                if (!"000000000000200101".equals(snCommodityCode)) {
                    System.out.println(snCommodityCode);
                }
                carrefourCode = "";
            }
            Cell cell = row.createCell(28);
            cell.setCellValue(carrefourCode);
        }
        fis.close();// 关闭文件输入流

        FileOutputStream fos = new FileOutputStream(path.toFile());
        workbook.write(fos);
        fos.close();
    }

    public static void insertValue(JdbcTemplate jdbcTemplate) {
        String sql = "INSERT INTO t1 ( a, b, c)  VALUES  (  ?,?, ?) ";
        for (long j = 0; j < 10000; j++) {
            final long k = j;

            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    preparedStatement.setLong(1, k * 1000 + i);
                    preparedStatement.setLong(2, k * 1000 + i);
                    preparedStatement.setLong(3, k * 1000 + i);
                }

                @Override
                public int getBatchSize() {
                    return 1000;
                }
            });
        }

    }

    public static void insertBatchExcelCommon(JdbcTemplate jdbcTemplate) throws IOException {
        String sql = "insert into commodity_carrefour_create_task (   commodity_code,   channel_type,   store_code,   task_status,   version_no,   retry_count,   error_type,   error_msg ) values   (    ?,     ?,    ?,    ?,  ?,    ?,   ?,   ?   ) ";
        int n = 8;
        ExecutorService executor = Executors.newFixedThreadPool(50);
        Files.list(Paths.get("D:\\data1\\exportData (14)")).forEach(path -> {
            executor.execute(() -> insertExcelCommonUseBatch(path, jdbcTemplate, sql, n));
        });
        executor.shutdown();
    }

    public static void insertExcelCommon(Path path, JdbcTemplate jdbcTemplate, String sql, int n) {
        try {
            System.out.println("start " + path);
            Workbook workbook = new WorkbookFactory().create(new FileInputStream(path.toFile()));
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Object[] praram = new Object[n];
                for (int j = 0; j < n; j++) {
                    praram[j] = row.getCell(j).getStringCellValue();
                }
                jdbcTemplate.update(sql, praram);
            }
            System.out.println("end " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertExcelCommonUseBatch(Path path, JdbcTemplate jdbcTemplate, String sql, int n) {
        try {
            System.out.println(path);
            Workbook workbook = new WorkbookFactory().create(new FileInputStream(path.toFile()));
            Sheet sheet = workbook.getSheetAt(0);
            List<Object[]> list = new ArrayList<>(5000);

            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Object[] praram = new Object[n];
                for (int j = 0; j < n; j++) {
                    praram[j] = row.getCell(j).getStringCellValue();
                }
                list.add(praram);
                if (list.size() == 5000) {
                    jdbcTemplate.batchUpdate(sql, list);
                    list.clear();
                }
            }
            if (CollectionUtils.isNotEmpty(list)) {
                jdbcTemplate.batchUpdate(sql, list);
            }
            System.out.println("end " + path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void convertFile2(Path path) {
        int k = 1;
        try {
            System.out.println("path=" + path);

            Workbook workbook = new WorkbookFactory().create(new FileInputStream(path.toFile()));
            Sheet sheet = workbook.getSheetAt(0);
            List<String> list = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String sncode = row.getCell(0).getStringCellValue();
                String carrefourCode = row.getCell(1).getStringCellValue();
                String name = row.getCell(2).getStringCellValue();
                for (int j = 0; j < 50; j++) {
                    String str = "delete from  md_commodity_carrefour_new_" + String.format("%02d", j) + " where  CF_COMMODITY_CODE='" + sncode + "' and CF_STORE_CODE='" + carrefourCode + "' and  CHANNEL_TYPE='" + name + "' ;";
                    list.add(str);
                    if (list.size() == 150000) {
                        File f = new File("D:\\new2\\delete" + k + ".sql");
                        FileUtils.writeLines(f, list);
                        list.clear();
                        k++;
                    }
                }
            }
            File f = new File("D:\\new2\\delete" + k + ".sql");
            FileUtils.writeLines(f, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void convertFile1(Path path) {
        try {
            System.out.println("path=" + path);
            File f = new File("D:\\new2\\" + 1 + ".txt");
            System.out.println("file=" + f);

            LineIterator lineIterator = FileUtils.lineIterator(path.toFile());
            List<String> list = new ArrayList<>(10000);
            int i = 0;
            while (lineIterator.hasNext()) {
                i++;
                String str = lineIterator.nextLine().replaceAll("\"", "");
                if (i == 1) {
                    continue;
                }
                list.add(str);
                if (i % 10000 == 0) {
                    FileUtils.writeLines(f, list, true);
                    list.clear();
                }

            }

            lineIterator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertTask(JdbcTemplate jdbcTemplate) throws IOException {
        Files.list(Paths.get("C:\\Users\\simpleguy\\Downloads\\exportData (2)")).forEach(path -> insertExcelCmmdityInfo(path, jdbcTemplate));
    }

    private static void convert(JdbcTemplate jdbcTemplate) throws IOException {
        Files.list(Paths.get("D:\\new1")).forEach(path -> convertFile(path));
    }

    private static void docon(JdbcTemplate jdbcTemplate) throws IOException {
        List<Path> list = Files.list(Paths.get("D:\\newdata")).collect(Collectors.toList());

        File f = null;
        for (int i = 0; i < list.size(); i++) {
            if (i % 5 == 0) {
                f = new File("D:\\newdata\\" + i / 5 + ".txt");
            }
            List<String> strList = FileUtils.readLines(list.get(i).toFile());
            FileUtils.writeLines(f, strList, true);
        }
    }

    private static void print(JdbcTemplate jdbcTemplate) throws IOException {
        Files.list(Paths.get("D:\\newdata")).forEach(path -> printFile(path));
    }

    private static void printFile(Path path) {
        try {
            System.out.println("path=" + path);

            LineIterator lineIterator = FileUtils.lineIterator(path.toFile());
            List<String> list = new ArrayList<>(10000);
            int i = 0;
            while (lineIterator.hasNext()) {
                i++;
                String str = lineIterator.nextLine().replaceAll("\"", "");
                if (i == 1) {
                    continue;
                }
                System.out.println(str);
                break;
            }
            lineIterator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void convertFile(Path path) {
        try {
            System.out.println("path=" + path);
            File f = new File("D:\\new2\\" + System.currentTimeMillis() + ".sql");
            System.out.println("file=" + f);

            Workbook workbook = new WorkbookFactory().create(new FileInputStream(path.toFile()));
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String sncode = row.getCell(1).getStringCellValue();
                String carrefourCode = row.getCell(2).getStringCellValue();
                String name = row.getCell(3).getStringCellValue();
                Object[] praram = new Object[]{sncode, carrefourCode, name};
                System.out.println(Arrays.toString(praram));
                String sql = "insert into commodity_carrefour_create_task (  commodity_code,  channel_type,  store_code,  task_status,  version_no,  retry_count,  error_type,  error_msg)values  (  ?,  ?,  ?,   ?,   1,  0,    '',   ?   )";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addCommodityByExcel(JdbcTemplate jdbcTemplate) throws IOException {
        Files.list(Paths.get("D:\\JlfCommdtyInfos (5)")).forEach(path -> insertExcel(path, jdbcTemplate));
    }

    private static void insertOld(JdbcTemplate jdbcTemplate) throws IOException {
        String sql = "insert  ignore into md_commodity_old (  cf_commodity_code,  cf_store_code,  channel_type)values   (? , ?,  ?)";
        Files.list(Paths.get("D:\\olddata")).forEach(path -> insertFile(path, jdbcTemplate, sql));
    }

    private static void insertnew(JdbcTemplate jdbcTemplate) throws IOException {
        String sql = "insert  ignore into md_commodity_new (  cf_commodity_code,  cf_store_code,  channel_type)values   (? , ?,  ?)";
        Files.list(Paths.get("D:\\new2")).forEach(path -> insertFile(path, jdbcTemplate, sql));
    }

    private static void insertFileList(JdbcTemplate jdbcTemplate) throws IOException {
        String sql = "INSERT INTO md_commodity (   commodity_code,   store_code,   channel_type ) VALUES   (    ?,    ?,    ?   )  ";
        Files.list(Paths.get("D:\\txt\\decrypt")).forEach(path -> insertFile2(path, jdbcTemplate, sql));
    }

    private static void insertFile(Path path, JdbcTemplate jdbcTemplate, String sql) {
        try {
            logger.info("path=" + path);

            LineIterator lineIterator = FileUtils.lineIterator(path.toFile());
            List<Object[]> list = new ArrayList<>(10000);
            int i = 0;
            while (lineIterator.hasNext()) {
                i++;
                String[] array = lineIterator.nextLine().split(",");
                list.add(new Object[]{array[0], array[1], array[2]});
                if (i % 10000 == 0) {
                    jdbcTemplate.batchUpdate(sql, list);
                    list.clear();
                }

            }
            if (CollectionUtils.isNotEmpty(list)) {
                jdbcTemplate.batchUpdate(sql, list);
            }
            lineIterator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertFile2(Path path, JdbcTemplate jdbcTemplate, String sql) {
        try {
            logger.info("path=" + path);

            LineIterator lineIterator = FileUtils.lineIterator(path.toFile());
            List<Object[]> list = new ArrayList<>(10000);
            int i = 0;
            while (lineIterator.hasNext()) {
                i++;
                String[] array = lineIterator.nextLine().split(",");
                list.add(new Object[]{StringUtils.replace(array[1], "\"", ""), StringUtils.replace(array[2], "\"", ""), StringUtils.replace(array[3], "\"", "")});
                if (i % 10000 == 0) {
                    jdbcTemplate.batchUpdate(sql, list);
                    list.clear();
                }

            }
            if (CollectionUtils.isNotEmpty(list)) {
                jdbcTemplate.batchUpdate(sql, list);
            }
            lineIterator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertExcelTask(Path path, JdbcTemplate jdbcTemplate) {
        try {
            System.out.println(path);
            String sql = "insert ignore into commodity_carrefour_create_task (  commodity_code,  channel_type,  store_code,  task_status,  version_no,  retry_count,  error_type,  error_msg)values  (  ?,  ?,  ?,   ?,   1,  0,    '',   ?   )";

            Workbook workbook = new WorkbookFactory().create(new FileInputStream(path.toFile()));
            Sheet sheet = workbook.getSheetAt(0);
            List<Object[]> list = new ArrayList<>(5000);
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String sncode = row.getCell(0).getStringCellValue();
                String carrefourCode = row.getCell(1).getStringCellValue();
                String channelType = row.getCell(2).getStringCellValue();
                String storeCode = row.getCell(3).getStringCellValue();
                String status = row.getCell(4).getStringCellValue();
                String msg = row.getCell(6).getStringCellValue();
                Object[] praram = new Object[]{sncode, channelType, storeCode, status, msg};
                list.add(praram);
                if (i % 5000 == 0) {
                    jdbcTemplate.batchUpdate(sql, list);
                    list.clear();
                }
            }
            if (CollectionUtils.isNotEmpty(list)) {
                jdbcTemplate.batchUpdate(sql, list);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertExcelCmmdityInfo(Path path, JdbcTemplate jdbcTemplate) {
        try {
            System.out.println(path);
            Workbook workbook = new WorkbookFactory().create(new FileInputStream(path.toFile()));
            Sheet sheet = workbook.getSheetAt(0);
            List<Object[]> list = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Object[] praram = new Object[]{row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue()};
                list.add(praram);
            }
            String sql = "insert ignore into md_cmmdty_info (  CMMDTY_CODE,  DISTRIBUTE_SYS,  SUPPLIER_CMMDTY_CODE,  SUPPLIER_CMMDTY_NAME)values  (    ?,   ?,   ?,    ? )";
            jdbcTemplate.batchUpdate(sql, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(JdbcTemplate jdbcTemplate) {
        String sql = "INSERT INTO `md_commodity_carrefour_new_00` (  `cf_commodity_code`,  `cf_store_code`,  `channel_type`)VALUES  ( ?,  ?, 'MT'  )";
        for (int i = 0; i < 1000000; i++) {
            Object[] praram = new Object[]{i, "120"};
            jdbcTemplate.update(sql, praram);
        }
    }

    private static void insertExcel(Path path, JdbcTemplate jdbcTemplate) {
        try {
            System.out.println(path);
            Workbook workbook = new WorkbookFactory().create(new FileInputStream(path.toFile()));
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String sncode = row.getCell(1).getStringCellValue();
                String carrefourCode = row.getCell(2).getStringCellValue();
                String name = row.getCell(3).getStringCellValue();
                Object[] praram = new Object[]{sncode, carrefourCode, name};
                System.out.println(Arrays.toString(praram));
                String sql = "insert into commodity_carrefour_create_task (  commodity_code,  channel_type,  store_code,  task_status,  version_no,  retry_count,  error_type,  error_msg)values  (  ?,  ?,  ?,   ?,   1,  0,    '',   ?   )";
//                jdbcTemplate.update(sql, praram);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteCommodity(JdbcTemplate jdbcTemplate) {
        for (int i = 0; i < 50; i++) {
            String sql = "delete from md_commodity_carrefour_" + String.format("%02d", i);
            jdbcTemplate.update(sql);
        }

    }

    private static void insertCommodity(JdbcTemplate jdbcTemplate) {
        String sql = "select   concat(`CMMDTY_CODE`,  ',',`SUPPLIER_CMMDTY_CODE`) from md_cmmdty_info";
        List<String> list = jdbcTemplate.queryForList(sql, String.class);
        for (String s : list) {
            String[] array = StringUtils.split(s, ",");
            String sncode = array[0];
            String cacode = array[1];
            int index = PublicDBRouter.route(sncode);
            System.out.println(sncode + "|" + cacode + "|" + index);
            Object[] param = new Object[]{sncode, cacode};
            sql = "insert ignore into md_commodity_carrefour_" + String.format("%02d", index) + " (  `commodity_code`,  `store_code`,  `cf_commodity_code`,  `cf_store_code`,  `channel_type`)  select  ?,    `ZSNDM`,   ?,    `ZJLF`,    'MT'  from `md_store_data`  where `ZSQLX`='M' ";
            jdbcTemplate.update(sql, param);
            sql = "insert ignore into md_commodity_carrefour_" + String.format("%02d", index) + " (  `commodity_code`,  `store_code`,  `cf_commodity_code`,  `cf_store_code`,  `channel_type`)  select  ?,    `ZSNDM`,   ?,    `ZJLF`,    'ELE'  from `md_store_data`  where `ZSQLX`='M' ";
            jdbcTemplate.update(sql, param);
        }


    }

    private static void insertStoreData(JdbcTemplate jdbcTemplate) throws IOException, InvalidFormatException {

        Workbook workbook = new WorkbookFactory().create(new FileInputStream(new File("C:\\Users\\simpleguy\\Downloads\\exportData\\0.xls")));
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            Object[] praram = new Object[]{row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(), row.getCell(4).getStringCellValue(), row.getCell(5).getStringCellValue()};
            System.out.println(Arrays.toString(praram));

            String sql = "insert into md_store_data (  `ZSQLX`,  `ZSQLXNAME`,  `ZJLF`,  `ZJLFNAME`,  `ZSNDM`)values  (   ?, ?,  ?,   ?,    ?  )";
            jdbcTemplate.update(sql, praram);
        }
    }

    private static void testSelectFor(JdbcTemplate jdbcTemplate) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(jdbcTemplate.getDataSource());
                    TransactionTemplate transactionTemplate = new TransactionTemplate(dataSourceTransactionManager);
                    for (int k = 0; k < 100000; k++) {
                        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                            @Override
                            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                                int value = RandomUtils.nextInt(10);
                                int name = RandomUtils.nextInt(100);
                                List<String> result = jdbcTemplate.query("select name from  test_dup where num=?  for update", new Object[]{value}, new RowMapper<String>() {
                                    @Override
                                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                                        return rs.getString("name");
                                    }
                                });
                                if (CollectionUtils.isEmpty(result)) {
                                    jdbcTemplate.update("insert  into test_dup(num,name) values(?,?) ", value, name);

                                } else {
                                    jdbcTemplate.update("update test_dup set name= ? where num=? ", name, value);

                                }
                            }
                        });

                    }
                }
            });
        }
    }

    private static void testInertOndunplicatiedBatch(JdbcTemplate jdbcTemplate) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int k = 0; k < 1000; k++) {
                        List<Object[]> list = new ArrayList<>();
                        for (int j = 0; j < 1000; j++) {
                            list.add(new Object[]{RandomUtils.nextInt(10), RandomUtils.nextInt(100)});
                        }
                        jdbcTemplate.batchUpdate("insert into test_dup(num,name) values(?,'no') on duplicate key update name=?", list);
                        System.out.println("end " + k);
                    }

                }
            });
        }
    }

    private static void testReplaceInto(JdbcTemplate jdbcTemplate) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                for (int k = 0; k < 100000; k++) {
                    jdbcTemplate.update("replace into test_dup(num,name) values(?,?) ", RandomUtils.nextInt(10), RandomUtils.nextInt(100));
                }
            });
        }
    }

    private static void testInsertIgnore(JdbcTemplate jdbcTemplate) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int k = 0; k < 100000; k++) {
                        jdbcTemplate.update("insert IGNORE  into test_dup(num,name) values(?,?) ", RandomUtils.nextInt(10), RandomUtils.nextInt(100));
                    }
                }
            });
        }
    }

    private static void testInertOndunplicatied(JdbcTemplate jdbcTemplate) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int k = 0; k < 100000; k++) {
                        jdbcTemplate.update("insert into test_dup(num,name) values(?,'no') on duplicate key update name=?", RandomUtils.nextInt(10), RandomUtils.nextInt(100));
                    }
                }
            });
        }
    }

    private static void insertData1(JdbcTemplate jdbcTemplate) throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        for (int i = 0; i < 100000; i++) {
            String sql = " INSERT INTO person (   name,   score,   create_time ) VALUES   (  ?,     ?,   ?  ) ";
            jdbcTemplate.update(sql, "name" + i, i + 100, Timestamp.valueOf(localDateTime.minusMinutes(i)));
        }
    }

    private static void insertData2(JdbcTemplate jdbcTemplate) throws IOException {
        List<String> list2 = Files.readAllLines(Paths.get("e://2.txt"));
        for (int i = 0; i < list2.size(); i++) {
            String sql = "insert ignore into md_cmmdty_info (  CMMDTY_CODE,  DISTRIBUTE_SYS,  SUPPLIER_CMMDTY_CODE,  SUPPLIER_CMMDTY_NAME)values  ( ?,  'MCCSP,O2OPOAS,SCCP', ?, 'test'  )";
            jdbcTemplate.update(sql, list2.get(i), i + 1000158200101l);
        }
    }

    private static void insertLog(JdbcTemplate jdbcTemplate) throws IOException {
        List<String> allList = new ArrayList<>();
        List<Object[]> list = new ArrayList<>(5000);
        for (int i = 0; i < 3000000; i++) {
            list.add(new Object[]{String.format("%10d", i), String.format("%08d", i), i % 300});
            if (i % 5000 == 0) {
                System.out.println(LocalDateTime.now() + "-start-" + i);
                jdbcTemplate.batchUpdate("insert     into `commodity_carrefour_create_log` (  `commodity_code`,  `cf_commodity_code`, channel_type, `cf_store_code`,  `task_status`,  `oper_type`,  `error_msg`)values  (    ?,  ?,   'MT', ?,     '1',    '1',    'aaa'   ) on duplicate key update modify_time=now() ", list);
                System.out.println(LocalDateTime.now() + "-end-" + i);
                list.clear();
            }
        }
        jdbcTemplate.batchUpdate("insert     into `commodity_carrefour_create_log` (  `commodity_code`,  `cf_commodity_code`, channel_type, `cf_store_code`,  `task_status`,  `oper_type`,  `error_msg`)values  (    ?,  ?,   'MT', ?,     '1',    '1',    'aaa'   ) on duplicate key update modify_time=now() ", list);
    }

    private static BasicDataSource getPanguDevDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.37.158.244:3306/pangu");
        dataSource.setUsername("fabu");
        dataSource.setPassword("4xcehZB3EA81");
        return dataSource;
    }

    private static BasicDataSource gePOASSitPubDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.37.158.244:3306/pangu");
        dataSource.setUsername("fabu");
        dataSource.setPassword("4xcehZB3EA81");
        return dataSource;
    }

    private static BasicDataSource getO2OPOASPSTDevDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.244.202.126/o2opoaspub");
        dataSource.setUsername("o2oadmin");
        dataSource.setPassword("123456");
        return dataSource;
    }

    private static BasicDataSource getO2OPOASDevDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.47.232.43/o2opoaspub?rewriteBatchedStatements=true");
        dataSource.setUsername("fabu");
        dataSource.setPassword("vcfAJ3o19SK2");
        return dataSource;
    }

    private static BasicDataSource getO2OPOASPreDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.47.229.208:3306/o2opoaspub?rewriteBatchedStatements=true");
        dataSource.setUsername("fabu");
        dataSource.setPassword("52jeh#tbTozG");
        return dataSource;
    }

    private static BasicDataSource getO2OPOASSitDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.47.229.202:3306/o2opoaspub?rewriteBatchedStatements=true");
        dataSource.setUsername("fabu");
        dataSource.setPassword("6n40xjlU4FJY");
        return dataSource;
    }

    private static BasicDataSource getO2OPOASPrePots1DataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.47.229.206:3306/o2opoaspub?rewriteBatchedStatements=true");
        dataSource.setUsername("fabu");
        dataSource.setPassword("52jeh#tbTozG");
        return dataSource;
    }

    private static BasicDataSource getShengyuPre() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.237.62.7:3306/panguxpub?generateSimpleParameterMetadata=true&zeroDateTimeBehavior=convertToNull");
        dataSource.setUsername("shengyu");
        dataSource.setPassword("Shengyu_123456");
        return dataSource;
    }

    public void testFile(JdbcTemplate jdbcTemplate) throws IOException {
        String str = FileUtils.readFileToString(new File("d:\\12"));
        jdbcTemplate.update("insert into t_t2(v2) values(?)", str);
    }

    public void testOrderInfo(JdbcTemplate jdbcTemplate) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            builder.append("abcdefghijklmnopqrstuvwx");
        }
        String str = builder.toString();
        String[] array = stringDivideByNum(str, 65000);
        for (int i = 0; i < array.length; i++) {
            String sql = "INSERT INTO md_order_info (order_id,pots_id, idx,content) VALUES  (?, ? ,? ,?)";
            jdbcTemplate.update(sql, "1", "1", i, array[i]);
        }
    }

    public String[] stringDivideByNum(String str, int num) {
        if (str.length() == 0) {
            return new String[0];
        }
        if (str.length() <= num) {
            String[] result = new String[1];
            result[0] = str;
            return result;
        }
        int divideNum = 0;
        if (str.length() % num == 0) {
            divideNum = str.length() / num;
        } else {
            divideNum = str.length() / num + 1;
        }
        String[] result = new String[divideNum];
        for (int i = 0; i < divideNum - 1; i++) {
            result[i] = str.substring(i * num, i * num + num);
        }
        result[divideNum - 1] = str.substring((divideNum - 1) * num);
        return result;
    }


    public void testhuawei() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        Properties info = new Properties();
        // 设置http访问要使用的代理服务器的地址
        info.put("proxy_type", "1"); // SSL Tunneling
        info.setProperty("http.proxyHost", "10.37.235.10");
        // 设置http访问要使用的代理服务器的端口
        info.setProperty("http.proxyPort", "8080");
        info.put("proxy_host", "10.37.235.10");
        info.put("proxy_port", "8080");
        info.put("user", "root");
        info.put("password", "Sn@20210802");
        conn = DriverManager.getConnection("jdbc:mysql://123.60.46.82:3306?", info);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select NOW()");
        rs.next();
        System.out.println("Data- " + rs.getString(1));
        rs.close();
        stmt.close();
        conn.close();
    }


}



