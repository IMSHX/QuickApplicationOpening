package com.Shx.PropertiesClass;

import com.Shx.FileUse.FileClass;

import java.io.*;
import java.util.*;
/**
 * 编写人：SHX
 * 编写时间：2019/3/10
 * 编写目的：配置properties配置文件，实现添加查询等功能
 */
public class PropertiesUse {
    private final String propertiesName = "FileName.properties";
    private List<PropertiesData> Key_Data;
    private Properties properties;

    /**
     * 构造函数，新建Properties对象、list对象
     */
    public PropertiesUse() {
        properties = new Properties();
        FileClass fileClass = new FileClass(propertiesName);
        this.Key_Data = new ArrayList<>();
        fileClass.setNewFile();
    }

    /**
     * 读取properties文件中全部对象生成相应的PropertiesData对象输入队列
     * @return 读取到的队列
     */
    public List<PropertiesData> Read_List(){
        String Key;
        String Data;
        try {
            properties.load(new FileInputStream(propertiesName));
            Enumeration enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()){
                Key = (String)enumeration.nextElement();
                Data = properties.getProperty(Key);
                Key_Data.add(new PropertiesData(Key,Data));
                System.out.println(Key+"="+Data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取失败");
        }
        return Key_Data;
    }

    /**
     * 按Kay值进行检索
     * @param Key
     * @return PropertiesData
     */
    public PropertiesData Read_Key(String Key){
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(propertiesName));
            properties.load(in);
            String value = properties.getProperty(Key);
            System.out.println(Key + " = " + value);
            return new  PropertiesData(Key,value);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 写入数据
     * @param NewData
     * @return
     */
    public boolean WriteForData(PropertiesData NewData){
        try {
            InputStream is = new FileInputStream(propertiesName);
            properties.load(is);
            OutputStream outputStream = new FileOutputStream(propertiesName);
            properties.setProperty(NewData.getKay(),NewData.getData());
            properties.store(outputStream,"Update " + NewData.getKay() + " name");//变量一为添加文件，变量二为注解
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
