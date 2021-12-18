package sql;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeanInfo {

    private String javaCode;

    private String dataBaseCode;

    private String dataBaseType;

    private String javaType;

    private String comment;

    public static void main(String[] args) {
        BeanInfo beanInfo = new BeanInfo();
        beanInfo.setJavaCode("beanInfoAdDFasd");
        System.out.println(beanInfo.convertJavaCodeUpper());
    }

    public void buildJava() {
        String str = dataBaseCode.toLowerCase();
        final StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile("_(\\w)");
        Matcher m = p.matcher(str);
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        javaCode = sb.toString();
        if (dataBaseType.equals("varchar")) {
            javaType = "String";
        } else if (dataBaseType.equals("bigint")) {
            javaType = "long";
        } else if (dataBaseType.equals("datetime") || dataBaseType.equals("timestamp")) {
            javaType = "Date";
        } else if (dataBaseType.equals("int")) {
            javaType = "int";
        }
    }

    public String convertSqlType() {
        if ("Date".equals(javaType)) {
            return "timestamp";
        } else {
            return javaType;
        }
    }

    public String convertJavaCodeUpper() {
        return StringUtils.capitalize(javaCode);
    }

    public String getJavaCode() {
        return javaCode;
    }

    public void setJavaCode(String javaCode) {
        this.javaCode = javaCode;
    }

    public String getDataBaseCode() {
        return dataBaseCode;
    }

    public void setDataBaseCode(String dataBaseCode) {
        this.dataBaseCode = dataBaseCode;
    }

    public String getJavaCodeWithCommma() {
        return ":" + javaCode;
    }

    public String getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(String dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
