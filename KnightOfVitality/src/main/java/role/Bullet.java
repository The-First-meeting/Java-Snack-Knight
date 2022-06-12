package role;

import ui.GamePanel;

import javax.swing.*;

public class Bullet implements Runnable{

    public GamePanel gp;
    public int bx;
    public int by;
    public boolean flag=true;

    int x,y;
    JLabel bullet=new JLabel(new ImageIcon("image/bullet.png"));
    public Bullet(GamePanel gp)
    {
        this.gp=gp;

        //设置子弹的初始位置
        this.gp.add(bullet);
        bullet.setBounds(300,200,25,25);
        this.bx=300;
        this.by=200;
        System.out.println("子弹坐标设置成功");
//        gf.getLayeredPane().add(bullet, Integer.valueOf(Integer.MAX_VALUE));
    }

    public void stop()
    {
        this.flag=false;
    }

    @Override
    public void run() {
        while(flag)
        {
            while(this.by<=350)
            {
                this.by++;
                //System.out.println(this.by);
                bullet.setBounds(this.bx,this.by,25,25);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while(this.by>=200)
            {
                this.by--;
                //System.out.println(this.by);
                bullet.setBounds(this.bx,this.by,25,25);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
