package com.Shx.UIFxml;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements KeyListener {
    public final int MainUi_Height = 400;
    public final int MainUi_Width = 500;
    public static final ImageIcon icon = new ImageIcon("Img/IcoImg.jpg");
    private Boolean WindowDisplayStatus = true;

    public MainFrame() throws HeadlessException {
        this.setTitle("桌面小程序");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.setIconImage(icon.getImage());
        int Screen_height = screenSize.height;
        int Screen_width = screenSize.width;
        this.setSize(MainUi_Width,MainUi_Height);
        this.setLocation(Screen_width-MainUi_Width,Screen_height-MainUi_Height);
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
                setWindowDisplayStatus(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        PlaceTray();
        this.addKeyListener(this);
        setWindowDisplayStatus(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode()+"KT");
        if((e.getKeyCode()==17)){
//            requestFocus();//焦点获得
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode()+"KP");

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if((e.getKeyCode() == KeyEvent.VK_ENTER&&e.isControlDown())){
            setWindowDisplayStatus(!getWindowDisplayStatus());
        }
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
