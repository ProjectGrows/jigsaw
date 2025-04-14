package com.github.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 登录的主界面，和登录相关逻辑都写在这个类中
 */
public class LoginJFrame extends JFrame {

    public LoginJFrame() throws HeadlessException {
        this("拼图登录界面 v1.0");
    }

    public LoginJFrame(String title) throws HeadlessException {
        super(title);
        // 设置显示
        this.setVisible(true);
        // 设置宽高
        this.setSize(488, 430);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置居中显示
        this.setLocation(x, y);
    }


}
