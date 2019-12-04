package com.jacks.snake;

import javax.swing.*;

/**
 * 启动类main
 */
public class StartGames {
    public static void main(String[] args) {
        /**
         绘制一个游戏窗口
         */
        JFrame frame = new JFrame("贪吃蛇小游戏   作者：赤魂丶");        //窗口标题
        frame.setBounds(10,10,900,720);   //设置窗口的大小
        frame.setResizable(false);                           //窗口不可以改变
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置关闭事件，游戏关闭，程序结束
        frame.add(new GamePanel());//添加面板
        frame.setVisible(true);//展示窗口
    }
}
