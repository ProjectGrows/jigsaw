package com.github.ui;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * 游戏的主界面
 */
@Slf4j
public class GameJFrame extends JFrame implements KeyListener {

    private final int[][] data = new int[4][4];
    // 定义一个二维数组，存储正确的数据
    int[][] win = {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 0}
    };
    // 0 的横坐标
    private int x = 0;
    // 0 的纵坐标
    private int y = 0;
    // 定义变量用来统计步数
    private int step = 0;

    public GameJFrame() throws HeadlessException {
        this("拼图单机版 v1.0");
    }

    public GameJFrame(String title) throws HeadlessException {
        super(title);
        initJFrame();
        initJMenuBar();
        initData();
        initImage();
        // 让界面显示出来
        this.setVisible(true);
    }

    /**
     * 初始化数据（打乱）
     */
    private void initData() {
        log.info("{}", "initData()");
        // 1.定义一个一维数组
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        // 2.打乱数组中的数据的顺序
        // 遍历数组，得到每一个元素，拿着每一个元素跟随机索引上的数据进行交换
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            // 获取到随机索引
            int index = r.nextInt(tempArr.length);
            // 拿着遍历到的每一个数据，跟随机索引上的数据进行交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        // 4.给二维数组添加数据
        // 遍历一维数组tempArr得到每一个元素，把每一个元素依次添加到二维数组当中
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    /**
     * 初始化图片
     */
    @SneakyThrows
    private void initImage() {
        log.info("{}", "initImage()");
        // 清空原本已经出现的所有图片
        this.getContentPane().removeAll();
        // 如果胜利
        if (victory()) {
            URL resource = this.getClass().getClassLoader().getResource("assets/win.png");
            File file = new File(Objects.requireNonNull(resource).toURI());
            JLabel winJLabel = new JLabel(new ImageIcon(file.getAbsolutePath()));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }
        // 步数
        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);
        // 添加图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                ImageIcon imageIcon;
                if (num == 0) {
                    imageIcon = new ImageIcon();
                } else {
                    URL resource =
                            this.getClass().getClassLoader().getResource("assets/animal/animal3/" + num + ".jpg");
                    File file = new File(Objects.requireNonNull(resource).toURI());
                    imageIcon = new ImageIcon(file.getAbsolutePath());
                }
                JLabel jLabel = new JLabel(imageIcon);
                // 指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                // 给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                this.getContentPane().add(jLabel);
            }
        }
        // 添加背景图片
        URL bgURL = this.getClass().getClassLoader().getResource("assets/background.png");
        JLabel bgJLabel =
                new JLabel(new ImageIcon(new File(Objects.requireNonNull(bgURL).toURI()).getAbsolutePath()));
        // 指定图片位置
        bgJLabel.setBounds(40, 40, 508, 560);
        this.getContentPane().add(bgJLabel);
        // 重新布局和重绘
        this.getContentPane().revalidate();
        // 刷新一下界面
        this.getContentPane().repaint();
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
        // 设置键盘监听
        this.addKeyListener(this);
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
        replayGameJMenuItem.addActionListener(e -> {
            step = 0;
            initData();
            initImage();
        });
        JMenuItem OneClickWinJMenuItem = new JMenuItem("一键通关");
        OneClickWinJMenuItem.addActionListener(e -> {
            step = 0;
            for (int i = 0; i < win.length; i++) {
                System.arraycopy(win[i], 0, data[i], 0, win[i].length);
            }
            initImage();
        });
        JMenuItem exitJMenuItem = new JMenuItem("退出");
        exitJMenuItem.addActionListener(e -> System.exit(0));

        jMenu.add(changeImagesJMenuItem);
        jMenu.add(replayGameJMenuItem);
        jMenu.add(OneClickWinJMenuItem);
        jMenu.add(exitJMenuItem);
        return jMenu;
    }

    /**
     * 创建关于菜单
     */
    @SneakyThrows
    private JMenu createAboutJMenu() {
        JMenu jMenu = new JMenu("关于我们");
        JMenuItem weichatJMenuItem = new JMenuItem("微信公众号");
        weichatJMenuItem.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                URL url = this.getClass().getClassLoader().getResource("assets/about.png");
                JLabel jLabel = new JLabel(
                        new ImageIcon(new File(Objects.requireNonNull(url).toURI()).getAbsolutePath()));
                // 指定图片位置
                jLabel.setBounds(40, 40, 258, 258);
                GameJFrame.super.getContentPane().add(jLabel);
            }
        });
        jMenu.add(weichatJMenuItem);
        return jMenu;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @SneakyThrows
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        log.info("{}", keyCode);
        if (65 == keyCode) {
            // 清空原本出现的图片
            this.getContentPane().removeAll();
            // 显示完整图片
            URL resource = this.getClass().getClassLoader().getResource("assets/animal/animal3/all.jpg");
            File file = new File(Objects.requireNonNull(resource).toURI());
            JLabel allJLabel = new JLabel(new ImageIcon(file.getAbsolutePath()));
            allJLabel.setBounds(83, 134, 420, 420);
            this.getContentPane().add(allJLabel);
            // 添加背景图片
            URL bgURL = this.getClass().getClassLoader().getResource("assets/background.png");
            JLabel bgJLabel = new JLabel(
                    new ImageIcon(new File(Objects.requireNonNull(bgURL).toURI()).getAbsolutePath()));
            // 指定图片位置
            bgJLabel.setBounds(40, 40, 508, 560);
            this.getContentPane().add(bgJLabel);
            // 重新布局和重绘
            this.getContentPane().revalidate();
            // 刷新一下界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        log.info("{}", Arrays.deepToString(data));
        // 判断游戏是否胜利，如果胜利，此方法需要直接结束
        if (victory()) {
            return;
        }
        int keyCode = e.getKeyCode();
        // 38 上 37 左 39 右  40 下
        log.info(" keyReleased {}", keyCode);
        switch (keyCode) {
            case 37 -> {
                // 将空白方块右方的图片向左移动
                log.info("{}", "左");
                if (y == 3) {
                    return;
                }
                swapImages(x, y, x, y + 1);
                y++;
                step++;
                initImage();
                log.info("{}", Arrays.deepToString(data));
            }
            case 38 -> {
                // 将空白方块下方的图片向上移动
                log.info("{}", "上");
                if (x == 3) {
                    return;
                }
                swapImages(x, y, x + 1, y);
                x++;
                step++;
                initImage();
                log.info("{}", Arrays.deepToString(data));
            }
            case 39 -> {
                // 将空白方块左方的图片向右移动
                log.info("{}", "右");
                if (y == 0) {
                    return;
                }
                swapImages(x, y, x, y - 1);
                y--;
                step++;
                initImage();
                log.info("{}", Arrays.deepToString(data));
            }
            case 40 -> {
                // 将空白方块上方的图片向下移动
                log.info("{}", "下");
                if (x == 0) {
                    return;
                }
                swapImages(x, y, x - 1, y);
                x--;
                step++;
                initImage();
                log.info("{}", Arrays.deepToString(data));
            }
            case 65 -> {
                initImage();
                log.info("{}", Arrays.deepToString(data));
            }
        }
    }

    /**
     * 判断 data 数组和 win 数组中的数据是否一致
     * @return true/false
     */
    public boolean victory() {
        for (int i = 0; i < data.length; i++) {

            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 交换两个图片的位置
     *
     * @param row1 第一个图片的行索引
     * @param col1 第一个图片的列索引
     * @param row2 第二个图片的行索引
     * @param col2 第二个图片的列索引
     */
    private void swapImages(int row1, int col1, int row2, int col2) {
        // 交换数据
        int tempData = data[row1][col1];
        data[row1][col1] = data[row2][col2];
        data[row2][col2] = tempData;
    }
}
