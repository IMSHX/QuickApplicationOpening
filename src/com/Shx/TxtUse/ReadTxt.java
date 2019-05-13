package com.Shx.TxtUse;

import java.io.File;
import java.io.IOException;

public class ReadTxt {
    private String FileName;

    public ReadTxt(String fileName) {
        FileName = fileName;
        File file = new File(fileName);
        System.out.println(file.getAbsolutePath());
        try {
            Process exec = Runtime.getRuntime().exec("cmd.exe  /c notepad "+ file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Txt文件打开失败");
        }
    }
}
