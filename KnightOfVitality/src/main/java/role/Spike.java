package role;

import ui.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Spike implements Runnable{
    public static List<String> list = new ArrayList<>();
    public GamePanel gp;
    public int sx;
    public int sy;
    public boolean flag = true;
    public boolean on = false;
    JLabel spikeOff = new JLabel();
    JLabel spikeOn = new JLabel();
    public Spike(GamePanel gp,String[] values) throws Exception {
        this.gp = gp;
        this.sx = Integer.parseInt(values[0]);
        this.sy = Integer.parseInt(values[1]);
        // 设置子弹的初始位置
        ImageIcon icon1 = new ImageIcon("image/spikeOff.png");
        icon1.setImage(icon1.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT));
        ImageIcon icon2 = new ImageIcon("image/spikeOn.png");
        icon2.setImage(icon2.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT));
        spikeOn.setIcon(icon2);
        spikeOff.setIcon(icon1);
        this.gp.add(spikeOff);
        this.gp.add(spikeOn);
        spikeOn.setBounds(this.sx, this.sy, 25, 25);
        spikeOff.setBounds(this.sx,this.sy,25,25);
        spikeOn.setVisible(false);
        spikeOff.setVisible(true);
        System.out.println("子弹坐标设置成功");
        // gf.getLayeredPane().add(bullet, Integer.valueOf(Integer.MAX_VALUE));
    }
    public static List<String> readSpike(int index) throws Exception {
        // 构造文件输入流
        FileInputStream fis = new FileInputStream("spike/spike1.txt");
        if(index == 0)
        {
            fis = new FileInputStream("spike/spike1.txt");
        }
        else if(index == 1)
        {
            fis = new FileInputStream("spike/spike2.txt");
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
            if (on) {
                spikeOn.setVisible(true);
                spikeOff.setVisible(false);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                on = false;
            }
            else
            {
                spikeOn.setVisible(false);
                spikeOff.setVisible(true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                on = true;
            }
        }
    }
}
