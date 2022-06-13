package role;

import ui.Data;
import ui.GamePanel;
import util.Hit;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Knight implements Runnable{
    public List<String> list = new ArrayList<>();
    public GamePanel gp;
    public int kx;
    public int ky;
    public boolean isChangeToward;
    public int toward;//上下左右 对应 1234
    public int Tx[] = new int[100];
    public int Ty[] = new int[100];
    public int klenth;
    public JLabel jknight = new JLabel(new ImageIcon("image/knight.png"));
    public JLabel[] jTail = new JLabel[100];
    public boolean bool;
    public Hit hit=new Hit();
    public Knight(GamePanel gp,int klenth,int index) throws Exception {
        this.gp=gp;
        this.isChangeToward = true;
        //读取其实的初识位置
        readKnight(index);
        //设置骑士的初始位置
        jknight.setBounds(this.kx,this.ky,25,25);
        this.klenth = klenth;
        for(int i = 0; i < klenth; i++)
        {
            jTail[i] = new JLabel(new ImageIcon("image/knight.png"));
            Tx[i] = this.kx;
            Ty[i] = this.ky;
        }

        this.gp.add(jknight);
        for(int i = 0; i < klenth; i++)
        {
            this.gp.add(jTail[i]);
        }
        System.out.println("骑士坐标设置成功");
//        gf.getLayeredPane().add(jknight, Integer.valueOf(Integer.MAX_VALUE));
//        for(int i = 0; i < klenth; i++)
//        {
//            gf.getLayeredPane().add(jTail[i], Integer.valueOf(Integer.MAX_VALUE));
//        }
        //this.run();
    }

    public void readKnight(int index) throws Exception{
        // 构造文件输入流
        FileInputStream fis = new FileInputStream("knight/knight1.txt");
        if(index == 0)
        {
            fis = new FileInputStream("knight/knight1.txt");
        }
        else if(index == 1)
        {
            fis = new FileInputStream("knight/knight2.txt");
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
    }

    @Override
    public void run() {
        this.kx=kx;
        this.ky=ky;
    }
}
