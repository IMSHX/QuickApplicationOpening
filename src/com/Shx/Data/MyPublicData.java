package com.Shx.Data;

import com.Shx.PropertiesClass.PropertiesUse;
import com.Shx.UIFxml.AddFrame;

import javax.swing.*;
import java.util.List;

/**
 * 该类用于储存程序中的共享数据
 */
public class MyPublicData {
    private static MyPublicData myPublicData = null;

    public List<PropertiesData> propertiesDataList = null;
    /*********************************************************/
    public JTextField OpenKey = null;
    /*********************************************************/
    public String fileaddress = "";
    public JTextField FileKey = null;
    public AddFrame addFrame = null;
    public MyTableData myTableData = null;
    /*********************************************************/

    private MyPublicData() {
        propertiesDataList =new PropertiesUse().Read_List();
    }
    public static MyPublicData getMyPublicDataClass(){
        if (myPublicData == null){
            myPublicData = new MyPublicData();
        }
        return myPublicData;
    }
}
