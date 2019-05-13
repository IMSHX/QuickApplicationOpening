package com.Shx.UIFxml;

import com.Shx.Data.MyPublicData;
import com.Shx.Data.MyTableData;
import com.Shx.Data.PropertiesData;
import com.Shx.FileUse.FileClass;
import com.Shx.PropertiesClass.PropertiesUse;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

/**
 * 编写人：SHX
 * 编写时间：2019/4/6
 * 编写目的：生成对应的容器
 * 引用资料：
 * JavaSwing文档
 */
public class PanleFactory {
    private static PanleFactory panleFactory;
    private MyPublicData myPublicData;

    private PanleFactory() {
        myPublicData = MyPublicData.getMyPublicDataClass();
    }

    public static PanleFactory getPanleFactory() {
        if (panleFactory == null) {
            panleFactory = new PanleFactory();
        }
        return panleFactory;
    }

    public JPanel MainFrame_NORTH() {
        JPanel jPanel = new JPanel();
        JLabel Tips = new JLabel("请输入要打开的文件对应短语:");
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel.setBackground(Color.white);
        jPanel.add(Tips);
        return jPanel;
    }

    public JPanel MainFrame_CENTER() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel.setBackground(Color.white);
        JTextField AppName = new JTextField(15);
        myPublicData.OpenKey = AppName;
        AppName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        jPanel.add(AppName);
        return jPanel;
    }

    public JPanel MainFrame_SOUTH() {
        JPanel jPanel = new JPanel();
        JButton Setting = new JButton("设置");
        Setting.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingFrame();
            }
        });
        JButton Open = new JButton("开启");
        Open.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (PropertiesData propertiesData:myPublicData.propertiesDataList){
                    if(myPublicData.OpenKey.getText().equals(propertiesData.getKay())){
                        new FileClass(propertiesData.getData()).WhatFile();
                        break;
                    }
                }
            }
        });
        Setting.setSize(100, 50);
        Open.setSize(100, 50);
        jPanel.add(Setting);
        jPanel.add(Open);
        jPanel.setBackground(Color.white);
        return jPanel;
    }

    public JPanel SettingFramePanel(String Explain_data, String data, AbstractAction abstractAction) {
        JPanel jPanel = new JPanel();
        JLabel Explain = new JLabel(Explain_data);
        JButton Open = new JButton(data);
        Open.addActionListener(abstractAction);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        jPanel.add(Box.createHorizontalStrut(10));
        jPanel.add(Explain);
        jPanel.add(Box.createHorizontalGlue());
        jPanel.add(Open);
        jPanel.add(Box.createHorizontalStrut(10));
        return jPanel;
    }

    /**
     * 添加界面
     * @return
     */
    public JPanel AddFramePanel(AddFrame addFrame) {
        myPublicData.addFrame = addFrame;
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        List<PropertiesData> propertiesDataList = myPublicData.propertiesDataList;
        String[] head = {"选择", "编号", "对应短语", "文件地址"};//,"删除","修改"
        Object[][] Data = new Object[propertiesDataList.size()][];
        int number = 0;
        for (PropertiesData propertiesData : propertiesDataList) {
            Data[number] = new Object[]{false, number, propertiesData.getKay(), propertiesData.getData()};//,"删除","修改"
            number++;
        }
        Class[] tryArray = {Boolean.class, Integer.class, String.class, String.class};
        /**********************************************/
        MyTableData myTableData = new MyTableData(head, Data, tryArray);
        myPublicData.myTableData = myTableData;
        JTable jTable = new JTable(myTableData);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(320);
        jTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        /**********************************************/
        JPanel deleteAndmodify = new JPanel();
        deleteAndmodify.setLayout(new BoxLayout(deleteAndmodify, BoxLayout.X_AXIS));
        JButton delete = new JButton("删除");
        delete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0;i<myPublicData.myTableData.getObjectsLength();i++){
                    System.out.println("Try:"+myPublicData.myTableData.getValueAt(i,0));
                    if((boolean)myPublicData.myTableData.getValueAt(i,0)==true){
                        System.out.println("Try:"+myPublicData.myTableData.getOldValueAt(i).toString());
                        PropertiesData oldValueAt = myPublicData.myTableData.getOldValueAt(i);
                        File file = new File(oldValueAt.getData());
                        file.delete();
                        myPublicData.propertiesDataList.remove(i);
                    }
                }
                myPublicData.addFrame.MyUpData();
            }
        });
        JButton modeify = new JButton("修改");
        modeify.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0;i<myPublicData.myTableData.getObjectsLength();i++){
                    System.out.println("Try:"+myPublicData.myTableData.getValueAt(i,0));
                    if((boolean)myPublicData.myTableData.getValueAt(i,0)==true){
                        System.out.println("Try:"+myPublicData.myTableData.getNewValueAt(i).toString());
                        myPublicData.propertiesDataList.remove(i);
                        myPublicData.propertiesDataList.add(i,myPublicData.myTableData.getNewValueAt(i));
                    }
                }
            }
        });
        deleteAndmodify.add(Box.createHorizontalGlue());
        deleteAndmodify.add(delete);
        deleteAndmodify.add(Box.createHorizontalGlue());
        deleteAndmodify.add(modeify);
        deleteAndmodify.add(Box.createHorizontalGlue());
        /**********************************************/
        JPanel FileChoice = new JPanel();
        FileChoice.setLayout(new BoxLayout(FileChoice, BoxLayout.X_AXIS));
        JTextField FileKey = new JTextField();
        myPublicData.FileKey = FileKey;
        JLabel label = new JLabel("输入快捷短语");
        JButton Choice = new JButton("文件选择");
        Choice.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setMultiSelectionEnabled(false);
                jFileChooser.showOpenDialog(Choice);
                myPublicData.fileaddress = jFileChooser.getSelectedFile().getAbsolutePath()!=null?jFileChooser.getSelectedFile().getAbsolutePath():null;
                System.out.println( myPublicData.fileaddress);
            }
        });
        JButton ReadyAdd = new JButton("添加");
        ReadyAdd.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myPublicData.FileKey == null){
                    System.out.println("未加载成功");
                }else if(new PropertiesUse().Read_Key(myPublicData.FileKey.getText()) == null){
                    System.out.println("该短语已存在");
                }
                else if ("".equals( myPublicData.fileaddress)) {
                    System.out.println("文件不存在");
                } else {
                    FileClass fileClass = new FileClass( myPublicData.fileaddress);
                    fileClass.FileCopy();
//                    new PropertiesUse().WriteForData(new PropertiesData(myPublicData.FileKey.getText(),"Lnk/"+fileClass.getFile().getName()));
                    myPublicData.propertiesDataList.add(new PropertiesData(myPublicData.FileKey.getText(),"Lnk/"+fileClass.getFile().getName()));
                    myPublicData.addFrame.MyUpData();
//                    System.out.println(myPublicData.FileKey.getText()+":"+"Lnk/"+fileClass.getFile().getName());
                }
            }
        });
        FileChoice.add(label);
        FileChoice.add(FileKey);
        FileChoice.add(Choice);
        FileChoice.add(ReadyAdd);
        /*********************************************/
        jPanel.add(jScrollPane);
        jPanel.add(Box.createVerticalStrut(5));
        jPanel.add(deleteAndmodify);
        jPanel.add(Box.createVerticalStrut(5));
        jPanel.add(FileChoice);
        jPanel.add(Box.createVerticalStrut(5));
        /**********************************************/
        return jPanel;
    }

    public JPanel ButtonPanel(int Number, PropertiesData propertiesData) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setPreferredSize(new Dimension(500, 22));
        jPanel.setBackground(Color.black);
        JTextField Text_Number = new JTextField();
        JTextField Text_Key = new JTextField();
        JTextField Text_Data = new JTextField();
        Text_Number.setBounds(0, 1, 79, 20);
        Text_Key.setBounds(80, 1, 78, 20);
        Text_Data.setSize(79, 20);
        jPanel.add(Text_Data);
        jPanel.add(Text_Data);
        jPanel.add(Text_Data);
        return jPanel;
    }

//    public JPanel ButtonPanel(PropertiesData propertiesData){
//        JPanel ButtonPanel = new JPanel();
//        ButtonPanel.setLayout(new BoxLayout(ButtonPanel,BoxLayout.X_AXIS));
//        JButton delete = new JButton("删除");
//        JButton modify = new JButton("修改");
//        ButtonPanel.add(Box.createHorizontalStrut(2));
//        ButtonPanel.add(delete);
//        ButtonPanel.add(Box.createHorizontalStrut(2));
//        ButtonPanel.add(modify);
//        ButtonPanel.add(Box.createHorizontalStrut(2));
//        return ButtonPanel;
//    }

//    private class ButtonPanel implements TableCellRenderer{
//        private JPanel jPanel;
//        private JButton jButton;
//        private int num;
//
//        public ButtonPanel() {
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            return null;
//        }
//    }
}
