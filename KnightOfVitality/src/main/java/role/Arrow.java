package role;

import ui.GamePanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Arrow implements Runnable{
    public static List<String> list = new ArrayList<>();
    public GamePanel gp;
    public int ax;
    public int ay;
    public int toward;
    public int end;
    public int setx;
    public int sety;
    public boolean flag = false;
    JLabel arrow;
    public  Arrow(GamePanel gp, String[] values) throws Exception {
        this.gp = gp;
        this.toward = Integer.parseInt(values[0]);
        this.ax = Integer.parseInt(values[1]);
        this.ay = Integer.parseInt(values[2]);
        this.end = Integer.parseInt(values[3]);
        this.setx = Integer.parseInt(values[4]);
        this.sety = Integer.parseInt(values[5]);
        // 设置弩箭的初始位置
        if(toward == 0)//往上
        {
            arrow = new JLabel(new ImageIcon("image/arrowUp.png"));
        }
        else if(toward == 1)//往下
        {
            arrow = new JLabel(new ImageIcon("image/arrowDown.png"));
        }
        else if(toward == 2)//往左
        {
            arrow = new JLabel(new ImageIcon("image/arrowLeft.png"));
        }
        else
        {
            arrow = new JLabel(new ImageIcon("image/arrowRight.png"));
        }
        this.gp.add(arrow);
        arrow.setBounds(this.ax, this.ay, 25, 25);
        arrow.setVisible(false);
        System.out.println("弩箭坐标设置成功");
    }
    public void shot() {
        arrow.setVisible(true);
        this.flag = true;
    }
    public static List<String> readArrow(int index) throws Exception {
        // 构造文件输入流
        FileInputStream fis = new FileInputStream("arrow/arrow1.txt");
        if(index == 0)
        {
            fis = new FileInputStream("arrow/arrow1.txt");
        }
        else if(index == 1)
        {
            fis = new FileInputStream("arrow/arrow2.txt");
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
            if (this.toward == 0)// 向上
            {
                while (this.ay >= this.end) {
                    this.ay--;
                    // System.out.println(this.by);
                    arrow.setBounds(this.ax, this.ay, 25, 25);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if(this.toward == 1) //向下
            {
                while (this.ay <= this.end) {
                    this.ay++;
                    // System.out.println(this.by);
                    arrow.setBounds(this.ax, this.ay, 25, 25);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if(this.toward == 2) //向左
            {
                while (this.ax >= this.end) {
                    this.ax--;
                    // System.out.println(this.by);
                    arrow.setBounds(this.ax, this.ay, 25, 25);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if(this.toward == 3) //向右
            {
                while (this.ax <= this.end) {
                    this.ax++;
                    // System.out.println(this.by);
                    arrow.setBounds(this.ax, this.ay, 25, 25);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            this.flag = false;
        }
    }
}
