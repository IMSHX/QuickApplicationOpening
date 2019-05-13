package com.Shx.FileUse;

import com.Shx.ExeUse.ExeOpen;
import com.Shx.LnkUse.ReadLnk;
import com.Shx.TxtUse.ReadTxt;
import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
            new ExeOpen().OpenExe(new ReadLnk(file).getExeName());
        }else if(split[split.length-1].equals("exe")||split[split.length-1].equals("Exe")||split[split.length-1].equals("EXE")){
            System.out.println("为EXE文件调用ExeOpen.java进行开启");
            new ExeOpen().OpenExe(fileName);
        }else if(split[split.length-1].equals("txt")||split[split.length-1].equals("Txt")||split[split.length-1].equals("TXT")){
            System.out.println("为EXE文件调用TxtOpen.java进行开启");
            new ReadTxt(fileName);
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
        return true;
    }
    public Boolean FileCopy(){
        File fileaddress = new File("Lnk");
       if(!fileaddress.isDirectory()){
           fileaddress.mkdir();
       }
        File Newfile = new File(fileaddress.getAbsolutePath() + "\\" + this.file.getName());
       if(Newfile.exists()){
           Newfile.delete();
       }
        try {
            if(Newfile.createNewFile()){
                FileInputStream fileInputStream = new FileInputStream(this.file);
                FileOutputStream fileOutputStream = new FileOutputStream(Newfile);
                byte[] bytes = new byte[1024];
                int i = 0;
                while ((i = fileInputStream.read(bytes))>0){
                    fileOutputStream.write(bytes);
                }
                fileInputStream.close();
                fileOutputStream.close();
            }else {
                System.out.println("文件创建失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static void main(String[] args) {
    }
}
