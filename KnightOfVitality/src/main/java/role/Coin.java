package role;

import ui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Coin implements Runnable {
    public static List<String> list = new ArrayList<>();
    public GamePanel gp;
    public int bx;
    public int by;
    public boolean flag = true;
    JLabel coin;

    public Coin(GamePanel gp, String[] values) throws Exception {
        this.gp = gp;
        this.bx = Integer.parseInt(values[0]);
        this.by = Integer.parseInt(values[1]);

        // 设置子弹的初始位置
        ImageIcon iconCoin = new ImageIcon("image/knight.png");
        iconCoin.setImage(iconCoin.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        coin = new JLabel(iconCoin);
        this.gp.add(coin);
        coin.setBounds(this.bx, this.by, 25, 25);
        System.out.println("金币坐标设置成功");
        // gf.getLayeredPane().add(coin, Integer.valueOf(Integer.MAX_VALUE));
    }

    public void stop() {
        this.flag = false;
    }

    public static List<String> readCoin(int index) throws Exception {
        // 构造文件输入流
        FileInputStream fis = new FileInputStream("coin/coin1.txt");
        if (index == 0) {
            fis = new FileInputStream("coin/coin1.txt");
        } else if (index == 1) {
            fis = new FileInputStream("coin/coin2.txt");
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

            // System.out.println(this.by);
            coin.setBounds(this.bx, this.by, 25, 25);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
