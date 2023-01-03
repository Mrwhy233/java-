package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {
    int width = 2*GameUtil.OFFSET+GameUtil.MAP_W*GameUtil.SQUARE_LENGTH;
    int height  =4*GameUtil.OFFSET+GameUtil.MAP_H*GameUtil.SQUARE_LENGTH;
    Image offScreenImage = null;
    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();
    GameSelect gameSelect = new GameSelect();
    //是否开始，f未开始，t开始
    boolean begin = false;

    void launch(){
        GameUtil.START_TIME = System.currentTimeMillis();//开始时间
        this.setVisible(true);//可见
        if(GameUtil.state==3){
            this.setSize(500,500);
        }else{
            this.setSize(width,height);//窗口大小
        }
        //this.setSize(width,height);//窗口大小
        this.setLocationRelativeTo(null);//居中显示
        this.setTitle("扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭方法
        //鼠标事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch(GameUtil.state){
                    case 0:
                        //左键被点击
                        if(e.getButton()==1){
                        GameUtil.MOUSE_x = e.getX();
                        GameUtil.MOUSE_Y  =e.getY();
                        GameUtil.LEFT = true;
                    }
                        //右键被点击
                        if(e.getButton()==3){
                            GameUtil.MOUSE_x = e.getX();
                            GameUtil.MOUSE_Y  =e.getY();
                            GameUtil.RIGHT = true;
                        }
                    case 1:
                    case 2:
                        if(e.getButton()==1) {
                            if (e.getX() > GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W / 2)
                                    && e.getX() < GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W / 2) + GameUtil.SQUARE_LENGTH
                                    && e.getY() > GameUtil.OFFSET
                                    && e.getY() < GameUtil.OFFSET + GameUtil.SQUARE_LENGTH) {
                                mapBottom.reGame();
                                mapTop.reGame();
                                GameUtil.FLAG_NUM = 0;
                                GameUtil.START_TIME = System.currentTimeMillis();
                                GameUtil.state = 0;
                            }
                        }
                        //单机滚轮，切换难度
                        if(e.getButton()==2){
                            GameUtil.state=3;
                            begin=true;
                        }
                        break;
                    case 3:
                        if(e.getButton()==1){
                            GameUtil.MOUSE_x = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            begin = gameSelect.hard();
                        }
                        break;
                }

            }
        });
        while(true){
            repaint();
            begin();
            try {
                Thread.sleep(40);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    void begin(){
        if(begin){
            begin = false;
            gameSelect.hard(GameUtil.level);
            dispose();
            GameWin gameWin = new GameWin();
            GameUtil.START_TIME = System.currentTimeMillis();
            GameUtil.FLAG_NUM=0;
            mapBottom.reGame();
            mapTop.reGame();
            gameWin.launch();
        }
    }

    @Override
    public void paint(Graphics g) {
        if(GameUtil.state==3){
            g.setColor(Color.white);
            g.fillRect(0,0,500,500);
            gameSelect.paintSelf(g);
        }else {
            offScreenImage = this.createImage(width, height);
            Graphics gImage = offScreenImage.getGraphics();
            //设置背景颜色
            gImage.setColor(Color.orange);
            gImage.fillRect(0, 0, width, height);
            mapBottom.paintSelf(gImage);
            mapTop.paintSelf(gImage);
            g.drawImage(offScreenImage, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
