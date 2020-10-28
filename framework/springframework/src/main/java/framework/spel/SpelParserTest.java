/**
 * Copyright (C), 2015-2020
 * FileName: SpelParserTest
 * Author:   luo.yongqian
 * Date:     2020/10/14 21:30
 * Description:
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2020/10/14 21:30      1.0.0               创建
 */
package framework.spel;

import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 *
 * 〈〉
 * @author roboslyq
 * @date 2020/10/14
 * @since 1.0.0
 */
public class SpelParserTest {
    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();
        String randomPhrase = parser.parseExpression(
                "random number is #{T(framework.spel.Test1).random()}",
                new TemplateParserContext()).getValue(String.class);
        System.out.println(randomPhrase);

        // evals to "Hello World"
        String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();

        double avogadrosNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();

// evals to 2147483647
        int maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();

        boolean trueValue = (Boolean) parser.parseExpression("true").getValue();

        Object nullValue = parser.parseExpression("null").getValue();
    }

    public static void print(Object obj){
        System.out.println("当前值：" + obj);
    }
}
class Test1{
    public static double random(){
        return Math.random();
    }
}