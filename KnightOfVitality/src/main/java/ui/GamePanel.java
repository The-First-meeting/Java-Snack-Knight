package ui;

import role.Bullet;
import role.Knight;
import util.GameOver;
import util.Map;
import util.Winner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public Knight knight;
    Thread nightThread;
    Thread bulletThread;
    boolean isStart;
    boolean isFail;
    boolean isWin;
    public Bullet bullet;
    Timer timer = new Timer(300,this);//时钟信息
    public int[][] map = null;//地图信息
    {
        // 实例代码块中初始化地图资源的数据
        Map mp = new Map();
        try {
            map = mp.readMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //构造函数
    public GamePanel() throws InterruptedException {
        this.setLayout(null);
        this.setVisible(true);
        this.setFocusable(true); //获得焦点坐标
        this.addKeyListener(this);
        isStart = false;
        isFail = false;
        isWin = false;
        init();
        timer.start();//时钟启动
    }

    public void init(){
        isFail = false;
        isStart = false;
        isWin = false;
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
                    j1.setBounds(20*j,20*i+20,20,20);
                    this.add(j1);
                }
                else if(map[i][j]==0)//2是墙壁
                {
                    JLabel j2=new JLabel(new ImageIcon("image/brick.png"));
                    j2.setBounds(20*j,20*i+20,20,20);
                    this.add(j2);
                }
                else
                {
                    System.out.println("存在非0和1的元素!");
                }
            }
        }

        //设置骑士的线程
        nightThread = new Thread(knight);
        nightThread.start();
        //子弹的线程
        bulletThread = new Thread(bullet);
        bulletThread.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        if (!isStart) {
            g.setColor(Color.black); //设置字体
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",300,300);
        }
        if(isFail){
            g.setColor(Color.red); //设置字体
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("失败，按下空格重新开始",300,300);
        }
        if(isWin){
            g.setColor(Color.red); //设置字体
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("胜利！按下空格重新开始",300,300);
        }
        if(isStart&&!isFail&&!isWin){
            super.paintComponent(g); // 清屏
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //游戏开始
        if(isStart&&!isFail) {
            if (knight.toward == 1)//方向向上时
            {
                knight.bool = knight.hit.hitCheck(knight.kx, knight.ky, knight.toward, this.map);//未碰壁
                if (knight.bool) {
                    for (int i = knight.klenth - 1; i > 0; i--)//身体前进
                    {
                        knight.Tx[i] = knight.Tx[i - 1];
                        knight.Ty[i] = knight.Ty[i - 1];
                    }
                    knight.Tx[0] = knight.kx;
                    knight.Ty[0] = knight.ky;
                    knight.ky -= 20;//头前进
                }
            } else if (knight.toward == 2)//下
            {
                knight.bool = knight.hit.hitCheck(knight.kx, knight.ky, knight.toward, this.map);
                if (knight.bool) {
                    for (int i = knight.klenth - 1; i > 0; i--) {
                        knight.Tx[i] = knight.Tx[i - 1];
                        knight.Ty[i] = knight.Ty[i - 1];
                    }
                    knight.Tx[0] = knight.kx;
                    knight.Ty[0] = knight.ky;
                    knight.ky += 20;
                }
            } else if (knight.toward == 3)//左
            {
                knight.bool = knight.hit.hitCheck(knight.kx, knight.ky, knight.toward, this.map);
                if (knight.bool) {
                    for (int i = knight.klenth - 1; i > 0; i--) {
                        knight.Tx[i] = knight.Tx[i - 1];
                        knight.Ty[i] = knight.Ty[i - 1];
                    }
                    knight.Tx[0] = knight.kx;
                    knight.Ty[0] = knight.ky;
                    knight.kx -= 20;
                }
            } else if (knight.toward == 4)//右
            {
                knight.bool = knight.hit.hitCheck(knight.kx, knight.ky, knight.toward, this.map);
                if (knight.bool) {
                    for (int i = knight.klenth - 1; i > 0; i--) {
                        knight.Tx[i] = knight.Tx[i - 1];
                        knight.Ty[i] = knight.Ty[i - 1];
                    }
                    knight.Tx[0] = knight.kx;
                    knight.Ty[0] = knight.ky;
                    knight.kx += 20;
                }
            }
            //刷新图画
            knight.jknight.setBounds(knight.kx, knight.ky, 20, 20);
            for (int i = 0; i < knight.klenth; i++) {
                knight.jTail[i].setBounds(knight.Tx[i], knight.Ty[i], 20, 20);
            }
            knight.isChangeToward = true;
            //头与子弹碰撞————失败
            if (this.bullet.bx == this.knight.kx && Math.abs(this.bullet.by - this.knight.ky) <= 20) {
                System.out.println(this.bullet.by + "====" + this.knight.ky);
                bullet.stop();
                isFail = true;
                GameOver go = new GameOver();
                go.gameOver();
                repaint();
            }

            for (int i = 0; i < knight.klenth; i++) {
                //尾巴与子弹碰撞————切断
                if (this.bullet.bx == this.knight.Tx[i] && Math.abs(this.bullet.by - this.knight.Ty[i]) <= 20) {
                    for (int j = i; j < knight.klenth; j++) {
                        this.knight.gp.remove(this.knight.jTail[j]);
                    }
                    this.knight.klenth = i;
                }
                //咬到尾巴————失败
                if (this.knight.Tx[i] == this.knight.kx && this.knight.Ty[i] == this.knight.ky && knight.bool) {
                    System.out.println(this.knight.Ty[i] + "====" + this.knight.ky);
                    bullet.stop();
                    isFail = true;
                    GameOver go = new GameOver();
                    go.gameOver();
                    repaint();
                }
            }

            //胜利判断
            if (knight.kx == 160 && knight.ky == 300 && !isWin) {
                //获得胜利
                isWin = true;
                Winner winner = new Winner();
                winner.winner(knight.klenth);
                bullet.stop();
                isFail = false;
                repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(isStart&&!isFail&&!isWin) {
            if (knight.isChangeToward) {
                //转向
                if (keyCode == e.VK_LEFT) {
                    if (knight.toward != 4 && knight.hit.hitCheck(knight.kx, knight.ky, 3, this.map)) {
                        knight.toward = 3;
                        knight.isChangeToward = false;
                    }
                } else if (keyCode == e.VK_RIGHT) {
                    if (knight.toward != 3 && knight.hit.hitCheck(knight.kx, knight.ky, 4, this.map)) {
                        knight.toward = 4;
                        knight.isChangeToward = false;
                    }
                } else if (keyCode == e.VK_UP) {
                    if (knight.toward != 2 && knight.hit.hitCheck(knight.kx, knight.ky, 1, this.map)) {
                        knight.toward = 1;
                        knight.isChangeToward = false;
                    }
                } else if (keyCode == e.VK_DOWN) {
                    if (knight.toward != 1 && knight.hit.hitCheck(knight.kx, knight.ky, 2, this.map)) {
                        knight.toward = 2;
                        knight.isChangeToward = false;
                    }
                }
            }
        }
        else
        {
            if(keyCode == KeyEvent.VK_SPACE){
                if(isFail){
                    //重新开始
                    this.knight.gp.removeAll();
                    this.bullet.gp.removeAll();
                    init();
                    isStart = true;
                    timer.setDelay(300);
                }
                else if(!isWin)//开始游戏
                {
                    isStart = true;
                    timer.setDelay(300);
                }
                else {//游戏胜利
                    this.knight.gp.removeAll();
                    this.bullet.gp.removeAll();
                    init();
                    isStart = true;
                    timer.setDelay(300);
                }
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
