package com.Shx.FileUse;

import java.io.File;
import java.io.IOException;

/**
 * 编写人：SHX
 * 编写时间：2019/3/9
 * 编写目的：对文件进行处理分类出是否为lnk、exe、url文件，并调用相对的类获取信息
 * 引用资料：
 * JAVA文件处理
 */
public class FileClass {
    private String fileName;
    private File file;

    public FileClass(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }
    public void WhatFile(){
        String[] split = fileName.split("\\.");
        if(split[split.length-1].equals("lnk")||split[split.length-1].equals("Lnk")||split[split.length-1].equals("LNK")){
            System.out.println("为lnk文件调用ReadLnk.java进行解析");
        }else if(split[split.length-1].equals("exe")||split[split.length-1].equals("Exe")||split[split.length-1].equals("EXE")){
            System.out.println("为EXE文件调用ExeOpen.java进行开启");
        }

    }

    public boolean TheFileIsTrue(){
        return file.isFile();
    }

    public boolean setNewFile(){
        if(!TheFileIsTrue()){
            try {
                file.createNewFile();
                System.out.println("创建成功");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("创建失败");
                return false;
            }
        }
        System.out.println("该文件已存在");
        return true;
    }

}
