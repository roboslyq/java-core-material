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
import java.nio.charset.StandardCharsets;

/**
 *
 * 〈直接使用流打印Class内容〉
 * @author luo.yongqian
 * @create 2019/4/23
 * @since 1.0.0
 */
public class PrintClass {
    public static void main(String[] args) throws IOException {
        //Class文件保存路径(根据实际情况自己选定)
        File file = new File("core\\target\\classes\\com\\roboslyq\\core\\common\\HelloWorldBean.class");
        try( FileInputStream isr = new FileInputStream(file);){
//            byte[] fileContext = isr.readAllBytes();
            //此处输入为Class文件原内容(十六进制展示)
//            System.out.println(str2HexStr(fileContext));
            //测试将十六进制转换为普通字符串（翻译），即可以根据需要将读出Class内容进行翻译
            System.out.println(str2HexStr("sayHello2".getBytes()));
        }
    }

    /**
     * 将字符串转换为16进制
     * @param bs
     * @return
     */
    private static String str2HexStr(byte[] bs) {
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

    /**
     * 将十六进制转换成字符串
     * @param srcStr
     * @return
     */
    public static String hexStringToString(String srcStr) {
        if (srcStr == null || srcStr.equals("")) {
            return null;
        }
        srcStr = srcStr.replace(" ", "");
        byte[] baKeyword = new byte[srcStr.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(srcStr.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            srcStr = new String(baKeyword, StandardCharsets.UTF_8);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return srcStr;
    }
}