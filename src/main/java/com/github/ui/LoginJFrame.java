package com.github.ui;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录的主界面
 */
@Slf4j
public class LoginJFrame extends JFrame implements ActionListener {

    public LoginJFrame() throws HeadlessException {
        this("拼图登录界面 v1.0");
    }

    public LoginJFrame(String title) throws HeadlessException {
        super(title);
        initJFrame();
        initButton();
        // 让界面显示出来
        this.setVisible(true);
    }

    private void initButton() {
        JButton jBtn1 = new JButton("按钮1");
        jBtn1.setBounds(0, 0, 100, 50);
        jBtn1.addActionListener(this);
        this.getContentPane().add(jBtn1);
    }

    /**
     * 初始化界面
     */
    private void initJFrame() {
        // 设置宽高
        this.setSize(488, 430);
        // 设置居中显示
        this.setLocationRelativeTo(null);
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 去掉默认的居中放置
        this.setLayout(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        log.info("{}", source);
    }
}
