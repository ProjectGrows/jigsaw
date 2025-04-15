package com.github.ui;

import com.github.utils.ImageUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * 游戏的主界面，和游戏相关的所有逻辑都写在这个类中
 */
@Slf4j
public class GameJFrame extends JFrame {

    private final Random random = new SecureRandom();
    private JLabel[][] imageJLabelArrArr;

    public GameJFrame() throws HeadlessException {
        this("拼图单机版 v1.0");
    }

    public GameJFrame(String title) throws HeadlessException {
        super(title);
        initJFrame();
        initJMenuBar();
        loadImage();
        initImage();
        // 让界面显示出来
        this.setVisible(true);
    }

    /**
     * 初始化图片
     */
    private void initImage() {

        JLabel[] imageJLabelArr =
                Arrays.stream(imageJLabelArrArr).flatMap(Arrays::stream).toArray(JLabel[]::new);
        for (int i = 0; i < imageJLabelArr.length; i++) {
            int index = random.nextInt(imageJLabelArr.length);
            // 交换 JLabel 对象
            JLabel temp = imageJLabelArr[index];
            imageJLabelArr[index] = imageJLabelArr[i];
            imageJLabelArr[i] = temp;
            // 更新交换后的位置
            int tempX = imageJLabelArr[index].getX();
            int tempY = imageJLabelArr[index].getY();
            imageJLabelArr[index].setBounds(
                    imageJLabelArr[i].getX(),
                    imageJLabelArr[i].getY(),
                    imageJLabelArr[index].getWidth(),
                    imageJLabelArr[index].getHeight());
            imageJLabelArr[i].setBounds(tempX, tempY, imageJLabelArr[i].getWidth(), imageJLabelArr[i].getHeight());
        }

        // 再将转换之后的一维数组设置到二维数组中
        int index = 0;
        for (int i = 0; i < imageJLabelArrArr.length; i++) {
            for (int j = 0; j < imageJLabelArrArr[i].length; j++) {
                imageJLabelArrArr[i][j] = imageJLabelArr[index++];
            }
        }

        // 将图片组件再添加到 ContentPane 中
        for (JLabel[] imageJLabel : imageJLabelArrArr) {
            for (JLabel jLabel : imageJLabel) {
                this.getContentPane().add(jLabel);
            }
        }
    }

    /**
     * 加载图片
     */
    @SneakyThrows
    private void loadImage() {
        imageJLabelArrArr = ImageUtil.loadImagesAsGrid("assets/animal/animal3", 4, 4);
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
        this.setLocationRelativeTo(null);
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
