package com.roboslyq.tu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("all")
public class PropertiesUtil {
    private final static String PATH=System.getProperty("user.dir");
    public  static final Map<String, String> PARAMETERS_MAP=PropertiesUtil.getMap("/config/config.properties");

    static{
        Map<String, String> map = PARAMETERS_MAP;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.printf("%s = %s%n", entry.getKey(), entry.getValue());
        }
    }
    public static Map<String, String> getMap(String path){
        Properties properties = new Properties();
        properties = readFile(PATH+path);
        Map<String, String> map = new HashMap<String, String>((Map) properties);
        return map;
    }

    public static Properties readFile(String path){
        File file = new File(path);
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

}