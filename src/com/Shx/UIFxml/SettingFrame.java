package com.Shx.UIFxml;

import com.Shx.FileUse.FileClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 编写人：SHX
 * 编写时间：2019/4/6
 * 编写目的：建立设置界面
 * 引用资料：
 * JavaSwing文档
 * https://www.ibm.com/developerworks/cn/java/j-lo-boxlayout/
 */
public class SettingFrame extends JFrame {
    public final int MainUi_Height = 200;
    public final int MainUi_Width = 300;
    public SettingFrame() throws HeadlessException {
        this.setTitle("设置");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.add(PanleFactory.getPanleFactory().SettingFramePanel("本产品应用说明","打开说明",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileClass fileClass = new FileClass("Lnk/hello.txt");
                fileClass.WhatFile();
            }
        }));
//        jPanel.add(PanleFactory.getPanleFactory().SettingFramePanel("开机自启动设置","打开说明"));
//        jPanel.add(PanleFactory.getPanleFactory().SettingFramePanel("快捷方式设置","修改设置"));
        jPanel.add(PanleFactory.getPanleFactory().SettingFramePanel("修改、删除、添加短语", "打开设置", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFrame();
            }
        }));
        this.add(jPanel);
        this.setIconImage(MainFrame.icon.getImage());
        int Screen_height = screenSize.height;
        int Screen_width = screenSize.width;
        this.setSize(MainUi_Width,MainUi_Height);
        this.setResizable(false);
        this.setLocation((Screen_width-MainUi_Width)/2,(Screen_height-MainUi_Height-30)/2);
        this.setVisible(true);
    }
}
