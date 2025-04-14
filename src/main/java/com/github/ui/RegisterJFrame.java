package com.github.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 注册的主界面，和注册相关逻辑都写在这个类中
 */
public class RegisterJFrame extends JFrame {

    public RegisterJFrame() throws HeadlessException {
        this("拼图注册界面 v1.0");
    }

    public RegisterJFrame(String title) throws HeadlessException {
        super(title);
        // 设置显示
        this.setVisible(true);
        // 设置宽高
        this.setSize(488, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置居中显示
        this.setLocation(x, y);
    }


}
