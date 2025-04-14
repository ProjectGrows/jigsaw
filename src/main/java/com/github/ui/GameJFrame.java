package com.github.ui;

import com.github.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 游戏的主界面，和游戏相关的所有逻辑都写在这个类中
 */
@Slf4j
public class GameJFrame extends JFrame {

    public GameJFrame() throws HeadlessException {
        this("拼图单机版 v1.0");
    }

    public GameJFrame(String title) throws HeadlessException {
        super(title);
        initJFrame();
        initJMenuBar();
        initImage();
        // 让界面显示出来
        this.setVisible(true);
    }

    /**
     * 初始化图片
     */
    private void initImage() {
        try {
            List<JLabel> imageIconList = ImageUtil.loadImages("assets/animal/animal3");
            imageIconList.forEach(icon -> {
                this.getContentPane().add(icon);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始化菜单
     */
    private void initJMenuBar() {
        // 初始化菜单
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createFunctionJMenu());
        jMenuBar.add(createAboutJMenu());
        // 给整个界面设置
        this.setJMenuBar(jMenuBar);
    }

    /**
     * 初始化界面
     */
    private void initJFrame() {
        // 设置宽高
        this.setSize(603, 680);
        // 设置居中显示
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 去掉默认的居中放置
        this.setLayout(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * 创建功能菜单
     */
    private JMenu createFunctionJMenu() {
        JMenu jMenu = new JMenu("功能");
        JMenuItem changeImagesJMenuItem = new JMenuItem("更换图片");
        JMenuItem replayGameJMenuItem = new JMenuItem("重新游戏");
        JMenuItem OneClickClearanceJMenuItem = new JMenuItem("一键通关");
        JMenuItem exitJMenuItem = new JMenuItem("退出");
        jMenu.add(changeImagesJMenuItem);
        jMenu.add(replayGameJMenuItem);
        jMenu.add(OneClickClearanceJMenuItem);
        jMenu.add(exitJMenuItem);
        return jMenu;
    }

    /**
     * 创建关于菜单
     */
    private JMenu createAboutJMenu() {
        JMenu jMenu = new JMenu("关于我们");
        JMenuItem weichatJMenuItem = new JMenuItem("微信公众号");
        jMenu.add(weichatJMenuItem);
        return jMenu;
    }
}
