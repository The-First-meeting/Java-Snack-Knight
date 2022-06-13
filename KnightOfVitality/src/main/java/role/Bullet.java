package role;

import ui.GamePanel;

import javax.swing.*;
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

    int x, y;
    JLabel bullet;

    public Bullet(GamePanel gp,String[] values) throws Exception {
        this.gp = gp;
        this.toward = Integer.parseInt(values[0]);
        this.bx = Integer.parseInt(values[1]);
        this.by = Integer.parseInt(values[2]);
        this.min = Integer.parseInt(values[3]);
        this.max = Integer.parseInt(values[4]);
        // 设置子弹的初始位置
        bullet = new JLabel(new ImageIcon("image/bullet.png"));
        this.gp.add(bullet);
        bullet.setBounds(this.bx, this.by, 25, 25);
        System.out.println("子弹坐标设置成功");
        // gf.getLayeredPane().add(bullet, Integer.valueOf(Integer.MAX_VALUE));
    }

    public void stop() {
        this.flag = false;
    }

    public static List<String> readBullet(int index) throws Exception {
        // 构造文件输入流
        FileInputStream fis = new FileInputStream("bullet/bullet.txt");

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
                    while (this.by <= this.max) {
                        this.by++;
                        // System.out.println(this.by);
                        bullet.setBounds(this.bx, this.by, 25, 25);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    while (this.by >= this.min) {
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
                    while (this.bx <= this.max) {
                        this.bx++;
                        // System.out.println(this.by);
                        bullet.setBounds(this.bx, this.by, 25, 25);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    while (this.bx >= this.min) {
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
