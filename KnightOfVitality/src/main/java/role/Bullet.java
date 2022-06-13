package role;

import ui.GamePanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Bullet implements Runnable {

    public List<String> list = new ArrayList<>();

    public GamePanel gp;
    public int num;
    public int[] tx = new int[1000];
    public int[] ty = new int[1000];
    public int[] toward = new int[1000];
    public int[] min = new int[1000];
    public int[] max = new int[1000];
    public boolean flag = true;

    int x, y;
    JLabel[] bullet = new JLabel[1000];

    public Bullet(GamePanel gp) throws Exception {
        this.gp = gp;
        // 设置子弹的初始位置
        readBullet();
        int i;
        for (i = 0; i < this.num; i++) {
            bullet[i] = new JLabel(new ImageIcon("image/bullet.png"));
            this.gp.add(bullet[i]);
            bullet[i].setBounds(this.tx[i], this.ty[i], 25, 25);
        }

        System.out.println("子弹坐标设置成功");
        // gf.getLayeredPane().add(bullet, Integer.valueOf(Integer.MAX_VALUE));
    }

    public void stop() {
        this.flag = false;
    }

    public void readBullet() throws Exception {
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

        // 将读到的字符创转换成整数，并赋值
        int i;
        this.num = list.size();

        for (i = 0; i < list.size(); i++) {
            String str = list.get(i);
            String[] values = str.split(",");
            this.toward[i] = Integer.parseInt(values[0]);
            this.tx[i] = Integer.parseInt(values[1]);
            this.ty[i] = Integer.parseInt(values[2]);
            this.min[i] = Integer.parseInt(values[3]);
            this.max[i] = Integer.parseInt(values[4]);
        }
    }

    @Override
    public void run() {
        while (flag) {
            int i;
            for (i = 0; i < this.num; i++) {
                if (this.toward[i] == 1)// 方向为竖
                {
                    while (this.ty[i] <= this.max[i]) {
                        this.ty[i]++;
                        // System.out.println(this.by);
                        bullet[i].setBounds(this.tx[i], this.ty[i], 25, 25);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    while (this.ty[i] >= this.min[i]) {
                        this.ty[i]--;
                        // System.out.println(this.by);
                        bullet[i].setBounds(this.tx[i], this.ty[i], 25, 25);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(this.toward[i] == 0) //子弹方向为横向
                {
                    while (this.tx[i] <= this.max[i]) {
                        this.tx[i]++;
                        // System.out.println(this.by);
                        bullet[i].setBounds(this.tx[i], this.ty[i], 25, 25);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    while (this.tx[i] >= this.min[i]) {
                        this.tx[i]--;
                        // System.out.println(this.by);
                        bullet[i].setBounds(this.tx[i], this.ty[i], 25, 25);
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
}
