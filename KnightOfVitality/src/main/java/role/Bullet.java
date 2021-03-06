package role;

import ui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Bullet implements Runnable {
    public static List<String> list = new ArrayList<>();
    public GamePanel gp;
    public int bx;
    public int by;
    public int toward;
    public int min;
    public int max;
    public boolean flag = true;
    JLabel bullet;
    public Bullet(GamePanel gp,String[] values) throws Exception {
        this.gp = gp;
        this.toward = Integer.parseInt(values[0]);
        this.bx = Integer.parseInt(values[1]);
        this.by = Integer.parseInt(values[2]);
        this.min = Integer.parseInt(values[3]);
        this.max = Integer.parseInt(values[4]);
        // 设置子弹的初始位置
        ImageIcon iconBullet = new ImageIcon("image/bullet.png");
        iconBullet.setImage(iconBullet.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT));
        bullet = new JLabel(iconBullet);
        this.gp.add(bullet);
        bullet.setBounds(this.bx, this.by, 25, 25);
        System.out.println("子弹坐标设置成功");
        // gf.getLayeredPane().add(bullet, Integer.valueOf(Integer.MAX_VALUE));
    }

    public void stop() {
        this.flag = false;
    }
    public void begin(){this.flag = true;}
    public static List<String> readBullet(int index) throws Exception {
        // 构造文件输入流
        FileInputStream fis;
        if(index == 0)
        {
            fis = new FileInputStream("bullet/bullet1.txt");
        }
        else if(index == 1)
        {
            fis = new FileInputStream("bullet/bullet2.txt");
        }
        else
        {
            fis = new FileInputStream("bullet/bullet1.txt");
        }

        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        // 直接读取一行数据
        String value = br.readLine();
        list = new ArrayList<>();

        while (value != null) {
            // 将读取到的一行数据加入到容器中
            list.add(value);
            value = br.readLine();
        }
        br.close();
        return list;
    }

    @Override
    public void run() {
        while (flag) {
                if (this.toward == 1)// 方向为竖
                {
                    while (this.by <= this.max && flag) {
                        this.by++;
                        // System.out.println(this.by);
                        bullet.setBounds(this.bx, this.by, 25, 25);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    while (this.by >= this.min && flag) {
                        this.by--;
                        // System.out.println(this.by);
                        bullet.setBounds(this.bx, this.by, 25, 25);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(this.toward == 0) //子弹方向为横向
                {
                    while (this.bx <= this.max && flag) {
                        this.bx++;
                        // System.out.println(this.by);
                        bullet.setBounds(this.bx, this.by, 25, 25);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    while (this.bx >= this.min && flag) {
                        this.bx--;
                        // System.out.println(this.by);
                        bullet.setBounds(this.bx, this.by, 25, 25);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        }
    }
}
