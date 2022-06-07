package util;

import ui.GameFrame;

import javax.swing.*;

public class CountDown implements Runnable{
    //设计标题框的样式
    JLabel titlebox=new JLabel();
    int num=30;
    public boolean flag=true;
    public void titleBox(GameFrame gf) throws InterruptedException {

        gf.add(titlebox);
        titlebox.setBounds(0,0,300,20);
    }

    public void stop()
    {
        this.flag=false;
    }
    @Override
    public void run() {
        while(flag)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            titlebox.setText("元气骑士"+"   倒计时："+num--);
            if(num<=0)
            {
                GameOver go=new GameOver();
                go.gameOver();
                //System.exit(0);
                //break;
            }
        }
    }
}
