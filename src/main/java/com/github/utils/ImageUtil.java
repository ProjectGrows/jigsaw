package com.github.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ImageUtil {

    public static List<JLabel> loadImages(String directoryPath) throws IOException, URISyntaxException {
        List<JLabel> jLabelList = new ArrayList<>();
        URL resource = ImageUtil.class.getClassLoader().getResource(directoryPath);
        log.info("{}", resource);
        Path dirPath = null;
        if (resource != null) {
            dirPath = Paths.get(resource.toURI());
        }

        if (!Files.exists(dirPath) || !Files.isDirectory(dirPath)) {
            throw new IllegalArgumentException("目录不存在或不是一个有效的目录：" + directoryPath);
        }

        int xPosition = 0; // 初始 X 坐标
        int yPosition = 0; // 初始 Y 坐标
        int imageWidth = 105; // 图片宽度
        int imageHeight = 105; // 图片高度
        int someMaxWidth = 105 * 4;

        try {
            // 收集所有符合条件的文件路径
            List<Path> paths = Files.list(dirPath)
                    .filter(path -> path.toString().toLowerCase().endsWith(".jpg")
                            && !path.toString().contains("all"))
                    .sorted(Comparator.comparingInt(
                            o -> Integer.parseInt(getFileNameWithoutExtension(String.valueOf(o.getFileName())))))
                    .toList();

            for (Path path : paths) {
                ImageIcon imageIcon = new ImageIcon(path.toFile().getAbsolutePath());
                JLabel jLabel = new JLabel(imageIcon);
                // 指定图片的位置
                jLabel.setBounds(xPosition, yPosition, imageWidth, imageHeight);
                jLabelList.add(jLabel);

                // 更新 X 坐标
                xPosition += imageWidth;

                // 如果需要换行，重置 X 坐标并更新 Y 坐标
                if (xPosition >= someMaxWidth) { // someMaxWidth 是你希望的最大宽度
                    xPosition = 0;
                    yPosition += imageHeight;
                }
            }
        } catch (IOException e) {
            // 处理目录迭代异常
            throw new IOException("无法遍历目录: " + directoryPath, e);
        }

        return jLabelList;
    }

    public static String getFileNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(0, dotIndex);
        } else {
            return fileName; // 没有后缀或者后缀在文件名开头/结尾
        }
    }
}
