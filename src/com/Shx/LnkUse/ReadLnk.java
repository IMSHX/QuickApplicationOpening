package com.Shx.LnkUse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 编写人：SHX
 * 编写时间：2019/3/9
 * 编写目的：判断是否为lnk文件，并读取Lnk文件获取其中的EXE文件位置
 * 引用资料：
 * https://www.oschina.net/code/snippet_12_274
 * PS:lnk文件应该有自己的一套加密方法，目前无法解析得知，可以确定的是其指向的exe文件位置并不是轻易的保存在其数据流中
 */
public class ReadLnk {
    private File file;
    private boolean is_dir;
    private String real_file;
    private final int file_atts_offset = 0x18;
    private final int shell_offset = 0x4c;
    /**
     * 构造函数获取FileClass中得到初步验证的file
     * @param file
     */
    public ReadLnk(File file) {
        this.file = file;
        try {
            parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lnk文件解析出现问题");
        }
    }

    /**
     *  该函数作用为把两个字节转换成一个短地址，这是一个小尾数，因为它只适用于Intel操作系统。
     * @param bytes
     * @param off
     * @return
     */
    private int bytes2short(byte[] bytes,int off){
        return (bytes[off] & 0xFF) | ((bytes[off + 1] & 0xFF) << 8);
    }

    /**
     * 将字节计数到空字符（0）
     * @param bytes
     * @param off
     * @return
     */
    private String getNullDelimitedString(byte[] bytes, int off) {
        int len = 0;
        while(true) {
            if(bytes[off+len] == 0) {
                break;
            }
            len++;
        }
        return new String(bytes,off,len);
    }

    /**
     * 读取程序
     * @param f
     * @throws Exception
     */
    private void parse(File f) throws Exception {
        /*将整个文件读取到字节缓冲区中*/
        FileInputStream fin = new FileInputStream(f);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buff = new byte[256];
        while(true) {
            int n = fin.read(buff);
            if(n == -1) { break; }
            bout.write(buff,0,n);
        }
        fin.close();
        byte[] link = bout.toByteArray();
        //System.out.println(bout.toString());//调试用

        /*获取标志字节*/
        byte flags = link[0x14];

        /*获取文件属性字节*/
        byte fileatts = link[file_atts_offset];
        byte is_dir_mask = (byte)0x10;
        if((fileatts & is_dir_mask) > 0) {
            is_dir = true;
        } else {
            is_dir = false;
        }

        /*如果shell设置存在，则跳过它们*/
        int shell_len = 0;
        if((flags & 0x1) > 0) {
            /*加号2表示长度标记本身*/
            shell_len = bytes2short(link,shell_offset) + 2;
        }

        /*获取文件设置*/
        int file_start = 0x4c + shell_len;

        /*获取本地卷和本地系统值*/
        int local_sys_off = link[file_start+0x10] + file_start;
        real_file = getNullDelimitedString(link,local_sys_off);
        System.out.println("real filename = " + real_file);
    }

    /**
     * 文件解码信息发送
     * @return
     */
    public String getExeName() {
        return real_file;
    }
}
