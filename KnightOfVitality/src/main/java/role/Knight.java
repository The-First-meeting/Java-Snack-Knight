package role;

import ui.Data;
import ui.GamePanel;
import util.Hit;

import javax.swing.*;

public class Knight implements Runnable{

    public GamePanel gp;
    public int kx;
    public int ky;
    public boolean isChangeToward;
    public int toward;//上下左右 对应 1234
    public int Tx[] = new int[100];
    public int Ty[] = new int[100];
    public int klenth = 4;
    public JLabel jknight = new JLabel(new ImageIcon("image/knight.png"));
    public JLabel[] jTail = new JLabel[100];
    public boolean bool;
    public Hit hit=new Hit();
    public Knight(GamePanel gp)
    {
        this.gp=gp;
        this.toward = 2;
        this.isChangeToward = true;
        //设置骑士的初始位置
        jknight.setBounds(125,25,25,25);
        this.kx=125;
        this.ky=25;
        for(int i = 0; i < klenth; i++)
        {
            jTail[i] = new JLabel(new ImageIcon("image/knight.png"));
            Tx[i] = 125;
            Ty[i] = 25;
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


    @Override
    public void run() {
        this.kx=kx;
        this.ky=ky;
    }
}
