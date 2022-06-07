package role;

import ui.GameFrame;
import util.Hit;
import util.Winner;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Knight implements Runnable{

    public GameFrame gf;
    public int kx;
    public int ky;
    public int toward;//上下左右 对应 1234
    public int Tx[] = new int[100];
    public int Ty[] = new int[100];
    public int klenth = 4;
    public JLabel jknight = new JLabel(new ImageIcon("image/knight.png"));
    public JLabel[] jTail = new JLabel[100];
    public boolean bool;
    public Hit hit=new Hit();
    public Knight(GameFrame gf)
    {
        this.gf=gf;
        this.toward = 2;
        //设置骑士的初始位置
        this.gf.add(jknight);
        jknight.setBounds(100,20,20,20);
        this.kx=100;
        this.ky=20;
        for(int i = 0; i < klenth; i++)
        {
            jTail[i] = new JLabel(new ImageIcon("image/knight.png"));
            Tx[i] = 100;
            Ty[i] = 20;
        }
        System.out.println("骑士坐标设置成功");
        gf.getLayeredPane().add(jknight, Integer.valueOf(Integer.MAX_VALUE));
        for(int i = 0; i < klenth; i++)
        {
            gf.getLayeredPane().add(jTail[i], Integer.valueOf(Integer.MAX_VALUE));
        }
        //this.run();
    }


    @Override
    public void run() {
        this.kx=kx;
        this.ky=ky;
//        this.gf.addKeyListener(
//                new KeyAdapter() {
//                    public void keyPressed(KeyEvent e) {

                        //while(true)
                        //{
//                            int keyCode=e.getKeyCode();
//                            if (keyCode==KeyEvent.VK_UP && toward != 2)
//                            {
//                                System.out.println("你按下了上键");
//                                bool= hit.hitCheck(kx,ky,1, gf.map);
//                                if(bool)
//                                {
//                                    for(int i = klenth - 1; i > 0; i--)
//                                    {
//                                        Tx[i] = Tx[i-1];
//                                        Ty[i] = Ty[i - 1];
//                                    }
//                                    Tx[0] = kx;
//                                    Ty[0] = ky;
//                                    ky-=20;
//                                }
//                                toward = 1;
//                            }
//                            if (keyCode==KeyEvent.VK_DOWN && toward != 1)
//                            {
//                                System.out.println("你按下了下键");
//                                bool= hit.hitCheck(kx,ky,2, gf.map);
//                                if(bool)
//                                {
//                                    for(int i = klenth - 1; i > 0; i--)
//                                    {
//                                        Tx[i] = Tx[i - 1];
//                                        Ty[i] = Ty[i - 1];
//                                    }
//                                    Tx[0] = kx;
//                                    Ty[0] = ky;
//                                    ky+=20;
//                                }
//                                toward = 2;
//                            }
//                            if (keyCode==KeyEvent.VK_LEFT && toward != 4)
//                            {
//                                System.out.println("你按下了左键");
//                                bool= hit.hitCheck(kx,ky,3, gf.map);
//                                if(bool)
//                                {
//                                    for(int i = klenth - 1; i > 0; i--)
//                                    {
//                                        Tx[i] = Tx[i-1];
//                                        Ty[i] = Ty[i - 1];
//                                    }
//                                    Tx[0] = kx;
//                                    Ty[0] = ky;
//                                    kx-=20;
//                                }
//                                toward = 3;
//                            }
//                            if (keyCode==KeyEvent.VK_RIGHT && toward != 3)
//                            {
//                                System.out.println("你按下了右键");
//                                bool= hit.hitCheck(kx,ky,4, gf.map);
//                                if(bool)
//                                {
//                                    for(int i = klenth - 1; i > 0; i--)
//                                    {
//                                        Tx[i] = Tx[i-1];
//                                        Ty[i] = Ty[i - 1];
//                                    }
//                                    Tx[0] = kx;
//                                    Ty[0] = ky;
//                                    kx+=20;
//                                }
//                                toward = 4;
//                            }
//                            System.out.println(bool);
//                            System.out.println(kx+" "+ky);
//                            jknight.setBounds(kx,ky,20,20);
//                            for(int i = 0; i < klenth; i++)
//                            {
//                                jTail[i].setBounds(Tx[i],Ty[i],20,20);
//                            }
                            //检测胜利

                        //}

//                    }
//                }
//        );
    }
}
