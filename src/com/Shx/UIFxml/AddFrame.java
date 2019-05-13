package com.Shx.UIFxml;

import com.Shx.PropertiesClass.PropertiesUse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddFrame extends JFrame {
    public final int MainUi_Height = 400;
    public final int MainUi_Width = 500;
    private JPanel jPanel;
    public AddFrame() {
        this.setTitle("修改，添加，删除界面");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int Screen_height = screenSize.height;
        int Screen_width = screenSize.width;
        this.setIconImage(MainFrame.icon.getImage());
        this.setSize(MainUi_Width,MainUi_Height);
        this.setResizable(false);
        this.jPanel = PanleFactory.getPanleFactory().AddFramePanel(this);
        this.add(this.jPanel);
//        this.add(new JLabel("hello"));
        this.setLocation((Screen_width-MainUi_Width)/2,(Screen_height-MainUi_Height-30)/2);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new PropertiesUse().updata();
            }
        });
    }
    public void MyUpData(){
        this.remove(this.jPanel);
        this.jPanel = PanleFactory.getPanleFactory().AddFramePanel(this);
        this.add(this.jPanel);
        this.invalidate();
        this.repaint();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new AddFrame();
    }
}
