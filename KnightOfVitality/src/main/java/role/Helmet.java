package role;

import ui.Data;
import ui.GamePanel;
import util.Hit;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Helmet implements Runnable{
    public List<String> list = new ArrayList<>();
    public GamePanel gp;
    public int kx;
    public int ky;
    public int klenth;
    public int hel_num;
    public boolean isChangeToward;
    public int toward;//上下左右 对应 1234

    public JLabel jhelmet = new JLabel();

    public boolean bool;
    public Hit hit=new Hit();
    public Helmet(GamePanel gp,int klenth,int index) throws Exception {
        ImageIcon iconHelmet= new ImageIcon("image/greenbullet.png");
        iconHelmet.setImage(iconHelmet.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT));
        jhelmet.setIcon(iconHelmet);
        this.gp=gp;
        this.isChangeToward = true;
        //读取骑士的初识位置
        readHelmet(index);
        //设置骑士的初始位置
        jhelmet.setBounds(this.kx,this.ky,25,25);
        this.klenth = klenth;


        //this.gp.add(jhelmet,1);
        
        System.out.println("骑士头盔设置成功");
//        gf.getLayeredPane().add(jknight, Integer.valueOf(Integer.MAX_VALUE));
//        for(int i = 0; i < klenth; i++)
//        {
//            gf.getLayeredPane().add(jTail[i], Integer.valueOf(Integer.MAX_VALUE));
//        }
        //this.run();
    }

    public void readHelmet(int index) throws Exception{
        // 构造文件输入流
        FileInputStream fis = new FileInputStream("helmet/helmet.txt");
        if(index == 0)
        {
            fis = new FileInputStream("helmet/helmet.txt");
        }
        else if(index == 1)
        {
            fis = new FileInputStream("helmet/helmet.txt");
        }


        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        //直接读取一行数据
        String value = br.readLine();
        list = new ArrayList<>();

        while (value != null) {
            //将读取到的一行数据加入到容器中
            list.add(value);
            value = br.readLine();
        }

        br.close();

        //将读到的字符创转换成整数，并赋值
        String str = list.get(0);
        String[] values = str.split(",");
        this.toward = Integer.parseInt(values[0]);
        this.kx = Integer.parseInt(values[1]);
        this.ky = Integer.parseInt(values[2]);
        this.hel_num=Integer.parseInt(values[3]);
    }

    @Override
    public void run() {
    }
}
