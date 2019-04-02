package com.Shx.ExeUse;

import java.io.IOException;
/**
 * 编写人：SHX
 * 编写时间：2019/3/10
 * 编写目的：启动.exe文件
 */
public class ExeOpen {
    public void OpenExe(String fileName){
        try {
            Runtime.getRuntime().exec(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
