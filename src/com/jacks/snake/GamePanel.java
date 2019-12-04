package com.jacks.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * 游戏的业务逻辑类，制作 JPanel 面板
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    /*
    蛇的相关属性
     */
    private int length;                     //蛇长度
    private int[] snakeX = new int[600];    //蛇的X坐标
    private int[] snakeY = new int[500];    //蛇的Y坐标
    private String fx;                      //蛇头方向
    /*
    食物的相关属性
     */
    private int foodX;
    private int foodY;
    private Random random = new Random();
    /*
    系统相关属性
     */
    private int speed;
    private Timer timer = new Timer(speed, this);//定时器
    private boolean isStart = false;        //游戏是否开始
    private int isFail = 0;         //游戏是否失败,默人不失败
    private int score;                      //分数系统
    private int scoreM = 0;                 //历史最高分

    /**
     * 初始化函数
     */
    private void init() {
        speed = 100;
        timer.setDelay(speed);
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(24);
        score = 0;
        fx = "R";

    }

    /**
     * 构造函数
     */
    GamePanel() {
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();//定时器启动
    }

    @Override
    protected void paintComponent(Graphics g) {//TODO：画静态界面
        super.paintComponent(g);            //清屏
        this.setBackground(Color.BLACK);    //背景颜色
        Data.header.paintIcon(this, g, 25, 11);//绘制头部的广告栏
        g.fillRect(25, 75, 850, 600);//绘制游戏区域

        switch (fx) {//TODO：根据控制画蛇头方向
            case "R":
                Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "L":
                Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "U":
                Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "D":
                Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }
        for (int i = 1; i < length; i++) {//TODO：根据长度画蛇的身体
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        Data.food.paintIcon(this, g, foodX, foodY);//TODO：画食物
        // 画积分
        g.setColor(Color.WHITE);//TODO：画计分系统
        g.setFont(new Font("华文楷体", Font.BOLD, 18));
        g.drawString("当前长度：" + length, 520, 35);
        g.drawString("当前分数：" + score, 370, 35);
        if (score >= scoreM) scoreM = score;
        g.drawString("历史最高分：" + scoreM, 370, 50);
        if (!isStart) {//TODO：用字符串提示游戏开始
            g.setColor(Color.RED);
            g.setFont(new Font("华文楷体", Font.BOLD, 45));//字体、加粗、大小
            g.drawString("按下空格开始游戏", 300, 300);
        }
        if (isFail == 1) {//TODO：用字符串提示游戏失败
            g.setColor(Color.RED);
            g.setFont(new Font("华文楷体", Font.BOLD, 40));//字体、加粗、大小
            g.drawString("游戏失败，你啃到自己了，按空格重新开始!", 35, 300);
        } else if (isFail == 2) {//TODO：提示游戏失败
            g.setColor(Color.RED);
            g.setFont(new Font("华文楷体", Font.BOLD, 40));//字体、加粗、大小
            g.drawString("游戏失败，您撞到墙了，按空格重新开始!", 35, 300);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下，弹起
    }

    // TODO：监听键盘输入
    // 按下不释放
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();//记录按键
        if (keyCode == KeyEvent.VK_SPACE) {//TODO：按下空格暂停或重启游戏
            if (isFail != 0) {//失败后按空格重启
                isFail = 0;
                init();
            } else {//为失败按空格为暂停
                isStart = !isStart;
            }
            repaint();//刷新界面
        }
        if (keyCode == KeyEvent.VK_LEFT && !fx.equals("R")) {//TODO：用键盘控制移动方向
            fx = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT && !fx.equals("L")) {
            fx = "R";
        } else if (keyCode == KeyEvent.VK_UP && !fx.equals("D")) {
            fx = "U";
        } else if (keyCode == KeyEvent.VK_DOWN && !fx.equals("U")) {
            fx = "D";
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //释放某个键
    }

    @Override
    public void actionPerformed(ActionEvent e) {//TODO：控制游戏状态
        if (isStart && isFail == 0) {//TODO：游戏开启并且没有失败
            for (int i = length - 1; i > 0; i--) {//TODO：移动身体
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            switch (fx) {//TODO：判断是否碰到边界
                case "R":
                    snakeX[0] = snakeX[0] + 25;
                    if (snakeX[0] > 850) {
                        isFail = 2;
                        init();
                        // snakeX[0] = 25;
                    }
                    break;
                case "L":
                    snakeX[0] = snakeX[0] - 25;
                    if (snakeX[0] < 25) {
                        isFail = 2;
                        init();
                        //snakeX[0] = 850;
                    }
                    break;
                case "U":
                    snakeY[0] = snakeY[0] - 25;
                    if (snakeY[0] < 75) {
                        isFail = 2;
                        init();
                        //snakeY[0] = 650;
                    }
                    break;
                case "D":
                    snakeY[0] = snakeY[0] + 25;
                    if (snakeY[0] > 650) {
                        isFail = 2;
                        init();
                        //snakeY[0] = 75;
                    }
                    break;
            }
            //判断吃食物
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                length++;               //蛇长度加1
                score += 10;            //分数+10
                if (length <= 7) {
                    speed -= 5;
                } else if (length <= 10) {
                    speed -= 2;
                } else if (length <= 20) {
                    speed -= 1;
                } else if (length % 3 == 0) {
                    speed -= 1;
                }
                timer.setDelay(speed);
                //生成新食物
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);

            }

            // 吃到自己失败
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = 1;
                    break;
                }
            }
            //刷新界面
            repaint();
        }
        timer.start();//定时器启动
    }
}
