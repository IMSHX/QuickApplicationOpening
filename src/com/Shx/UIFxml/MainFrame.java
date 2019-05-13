package com.Shx.UIFxml;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 编写人：SHX
 * 编写时间：2019/4/6
 * 编写目的：建立主要界面
 * 引用资料：
 * JavaSwing文档
 * https://www.ibm.com/developerworks/cn/java/j-lo-boxlayout/
 */
public class MainFrame extends JFrame {
    public final int MainUi_Height = 150;
    public final int MainUi_Width = 300;
    public static final ImageIcon icon = new ImageIcon("Img/IcoImg.jpg");
    private Boolean WindowDisplayStatus = true;

    public MainFrame() throws HeadlessException {
        this.setTitle("桌面小程序");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setIconImage(icon.getImage());
        int Screen_height = screenSize.height;
        int Screen_width = screenSize.width;
        this.setSize(MainUi_Width,MainUi_Height);
        this.setLocation(Screen_width-MainUi_Width,Screen_height-MainUi_Height-30);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
               setWindowDisplayStatus(false);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
//                setWindowDisplayStatus(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        PanleFactory panleFactory = PanleFactory.getPanleFactory();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(panleFactory.MainFrame_NORTH());
        panel.add(panleFactory.MainFrame_CENTER());
        panel.add(panleFactory.MainFrame_SOUTH());
        this.add(panel);
        PlaceTray();
        setWindowDisplayStatus(true);
    }

    public void PlaceTray(){
        SystemTray systemTray = SystemTray.getSystemTray();
        TrayIcon test = new TrayIcon(icon.getImage(), "test", new PopupMenu());
        test.setImageAutoSize(true);
        test.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    setWindowDisplayStatus(!getWindowDisplayStatus());
                    setState(Frame.NORMAL);//恢复默认状态
                }
            }
        });
        try {
            systemTray.add(test);
        } catch (AWTException e) {
            System.out.println("最小化托盘出错");
        }
    }

    private void setWindowDisplayStatus(Boolean WindowDisplayStatus){
        this.WindowDisplayStatus = WindowDisplayStatus;
        setVisible(this.WindowDisplayStatus);

    }

    public Boolean getWindowDisplayStatus() {
        return WindowDisplayStatus;
    }
}
