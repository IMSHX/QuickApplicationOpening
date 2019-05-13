package com.Shx.PropertiesClass;

import com.Shx.Data.MyPublicData;
import com.Shx.Data.PropertiesData;
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
            InputStream in = new BufferedInputStream(new FileInputStream(propertiesName));
            properties.load(in);
            Enumeration enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()){
                Key = (String)enumeration.nextElement();
                Data = properties.getProperty(Key);
                Key_Data.add(new PropertiesData(Key,Data));
//                System.out.println(Key+"="+Data);
                in.close();
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
            in.close();
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
            is.close();
            outputStream.close();
            return true;
        }  catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updata(){
        try {
            OutputStream fileOutputStream = new FileOutputStream(propertiesName);
            for (PropertiesData propertiesData:this.Read_List()){
                properties.remove(propertiesData.getKay());
                properties.store(fileOutputStream,"Delete'"+propertiesData.getKay());
                fileOutputStream.close();
            }
            for (PropertiesData propertiesData:MyPublicData.getMyPublicDataClass().propertiesDataList){
                WriteForData(propertiesData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(String deleteData){
        try {
            System.out.println(deleteData);
            OutputStream fileOutputStream = new FileOutputStream(propertiesName);
            properties.remove(deleteData);
            properties.store(fileOutputStream,"Delete'"+deleteData);
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        PropertiesUse propertiesUse = new PropertiesUse();
        propertiesUse.WriteForData(new PropertiesData("eww","asd"));
        List<PropertiesData> list = propertiesUse.Read_List();
        for(PropertiesData propertiesData:list){
            System.out.println(propertiesData);
        }
        System.out.println("**********************************************");
        propertiesUse.delete("159");
        List<PropertiesData> list2 = propertiesUse.Read_List();
        for(PropertiesData propertiesData:list2){
            System.out.println(propertiesData);
        }
    }
}
