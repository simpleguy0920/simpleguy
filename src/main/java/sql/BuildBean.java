package sql;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class BuildBean {

    public static void main(String[] args) throws Exception {
        String beanName = "SyPickCode";
        String tableName = "sy_pick_code";
        String schema = "shengyupublic1";
        BasicDataSource dataSource = getShengyuDevDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select  a.`COLUMN_NAME`,a.`DATA_TYPE`,a.`COLUMN_COMMENT` from `information_schema`.`COLUMNS` a where a.`TABLE_NAME`=?  and   `TABLE_SCHEMA`=?  order by a.`ORDINAL_POSITION`";
        List<BeanInfo> beanInfoList = jdbcTemplate.query(sql, new Object[]{tableName, schema}, (rs, i) -> {
            BeanInfo beanInfo = new BeanInfo();
            beanInfo.setDataBaseCode(rs.getString("COLUMN_NAME"));
            beanInfo.setDataBaseType(rs.getString("DATA_TYPE"));
            beanInfo.setComment(rs.getString("COLUMN_COMMENT"));
            beanInfo.buildJava();
            return beanInfo;
        });
        System.out.println("import java.io.Serializable;");
        System.out.println("import java.util.Date;");
        System.out.println();
        System.out.println("public class " + beanName + " implements Serializable {");
        for (BeanInfo beanInfo : beanInfoList) {
            System.out.println("/**");
            System.out.println("* " + beanInfo.getComment());
            System.out.println("*/");
            System.out.println("private " + beanInfo.getJavaType() + " " + beanInfo.getJavaCode() + ";");
        }
        System.out.println("}");
        System.out.println();
        System.out.println("import org.springframework.jdbc.core.RowMapper;\n" +
                "\n" +
                "import java.sql.ResultSet;\n" +
                "import java.sql.SQLException;");
        System.out.println();
        System.out.println("public class " + beanName + "RowMapper  implements  RowMapper<" + beanName + "> {");
        System.out.println("@Override");
        System.out.println("public " + beanName + " mapRow(ResultSet rs, int i) throws SQLException {");
        System.out.println(beanName + " " + StringUtils.uncapitalize(beanName + "= new " + beanName + "();"));
        for (BeanInfo beanInfo : beanInfoList) {
            StringBuilder builder = new StringBuilder();
            builder.append(StringUtils.uncapitalize(beanName) + ".set" + StringUtils.capitalize(beanInfo.getJavaCode()) + "(rs.get" + StringUtils.capitalize(beanInfo.convertSqlType()) + "(\"" + beanInfo.getDataBaseCode() + "\"));");
            System.out.println(builder);
        }
        System.out.println("return  " + StringUtils.uncapitalize(beanName + "; "));

        System.out.println("}");
        System.out.println("}");
        System.out.println();
        String daoName = beanName + "Dao";
        System.out.println("public interface " + daoName + " {" +
                "}");
        System.out.println();
        String daoImplName = beanName + "DaoImpl";
        System.out.println("import org.springframework.stereotype.Repository;");
        System.out.println("@Repository");
        System.out.println("public class " + daoImplName + "   extends BaseDaoImpl implements " + daoName + "{\n" +
                "}");


        for (BeanInfo beanInfo : beanInfoList) {
            String putStr = "paramMap.put(\"" + beanInfo.getJavaCode() + "\"," + StringUtils.uncapitalize(beanName) + ".get" + StringUtils.capitalize(beanInfo.getJavaCode() + "());");
            System.out.println(putStr);
        }
        System.out.println();
        System.out.println("sqlMap_" + StringUtils.upperCase(tableName) + ".xml");
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        System.out.println("<sqlMap namespace=\"" + StringUtils.upperCase(tableName) + "\" jdbcTimeout=\"60\">");
        System.out.println("<sql id=\"INSERT\">");
        System.out.println("<![CDATA[");
        String insertSql = " insert into  " + tableName + "(" + beanInfoList.stream().map(BeanInfo::getDataBaseCode).collect(Collectors.joining(",")) + ")values(" + beanInfoList.stream().map(BeanInfo::getJavaCodeWithCommma).collect(Collectors.joining(",")) + ")";
        System.out.println(insertSql);
        System.out.println("]]>");
        System.out.println("</sql>");


        System.out.println();

        System.out.println("update " + tableName + " set ");
        for (BeanInfo beanInfo : beanInfoList) {
            System.out.println(beanInfo.getDataBaseCode() + " = " + beanInfo.getJavaCodeWithCommma() + ",");
        }
    }

    private static BasicDataSource getPoasPubSitDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.47.155.75:3306/");
        dataSource.setUsername("fabu");
        dataSource.setPassword("73R4_h8td6fE");
        return dataSource;
    }

    private static BasicDataSource getO2OPoasPubSitDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.47.229.202:3306/");
        dataSource.setUsername("fabu");
        dataSource.setPassword("6n40xjlU4FJY");
        return dataSource;
    }

    private static BasicDataSource getO2OPOASPrePots1DataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.47.229.206:3306/");
        dataSource.setUsername("fabu");
        dataSource.setPassword("52jeh#tbTozG");
        return dataSource;
    }

    private static BasicDataSource getShengyuDevDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.237.62.7:3306/");
        dataSource.setUsername("shengyu");
        dataSource.setPassword("Shengyu_123456");
        return dataSource;
    }

    public void buildBean(Object o) {
//        Map map= BeanUtils.describe(o )

    }
}
