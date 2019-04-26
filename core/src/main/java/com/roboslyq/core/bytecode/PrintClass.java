/**
 * Copyright (C), 2015-2019
 * FileName: PrintClass
 * Author:   luo.yongqian
 * Date:     2019/4/23 14:38
 * Description: 直接使用流打印Class内容
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/4/23 14:38      1.0.0               创建
 */
package com.roboslyq.core.bytecode;

import java.io.*;

/**
 *
 * 〈直接使用流打印Class内容〉
 * @author luo.yongqian
 * @create 2019/4/23
 * @since 1.0.0
 */
public class PrintClass {
    public static void main(String[] args) throws IOException {
        File file = new File("core\\target\\classes\\com\\roboslyq\\core\\common\\HelloWorldBean.class");
        FileInputStream isr = new FileInputStream(file);
        byte[] fileContext = isr.readAllBytes();
        System.out.println(str2HexStr(fileContext));

        byte[] stringByte = "sayHello2".getBytes();
        System.out.println(str2HexStr(stringByte));
        System.out.println(str2HexStr("sayHello2".getBytes()));
        System.out.println(hexStringToString("4C696E654E756D6265725461626C65"));


    }
    public static String str2HexStr(byte[] bs) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }

    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }
}