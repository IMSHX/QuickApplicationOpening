package com.Shx.PropertiesClass;
/**
 * 编写人：SHX
 * 编写时间：2019/3/10
 * 编写目的：储存properties文件信息
 */
public class PropertiesData {
    private String Kay;
    private String Data;

    public PropertiesData() {
    }

    public PropertiesData(String kay, String data) {
        Kay = kay;
        Data = data;
    }

    public String getKay() {
        return Kay;
    }

    public void setKay(String kay) {
        Kay = kay;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
