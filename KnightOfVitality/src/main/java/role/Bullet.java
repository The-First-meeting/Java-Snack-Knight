package role;

import ui.GameFrame;

import javax.swing.*;

public class Bullet implements Runnable{

    public GameFrame gf;
    public int bx;
    public int by;
    public boolean flag=true;

    int x,y;
    JLabel bullet=new JLabel(new ImageIcon("image/bullet.png"));
    public Bullet(GameFrame gf)
    {
        this.gf=gf;

        //设置子弹的初始位置
        this.gf.add(bullet);
        bullet.setBounds(240,160,20,20);
        this.bx=240;
        this.by=160;
        System.out.println("子弹坐标设置成功");
        gf.getLayeredPane().add(bullet, Integer.valueOf(Integer.MAX_VALUE));
    }

    public void stop()
    {
        this.flag=false;
    }

    @Override
    public void run() {
        while(flag)
        {
            while(this.by<=280)
            {
                this.by++;
                //System.out.println(this.by);
                bullet.setBounds(this.bx,this.by,20,20);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while(this.by>=160)
            {
                this.by--;
                //System.out.println(this.by);
                bullet.setBounds(this.bx,this.by,20,20);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
