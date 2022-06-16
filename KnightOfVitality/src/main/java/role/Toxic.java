package role;

import ui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Toxic implements Runnable{
    public static List<String> list = new ArrayList<>();
    public GamePanel gp;
    Random random = new Random();
    int icon;
    public int tx;
    public int ty;
    public boolean flag = true;
    JLabel Toxic1 = new JLabel();
    JLabel Toxic2 = new JLabel();
    JLabel Toxic3 = new JLabel();
    ImageIcon toxic1 = new ImageIcon("image/toxic1.png");
    ImageIcon toxic2 = new ImageIcon("image/toxic2.png");
    ImageIcon toxic3 = new ImageIcon("image/toxic3.png");
    public Toxic(GamePanel gp,String[] values) throws Exception {
        this.gp = gp;
        this.tx = Integer.parseInt(values[0]);
        this.ty = Integer.parseInt(values[1]);
        this.icon = random.nextInt(3);
        // 设置子弹的初始位置
        toxic1.setImage(toxic1.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT));
        toxic2.setImage(toxic2.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT));
        toxic3.setImage(toxic3.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT));
        Toxic1.setIcon(toxic1);
        Toxic2.setIcon(toxic2);
        Toxic3.setIcon(toxic3);
        this.gp.add(Toxic1);
        this.gp.add(Toxic2);
        this.gp.add(Toxic3);
        Toxic1.setBounds(this.tx, this.ty, 25, 25);
        Toxic2.setBounds(this.tx, this.ty, 25, 25);
        Toxic3.setBounds(this.tx, this.ty, 25, 25);
        Toxic1.setVisible(true);
        Toxic2.setVisible(false);
        Toxic3.setVisible(false);
        // gf.getLayeredPane().add(bullet, Integer.valueOf(Integer.MAX_VALUE));
    }
    public static List<String> readToxic(int index) throws Exception {
        // 构造文件输入流
        FileInputStream fis = new FileInputStream("toxic/toxic1.txt");
        if(index == 0)
        {
            fis = new FileInputStream("toxic/toxic1.txt");
        }
        else if(index == 1)
        {
            fis = new FileInputStream("toxic/toxic2.txt");
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
        while(flag)
        {
            if(icon == 0)
            {
                Toxic2.setVisible(true);
                Toxic1.setVisible(false);
                Toxic3.setVisible(false);
                this.icon = random.nextInt(3);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(icon == 1)
            {
                Toxic3.setVisible(true);
                Toxic2.setVisible(false);
                Toxic1.setVisible(false);
                this.icon = random.nextInt(3);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(icon == 2)
            {
                Toxic1.setVisible(true);
                Toxic3.setVisible(false);
                Toxic2.setVisible(false);
                this.icon = random.nextInt(3);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
