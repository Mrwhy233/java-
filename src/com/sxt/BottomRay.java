package com.sxt;
/*初始化地雷*/
public class BottomRay {
    //存放坐标
    int []rays = new int [GameUtil.RAY_MAX*2];//相邻两个数为地雷坐标
    //地理坐标
    int x,y;
    //是否放置T表示放置，F表示不可放置
    boolean isplace = true;
    //生成雷
    void newRay(){
        for(int i=0;i<GameUtil.RAY_MAX*2;i=i+2){
            x=(int)(Math.random()*GameUtil.MAP_W+1);//1-12
            y=(int)(Math.random()*GameUtil.MAP_H+1);//1-12
            rays[i]=x;
            rays[i+1]=y;
            //判断坐标是否存在
            for(int j=0;j<i;j+=2){
                if(x==rays[j]&&y==rays[j+1]){
                    i=i-2;
                    isplace = false;
                    break;
                }
            }
            //将坐标放入数组
            if(isplace){
                rays[i] = x;
                rays[i+1] = y;
            }
            isplace = true;
        }
        for (int i= 0;i<GameUtil.RAY_MAX*2;i=i+2){
            GameUtil.DATA_BUTTON[rays[i]][rays[i+1]]=-1;
        }
    }
}
