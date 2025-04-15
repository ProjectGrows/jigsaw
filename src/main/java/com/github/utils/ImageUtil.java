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
import java.util.Objects;
import java.util.stream.Stream;

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

        if (Objects.isNull(dirPath) || !Files.exists(dirPath) || !Files.isDirectory(dirPath)) {
            throw new IllegalArgumentException("目录不存在或不是一个有效的目录：" + directoryPath);
        }

        int xPosition = 0; // 初始 X 坐标
        int yPosition = 0; // 初始 Y 坐标
        int imageWidth = 105; // 图片宽度
        int imageHeight = 105; // 图片高度
        int someMaxWidth = 105 * 4;

        try (Stream<Path> list = Files.list(dirPath)) {
            List<Path> paths = list.filter(path -> path.toString().toLowerCase().endsWith(".jpg")
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

    public static JLabel[][] loadImagesAsGrid(String directoryPath, int rows, int cols)
            throws IOException, URISyntaxException {
        // 加载图片列表
        List<JLabel> jLabelList = loadImages(directoryPath);

        // 创建二维数组
        JLabel[][] grid = new JLabel[rows][cols];

        // 填充二维数组
        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (index < jLabelList.size()) {
                    grid[row][col] = jLabelList.get(index++);
                } else {
                    // 如果图片不足，填充空的 JLabel
                    JLabel jLabel = new JLabel();
                    jLabel.setBounds(315, 315, 105, 105);
                    grid[row][col] = jLabel;
                }
            }
        }
        return grid;
    }

    /**
     * 获取去除扩展名的文件名
     *
     * @param fileName 文件名
     * @return 去除扩展名的文件名
     */
    public static String getFileNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(0, dotIndex);
        } else {
            return fileName;
        }
    }
}
