package com.test.java;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class ClassLoad {
    static {
        System.out.println("static");
    }

    public ClassLoad() {
        System.out.println("constror");
    }

    public static void main(String[] args) {
        ClassLoad classLoad = new ClassLoad();
        ClassLoad classLoad1 = new ClassLoad();

    }
}
