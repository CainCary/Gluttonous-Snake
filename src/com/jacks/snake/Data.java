package com.jacks.snake;

import javax.swing.*;
import java.net.URL;

/**
 * 游戏的数据存放类
 */
class Data {
    /**
     * 头部图片
     * URL
     * 图片
     */
    //顶部广告
    private static URL headerUrl = Data.class.getResource("/statics/header.png");
    static ImageIcon header = new ImageIcon(headerUrl);

    //蛇头
    private static URL upUrl = Data.class.getResource("/statics/up.png");
    private static URL downUrl = Data.class.getResource("/statics/down.png");
    private static URL leftUrl = Data.class.getResource("/statics/left.png");
    private static URL rightUrl = Data.class.getResource("/statics/right.png");
    static ImageIcon up = new ImageIcon(upUrl);
    static ImageIcon down = new ImageIcon(downUrl);
    static ImageIcon left = new ImageIcon(leftUrl);
    static ImageIcon right = new ImageIcon(rightUrl);

    // 身体
    private static URL bodyUrl = Data.class.getResource("/statics/body.png");
    static ImageIcon body = new ImageIcon(bodyUrl);

    //食物
    private static URL foodUrl = Data.class.getResource("/statics/food.png");
    static ImageIcon food = new ImageIcon(foodUrl);
}
