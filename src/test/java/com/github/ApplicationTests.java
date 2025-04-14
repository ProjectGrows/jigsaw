package com.github;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

class ApplicationTests {

    @Test
    void testMain() {
        // 创建一个游戏的主界面
        JFrame gameFrame = new JFrame();
        gameFrame.setSize(new Dimension(603,680));
        gameFrame.setVisible(true);
    }

}
