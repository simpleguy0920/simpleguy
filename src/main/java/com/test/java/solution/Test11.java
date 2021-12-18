package com.test.java.solution;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class Test11 {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("1+10*10");
        Object message = exp.getValue();
        System.out.println(message);
    }
}
