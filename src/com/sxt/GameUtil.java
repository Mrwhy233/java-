package com.sxt;

import javax.tools.Tool;
import java.awt.*;

/*工具类
* 存放静态参数
* 工具方法*/
public class GameUtil {
    //地雷个数
    static int RAY_MAX = 100;
    //地图的宽
    static int MAP_W= 36;
    //地图的高
    static int MAP_H = 17;
    //雷区偏移量
    static int OFFSET = 45;
    //格子边长
    static int SQUARE_LENGTH = 20;
    //插旗数量
    static int FLAG_NUM = 0;

    //鼠标相关
    //坐标
    static int MOUSE_x;
    static int MOUSE_Y;
    //状态
    static boolean LEFT = false;
    static boolean RIGHT = false;
    //游戏状态 0 游戏中 1胜利 2失败
    static int state = 3;
    //游戏难度
    static  int level;
    //倒计时
    static long START_TIME;
    static long END_TIME;
    //底层元素 -1雷 0空1-8表示对应数字
    static int[][]DATA_BUTTON = new int[MAP_W+2][MAP_H+2];//+2是为了雷区大一圈
    //顶层元素 -1无覆盖 0 覆盖 1插旗 2插错旗
    static int[][]DATA_TOP = new int[MAP_W+2][MAP_H+2];

    //载入图片
    static Image lei = Toolkit.getDefaultToolkit().getImage("imgs/icon.gif");
    static Image top = Toolkit.getDefaultToolkit().getImage("imgs/0.gif");
    static Image flag = Toolkit.getDefaultToolkit().getImage("imgs/flag.gif");
    static Image noflag = Toolkit.getDefaultToolkit().getImage("imgs/ask.gif");

    static Image face = Toolkit.getDefaultToolkit().getImage("imgs/face0.gif");
    static Image over = Toolkit.getDefaultToolkit().getImage("imgs/face3.gif");
    static Image win = Toolkit.getDefaultToolkit().getImage("imgs/face4.gif");
    static Image bac = Toolkit.getDefaultToolkit().getImage("imgs/R.png");
    static Image[] images = new Image[9];
    static{
        for(int i = 0;i<=8;i++){
            images[i] = Toolkit.getDefaultToolkit().getImage("imgs/num/"+i+".gif");
        }
    }
    static void drawWord(Graphics g,String str,int x,int y,int size,Color color){
        g.setColor(color);
        g.setFont(new Font("仿宋",Font.BOLD,30));
        g.drawString(str,x,y);
    }
}
