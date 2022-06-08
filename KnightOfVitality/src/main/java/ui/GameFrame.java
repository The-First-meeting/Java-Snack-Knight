package ui;

import role.Bullet;
import role.Knight;
import util.CountDown;
import util.GameOver;
import util.Map;
import util.Winner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameFrame extends JFrame implements KeyListener, ActionListener {
    //定义一个集合容器装墙壁
    public ArrayList<JLabel> eneryList = new ArrayList<JLabel>();
    public Knight knight;
    boolean isStart = false;
   // public CountDown cd;
    public Bullet bullet;
    Timer timer = new Timer(300,this);//时钟信息
    public int[][] map = null;//地图信息
    {
        // 实例代码块中初始化地图资源的数据
        Map mp = new Map();

        try {
            map = mp.readMap();
            /*for(int i = 0 ; i < map.length ; i++ ){
                for(int j = 0 ; j < map[i].length ; j++) {
                    System.out.print(map[i][j]+" ");
                }
                System.out.println();
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //构造函数
    public GameFrame() throws InterruptedException {
        //初始化窗体相关属性信息数据
        // this代表了当前主界面对象。
        this.setSize(300, 320);
        this.setTitle("元气骑士");
        this.setLayout(null);
        this.setUndecorated(true);
        // 居中展示窗口
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(this);
        //创建骑士信息
        knight=new Knight(this);
      //  cd=new CountDown();
        bullet=new Bullet(this);


        // 读取地图，并配置地图
        for (int i = 0; i < map.length; i++) {
            //System.out.println("map.length="+map.length);
            for (int j = 0; j < map[0].length; j++) {
                //System.out.println("现在是:"+i+"行，"+j+"列");
                if (map[i][j]==1)//1是道路
                {
                    JLabel j1=new JLabel(new ImageIcon("image/floor.png"));
                    this.add(j1);
                    j1.setBounds(20*j,20*i+20,20,20);
                    //eneryList.add(j1);
                }
                else if(map[i][j]==0)//2是墙壁
                {
                    JLabel j2=new JLabel(new ImageIcon("image/brick.png"));
                    this.add(j2);
                    j2.setBounds(20*j,20*i+20,20,20);
                    //eneryList.add(j2);
                }
                else
                {
                    System.out.println("存在非0和1的元素!");
                }

            }
        }

        //设置骑士的线程
        new Thread(knight).start();
        //倒计时的线程
       // cd.titleBox(this);
//        new Thread(cd).start();


        //Thread bullet1=new Thread(bullet);

        //bullet1.start();
        //子弹的线程
        new Thread(bullet).start();

        JButton button=new JButton("Exit");
        this.add(button);
        button.setBounds(240,0,60,20);
        button.addActionListener(e->System.exit(0));
        this.getLayeredPane().add(button, Integer.valueOf(Integer.MAX_VALUE));


        /*System.out.println(this.bullet.bx+"---------------------------");
        System.out.println(this.bullet.by+"---------------------------");
        System.out.println(this.knight.kx+"==================");
        System.out.println(this.knight.ky+"=======================");*/
        timer.start();//时钟启动

    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if(knight.toward == 1)//方向向上时
        {
            knight.bool= knight.hit.hitCheck(knight.kx,knight.ky,knight.toward, this.map);//未碰壁
            if(knight.bool)
            {
                for(int i = knight.klenth - 1; i > 0; i--)//身体前进
                {
                    knight.Tx[i] = knight.Tx[i-1];
                    knight.Ty[i] = knight.Ty[i - 1];
                }
                knight.Tx[0] = knight.kx;
                knight.Ty[0] = knight.ky;
                knight.ky-=20;//头前进
            }
        }
        else if(knight.toward == 2)//下
        {
            knight.bool= knight.hit.hitCheck(knight.kx,knight.ky,knight.toward, this.map);
            if(knight.bool)
            {
                for(int i = knight.klenth - 1; i > 0; i--)
                {
                    knight.Tx[i] = knight.Tx[i-1];
                    knight.Ty[i] = knight.Ty[i - 1];
                }
                knight.Tx[0] = knight.kx;
                knight.Ty[0] = knight.ky;
                knight.ky+=20;
            }
        }
        else if(knight.toward == 3)//左
        {
            knight.bool= knight.hit.hitCheck(knight.kx,knight.ky,knight.toward, this.map);
            if(knight.bool)
            {
                for(int i = knight.klenth - 1; i > 0; i--)
                {
                    knight.Tx[i] = knight.Tx[i-1];
                    knight.Ty[i] = knight.Ty[i - 1];
                }
                knight.Tx[0] = knight.kx;
                knight.Ty[0] = knight.ky;
                knight.kx-=20;
            }
        }
        else if(knight.toward == 4)//右
        {
            knight.bool= knight.hit.hitCheck(knight.kx,knight.ky,knight.toward, this.map);
            if(knight.bool)
            {
                for(int i = knight.klenth - 1; i > 0; i--)
                {
                    knight.Tx[i] = knight.Tx[i-1];
                    knight.Ty[i] = knight.Ty[i - 1];
                }
                knight.Tx[0] = knight.kx;
                knight.Ty[0] = knight.ky;
                knight.kx+=20;
            }
        }
        //刷新图画
        knight.jknight.setBounds(knight.kx,knight.ky,20,20);
        for(int i = 0; i < knight.klenth; i++)
        {
            knight.jTail[i].setBounds(knight.Tx[i],knight.Ty[i],20,20);
        }
        knight.isChangeToward = true;
        //头与子弹碰撞————失败
        if(this.bullet.bx==this.knight.kx&&Math.abs(this.bullet.by-this.knight.ky)<=20)
        {
            //System.exit(0);
            System.out.println(this.bullet.by+"===="+this.knight.ky);
            bullet.stop();
          //  cd.stop();
            GameOver go=new GameOver();
            go.gameOver();
        }

        for(int i = 0; i < knight.klenth; i++)
        {
            //尾巴与子弹碰撞————切断
            if(this.bullet.bx == this.knight.Tx[i] && Math.abs(this.bullet.by - this.knight.Ty[i]) <= 20)
            {
                for(int j = i; j < knight.klenth; j++)
                {
                    this.knight.jTail[j].setBounds(this.knight.Tx[j],this.knight.Ty[j],0,0);
                }
                this.knight.klenth = i;
            }
            //咬到尾巴————失败
            if(this.knight.Tx[i]==this.knight.kx && this.knight.Ty[i] == this.knight.ky && knight.bool)
            {
                //System.exit(0);
                System.out.println(this.knight.Ty[i]+"===="+this.knight.ky);
                bullet.stop();
              //  cd.stop();
                GameOver go=new GameOver();
                go.gameOver();
            }
        }

        //胜利判断
        if (knight.kx==160&&knight.ky==300)
        {
            //获得胜利
            Winner winner=new Winner();
            winner.winner(knight.klenth);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(knight.isChangeToward)
        {
            //转向
            if(keyCode == e.VK_LEFT){
                if(knight.toward != 4 && knight.hit.hitCheck(knight.kx,knight.ky,3, this.map))
                {
                    knight.toward = 3;
                    knight.isChangeToward = false;
                }
            }
            else if(keyCode == e.VK_RIGHT){
                if(knight.toward != 3 && knight.hit.hitCheck(knight.kx,knight.ky,4, this.map))
                {
                    knight.toward = 4;
                    knight.isChangeToward = false;
                }
            }
            else if(keyCode == e.VK_UP){
                if(knight.toward != 2 && knight.hit.hitCheck(knight.kx,knight.ky,1, this.map))
                {
                    knight.toward = 1;
                    knight.isChangeToward = false;
                }
            }
            else if(keyCode == e.VK_DOWN){
                if(knight.toward != 1 && knight.hit.hitCheck(knight.kx,knight.ky,2, this.map))
                {
                    knight.toward = 2;
                    knight.isChangeToward = false;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
